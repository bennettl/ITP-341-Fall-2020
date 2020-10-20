package itp341.lee.firebasedemoitp.model;

public class Note {

    private String body;

    private Boolean completed;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return body;
    }
}
