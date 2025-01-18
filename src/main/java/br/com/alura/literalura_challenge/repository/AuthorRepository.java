package br.com.alura.literalura_challenge.repository;

import br.com.alura.literalura_challenge.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AuthorRepository extends JpaRepository <Author, Long> {

   List<Author> findByAnoDeNascimentoLessThanEqualAndAnoDeFalecimentoGreaterThanEqual(Integer anoDeNascimento, Integer AnoDeFalecimento);
    }



