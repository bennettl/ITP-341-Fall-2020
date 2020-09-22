package itp341.lee.geoquiz.model;

public class QuizQuestion {
    private String question;

    private boolean answer;

    public QuizQuestion(String question, boolean answer){
        super();
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
