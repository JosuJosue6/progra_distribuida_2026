package com.distribuida.app.recommend.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRecDto {
    private String title;
    private String isbn;
    private String Editorial;
    private String description;

}
