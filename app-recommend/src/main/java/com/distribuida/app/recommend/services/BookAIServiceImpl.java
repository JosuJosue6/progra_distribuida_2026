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
        String prompText ="""
            Recomienda 2 libros para alguien que le interesa {titulo}.
            Devuelve exclusivamente un JSON con el siguiente formato:
            [
                \\{
                   "titulo": "..",
                   "isbn": "...",
                   "editorial": "...",
                   "descripcion": "..."
                }
            ]
            No agregues texto explicativo ni texto adicional.
            """;
        ;
        return chatClient.prompt()
                .user(promptUserSpec -> promptUserSpec
                        .text(prompText)
                        .param("titulo", title))
                .call()
                .entity(new ParameterizedTypeReference<List<BookRecDto>>() {
                });
    }
}
