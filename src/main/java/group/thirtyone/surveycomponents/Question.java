package group.thirtyone.surveycomponents;

import java.util.List;

public interface Question {

    void setQuestion(String question);
    String getQuestion();
    List<String> getAnswers();
    void addAnswer(String answer);
    String getType();

}
