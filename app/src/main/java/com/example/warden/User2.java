package com.example.warden;

public class User2 {

    String description,name,room,usn;

    public User2(){}

    public User2(String description, String name, String room, String usn) {
        this.description = description;
        this.name = name;
        this.room = room;
        this.usn = usn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }
}
