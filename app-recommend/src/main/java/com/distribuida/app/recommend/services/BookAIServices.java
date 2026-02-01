package com.distribuida.app.recommend.services;

import com.distribuida.app.recommend.dtos.BookRecDto;

import java.util.List;

public interface BookAIServices   {

    List<BookRecDto> recommendar(String title);
}
