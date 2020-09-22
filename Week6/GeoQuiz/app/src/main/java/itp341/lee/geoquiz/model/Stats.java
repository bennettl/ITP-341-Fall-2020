package itp341.lee.geoquiz.model;

public class Stats {
    private int correct;

    private int incorrect;

    private int cheated;

    public Stats(int correct, int incorrect, int cheated){
        this.correct = correct;
        this.incorrect = incorrect;
        this.cheated = cheated;
    }

    public void incrementCorrect(){
        correct++;
    }
    public void incrementIncorrect(){
        incorrect++;
    }
    public void incrementCheated(){
        cheated++;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getCheated() {
        return cheated;
    }

    public void setCheated(int cheated) {
        this.cheated = cheated;
    }
}
