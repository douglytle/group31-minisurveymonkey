package group.thirtyone.surveycomponents;


import java.util.ArrayList;
import java.util.List;

public class Survey {

    //id

    List<Question> questions;

    public Survey() {
        questions = new ArrayList<Question>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }


}
