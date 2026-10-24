package model;

/**
 * Opções de ordenação das tabelas, com a coluna do banco usada em cada uma.
 * Como o SQL só recebe as colunas desta lista, o texto da tela nunca entra direto na consulta.
 */
public enum Ordenacao {
    DISTANCIA("Distancia", "distancia"),
    TAMANHO("Tamanho", "tamanho"),
    VELOCIDADE("Velocidade", "velocidade"),
    RISCO("Risco", "risco");

    private final String nomeNaTela;
    private final String coluna;

    Ordenacao(String nomeNaTela, String coluna) {
        this.nomeNaTela = nomeNaTela;
        this.coluna = coluna;
    }

    public String coluna() {
        return coluna;
    }

    public static Ordenacao doNomeNaTela(String nome) {
        for (Ordenacao ordenacao : values()) {
            if (ordenacao.nomeNaTela.equals(nome)) {
                return ordenacao;
            }
        }
        throw new IllegalArgumentException("Ordenação desconhecida: " + nome);
    }
}
