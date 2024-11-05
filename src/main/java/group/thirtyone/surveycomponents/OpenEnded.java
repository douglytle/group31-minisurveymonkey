package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;

public class OpenEnded extends Question {

    private String question;
    private List<String> answers;

    public OpenEnded() {}

    public OpenEnded(String question) {
        this.question = question;
        answers = new ArrayList<String>();
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer (String answer) {
        answers.add(answer);
    }

    public String getQuestion() { return question; }

    public List<String> getAnswers() { return answers; }
}
