package group.thirtyone.controllers;

import group.thirtyone.surveycomponents.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CreateSurveyController {

    @GetMapping("/create")
    public String createSurvey(Model model) {
        List<MultipleChoice> MCQuestions = new ArrayList<>();
        MCQuestions.add(new MultipleChoice());
        List<NumberRange> NRQuestions = new ArrayList<>();
        NRQuestions.add(new NumberRange());
        List<OpenEnded> OEQuestions = new ArrayList<>();
        OEQuestions.add(new OpenEnded());
        model.addAttribute("MCQuestions", MCQuestions);
        model.addAttribute("NRQuestions", NRQuestions);
        model.addAttribute("OEQuestions", OEQuestions);
        return "create";
    }
}
