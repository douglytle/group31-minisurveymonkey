package group.thirtyone.surveycomponents;

import java.util.List;
import jakarta.persistence.*;

/**
 * Interface representing a question on a survey page. Provides a set of methods to help with survey creation.
 */

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
