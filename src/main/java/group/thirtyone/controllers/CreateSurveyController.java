package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CreateSurveyController {

    private final SurveyRepository surveyRepository;

    public CreateSurveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @GetMapping("/create")
    public String createSurvey(Model model) {
        return "create";
    }

    @PostMapping("/createSurvey")
    @CrossOrigin
    public String createSurveySubmit(@RequestBody String surveyJSON) {
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
                OpenEnded oe = new OpenEnded();
                oe.setQuestion(values[i+1].replace("value:", ""));
                oe.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                questions.add(oe);
                i+=2;
            }
            if(values[i].equals("name:NRQuestion")) {
                NumberRange nr = new NumberRange();
                nr.setQuestion(values[i+1].replace("value:", ""));
                nr.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                nr.setMin(Integer.parseInt(values[i+5].replace("value:", "")));
                nr.setMax(Integer.parseInt(values[i+7].replace("value:", "")));
                questions.add(nr);
                i+=6;
            }
            if(values[i].equals("name:MCQuestion")) {
                MultipleChoice mc = new MultipleChoice();
                mc.setQuestion(values[i+1].replace("value:", ""));
                mc.setOrderOnPage(Integer.parseInt(values[i+3].replace("value:", "")));
                i+=2;
                while(values[i+2].equals("name:MCChoice")) {
                    mc.addChoice(values[i+3].replace("value:", ""));
                    i+=2;
                }
                questions.add(mc);
            }
        }
        questions.sort(Comparator.comparing(Question::getOrderOnPage));
        for(Question question : questions){
            survey.addQuestion(question);
        }
        surveyRepository.save(survey);
        return "home";
    }
}
