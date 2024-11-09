package group.thirtyone.surveycomponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberRangeTest {

    NumberRange numberRange;

    @BeforeEach
    void setUp() {
        numberRange = new NumberRange();
    }

    @Test
    void setMin() {
        numberRange.setMin(5);
        assertEquals(5, numberRange.getMin());
    }

    @Test
    void setMax() {
        numberRange.setMax(5);
        assertEquals(5, numberRange.getMax());
    }

    @Test
    void setQuestion() {
        numberRange.setQuestion("This is a question");
        assertEquals("This is a question", numberRange.getQuestion());
    }

    @Test
    void addAnswer() {
        numberRange.addAnswer("3");
        numberRange.addAnswer("2");

        assertEquals(2, numberRange.getAnswers().size());
        assertEquals("3", numberRange.getAnswers().get(0));
        assertEquals("2", numberRange.getAnswers().get(1));
    }

    @Test
    void setOrderOnPage(){
        numberRange.setOrderOnPage(1);
        assertEquals(numberRange.getOrderOnPage(), 1);
    }
}