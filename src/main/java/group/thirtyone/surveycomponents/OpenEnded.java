package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Class representation of an Open Ended question. An open-ended question has the question being asked, and the responses
 * as a list of all responses submitted to the question.
 *
 * @author Marwan Zeid
 * @version 2024.11.10
 */

@Entity
public class OpenEnded implements Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private int orderOnPage;
    @ElementCollection
    private List<String> answers;

    /**
     * Constructor for OpenEnded question
     */
    public OpenEnded() {
        answers = new ArrayList<>();
    }

    /**
     * Constructor for OpenEnded question
     * @param question Question being asked
     */
    public OpenEnded(String question) {
        this.question = question;
        answers = new ArrayList<>();
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
     * @param answer Answer being submitted
     */
    public void addAnswer (String answer) {
        answers.add(answer);
    }

    /**
     * Gets the type of question
     * @return Returns a string representation of the type of question
     */
    @Override
    public String getType() {
        return "OE";
    }

    /**
     * Gets the question being asked
     * @return Returns a string representation of the question being asked
     */
    public String getQuestion() { return question; }

    /**
     * Gets a list of the current responses
     * @return Returns a list representation of the responses submitted
     */
    public List<String> getAnswers() { return answers; }

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

    @Override
    public int getMin() {
        return 0;
    }

    @Override
    public int getMax() {
        return 0;
    }

    public Long getId() {
        return this.id;
    }
}
