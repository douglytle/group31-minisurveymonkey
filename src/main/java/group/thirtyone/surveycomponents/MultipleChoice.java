package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoice implements Question {

    private List<String> choices;
    private String question;
    private List<String> answers;

    public MultipleChoice() {
        choices = new ArrayList<>();
        answers = new ArrayList<>();
    }

    public MultipleChoice(List<String> choices, String question) {
        this.choices = choices;
        this.question = question;
    }

    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public List<String> getAnswers() {
        return answers;
    }

    @Override
    public void addAnswer(String answer) {
        answers.add(answer);
    }

    @Override
    public String getType() {
        return "MCQ";
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void addChoice(String choice) {
        choices.add(choice);
    }
}
