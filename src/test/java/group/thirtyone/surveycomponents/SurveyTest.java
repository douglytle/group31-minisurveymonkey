package group.thirtyone.surveycomponents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SurveyTest {

    @Test
    void addQuestion() {
        MultipleChoice newChoice = new MultipleChoice();
        OpenEnded openEnded = new OpenEnded();
        NumberRange numberRange = new NumberRange();

        Survey survey = new Survey();
        survey.addQuestion(newChoice);
        survey.addQuestion(openEnded);
        survey.addQuestion(numberRange);

        assertTrue(survey.getQuestions().contains(newChoice));
        assertTrue(survey.getQuestions().contains(openEnded));
        assertTrue(survey.getQuestions().contains(numberRange));
        assertEquals("MCQ", survey.getQuestions().get(0).getType());
        assertEquals("OE", survey.getQuestions().get(1).getType());
        assertEquals("NR", survey.getQuestions().get(2).getType());
    }

    @Test
    void testName() {
        Survey survey = new Survey();
        survey.setName("test");
        assertEquals(survey.getName(), "test");
    }

    @Test
    void testNumber()
    {
        Survey survey = new Survey();
        survey.addQuestion(new MultipleChoice());
        survey.addQuestion(new NumberRange());

        assertEquals(2, survey.getNumberOfQuestions());
    }
}