package br.com.alura.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Data(
        @JsonAlias("codigo") String code,
        @JsonAlias("nome") String name) {
}