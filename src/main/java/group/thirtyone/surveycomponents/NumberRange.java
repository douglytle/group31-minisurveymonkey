package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;

public class NumberRange implements Question {
    private int min;
    private int max;
    private List<String> chosen;
    private String question;

    public NumberRange() {
        chosen = new ArrayList<>();
    }

    public NumberRange(String question, int min, int max) {
        this.question = question;
        this.min = min;
        this.max = max;
        chosen = new ArrayList<>();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public List<String> getAnswers() {
        return chosen;
    }

    public String getQuestion() {
        return question;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer(String chosen) {
        this.chosen.add(chosen);
    }

    @Override
    public String getType() {
        return "NR";
    }
}
