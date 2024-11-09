package group.thirtyone.surveycomponents;

import group.thirtyone.persistencerepositories.MultipleChoiceRepository;
import group.thirtyone.persistencerepositories.NumberRangeRepository;
import group.thirtyone.persistencerepositories.OpenEndedRepository;
import group.thirtyone.persistencerepositories.SurveyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersistenceTest {
    MultipleChoiceRepository multipleChoiceRepository;
    NumberRangeRepository numberRangeRepository;
    OpenEndedRepository openEndedRepository;
    SurveyRepository surveyRepository;
    MultipleChoice multipleChoice;
    NumberRange numberRange;
    OpenEnded openEnded;
    Survey survey;

    @BeforeEach
    void setUp(){
        List<String> choices = new ArrayList<>();
        choices.add("A");
        choices.add("B");
        choices.add("C");

        multipleChoice = new MultipleChoice(choices, "Test Question");
        numberRange = new NumberRange("Test Question", 0, 100);
        openEnded = new OpenEnded("Test Question");

        survey = new Survey();
        survey.addQuestion(multipleChoice);
        survey.addQuestion(numberRange);
        survey.addQuestion(openEnded);
    }

    @Test
    void multipleChoice() {
        /*
        multipleChoiceRepository.save(multipleChoice);
        Optional<MultipleChoice> result = multipleChoiceRepository.findById(0L);
        assertTrue(result.isPresent());
         */
    }

    @Test
    void numberRange() {
        /*
        numberRangeRepository.save(numberRange);
        Optional<NumberRange> result = numberRangeRepository.findById(0L);
        assertTrue(result.isPresent());
         */
    }

    @Test
    void OpenEnded() {
        /*
        openEndedRepository.save(openEnded);
        Optional<OpenEnded> result = openEndedRepository.findById(0L);
        assertTrue(result.isPresent());
         */
    }

    @Test
    void survey() {
        /*
        surveyRepository.save(survey);
        Optional<Survey> result = surveyRepository.findById(0L);
        assertTrue(result.isPresent());
        assertEquals(result.get().getQuestions().size(), 3);
         */
    }

}
