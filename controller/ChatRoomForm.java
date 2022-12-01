package Babyak.babyak_backend.controller;

public class ChatRoomForm {
    private String chatTitle;
    private String time;
    private int people;
    private String food;
    private String location;
    private String content;

    public String getChatTitle(){
        return chatTitle;
    }
    public void setChatTitle(String title){
        this.chatTitle = title;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
