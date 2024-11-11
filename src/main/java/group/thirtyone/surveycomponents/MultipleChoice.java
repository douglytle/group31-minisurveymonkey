package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

/**
 * Class representation of a Multiple Choice question. An open-ended question has the question being asked, and the responses
 * as a list of all responses submitted to the question.
 *
 * @author Marwan Zeid
 * @version 2024.11.10
 */

@Entity
public class MultipleChoice implements Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> choices;
    private String question;
    private int orderOnPage;
    @ElementCollection
    private List<String> answers;

    /**
     * Constructor for MultipleChoice question
     */
    public MultipleChoice() {
        choices = new ArrayList<>();
        answers = new ArrayList<>();
    }

    /**
     * Constructor for MultipleChoice question
     * @param choices Choices for this question, as a string list
     * @param question Question being asked
     */
    public MultipleChoice(List<String> choices, String question) {
        this.choices = choices;
        this.question = question;
    }

    /**
     * Sets the question being asked
     * @param question Question being asked
     */
    @Override
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the question being asked
     * @return Returns a string representation of the question being asked
     */
    @Override
    public String getQuestion() {
        return question;
    }

    /**
     * Gets a list of the current responses
     * @return Returns a list representation of the responses submitted
     */
    @Override
    public List<String> getAnswers() {
        return answers;
    }

    /**
     * Adds an answer to the array of responses
     * @param answer Answer being submitted
     */
    @Override
    public void addAnswer(String answer) {
        answers.add(answer);
    }

    /**
     * Gets the type of question
     * @return Returns a string representation of the type of question
     */
    @Override
    public String getType() {
        return "MCQ";
    }

    /**
     * Gets the list of choices for the question
     * @return Returns a list representation of the choices for this question
     */
    @Override
    public List<String> getChoices() {
        return choices;
    }

    /**
     * Sets a list of choices for the question
     * @param choices The list of choices
     */
    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    /**
     * Adds a choice to the list of choices
     * @param choice The choice being added
     */
    public void addChoice(String choice) {
        choices.add(choice);
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
