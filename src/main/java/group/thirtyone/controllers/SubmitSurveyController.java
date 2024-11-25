package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.MultipleChoiceRepository;
import group.thirtyone.persistencerepositories.NumberRangeRepository;
import group.thirtyone.persistencerepositories.OpenEndedRepository;
import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SubmitSurveyController {

    private final SurveyRepository surveyRepository;
    private final MultipleChoiceRepository multipleChoiceRepository;
    private final NumberRangeRepository numberRangeRepository;
    private final OpenEndedRepository openEndedRepository;


    public SubmitSurveyController(SurveyRepository surveyRepository, MultipleChoiceRepository multipleChoiceRepository, NumberRangeRepository numberRangeRepository, OpenEndedRepository openEndedRepository) {
        this.surveyRepository = surveyRepository;
        this.multipleChoiceRepository = multipleChoiceRepository;
        this.numberRangeRepository = numberRangeRepository;
        this.openEndedRepository = openEndedRepository;
    }

    @PostMapping("/submitSurvey")
    public String submitSurvey(@RequestBody String surveyJSON) {
        System.out.println(surveyJSON);

        String stripped = surveyJSON.replace("{","");
        stripped = stripped.replace("}","");
        stripped = stripped.replace("[","");
        stripped = stripped.replace("]","");
        stripped = stripped.replace("\"","");
        String[] values = stripped.split(",");

        long id = Integer.parseInt(values[1].replace("value:",""));
        Survey survey = surveyRepository.findById(id);
        String[] data = Arrays.copyOfRange(values, 2, values.length);
        ArrayList<String> output = new ArrayList<>();

        for(int i=0;i<data.length;i+=2) {
            output.add(data[i+1].replace("value:", ""));
        }
        List<Question> questions = survey.getQuestions();
        for(int i=0;i<questions.size();i++) {
            questions.get(i).addAnswer(output.get(i));
            if(questions.get(i).getType().equals("MCQ")) multipleChoiceRepository.save((MultipleChoice) questions.get(i));
            else if(questions.get(i).getType().equals("NR")) numberRangeRepository.save((NumberRange) questions.get(i));
            else if(questions.get(i).getType().equals("OE")) openEndedRepository.save((OpenEnded) questions.get(i));
        }
        return "home";
    }
}
