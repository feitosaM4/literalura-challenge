package br.com.alura.literalura_challenge.service;

import br.com.alura.literalura_challenge.client.ConsumoAPI;
import br.com.alura.literalura_challenge.model.Author;
import br.com.alura.literalura_challenge.dto.DadosAuthor;
import br.com.alura.literalura_challenge.dto.DadosBook;
import br.com.alura.literalura_challenge.model.Livro;
import br.com.alura.literalura_challenge.repository.AuthorRepository;
import br.com.alura.literalura_challenge.repository.BookRepository;
import br.com.alura.literalura_challenge.util.ConvertDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConvertDados convertDados;

    public void salvarAtualizarAutor(Author author) {
        authorRepository.saveAndFlush(author);
    }

    public void processarLivro(DadosBook dadosBook) {
        System.out.println("Titulo: " + dadosBook.titulo());

        Author author = null;
        if (dadosBook.autores() != null && !dadosBook.autores().isEmpty()) {
            DadosAuthor dadosAuthor = dadosBook.autores().get(0);
            System.out.println("Autor recebido: " + dadosAuthor.autor());
            author = obterAutor(dadosAuthor);
            salvarAtualizarAutor(author);
        } else {
            System.out.println("Autor: Não informado.");
        }

        System.out.println("Idioma: " +
                (dadosBook.idioma() != null && !dadosBook.idioma().isEmpty()
                        ? dadosBook.idioma().get(0) : "Não informado"));

        System.out.println("Número de downloads: " +
                (dadosBook.numeroDeDownload() != null ? dadosBook.numeroDeDownload() : "Não informado"));

        persistirLivro(dadosBook, author);
    }

    public Author obterAutor(DadosAuthor dadosAuthor) {
        return new Author(dadosAuthor.autor(), dadosAuthor.anoDeNascimento(), dadosAuthor.anoDeFalecimento());

    }

    private void persistirLivro(DadosBook dadosBook, Author author) {
        Livro livro = new Livro(dadosBook);
        livro.setAutor(author);
        bookRepository.save(livro);
    }

    public List<Livro> listarLivrosIdiomas(String idioma) {
        return bookRepository.listarLivrosIdiomas(idioma);
    }
}