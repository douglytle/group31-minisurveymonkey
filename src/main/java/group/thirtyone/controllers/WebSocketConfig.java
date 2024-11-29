package group.thirtyone.controllers;

import group.thirtyone.persistencerepositories.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SurveyRepository surveyRepository;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(surveyRepository), "/comms/survey-speak").setAllowedOrigins("*");
    }
}
