package com.epam.jobmatch.bean.entity;

import com.epam.jobmatch.bean.entity.user.Employee;

public class Company {

    private int idCompany;
    private String companyName;
    private String country;
    private String city;
    private String companyDescription;
    private String website;
    private Employee admin;

    public int getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
    public String getCompanyDescription() {
        return companyDescription;
    }
    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public Employee getAdmin() {
        return admin;
    }
    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (idCompany != company.idCompany) return false;
        if (companyName != null ? !companyName.equals(company.companyName) : company.companyName != null) return false;
        if (country != null ? !country.equals(company.country) : company.country != null) return false;
        if (city != null ? !city.equals(company.city) : company.city != null) return false;
        if (companyDescription != null ? !companyDescription.equals(company.companyDescription) : company.companyDescription != null)
            return false;
        if (website != null ? !website.equals(company.website) : company.website != null) return false;
        return admin != null ? admin.equals(company.admin) : company.admin == null;

    }

    @Override
    public int hashCode() {
        int result = idCompany;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (companyDescription != null ? companyDescription.hashCode() : 0);
        result = 31 * result + (website != null ? website.hashCode() : 0);
        result = 31 * result + (admin != null ? admin.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {" +
                "idCompany=" + idCompany +
                ", companyName='" + companyName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                ", website='" + website + '\'' +
                ", admin=" + admin +
                '}';
    }
}
