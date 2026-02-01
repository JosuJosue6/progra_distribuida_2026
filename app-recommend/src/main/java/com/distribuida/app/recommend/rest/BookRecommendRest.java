package com.distribuida.app.recommend.rest;

import com.distribuida.app.recommend.dtos.BookRecDto;
import com.distribuida.app.recommend.services.BookAIServices;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/recommend")
public class BookRecommendRest {

    final BookAIServices services;

    public BookRecommendRest(BookAIServices services) {
        this.services = services;
    }

    @GetMapping(path = "/test")
    public String test() {
        System.out.println("Test");
        return "Test";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookRecDto> recommendar(String title) {
        return services.recommendar(title);
    }

}
