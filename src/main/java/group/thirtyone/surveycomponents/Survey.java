package group.thirtyone.surveycomponents;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import jakarta.persistence.*;

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

    public Survey() {
        multipleChoiceQuestions = new ArrayList<>();
        numberRangeQuestions = new ArrayList<>();
        openEndedQuestions = new ArrayList<>();
    }

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

    public List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.addAll(multipleChoiceQuestions);
        questions.addAll(numberRangeQuestions);
        questions.addAll(openEndedQuestions);
        questions.sort(Comparator.comparingInt(Question::getOrderOnPage));
        return questions;
    }

    public int getNumberOfQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.addAll(multipleChoiceQuestions);
        questions.addAll(numberRangeQuestions);
        questions.addAll(openEndedQuestions);
        return questions.size();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
