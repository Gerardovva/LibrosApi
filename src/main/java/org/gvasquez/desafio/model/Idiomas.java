package org.gvasquez.desafio.model;

public enum Idiomas {
    ESPANOL("es"),
    INGLES("en"),
    FRANCES("fr");

    private String lenguage;

    Idiomas(String lenguage){
        this.lenguage=lenguage;
    }


    public static Idiomas fromString(String text)  {
        for (Idiomas idiomas : Idiomas.values()) {
            if (idiomas.lenguage.equalsIgnoreCase(text)) {
                return idiomas;
            }
        }
        throw new IllegalArgumentException("Ninguna idioma encontrada: " + text);
    }
}
