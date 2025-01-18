package br.com.alura.literalura_challenge.model;

import br.com.alura.literalura_challenge.dto.DadosAuthor;
import br.com.alura.literalura_challenge.dto.DadosBook;
import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Author autor;
    private Double numeroDeDownloads;
    private String idioma;

    public Livro() {}

    public Livro(DadosBook dadosBook) {
        this.titulo = dadosBook.titulo();
        if (dadosBook.autores() != null && !dadosBook.autores().isEmpty()) {
            DadosAuthor dadosAuthor = dadosBook.autores().get(0);
            this.autor = new Author(dadosAuthor.autor(), dadosAuthor.anoDeNascimento(), dadosAuthor.anoDeFalecimento());
        } else {
            this.autor = new Author("Autor não informado", null, null);
        }
        this.setIdioma(dadosBook.idioma() != null && !dadosBook.idioma().isEmpty() ? dadosBook.idioma().get(0) : "Idioma não informado");
        this.numeroDeDownloads = dadosBook.numeroDeDownload() != null ? dadosBook.numeroDeDownload() : 0.0;
    }

    public void setAutor(Author author) {
        this.autor = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Author getAutor() {
        return autor;
    }
    public Double getNumeroDeDownloads() {
        return numeroDeDownloads;
    }

    public void setNumeroDeDownloads(Double numeroDeDownloads) {
        this.numeroDeDownloads = numeroDeDownloads;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma != null ? idioma.trim().toLowerCase() : "idioma não informado";
    }

    @Override
    public String toString() {
        return "----------Livro----------\n" +
                "Id=" + id + "\n" +
                "Titulo='" + titulo + "'\n" +
                "Número de Downloads=" + numeroDeDownloads + "\n" +
                "Idioma='" + idioma + "'\n" +
                "-------------------------------";
    }
    }

