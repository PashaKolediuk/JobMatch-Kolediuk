package com.epam.jobmatch.bean.entity.user;

import com.epam.jobmatch.bean.entity.user.enumiration.Role;
import com.epam.jobmatch.bean.entity.user.enumiration.Status;

public class    Employee extends User{

    private int idCompany;
    private String fullName;
    private Status status;

    public Employee() {
        super(Role.EMPLOYEE);
    }

    public int getIdCompany() {
        return idCompany;
    }
    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employee employee = (Employee) o;

        if (idCompany != employee.idCompany) return false;
        if (fullName != null ? !fullName.equals(employee.fullName) : employee.fullName != null) return false;
        return status == employee.status;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + idCompany;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", idCompany=" + idCompany +
                ", fullName='" + fullName + '\'' +
                ", status=" + status;
    }
}
