package group.thirtyone.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateSurveyController {

    @GetMapping("/create")
    public String createSurvey(Model model, HttpSession session) {
        return "create";
    }
}
