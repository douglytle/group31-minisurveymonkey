package group.thirtyone.surveycomponents;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class OpenEnded implements Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String question;
    private int orderOnPage;
    @ElementCollection
    private List<String> answers;

    public OpenEnded() {
        answers = new ArrayList<>();
    }

    public OpenEnded(String question) {
        this.question = question;
        answers = new ArrayList<>();
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void addAnswer (String answer) {
        answers.add(answer);
    }

    @Override
    public String getType() {
        return "OE";
    }

    public String getQuestion() { return question; }

    public List<String> getAnswers() { return answers; }

    public int getOrderOnPage() {
        return orderOnPage;
    }

    public void setOrderOnPage(int orderOnPage) {
        this.orderOnPage = orderOnPage;
    }

    public Long getId() {
        return this.id;
    }
}
