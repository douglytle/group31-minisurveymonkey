package group.thirtyone;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.Survey;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Start Spring Boot!");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

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

            surveyRepository.save(survey1);
            surveyRepository.save(survey2);
            surveyRepository.save(survey3);
            surveyRepository.save(survey4);
            surveyRepository.save(survey5);

        };
    }

}