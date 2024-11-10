package group.thirtyone.surveycomponents;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;



    private String name;

    @OneToMany(mappedBy = "id")
    private List<MultipleChoice> multipleChoiceQuestions;

    @OneToMany(mappedBy = "id")
    private List<NumberRange> numberRangeQuestions;

    @OneToMany(mappedBy = "id")
    private List<OpenEnded> openEndedQuestions;

    public Survey() {
        multipleChoiceQuestions = new ArrayList<>();
        numberRangeQuestions = new ArrayList<>();
        openEndedQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        if(question instanceof MultipleChoice) {
            multipleChoiceQuestions.add((MultipleChoice) question);
        } else if (question instanceof NumberRange) {
            numberRangeQuestions.add((NumberRange) question);
        } else if (question instanceof OpenEnded) {
            openEndedQuestions.add((OpenEnded) question);
        }
    }

    public List<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.addAll(multipleChoiceQuestions);
        questions.addAll(numberRangeQuestions);
        questions.addAll(openEndedQuestions);
        return questions;
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

    public List<MultipleChoice> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public void setMultipleChoiceQuestions(List<MultipleChoice> multipleChoiceQuestions) {
        this.multipleChoiceQuestions = multipleChoiceQuestions;
    }

    public List<NumberRange> getNumberRangeQuestions() {
        return numberRangeQuestions;
    }

    public void setNumberRangeQuestions(List<NumberRange> numberRangeQuestions) {
        this.numberRangeQuestions = numberRangeQuestions;
    }

    public List<OpenEnded> getOpenEndedQuestions() {
        return openEndedQuestions;
    }

    public void setOpenEndedQuestions(List<OpenEnded> openEndedQuestions) {
        this.openEndedQuestions = openEndedQuestions;
    }
}
