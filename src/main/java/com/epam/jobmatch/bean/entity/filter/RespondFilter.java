package com.epam.jobmatch.bean.entity.filter;


/**
 * Describes respond filter parameters for
 * searching necessary applicants with their respond's
 * information
 */
public class RespondFilter {

    private int idVacancy;
    private int page;
    private String lastName;
    private String email;
    private String phone;
    private String stage;
    private int minMark;
    private String sort;

    public int getIdVacancy() {
        return idVacancy;
    }
    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public int getMinMark() {
        return minMark;
    }
    public void setMinMark(int minMark) {
        this.minMark = minMark;
    }
    public String getSort() {
        return sort;
    }
    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RespondFilter that = (RespondFilter) o;

        if (idVacancy != that.idVacancy) return false;
        if (page != that.page) return false;
        if (minMark != that.minMark) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (stage != null ? !stage.equals(that.stage) : that.stage != null) return false;
        return sort != null ? sort.equals(that.sort) : that.sort == null;

    }

    @Override
    public int hashCode() {
        int result = idVacancy;
        result = 31 * result + page;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + minMark;
        result = 31 * result + (sort != null ? sort.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                " idVacancy=" + idVacancy +
                ", page=" + page +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", stage=" + stage +
                ", minMark=" + minMark +
                ", sort='" + sort + '\'';
    }
}
