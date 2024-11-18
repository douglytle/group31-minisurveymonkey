package group.thirtyone.surveycomponents;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.*;

/**
 * Class representing a Survey. A survey consists of questions and their answers.
 *
 * @author Marwan Zeid
 * @author Doug Lytle
 *
 * @version 2024.11.10
 */

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<MultipleChoice> multipleChoiceQuestions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<NumberRange> numberRangeQuestions;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OpenEnded> openEndedQuestions;

    /**
     * Constructor for Survey class
     */
    public Survey() {
        multipleChoiceQuestions = new ArrayList<>();
        numberRangeQuestions = new ArrayList<>();
        openEndedQuestions = new ArrayList<>();
    }

    /**
     * Adds a question to the survey
     * @param question A question implementing the Question interface
     */
    public void addQuestion(Question question) {
        question.setOrderOnPage(getNumberOfQuestions());

        if(question instanceof MultipleChoice) {
            multipleChoiceQuestions.add((MultipleChoice) question);
        } else if (question instanceof NumberRange) {
            numberRangeQuestions.add((NumberRange) question);
        } else if (question instanceof OpenEnded) {
            openEndedQuestions.add((OpenEnded) question);
        } else {
            System.out.println("Unknown question: " + question.getQuestion());
        }
    }

    /**
     * Gets the list of questions the survey contains
     * @return Retuns an ordered list of questions.
     */
    public List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.addAll(multipleChoiceQuestions);
        questions.addAll(numberRangeQuestions);
        questions.addAll(openEndedQuestions);
        questions.sort(Comparator.comparingInt(Question::getOrderOnPage));
        return questions;
    }

    public List<MultipleChoice> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public List<NumberRange> getNumberRangeQuestions() {
        return numberRangeQuestions;
    }

    public List<OpenEnded> getOpenEndedQuestions() {
        return openEndedQuestions;
    }

    /**
     * Gets the current number of questions
     * @return Returns the number of questions in the survey
     */
    public int getNumberOfQuestions() {
        List<Question> questions = getQuestions();
        return questions.size();
    }

    /**
     * Gets title of the survey
     * @return Returns a string representation of the survey's title
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the title of the survey
     * @param name The title to be added to the survey
     */
    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
}
