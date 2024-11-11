package group.thirtyone;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.MultipleChoice;
import group.thirtyone.surveycomponents.NumberRange;
import group.thirtyone.surveycomponents.OpenEnded;
import group.thirtyone.surveycomponents.Survey;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Start Spring Boot!");

        };
    }

    @Bean
    public CommandLineRunner createDemoSurveys(SurveyRepository surveyRepository) {
        return args -> {

            Survey survey1 = new Survey();
            survey1.setName("Sample survey title 1");

            Survey survey2 = new Survey();
            survey2.setName("Sample survey title 2");

            Survey survey3 = new Survey();
            survey3.setName("Sample survey title 3");

            Survey survey4 = new Survey();
            survey4.setName("Sample survey title 4");

            Survey survey5 = new Survey();
            survey5.setName("Sample survey title 5");

            ArrayList<String> choices = new ArrayList<>();
            choices.add("Choice 1");
            choices.add("Choice 2");
            choices.add("Choice 3");

            survey1.setName("Sample Survey");
            survey1.addQuestion(new MultipleChoice(choices, "Choose a choice"));
            survey1.addQuestion(new MultipleChoice(choices, "Choose a choice again"));
            survey1.addQuestion(new OpenEnded("What is this question??"));
            survey1.addQuestion(new NumberRange("Pick a number", 2, 20));
            survey1.addQuestion(new NumberRange("Pick a numbers", 10, 50));

            surveyRepository.save(survey1);
            surveyRepository.save(survey2);
            surveyRepository.save(survey3);
            surveyRepository.save(survey4);
            surveyRepository.save(survey5);
        };
    }

}