package br.com.alura.literalura_challenge.application;

import br.com.alura.literalura_challenge.client.ConsumoAPI;
import br.com.alura.literalura_challenge.dto.DadosBook;
import br.com.alura.literalura_challenge.dto.RespostaLivros;
import br.com.alura.literalura_challenge.model.*;
import br.com.alura.literalura_challenge.repository.AuthorRepository;
import br.com.alura.literalura_challenge.repository.BookRepository;
import br.com.alura.literalura_challenge.util.ConvertDados;
import br.com.alura.literalura_challenge.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final BookService bookService;
    private final ConsumoAPI consumoAPI;
    private final String ENDERECO = "https://gutendex.com/books/?search=";

    @Autowired
    public Principal(BookRepository repository, AuthorRepository authorRepository, BookService bookService, ConsumoAPI consumoAPI) {
        this.repository = repository;
        this.authorRepository = authorRepository;
        this.bookService = bookService;
        this.consumoAPI = consumoAPI;
    }

    @Autowired
    private ConvertDados convertDados;
    private List<Livro> livros = new ArrayList<>();

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    
                    0- Sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosAno();
                    break;
                case 5:
                    listarLivrosIdiomas();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    private void buscarLivroPeloTitulo() {
        System.out.println("Digite o título do livro:");
        String titulo = leitura.nextLine();
        try {
            String json = consumoAPI.obterDados(ENDERECO + titulo.replace(" ", "%20"));
            RespostaLivros respostaLivros = convertDados.obterDados(json, RespostaLivros.class);
            List<DadosBook> livros = respostaLivros.getResults();

            if (livros != null && !livros.isEmpty()) {
                for (DadosBook dadosBook : livros) {
                    bookService.processarLivro(dadosBook);
                }
            } else {
                System.out.println("Nenhum livro encontrado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar o livro: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> todosLivros = repository.findAll();
        Set<String> unicoTitilo = new HashSet<>();

        todosLivros.stream()
                .filter(livro -> unicoTitilo.add(livro.getTitulo()))
                .forEach(livro -> {

                    System.out.println("Título: " + livro.getTitulo());
                    System.out.println("Autor: " + livro.getAutor());
                    System.out.println("Idioma: " + livro.getIdioma());
                    System.out.println("-------------------------------");
                });

        if (todosLivros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private void listarAutoresRegistrados() {
        List<Author> autores = authorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
            return;
        }

        for (Author author : autores) {
            System.out.println("--------Autor--------");
            System.out.println("Nome do Autor: " + author.getNome());

            String anoFalecimento = (author.getAnoDeFalecimento() == null) ? "Vivo" : String.valueOf(author.getAnoDeFalecimento());
            System.out.println("Ano de Nascimento: " + author.getAnoDeNascimento());
            System.out.println("Ano de Falecimento: " + anoFalecimento);

            System.out.println("Livros Escritos: ");
            List<Livro> livros = author.getLivros();
            if (livros.isEmpty()) {
                System.out.println("Nenhum livro cadastrado");
            } else {
                for (Livro livro : livros) {
                    System.out.println("- " + livro.getTitulo());
                }
            }

            System.out.println("----------------------\n");
        }
    }

    private void listarAutoresVivosAno() {
        System.out.println("Digite o ano para buscar autores vivos nesse período: ");
        int ano = leitura.nextInt();

        if (ano <= 0) {
            System.out.println("Ano inválido. Por favor, insira um ano positivo.");
            return;
        }

        List<Author> listaAutores = authorRepository
                .findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoGreaterThanEqual(ano, ano);

        if (listaAutores.isEmpty()) {
            System.out.println("Nenhum autor vivo no ano " + ano);
        } else {
            System.out.println("Autores vivos no ano " + ano + ": ");
            for (Author author : listaAutores) {

                System.out.println("Nome: " + author.getNome());
                System.out.println("Data de Nascimento: " + author.getAnoDeNascimento());

                String falecimento = (author.getAnoDeFalecimento() != null) ?
                        String.valueOf(author.getAnoDeFalecimento()) : "Vivo";
                System.out.println("Ano de Falecimento: " + falecimento);

                System.out.println("----------");
            }
        }
    }

    public void listarLivrosIdiomas() {
        System.out.println("Selecione um idioma:");
        System.out.println(" 1 - Espanhol (es)");
        System.out.println(" 2 - Inglês (en)");
        System.out.println(" 3 - Francês (fr)");
        System.out.println(" 4 - Português (pt)");

        int opcao = leitura.nextInt();
        leitura.nextLine();

        String idioma = "";

        switch (opcao) {
            case 1:
                idioma = "es";
                break;
            case 2:
                idioma = "en";
                break;
            case 3:
                idioma = "fr";
                break;
            case 4:
                idioma = "pt";
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }


        List<Livro> livros = bookService.listarLivrosIdiomas(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma selecionado (" + idioma + ").");
        } else {
            System.out.println("Livros no idioma " + idioma + ":");
            for (Livro livro : livros) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor().getNome());
                System.out.println("Idioma: " + livro.getIdioma());
                System.out.println("Número de Downloads: " + livro.getNumeroDeDownloads());
                System.out.println("-----");
            }
        }
    }
}
