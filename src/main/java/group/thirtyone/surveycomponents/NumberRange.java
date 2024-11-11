package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

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

    public NumberRange() {
        chosen = new ArrayList<>();
    }

    public NumberRange(String question, int min, int max) {
        this.question = question;
        this.min = min;
        this.max = max;
        chosen = new ArrayList<>();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public List<String> getAnswers() {
        return chosen;
    }

    public String getQuestion() {
        return question;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer(String chosen) {
        this.chosen.add(chosen);
    }

    @Override
    public String getType() {
        return "NR";
    }

    public int getOrderOnPage() {
        return orderOnPage;
    }

    @Override
    public List<String> getChoices() {
        return null;
    }

    public void setOrderOnPage(int orderOnPage) {
        this.orderOnPage = orderOnPage;
    }

    public Long getId() {
        return this.id;
    }
}
