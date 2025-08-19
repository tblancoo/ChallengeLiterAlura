package com.alura.literalura.model;

public enum Idioma {
    EN("en"),
    PT("pt"),
    ES("es"),
    FR("fr"),
    DESCONHECIDO("desconhecido");

    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    public static Idioma fromCodigo(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigo.equalsIgnoreCase(codigo)) {
                return idioma;
            }
        }
        return DESCONHECIDO;
    }
}
