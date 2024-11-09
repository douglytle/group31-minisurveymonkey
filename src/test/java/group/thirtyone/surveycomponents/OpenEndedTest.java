package group.thirtyone.surveycomponents;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenEndedTest {

    OpenEnded openEnded;

    @BeforeEach
    void setUp() {
        openEnded = new OpenEnded();
    }

    @Test
    void setQuestion() {
        openEnded.setQuestion("This is a question");
        assertEquals("This is a question", openEnded.getQuestion());
    }

    @Test
    void addAnswer() {
        openEnded.addAnswer("This is an answer");
        openEnded.addAnswer("This is another answer");
        assertEquals(2, openEnded.getAnswers().size());
        assertEquals("This is an answer", openEnded.getAnswers().get(0));
        assertEquals("This is another answer", openEnded.getAnswers().get(1));
    }

    @Test
    void setOrderOnPage(){
        openEnded.setOrderOnPage(1);
        assertEquals(openEnded.getOrderOnPage(), 1);
    }
}