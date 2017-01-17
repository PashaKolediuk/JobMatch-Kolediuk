package com.epam.jobmatch.bean.entity.user;

import com.epam.jobmatch.bean.entity.user.enumiration.Role;

import java.util.Arrays;

public abstract class User {

    private Role role;
    private int id;
    private String email;
    private char[] password;
    private char[] confirmPassword;
    private String phone;
    private String skype;

    public User(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char[] getPassword() {
        return password;
    }
    public void setPassword(char[] password) {
        this.password = password;
    }
    public char[] getConfirmPassword() {
        return confirmPassword;
    }
    public void setConfirmPassword(char[] confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSkype() {
        return skype;
    }
    public void setSkype(String skype) {
        this.skype = skype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (role != user.role) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (!Arrays.equals(password, user.password)) return false;
        if (!Arrays.equals(confirmPassword, user.confirmPassword)) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return skype != null ? skype.equals(user.skype) : user.skype == null;

    }

    @Override
    public int hashCode() {
        int result = role != null ? role.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(confirmPassword);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                " id=" + id +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", password=" + Arrays.toString(password) +
                ", confirmPassword=" + Arrays.toString(confirmPassword) +
                ", phone='" + phone + '\'' +
                ", skype='" + skype + '\'';
    }
}
