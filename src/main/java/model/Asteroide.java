package model;

/**
 * Asteroide que passa perto da Terra, como vem da API NeoWs da NASA.
 */
public record Asteroide(
        String nome,
        float distanciaKm,
        float velocidadeKmH,
        float diametroMinimoKm,
        boolean potencialmentePerigoso,
        String dataDeAproximacao) {
}
