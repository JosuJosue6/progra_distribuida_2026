package com.distribuida.app.recommend;

import ch.qos.logback.classic.net.SyslogAppender;
import com.distribuida.app.recommend.services.BookAIServices;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRecommendMain {
    private final ChatModel chatModel;

    public AppRecommendMain(ChatModel chatModel) {
        this.chatModel = chatModel;
    }
    public static void main(String[] args) {
        SpringApplication.run(AppRecommendMain.class, args);
    }

    public CommandLineRunner run(ChatModel model, BookAIServices service) {
        return (String[] args) -> {
            System.out.println(model);

            var res = service.recommendar("Spring Framework");
            System.out.println(res);
        };
    }
}
