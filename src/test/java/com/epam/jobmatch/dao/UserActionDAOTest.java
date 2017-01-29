package com.epam.jobmatch.dao;

import com.epam.jobmatch.bean.entity.user.Applicant;
import com.epam.jobmatch.bean.entity.user.User;
import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.command.SourceInitCommand;
import com.epam.jobmatch.command.exception.SourceInitException;
import com.epam.jobmatch.dao.connection_pool.ConnectionPool;
import com.epam.jobmatch.dao.connection_pool.exception.ConnectionPoolException;
import com.epam.jobmatch.dao.exception.DAOException;
import com.epam.jobmatch.dao.exception.MatchingException;
import com.epam.jobmatch.dao.factory.DAOFactory;
import org.junit.*;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import static com.epam.jobmatch.service.util.Encryption.encryptedValue;
import static junit.framework.TestCase.*;

public class UserActionDAOTest {

    private static Connection connection;

    private static Applicant applicant = new Applicant();
    private static Applicant newApplicant = new Applicant();

    private static final String TEST_EMAIL_FIRST = "test.email.first@email.com";
    private static final String TEST_EMAIL_SECOND = "test.email.second@email.com";
    private static final String TEST_PASSWORD = "testPassword";
    private static final String TEST_PHONE = "+012345678910";
    private static final String TEST_SKYPE = "test.skype";
    private static final String TEST_FIRST_NAME = "TestFirstName";
    private static final String TEST_LAST_NAME = "TestLastName";
    private static final String TEST_COUNTRY = "TestCountry";
    private static final String TEST_CITY = "TestCity";
    private static final String TEST_UNIVERSITY = "TestUniversity";
    private static final int TEST_GRADUATION_YEAR = 2017;
    private static final EnglishLevel TEST_ENGLISH_LEVEL = EnglishLevel.A1;
    private static final String TEST_PROFESSIONAL_SKILLS = "TestProfessionalSkills";

    @BeforeClass
    public static void dataSourceInit() {

        applicant = setApplicantData();
        newApplicant = setApplicantData();

        try {
            SourceInitCommand sourceInitCommand = SourceInitCommand.getInstance();
            sourceInitCommand.initSource();
        } catch (SourceInitException e) {
            fail("Error during connection pool initialization:" + e);
        }
    }

    @AfterClass
    public static void dataSourceDestroy() {
        try {
            SourceInitCommand sourceInitCommand = SourceInitCommand.getInstance();
            sourceInitCommand.destroySource();
        } catch (SourceInitException e) {
            fail("Error during connection pool cleaning:" + e);
        }
    }

    private static Applicant setApplicantData() {
        Applicant applicant = new Applicant();
        applicant.setEmail(TEST_EMAIL_FIRST);
        applicant.setPhone(TEST_PHONE);
        applicant.setSkype(TEST_SKYPE);
        applicant.setFirstName(TEST_FIRST_NAME);
        applicant.setLastName(TEST_LAST_NAME);
        applicant.setCountry(TEST_COUNTRY);
        applicant.setCity(TEST_CITY);
        applicant.setUniversity(TEST_UNIVERSITY);
        applicant.setGraduationYear(TEST_GRADUATION_YEAR);
        applicant.setEnglishLevel(TEST_ENGLISH_LEVEL);
        applicant.setProfessionalSkills(TEST_PROFESSIONAL_SKILLS);
        return applicant;
    }

    @Before
    public void takeConnection() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
        } catch (ConnectionPoolException e) {
            fail("Error during taking connection:" + e);
        }
    }

    @After
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            fail("Error during connection closing:" + e);
        }
    }

    @Test
    public void userSignUp() {
        DAOFactory factory = DAOFactory.getInstance();
        SignUpDAO signUpDAO = factory.getSignUpDAO();
        EditingDAO editingDAO = factory.getEditingDAO();
        InfoDAO infoDAO = factory.getInfoDAO();

        try {

            // Deleting previous user
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM applicant where skype like ?");
            preparedStatement.setString(1, TEST_SKYPE);
            preparedStatement.executeUpdate();

            // Registration new user
            char[] encryptedPassword = encryptedValue(TEST_PASSWORD.toCharArray());
            applicant.setPassword(encryptedPassword);
            applicant = signUpDAO.applicantRegistration(applicant);

            // Editing user profile information
            newApplicant.setEmail(TEST_EMAIL_SECOND);
            editingDAO.editApplicantProfileInfo(newApplicant);

            // Checking edition results
            newApplicant = infoDAO.applicantInfo(String.valueOf(applicant.getId()));
            assertTrue("Applicants equals after editing", !applicant.equals(newApplicant));

        } catch (SQLException e) {
            fail("Error during applicant deleting:" + e);
        } catch (DAOException e) {
            fail("Error during applicant registration/editing/getting info:" + e);
        } catch (NoSuchAlgorithmException e) {
            fail("Error during applicant password encryption:" + e);
        } catch (MatchingException e) {
            // Nothing to do here
        }
    }

    @Test
    public void userSignIn() {
        DAOFactory factory = DAOFactory.getInstance();
        SignInDAO signInDAO = factory.getSignInDAO();
        try {
            char[] encryptedPassword = encryptedValue(TEST_PASSWORD.toCharArray());

            User user = signInDAO.authorization(encryptedPassword, TEST_EMAIL_FIRST);
            Arrays.fill(encryptedPassword, '\u0000');

            applicant.setId(user.getId());

            assertEquals("Applicant does not equals after authorization", applicant, user);

        } catch (DAOException e) {
            fail("Error during applicant authorization:" + e);
        } catch (NoSuchAlgorithmException e) {
            fail("Error during applicant password encryption:" + e);
        } catch (MatchingException e) {
            // Nothing to do here
        }
    }

}
