package group.thirtyone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SurveyAndUserControllers {


    @GetMapping("/{id}/results")
    public String results(@PathVariable int id) {
        return "results";
    }

}
