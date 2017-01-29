<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.local}"/>
<fmt:setBundle basename="local" var="loc"/>

<fmt:message bundle="${loc}" key="local.header.nav.home_page" var="home_page"/>
<fmt:message bundle="${loc}" key="local.header.nav.vacancies" var="vacancies"/>
<fmt:message bundle="${loc}" key="local.header.nav.contact" var="contact"/>
<fmt:message bundle="${loc}" key="local.header.nav.search" var="search"/>
<fmt:message bundle="${loc}" key="local.header.nav.slogan" var="slogan"/>
<fmt:message bundle="${loc}" key="local.header.nav.find_job" var="find_job"/>
<fmt:message bundle="${loc}" key="local.header.nav.post_job" var="post_job"/>
<fmt:message bundle="${loc}" key="local.header.nav.email" var="email"/>
<fmt:message bundle="${loc}" key="local.header.nav.password" var="password"/>
<fmt:message bundle="${loc}" key="local.header.nav.sign_in" var="sign_in"/>
<fmt:message bundle="${loc}" key="local.header.nav.sign_up_preview" var="sign_up_preview"/>
<fmt:message bundle="${loc}" key="local.header.nav.sign_up" var="sign_up"/>
<fmt:message bundle="${loc}" key="local.header.nav.for_applicant" var="for_applicant"/>
<fmt:message bundle="${loc}" key="local.header.nav.for_company" var="for_company"/>
<fmt:message bundle="${loc}" key="local.header.nav.no_js_text" var="no_js_text"/>
<fmt:message bundle="${loc}" key="local.header.nav.no_js_reference" var="no_js_reference"/>

<fmt:message bundle="${loc}" key="local.header.conversation_date" var="conversation_date"/>
<fmt:message bundle="${loc}" key="local.header.no_conversation" var="no_conversation"/>
<fmt:message bundle="${loc}" key="local.header.respond_list" var="respond_list"/>
<fmt:message bundle="${loc}" key="local.header.empty_list" var="empty_list"/>
<fmt:message bundle="${loc}" key="local.header.latest_vacancies" var="latest_vacancies"/>
<fmt:message bundle="${loc}" key="local.header.top_companies" var="top_companies"/>
<fmt:message bundle="${loc}" key="local.header.are_you_sure" var="are_you_sure"/>
<fmt:message bundle="${loc}" key="local.header.yes" var="yes"/>
<fmt:message bundle="${loc}" key="local.header.no" var="no"/>
<fmt:message bundle="${loc}" key="local.header.total_applicants" var="total_applicants"/>
<fmt:message bundle="${loc}" key="local.header.total_companies" var="total_companies"/>
<fmt:message bundle="${loc}" key="local.header.total_vacancies" var="total_vacancies"/>
<fmt:message bundle="${loc}" key="local.header.total_applicants_text" var="total_applicants_text"/>
<fmt:message bundle="${loc}" key="local.header.total_companies_text" var="total_companies_text"/>
<fmt:message bundle="${loc}" key="local.header.total_vacancies_text" var="total_vacancies_text"/>
<fmt:message bundle="${loc}" key="local.header.applicant_details" var="applicant_details"/>
<fmt:message bundle="${loc}" key="local.header.respond_note" var="respond_note"/>
<fmt:message bundle="${loc}" key="local.header.contact_us" var="contact_us"/>
<fmt:message bundle="${loc}" key="local.header.join_us" var="join_us"/>
<fmt:message bundle="${loc}" key="local.header.apply" var="apply"/>

<fmt:message bundle="${loc}" key="local.validation.validation_company_name" var="validation_company_name"/>
<fmt:message bundle="${loc}" key="local.validation.validation_name" var="validation_name"/>
<fmt:message bundle="${loc}" key="local.validation.validation_email" var="validation_email"/>
<fmt:message bundle="${loc}" key="local.validation.validation_password" var="validation_password"/>
<fmt:message bundle="${loc}" key="local.validation.validation_confirm_password" var="validation_confirm_password"/>
<fmt:message bundle="${loc}" key="local.validation.validation_phone" var="validation_phone"/>
<fmt:message bundle="${loc}" key="local.validation.validation_graduation_year" var="validation_graduation_year"/>
<fmt:message bundle="${loc}" key="local.validation.validation_required_experience" var="validation_required_experience"/>
<fmt:message bundle="${loc}" key="local.validation.validation_salary" var="validation_salary"/>
<fmt:message bundle="${loc}" key="local.validation.validation_mark" var="validation_mark"/>

<fmt:message bundle="${loc}" key="local.menu.company_information" var="company_information"/>
<fmt:message bundle="${loc}" key="local.menu.profile_editing" var="profile_editing"/>
<fmt:message bundle="${loc}" key="local.menu.my_respond_list" var="my_respond_list"/>
<fmt:message bundle="${loc}" key="local.menu.employee_list" var="employee_list"/>
<fmt:message bundle="${loc}" key="local.menu.vacancy_list" var="vacancy_list"/>
<fmt:message bundle="${loc}" key="local.menu.sign_out" var="sign_out"/>

<fmt:message bundle="${loc}" key="local.button.back" var="back"/>
<fmt:message bundle="${loc}" key="local.button.cancel" var="cancel"/>
<fmt:message bundle="${loc}" key="local.button.edit_result" var="edit_result"/>
<fmt:message bundle="${loc}" key="local.button.previous" var="previous"/>
<fmt:message bundle="${loc}" key="local.button.next" var="next"/>
<fmt:message bundle="${loc}" key="local.button.back" var="back"/>
<fmt:message bundle="${loc}" key="local.button.filter" var="filter"/>
<fmt:message bundle="${loc}" key="local.button.delete" var="delete"/>
<fmt:message bundle="${loc}" key="local.button.add_new" var="add_new"/>
<fmt:message bundle="${loc}" key="local.button.add" var="add"/>
<fmt:message bundle="${loc}" key="local.button.edit" var="edit"/>
<fmt:message bundle="${loc}" key="local.button.change_logo" var="change_logo"/>
<fmt:message bundle="${loc}" key="local.button.responds" var="responds"/>
<fmt:message bundle="${loc}" key="local.button.close" var="close"/>

<fmt:message bundle="${loc}" key="local.edit.profile_details" var="profile_details"/>
<fmt:message bundle="${loc}" key="local.edit.company_details" var="company_details"/>
<fmt:message bundle="${loc}" key="local.edit.profile_editing" var="profile_editing"/>

<fmt:message bundle="${loc}" key="local.sort.sort" var="sort"/>
<fmt:message bundle="${loc}" key="local.sort.vacancy_filter" var="vacancy_filter"/>
<fmt:message bundle="${loc}" key="local.sort.by_respond_date" var="by_respond_date"/>
<fmt:message bundle="${loc}" key="local.sort.by_mark_decrease" var="by_mark_decrease"/>
<fmt:message bundle="${loc}" key="local.sort.by_mark_increase" var="by_mark_increase"/>
<fmt:message bundle="${loc}" key="local.sort.by_meeting_date" var="by_meeting_date"/>
<fmt:message bundle="${loc}" key="local.sort.by_opening_date" var="by_opening_date"/>
<fmt:message bundle="${loc}" key="local.sort.by_salary_decrease" var="by_salary_decrease"/>
<fmt:message bundle="${loc}" key="local.sort.by_salary_increase" var="by_salary_increase"/>
<fmt:message bundle="${loc}" key="local.sort.by_experience_increase" var="by_experience_increase"/>

<fmt:message bundle="${loc}" key="local.filter.experience" var="experience"/>
<fmt:message bundle="${loc}" key="local.filter.no_matter" var="no_matter"/>
<fmt:message bundle="${loc}" key="local.filter.without_experience" var="without_experience"/>
<fmt:message bundle="${loc}" key="local.filter.one_three" var="one_three"/>
<fmt:message bundle="${loc}" key="local.filter.three_six" var="three_six"/>
<fmt:message bundle="${loc}" key="local.filter.six_and_more" var="six_and_more"/>
<fmt:message bundle="${loc}" key="local.filter.min_salary" var="min_salary"/>

<fmt:message bundle="${loc}" key="local.sign_up.respond.stage" var="stage"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.respond_date" var="respond_date"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.mark" var="mark"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.respond_details" var="respond_details"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.phone_stage" var="phone_stage"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.interview_stage" var="interview_stage"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.answer_stage" var="answer_stage"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.min_mark" var="min_mark"/>
<fmt:message bundle="${loc}" key="local.sign_up.respond.stage" var="stage"/>

<fmt:message bundle="${loc}" key="local.sign_up.user.applicant_registration" var="applicant_registration"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.account_details" var="account_details"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.first_name" var="first_name"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.last_name" var="last_name"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.confirm_password" var="confirm_password"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.phone" var="phone"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.optional_details" var="optional_details"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.country" var="country"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.city" var="city"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.university" var="university"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.graduation_year" var="graduation_year"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.english_level" var="english_level"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.professional_skills" var="professional_skills"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.choose_english_level" var="choose_english_level"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.file" var="file"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.choose_file" var="choose_file"/>

<fmt:message bundle="${loc}" key="local.sign_up.company.company_registration" var="company_registration"/>
<fmt:message bundle="${loc}" key="local.sign_up.company.company_details" var="company_details"/>
<fmt:message bundle="${loc}" key="local.sign_up.company.company_name" var="company_name"/>
<fmt:message bundle="${loc}" key="local.sign_up.company.website" var="website"/>
<fmt:message bundle="${loc}" key="local.sign_up.company.company_description" var="company_description"/>
<fmt:message bundle="${loc}" key="local.sign_up.company.admin_details" var="admin_details"/>

<fmt:message bundle="${loc}" key="local.sign_up.vacancy.vacancy_registration" var="vacancy_registrtion"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.vacancy_details" var="vacancy_details"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.vacancy_name" var="vacancy_name"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.open_date" var="open_date"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.salary" var="salary"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.required_experience" var="required_experience"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.required_skills" var="required_skills"/>
<fmt:message bundle="${loc}" key="local.sign_up.vacancy.vacancy_description" var="vacancy_description"/>

<fmt:message bundle="${loc}" key="local.sign_up.user.full_name" var="full_name"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.employee_registration" var="employee_registration"/>
<fmt:message bundle="${loc}" key="local.sign_up.user.employee_details" var="employee_details"/>

<fmt:message bundle="${loc}" key="local.message.${param.fail}" var="failMessage"/>
<fmt:message bundle="${loc}" key="local.message.${sessionScope.success}" var="successMessage"/>
