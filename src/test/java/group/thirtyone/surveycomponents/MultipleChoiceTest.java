package group.thirtyone.surveycomponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceTest {

    MultipleChoice mc;

    @BeforeEach
    void setUp() {
        mc = new MultipleChoice();
    }

    @Test
    void setQuestion() {
        mc.setQuestion("This is a question");
        assertEquals("This is a question", mc.getQuestion());
    }

    @Test
    void addAnswer() {
        mc.addAnswer("This is an answer");
        mc.addAnswer("This is another answer");
        assertEquals(2, mc.getAnswers().size());
        assertEquals("This is an answer", mc.getAnswers().get(0));
        assertEquals("This is another answer", mc.getAnswers().get(1));
    }

    @Test
    void getType() {
        assertEquals("MCQ", mc.getType());
    }

    @Test
    void setChoices() {
        List<String> choices = new ArrayList<>();
        choices.add("A");
        choices.add("B");
        choices.add("C");
        mc.setChoices(choices);
        assertEquals(3, mc.getChoices().size());
        assertEquals(choices, mc.getChoices());
    }

    @Test
    void addChoice() {
        mc.addChoice("A");
        mc.addChoice("B");
        assertTrue(mc.getChoices().contains("A"));
        assertTrue(mc.getChoices().contains("B"));
        assertEquals(2, mc.getChoices().size());
    }

    @Test
    void setOrderOnPage(){
        mc.setOrderOnPage(1);
        assertEquals(mc.getOrderOnPage(), 1);
    }
}