package sg.edu.rp.c302.c302l10petboardingapp;

import com.google.firebase.firestore.FieldValue;

import java.io.Serializable;
import java.util.Date;


public class Petboard implements Serializable {

    private String name;
    private int days;
    private String pet;
    private FieldValue date;
    private Boolean vac;



    public Petboard() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public Petboard(String name, int days, String pet, FieldValue date, Boolean vac) {
        this.name = name;
        this.days = days;
        this.pet = pet;
        this.date = date;
        this.vac = vac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public FieldValue getDate() {
        return date;
    }

    public void setDate(FieldValue date) {
        this.date = date;
    }

    public Boolean getVac() {
        return vac;
    }

    public void setVac(Boolean vac) {
        this.vac = vac;
    }
}

