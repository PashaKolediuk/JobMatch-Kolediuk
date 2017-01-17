package com.epam.jobmatch.bean.entity;

import java.sql.Date;

public class Vacancy {

    private int idVacancy;
    private String name;
    private int idCompany;
    private int salary;
    private Date date;
    private int requiredExperience;
    private String requiredSkills;
    private String vacancyDescription;

    public int getIdVacancy() {
        return idVacancy;
    }
    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getRequiredExperience() {
        return requiredExperience;
    }
    public void setRequiredExperience(int requiredExperience) {
        this.requiredExperience = requiredExperience;
    }
    public String getRequiredSkills() {
        return requiredSkills;
    }
    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }
    public String getVacancyDescription() {
        return vacancyDescription;
    }
    public void setVacancyDescription(String vacancyDescription) {
        this.vacancyDescription = vacancyDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacancy vacancy = (Vacancy) o;

        if (idVacancy != vacancy.idVacancy) return false;
        if (idCompany != vacancy.idCompany) return false;
        if (salary != vacancy.salary) return false;
        if (requiredExperience != vacancy.requiredExperience) return false;
        if (name != null ? !name.equals(vacancy.name) : vacancy.name != null) return false;
        if (date != null ? !date.equals(vacancy.date) : vacancy.date != null) return false;
        if (requiredSkills != null ? !requiredSkills.equals(vacancy.requiredSkills) : vacancy.requiredSkills != null)
            return false;
        return vacancyDescription != null ? vacancyDescription.equals(vacancy.vacancyDescription) : vacancy.vacancyDescription == null;

    }

    @Override
    public int hashCode() {
        int result = idVacancy;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + idCompany;
        result = 31 * result + salary;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + requiredExperience;
        result = 31 * result + (requiredSkills != null ? requiredSkills.hashCode() : 0);
        result = 31 * result + (vacancyDescription != null ? vacancyDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                " idVacancy=" + idVacancy +
                ", name='" + name + '\'' +
                ", idCompany=" + idCompany +
                ", salary=" + salary +
                ", date=" + date +
                ", requiredExperience=" + requiredExperience +
                ", requiredSkills='" + requiredSkills + '\'' +
                ", vacancyDescription='" + vacancyDescription + '\'';
    }
}
