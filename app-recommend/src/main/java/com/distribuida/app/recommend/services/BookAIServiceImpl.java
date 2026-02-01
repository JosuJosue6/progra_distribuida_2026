package com.distribuida.app.recommend.services;

import com.distribuida.app.recommend.dtos.BookRecDto;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAIServiceImpl implements BookAIServices{

    private final ChatClient chatClient;

    public BookAIServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public List<BookRecDto> recommendar(String title) {
        String promptText = "Recomienda 2 libros para alguien que le interesa {title} " ;

        var ret = chatClient.prompt()
                .user(userSpec ->userSpec.text(promptText)
                        .param("title", title)
                )
                .call()
                .entity(new ParameterizedTypeReference<List<BookRecDto>>() {
                });

        return ret;
    }
}
