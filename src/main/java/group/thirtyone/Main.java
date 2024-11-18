package group.thirtyone;

import group.thirtyone.othercomponents.UserAccount;
import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.persistencerepositories.UserAccountRepository;
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
    public CommandLineRunner createDemoSurveys(SurveyRepository surveyRepository, UserAccountRepository userAccountRepository) {
        return args -> {

            Survey survey1 = new Survey();
            survey1.setName("First Survey");

            Survey survey2 = new Survey();
            survey2.setName("Second Survey of the Night");

            Survey survey3 = new Survey();
            survey3.setName("Sample survey title 3");

            Survey survey4 = new Survey();
            survey4.setName("Sample survey title 4");

            ArrayList<String> choices = new ArrayList<>();
            choices.add("Choice 1");
            choices.add("Choice 2");
            choices.add("Choice 3");

            survey1.addQuestion(new MultipleChoice(choices, "Choose a choice"));
            survey1.addQuestion(new MultipleChoice(choices, "Choose a choice again"));
            survey1.addQuestion(new OpenEnded("What is this question??"));
            survey1.addQuestion(new NumberRange("Pick a number", 2, 20));
            survey1.addQuestion(new NumberRange("Pick a numbers", 10, 50));

            survey2.addQuestion(new MultipleChoice(choices, "Choose a first choice"));
            survey2.addQuestion(new MultipleChoice(choices, "Choose a second choice"));
            survey2.addQuestion(new NumberRange("Pick some", 2, 20));
            survey2.addQuestion(new OpenEnded("A new open ended question"));
            survey2.addQuestion(new NumberRange("Pick more", 10, 50));

            survey1.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey1.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey1.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey1.getMultipleChoiceQuestions().get(1).addAnswer("Choice 1");
            survey1.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey1.getMultipleChoiceQuestions().get(1).addAnswer("Choice 3");
            survey1.getMultipleChoiceQuestions().get(1).addAnswer("Choice 3");

            survey1.getOpenEndedQuestions().get(0).addAnswer("Open Ended Answer 1");
            survey1.getOpenEndedQuestions().get(0).addAnswer("Open Ended Answer 2");

            survey1.getNumberRangeQuestions().get(0).addAnswer("10");
            survey1.getNumberRangeQuestions().get(0).addAnswer("13");
            survey1.getNumberRangeQuestions().get(0).addAnswer("20");
            survey1.getNumberRangeQuestions().get(1).addAnswer("10");
            survey1.getNumberRangeQuestions().get(1).addAnswer("10");
            survey1.getNumberRangeQuestions().get(1).addAnswer("23");
            survey1.getNumberRangeQuestions().get(1).addAnswer("13");

            survey2.getNumberRangeQuestions().get(0).addAnswer("10");
            survey2.getNumberRangeQuestions().get(1).addAnswer("10");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 1");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(0).addAnswer("Choice 3");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 1");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 2");
            survey2.getMultipleChoiceQuestions().get(1).addAnswer("Choice 3");

            UserAccount user1 = new UserAccount("admin", "admin");
            user1.addSurvey(survey1);
            user1.addSurvey(survey2);


            userAccountRepository.save(user1);
            surveyRepository.save(survey3);
            surveyRepository.save(survey4);
        };
    }

}