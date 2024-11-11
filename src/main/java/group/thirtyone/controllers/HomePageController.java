package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomePageController {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<Survey> surveyList = new ArrayList<>();
        Iterable<Survey> surveys = surveyRepository.findAll();
        for (Survey s : surveys) {
            surveyList.add(s);
        }
        model.addAttribute("surveys", surveyList);
        return "home";
    }

    @GetMapping("/{id}")
    public String viewSurvey(@PathVariable Long id, Model model) {
        Optional<Survey> result = surveyRepository.findById(id);
        if (result.isPresent()) {
            Survey survey = result.get();
            model.addAttribute("survey", survey);
            return "survey";
        }
        return "error";
    }
}
