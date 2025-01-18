package br.com.alura.literalura_challenge.repository;

import br.com.alura.literalura_challenge.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> listarLivrosIdiomas(@Param("idioma") String idioma);
}


