package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Livro;
import com.alura.literalura.service.LivroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final LivroService livroService;
    private Scanner scanner = new Scanner(System.in);

    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }

    @Override
    public void run(String... args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
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
                        listarAutoresVivosPorAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Digite um número.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Buscar livro pelo título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos em um determinado ano");
        System.out.println("5 - Listar livros por idioma");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void buscarLivroPeloTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        livroService.buscarLivroPeloTituloESalvar(titulo);
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroService.listarTodosOsLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado ainda.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = livroService.listarTodosOsAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado ainda.");
        } else {
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosPorAno() {
        try {
            System.out.print("Digite o ano para buscar autores vivos: ");
            int ano = Integer.parseInt(scanner.nextLine());
            List<Autor> autores = livroService.listarAutoresVivosNoAno(ano);
            if (autores.isEmpty()) {
                System.out.println("Nenhum autor vivo encontrado neste ano.");
            } else {
                autores.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido. Digite um número inteiro.");
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma para buscar livros (en, pt, es, etc.): ");
        String idioma = scanner.nextLine();
        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para este idioma.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}