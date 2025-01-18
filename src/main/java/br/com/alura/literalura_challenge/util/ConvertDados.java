package br.com.alura.literalura_challenge.util;

import br.com.alura.literalura_challenge.dto.DadosBook;
import br.com.alura.literalura_challenge.dto.RespostaLivros;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvertDados implements IConvertDados {
    private ObjectMapper mapper;

    public ConvertDados() {
        this.mapper = new ObjectMapper();

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public <T> T obterDados(String json, Class<T> classe){
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DadosBook obterDados(RespostaLivros json, Class<DadosBook> dadosLivroClass) {
        return null;
    }
}

