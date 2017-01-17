package com.epam.jobmatch.bean.entity;

import com.epam.jobmatch.bean.entity.enumiration.Stage;

import java.sql.Date;

public class Respond {

    private int idApplicant;
    private int idVacancy;
    private Stage stage;
    private Date respondDate;
    private Date conversationDate;
    private String note;
    private int mark;

    public int getIdApplicant() {
        return idApplicant;
    }
    public void setIdApplicant(int idApplicant) {
        this.idApplicant = idApplicant;
    }
    public int getIdVacancy() {
        return idVacancy;
    }
    public void setIdVacancy(int idVacancy) {
        this.idVacancy = idVacancy;
    }
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Date getRespondDate() {
        return respondDate;
    }
    public void setRespondDate(Date respondDate) {
        this.respondDate = respondDate;
    }
    public Date getConversationDate() {
        return conversationDate;
    }
    public void setConversationDate(Date conversationDate) {
        this.conversationDate = conversationDate;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getMark() {
        return mark;
    }
    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Respond result = (Respond) o;

        if (idApplicant != result.idApplicant) return false;
        if (idVacancy != result.idVacancy) return false;
        if (mark != result.mark) return false;
        if (stage != result.stage) return false;
        if (respondDate != null ? !respondDate.equals(result.respondDate) : result.respondDate != null) return false;
        if (conversationDate != null ? !conversationDate.equals(result.conversationDate) : result.conversationDate != null)
            return false;
        return note != null ? note.equals(result.note) : result.note == null;

    }

    @Override
    public int hashCode() {
        int result = idApplicant;
        result = 31 * result + idVacancy;
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
        result = 31 * result + (respondDate != null ? respondDate.hashCode() : 0);
        result = 31 * result + (conversationDate != null ? conversationDate.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + mark;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                " idApplicant=" + idApplicant +
                ", idVacancy=" + idVacancy +
                ", stage=" + stage +
                ", respondDate=" + respondDate +
                ", conversationDate=" + conversationDate +
                ", note='" + note + '\'' +
                ", mark=" + mark;
    }


}
