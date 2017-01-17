package com.epam.jobmatch.bean.entity.user;

import com.epam.jobmatch.bean.entity.user.enumiration.EnglishLevel;
import com.epam.jobmatch.bean.entity.user.enumiration.Role;

public class Applicant extends User {

    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String university;
    private int graduationYear;
    private EnglishLevel englishLevel;
    private String professionalSkills;

    public Applicant() {
        super(Role.APPLICANT);
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getUniversity() {
        return university;
    }
    public void setUniversity(String university) {
        this.university = university;
    }
    public int getGraduationYear() {
        return graduationYear;
    }
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
    public EnglishLevel getEnglishLevel() {
        return englishLevel;
    }
    public void setEnglishLevel(EnglishLevel englishLevel) {
        this.englishLevel = englishLevel;
    }
    public String getProfessionalSkills() {
        return professionalSkills;
    }
    public void setProfessionalSkills(String professionalSkills) {
        this.professionalSkills = professionalSkills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Applicant applicant = (Applicant) o;

        if (graduationYear != applicant.graduationYear) return false;
        if (firstName != null ? !firstName.equals(applicant.firstName) : applicant.firstName != null) return false;
        if (lastName != null ? !lastName.equals(applicant.lastName) : applicant.lastName != null) return false;
        if (country != null ? !country.equals(applicant.country) : applicant.country != null) return false;
        if (city != null ? !city.equals(applicant.city) : applicant.city != null) return false;
        if (university != null ? !university.equals(applicant.university) : applicant.university != null) return false;
        if (englishLevel != applicant.englishLevel) return false;
        return professionalSkills != null ? professionalSkills.equals(applicant.professionalSkills) : applicant.professionalSkills == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (university != null ? university.hashCode() : 0);
        result = 31 * result + graduationYear;
        result = 31 * result + (englishLevel != null ? englishLevel.hashCode() : 0);
        result = 31 * result + (professionalSkills != null ? professionalSkills.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", university='" + university + '\'' +
                ", graduationYear=" + graduationYear +
                ", englishLevel=" + englishLevel +
                ", professionalSkills='" + professionalSkills + '\'';
    }
}
