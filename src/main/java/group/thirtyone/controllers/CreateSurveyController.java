package group.thirtyone.controllers;

import group.thirtyone.othercomponents.UserAccount;
import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.persistencerepositories.UserAccountRepository;
import group.thirtyone.surveycomponents.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CreateSurveyController {

    private final SurveyRepository surveyRepository;
    private final UserAccountRepository userAccountRepository;

    public CreateSurveyController(SurveyRepository surveyRepository, UserAccountRepository userAccountRepository) {
        this.surveyRepository = surveyRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping("/create")
    public String createSurvey(Model model) {
        return "create";
    }

    @PostMapping("/createSurvey")
    @CrossOrigin
    public String createSurveySubmit(@RequestBody String surveyJSON, HttpSession session) {
        UserAccount user = userAccountRepository.findByUsername(session.getAttribute("username").toString());
        Survey survey = new Survey();

        String stripped = surveyJSON.replace("{","");
        stripped = stripped.replace("}","");
        stripped = stripped.replace("[","");
        stripped = stripped.replace("]","");
        stripped = stripped.replace("\"","");
        String[] values = stripped.split(",");

        ArrayList<Question> questions = new ArrayList<>();

        for(int i = 0; i < values.length; i+=2){
            if(values[i].equals("name:surveyName")) {
                survey.setName(values[i+1].replace("value:", ""));
            }
            if(values[i].equals("name:OEQuestion")) {
                if(!values[i+1].replace("value:", "").isBlank()) {
                    OpenEnded oe = new OpenEnded();
                    oe.setQuestion(values[i+1].replace("value:", ""));
                    oe.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                    questions.add(oe);
                }
                i+=2;
            }
            if(values[i].equals("name:NRQuestion")) {
                if(!values[i+1].replace("value:", "").isBlank()) {
                    NumberRange nr = new NumberRange();
                    nr.setQuestion(values[i+1].replace("value:", ""));
                    nr.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                    nr.setMin(Integer.parseInt(values[i+5].replace("value:", "")));
                    nr.setMax(Integer.parseInt(values[i+7].replace("value:", "")));
                    questions.add(nr);
                }
                i+=6;
            }
            if(values[i].equals("name:MCQuestion")) {
                if(values[i+1].replace("value:", "").isBlank()) {
                    while(values[i+2].equals("name:MCChoice") || values[i+2].equals("name:MCOrder")) {
                        i+=2;
                    }
                }
                else {
                    MultipleChoice mc = new MultipleChoice();
                    mc.setQuestion(values[i+1].replace("value:", ""));
                    mc.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                    i+=2;
                    while(values[i+2].equals("name:MCChoice")) {
                        mc.addChoice(values[i+3].replace("value:", ""));
                        i+=2;
                    }
                    questions.add(mc);
                    System.out.println("Question: " + mc.getQuestion());
                    System.out.println(mc.getQuestion().length());
                }
            }
        }
        questions.sort(Comparator.comparing(Question::getOrderOnPage));
        for(Question question : questions){
            survey.addQuestion(question);
        }
        user.addSurvey(survey);
        return "home";
    }
}
