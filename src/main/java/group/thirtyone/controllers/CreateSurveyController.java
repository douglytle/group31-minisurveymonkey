package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("Create Survey Submitted");
        System.out.println(surveyJSON);

        Survey survey = new Survey();

        String stripped = surveyJSON.replace("{","");
        stripped = stripped.replace("}","");
        stripped = stripped.replace("[","");
        stripped = stripped.replace("]","");
        stripped = stripped.replace("\"","");
        String[] values = stripped.split(",");

        for(int i = 0; i < values.length; i+=2){
            if(values[i].equals("name:surveyName")) {
                survey.setName(values[i+1].replace("value:", ""));
            }
            if(values[i].equals("name:OEQuestion")) {
                OpenEnded oe = new OpenEnded();
                oe.setQuestion(values[i+1].replace("value:", ""));
                survey.addQuestion(oe);
            }
            if(values[i].equals("name:NRQuestion")) {
                NumberRange nr = new NumberRange();
                nr.setQuestion(values[i+1].replace("value:", ""));
                nr.setMin(Integer.parseInt(values[i+3].replace("value:", "")));
                nr.setMax(Integer.parseInt(values[i+5].replace("value:", "")));
                survey.addQuestion(nr);
                i+=4;
            }
            if(values[i].equals("name:MCQuestion")) {
                MultipleChoice mc = new MultipleChoice();
                mc.setQuestion(values[i+1].replace("value:", ""));
                while(values[i+2].equals("name:MCChoice")) {
                    mc.addChoice(values[i+3].replace("value:", ""));
                    i+=2;
                }
                survey.addQuestion(mc);
            }
        }
        surveyRepository.save(survey);

        return "home";
    }

    @RequestMapping(value="create", params={"addMCQuestion"})
    public String addMCQuestion(@ModelAttribute(name="survey") Survey survey, @ModelAttribute(name="MCQuestions") ArrayList<MultipleChoice> MCList, @ModelAttribute(name="OEQuestions") ArrayList<OpenEnded> OEList, @ModelAttribute(name="NRQuestions") ArrayList<NumberRange> NRList, Model model) {
        System.out.println(survey.getName());
        for(MultipleChoice multipleChoice : MCList) {
            System.out.println("MC question: " + multipleChoice.getId());
        }
        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setQuestion("");
        multipleChoice.addChoice("");
        multipleChoice.addChoice("");
        MCList.add(multipleChoice);
        for(MultipleChoice MC2 : MCList) {
            System.out.println("MC question: " + MC2.getId());
        }
        for(OpenEnded openEnded : OEList) {
            System.out.println("NR question: " + openEnded.getId());
        }

        model.addAttribute("NRQuestions", NRList);
        model.addAttribute("OEQuestions", OEList);
        model.addAttribute("MCQuestions", MCList);
        model.addAttribute("survey", survey);
        return "create";
    }

    @RequestMapping(value="create", params={"addNRQuestion"})
    public String addNRQuestion(@RequestBody List<NumberRange> NRList, Model model) {
        NumberRange numberRange = new NumberRange();
        numberRange.setQuestion("");
        NRList.add(numberRange);
        model.addAttribute("NRQuestions", NRList);
        return "create";
    }

    @RequestMapping(value="create", params={"addOEQuestion"})
    public String addOEQuestion(@RequestBody List<OpenEnded> OEList, Model model) {
        OpenEnded openEnded = new OpenEnded();
        openEnded.setQuestion("");
        OEList.add(openEnded);
        model.addAttribute("OEQuestions", OEList);
        return "create";
    }

    @PostMapping("/create2")
    public String createSurveySubmit2(@RequestBody String json, @ModelAttribute(name="surveyName") String surveyName, Model model) {
        System.out.println("Create Survey Submitted");
        Survey survey = new Survey();
        survey.setName(surveyName);
        System.out.println("Name: " + survey.getName());
        /*for(Question question : questions) {
            survey.addQuestion(question);
        }*/
        surveyRepository.save(survey);
        System.out.println("Survey Saved");

        for(Question question : survey.getQuestions()) {
            System.out.println(question);
        }

        List<Survey> surveyList = new ArrayList<>();
        Iterable<Survey> surveys = surveyRepository.findAll();
        for (Survey s : surveys) {
            surveyList.add(s);
        }
        model.addAttribute("surveys", surveyList);
        return "home";
    }
}
