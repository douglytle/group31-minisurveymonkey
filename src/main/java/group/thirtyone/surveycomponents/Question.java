package group.thirtyone.surveycomponents;

import java.util.List;
import jakarta.persistence.*;

public interface Question {

    void setQuestion(String question);
    String getQuestion();
    List<String> getAnswers();
    void addAnswer(String answer);
    String getType();
    void setOrderOnPage(int orderOnPage);
    int getOrderOnPage();
    List<String> getChoices();
    int getMin();
    int getMax();
}
