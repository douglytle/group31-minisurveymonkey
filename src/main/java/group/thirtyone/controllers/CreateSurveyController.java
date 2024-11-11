package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        Survey survey = new Survey();

        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setQuestion("");
        multipleChoice.addChoice("");
        multipleChoice.addChoice("");
        survey.addQuestion(multipleChoice);

        OpenEnded openEnded = new OpenEnded();
        openEnded.setQuestion("");
        survey.addQuestion(openEnded);

        NumberRange numberRange = new NumberRange();
        numberRange.setQuestion("");
        survey.addQuestion(numberRange);

        model.addAttribute("survey", survey);
        return "create";
    }

    @PostMapping("/create")
    public String createSurveySubmit(@ModelAttribute Survey survey, Model model) {
        System.out.println("Create Survey Submitted");
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
