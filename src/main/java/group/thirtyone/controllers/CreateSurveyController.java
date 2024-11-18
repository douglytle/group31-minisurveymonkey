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
        Survey survey = new Survey();

        List<MultipleChoice> MCQuestions = new ArrayList<>();

        MultipleChoice multipleChoice = new MultipleChoice();
        multipleChoice.setQuestion("");
        multipleChoice.addChoice("");
        multipleChoice.addChoice("");
        MCQuestions.add(multipleChoice);

        List<OpenEnded> OEQuestions = new ArrayList<>();

        OpenEnded openEnded = new OpenEnded();
        openEnded.setQuestion("");
        OEQuestions.add(openEnded);

        List<NumberRange> NRQuestions = new ArrayList<>();

        NumberRange numberRange = new NumberRange();
        numberRange.setQuestion("");
        NRQuestions.add(numberRange);

        model.addAttribute("NRQuestions", NRQuestions);
        model.addAttribute("OEQuestions", OEQuestions);
        model.addAttribute("MCQuestions", MCQuestions);
        model.addAttribute("survey", survey);
        return "create";
    }

    @PostMapping("/create")
    public String createSurveySubmit(@ModelAttribute Survey survey, @ModelAttribute ArrayList<MultipleChoice> MCList, @ModelAttribute ArrayList<OpenEnded> OEList, @ModelAttribute ArrayList<NumberRange> NRList, Model model) {
        System.out.println("Create Survey Submitted");
        survey.setMultipleChoiceQuestions(MCList);
        survey.setOpenEndedQuestions(OEList);
        survey.setNumberRangeQuestions(NRList);
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
}
