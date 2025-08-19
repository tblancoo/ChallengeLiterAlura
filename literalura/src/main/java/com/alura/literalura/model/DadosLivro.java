package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DadosAutor> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") Double download_count,
        @JsonAlias("formats") Map<String, String> formats) {
}