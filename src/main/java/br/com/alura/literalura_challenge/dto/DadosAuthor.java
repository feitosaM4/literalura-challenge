package br.com.alura.literalura_challenge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAuthor(@JsonAlias ("name") String autor,
                          @JsonAlias("birth_year") Integer anoDeNascimento,
                          @JsonAlias("death_year") Integer anoDeFalecimento) {
    @Override
    public String toString() {
        return "Autor: " + autor + ", anoDeNascimento=" + anoDeNascimento +
                ", anoDeFalecimento=" + (anoDeFalecimento != null ? anoDeFalecimento : "Vivo");
    }
}
