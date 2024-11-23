package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.Survey;
import jakarta.servlet.http.HttpSession;
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
    public String homePage(Model model, HttpSession session) {
        List<Survey> surveyList = new ArrayList<>();
        Iterable<Survey> surveys = surveyRepository.findAll();
        for (Survey s : surveys) {
            surveyList.add(s);
        }
        model.addAttribute("surveys", surveyList);
        model.addAttribute("username", session.getAttribute("username"));
        return "home";
    }

    @GetMapping("/{id}")
    public String viewSurvey(@PathVariable Long id, Model model, HttpSession session) {
        model.addAttribute("username", session.getAttribute("username"));

        Optional<Survey> result = surveyRepository.findById(id);
        if (result.isPresent()) {
            Survey survey = result.get();
            if (survey.isActive())
            {
                model.addAttribute("survey", survey);
                return "survey";
            }
            else
            {
                return "closed_survey";
            }
        }
        return "error";
    }

}
