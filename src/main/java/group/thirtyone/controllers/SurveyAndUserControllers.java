package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.MultipleChoice;
import group.thirtyone.surveycomponents.NumberRange;
import group.thirtyone.surveycomponents.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class SurveyAndUserControllers {

    @Autowired
    SurveyRepository surveyRepository;

    @GetMapping("/{id}/results")
    public String results(@PathVariable Long id, Model model) {
        Optional<Survey> result = surveyRepository.findById(id);
        if (result.isPresent()) {
            Survey survey = result.get();
            model.addAttribute("numberrange", survey.getNumberRangeQuestions());
            model.addAttribute("openended", survey.getOpenEndedQuestions());
            model.addAttribute("multiplechoice", survey.getMultipleChoiceQuestions());

            ArrayList<Map<String,Integer>> mcqmaplist = new ArrayList<>();
            for (MultipleChoice mcq : survey.getMultipleChoiceQuestions()) {
                Map<String,Integer> mcqmap = new HashMap<>();
                for (String choice : mcq.getChoices()) {
                    mcqmap.put(choice, Collections.frequency(mcq.getAnswers(), choice));
                }
                mcqmaplist.add(mcqmap);
            }

            ArrayList<Map<String,Integer>> nrmaplist = new ArrayList<>();

            for (NumberRange nr : survey.getNumberRangeQuestions()) {
                HashSet<String> nrAnswers = new HashSet<>(nr.getAnswers());
                Map<String,Integer> nrmap = new HashMap<>();
                for (String choice : nrAnswers) {
                    nrmap.put(choice, Collections.frequency(nr.getAnswers(), choice));
                }
                nrmaplist.add(nrmap);
            }

            model.addAttribute("mcqmaplist", mcqmaplist);
            model.addAttribute("nrmaplist", nrmaplist);
            model.addAttribute("survey", survey);
        }

        return "results";
    }

}
