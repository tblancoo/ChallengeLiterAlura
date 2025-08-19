package com.alura.literalura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private String urlDownload;
    private Double downloadCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores = new ArrayList<>();

    public Livro() {
    }

    public Livro(String titulo, String idioma, String urlDownload, Double downloadCount) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.urlDownload = urlDownload;
        this.downloadCount = downloadCount;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getUrlDownload() {
        return urlDownload;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void adicionarAutor(Autor autor) {
        this.autores.add(autor);
    }

    @Override
    public String toString() {
        return "\n📚 Livro: " + titulo +
                "\n🌐 Idioma: " + idioma +
                "\n✍️ Autores: " + autores +
                "\n🔗 Download: " + urlDownload;
    }
}
