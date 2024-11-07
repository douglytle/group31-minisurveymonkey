package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;

public class OpenEnded implements Question {

    private String question;
    private List<String> answers;

    public OpenEnded() {
        answers = new ArrayList<>();
    }

    public OpenEnded(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer (String answer) {
        answers.add(answer);
    }

    @Override
    public String getType() {
        return "OE";
    }

    public String getQuestion() { return question; }

    public List<String> getAnswers() { return answers; }
}
