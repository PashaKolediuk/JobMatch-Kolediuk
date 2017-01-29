var navbarHeight = $('.navbar').height();

$(window).scroll(function () {
    var navbarColor = "215,214,222";//color attr for rgba
    var smallLogoHeight = $('.small-logo').height();
    var bigLogoHeight = $('.big-logo').height();


    var smallLogoEndPos = 0;
    var smallSpeed = (smallLogoHeight / bigLogoHeight * 2.5);

    var ySmall = ($(window).scrollTop() * smallSpeed);

    var smallPadding = navbarHeight - ySmall;
    if (smallPadding > navbarHeight) {
        smallPadding = navbarHeight;
    }
    if (smallPadding < smallLogoEndPos) {
        smallPadding = smallLogoEndPos;
    }
    if (smallPadding < 0) {
        smallPadding = 0;
    }

    $('.small-logo-container ').css({"padding-top": smallPadding});

    var navOpacity = ySmall / smallLogoHeight;
    if (navOpacity > 1) {
        navOpacity = 1;
    }
    if (navOpacity < 0) {
        navOpacity = 0;
    }
    var navBackColor = 'rgba(' + navbarColor + ',' + navOpacity + ')';
    $('.navbar').css({"background-color": navBackColor});

    var shadowOpacity = navOpacity * 0.4;
    if (ySmall > 1) {
        $('.navbar').css({"box-shadow": "0 2px 3px rgba(0,0,0," + shadowOpacity + ")"});
    } else {
        $('.navbar').css({"box-shadow": "none"});
    }

});

function signUpSection() {
    var signUpSectionDiv = document.getElementById('sign-up-section');
    var language = document.getElementsByTagName('html')[0].getAttribute('lang');
    var signUp, forApplicant, forCompany;
    if (language == "en") {
        signUp = "Sign Up:";
        forApplicant = "FOR APPLICANT";
        forCompany = "FOR COMPANY";
    } else {
        signUp = "Регистрация:";
        forApplicant = "СОИСКАТЕЛЯ";
        forCompany = "КОМПАНИИ";
    }
    signUpSectionDiv.innerHTML = '<h4>' + signUp + '</h4>' +
        '<ul>' +
        '<li>' +
        '<a href="jsp/registration/applicantRegistration.jsp" class="sign-up-button">' + forApplicant + '</a>' +
        '</li>' +
        '<li>' +
        '<a href="jsp/registration/companyRegistration.jsp" class="sign-up-button">' + forCompany + '</a>' +
        '</li>' +
        '</ul>';
}

