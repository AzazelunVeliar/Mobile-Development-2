package ru.mirea.khudyakovma.plantpal.domain.models;

public class Plant {
    private int id;
    private String name;
    private String note;

    public Plant(int id, String name) {
        this.id = id;
        this.name = name;
        this.note = "";
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getNote() { return note; }

    public void setNote(String note) {
        this.note = note;
    }
}
