package br.com.alura.literalura_challenge.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBook(@JsonAlias("title") String titulo,
                        @JsonAlias("download_count") Double numeroDeDownload,
                        @JsonAlias("languages") List<String> idioma,
                        @JsonAlias("authors") List <DadosAuthor> autores){

}
