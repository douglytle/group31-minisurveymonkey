package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import group.thirtyone.surveycomponents.Survey;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    private static Set<WebSocketSession> sessions = new HashSet<>();

    private SurveyRepository surveyRepository;

    public SocketTextHandler(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        JSONObject jsonObject = new JSONObject(payload);
        String msgtoSend = "";
        switch (jsonObject.get("type").toString()) {
            case "CLOSE_SURVEY":
                closeSurvey(jsonObject.get("id").toString());
                msgtoSend = "CLOSE #" + jsonObject.get("id").toString() + "#";
                break;
            // other cases...
        }

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                try {
                    webSocketSession.sendMessage(new TextMessage(msgtoSend));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void closeSurvey(String id)
    {
        Long surveyId = Long.parseLong(id);
        Optional<Survey> surveyInQuestion = surveyRepository.findById(surveyId);
        if (surveyInQuestion.isPresent()) {
            Survey survey = surveyInQuestion.get();
            survey.close();
            surveyRepository.save(survey);
        }
    }

}
