package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Class representation of a Number Range question. A number range question has the question being asked, the range of
 * the response, and the responses as a list of all responses submitted to the question.
 *
 * @author Marwan Zeid
 * @version 2024.11.10
 */

@Entity
public class NumberRange implements Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int min;
    private int max;
    @ElementCollection
    private List<String> chosen;
    private String question;
    private int orderOnPage;

    /**
     * Constructor for NumberRange question
     */
    public NumberRange() {
        chosen = new ArrayList<>();
    }

    /**
     * Constructor for NumberRange question
     * @param question question being asked
     * @param min minimum range of the response
     * @param max maximum range of the response
     */
    public NumberRange(String question, int min, int max) {
        this.question = question;
        this.min = min;
        this.max = max;
        chosen = new ArrayList<>();
    }

    /**
     * Gets minimum end of the response range
     * @return Returns an integer representation of the minimum end of the response range
     */
    public int getMin() {
        return min;
    }

    /**
     * Gets maximum end of the response range
     * @return Returns an integer representation of the maximum end of the response range
     */
    public int getMax() {
        return max;
    }

    /**
     * Gets a list of the current responses
     * @return Returns a list representation of the responses submitted
     */
    public List<String> getAnswers() {
        return chosen;
    }

    /**
     * Gets the question being asked
     * @return Returns a string representation of the question being asked
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the minimum end of the response range
     * @param min Minimum end of the response range
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Sets the maximum end of the response range
     * @param max Maximum end of the response range
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Sets the question being asked
     * @param question Question being asked
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Adds an answer to the array of responses
     * @param chosen Answer being submitted
     */
    public void addAnswer(String chosen) {
        this.chosen.add(chosen);
    }

    /**
     * Gets the type of question
     * @return Returns a string representation of the type of question
     */
    @Override
    public String getType() {
        return "NR";
    }

    /**
     * Gets the question's position on survey page
     * @return Returns an integer representation of the questions position on the survey page
     */
    public int getOrderOnPage() {
        return orderOnPage;
    }

    /**
     * Sets the question's position on the survey page
     * @param orderOnPage Question's position on the survey page
     */
    public void setOrderOnPage(int orderOnPage) {
        this.orderOnPage = orderOnPage;
    }


    @Override
    public List<String> getChoices() {
        return null;
    }

    public Long getId() {
        return this.id;
    }
}
