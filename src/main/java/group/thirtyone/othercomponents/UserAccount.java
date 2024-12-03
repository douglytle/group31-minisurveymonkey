package group.thirtyone.othercomponents;

import group.thirtyone.surveycomponents.Survey;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Survey> surveys;

    public UserAccount() {
        surveys = new ArrayList<>();
    }

    public UserAccount(String username, String password) {
        surveys = new ArrayList<>();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addSurvey(Survey survey) {
        surveys.add(survey);
    }

    public void removeSurvey(Survey survey) {
        surveys.remove(survey);
    }

    public List<Survey> getSurveys(){
        return surveys;
    }

    public boolean hasSurvey(Long id){
        for (Survey survey : surveys) {
            if (survey.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }
}
