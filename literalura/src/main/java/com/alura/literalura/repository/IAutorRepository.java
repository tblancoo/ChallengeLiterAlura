package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNomeContainsIgnoreCase(String nome);
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND a.anoFalecimento >= :ano")
    List<Autor> buscarAutoresVivosNoAno(Integer ano);
}