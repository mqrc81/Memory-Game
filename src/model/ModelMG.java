package model;

/**
 * @author: Marc Schmidt
 * @date: 2020-04-09
 * @project: M120
 */

public class ModelMG {
    private int difficulty = 0;
    private int highScore = 0;
    private int currentScore = 0;
    private int counter = 0;

    public ModelMG(){
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(int currentScore) {
        this.currentScore = currentScore;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
