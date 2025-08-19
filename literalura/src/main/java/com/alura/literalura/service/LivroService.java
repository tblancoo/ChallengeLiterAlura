package com.alura.literalura.service;

import com.alura.literalura.model.DadosAutor;
import com.alura.literalura.model.DadosLivro;
import com.alura.literalura.model.DadosResultado;
import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.repository.IAutorRepository;
import com.alura.literalura.repository.ILivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ILivroRepository livroRepository;
    private final IAutorRepository autorRepository;
    private static final String URL_API = "https://gutendex.com/books/";

    public LivroService(ILivroRepository livroRepository, IAutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarLivroPeloTituloESalvar(String titulo) {
        String url = URL_API + "?search=" + titulo.replace(" ", "+");
        try {
            DadosResultado resultado = restTemplate.getForObject(url, DadosResultado.class);
            if (resultado == null || resultado.results().isEmpty()) {
                System.out.println("Livro não encontrado.");
                return;
            }
            DadosLivro dadosLivro = resultado.results().get(0);
            Livro livro = new Livro(
                    dadosLivro.title(),
                    dadosLivro.languages().isEmpty() ? "desconhecido" : dadosLivro.languages().get(0),
                    dadosLivro.formats().get("text/html; charset=utf-8"),
                    dadosLivro.download_count()
            );

            Optional<Livro> livroExistente = livroRepository.findByTituloContainsIgnoreCase(livro.getTitulo());
            if (livroExistente.isPresent()) {
                System.out.println("Livro já cadastrado!");
                return;
            }

            for (DadosAutor dadosAutor : dadosLivro.authors()) {
                Optional<Autor> autorExistente = autorRepository.findByNomeContainsIgnoreCase(dadosAutor.name());
                Autor autor;
                if (autorExistente.isPresent()) {
                    autor = autorExistente.get();
                } else {
                    autor = new Autor(dadosAutor.name(), dadosAutor.birth_year(), dadosAutor.death_year());
                    autorRepository.save(autor);
                }
                livro.adicionarAutor(autor);
            }

            livroRepository.save(livro);
            System.out.println("\nLivro salvo com sucesso:");
            System.out.println(livro);
        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public List<Livro> listarTodosOsLivros() {
        return livroRepository.findAll();
    }

    public List<Autor> listarTodosOsAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        return autorRepository.buscarAutoresVivosNoAno(ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}