package br.com.alura.literalura_challenge.util;

import br.com.alura.literalura_challenge.dto.DadosBook;
import br.com.alura.literalura_challenge.dto.RespostaLivros;

public interface IConvertDados {
    <T> T obterDados(String json, Class<T> classe);

    DadosBook obterDados(RespostaLivros json, Class<DadosBook> dadosLivroClass);
}
