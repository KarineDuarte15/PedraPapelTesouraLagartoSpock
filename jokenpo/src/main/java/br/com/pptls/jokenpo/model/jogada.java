package br.com.pptls.jokenpo.model;
import java.util.List;
import java.util.Map;

public enum jogada {
    PEDRA,
    PAPEL,
    TESOURA,
    LAGARTO,
    SPOCK;
    private static final Map<Jogada, List<Jogada>> REGRAS_VITORIA = Map.of(
        PEDRA, List.of(TESOURA, LAGARTO),
        PAPEL, List.of(PEDRA, SPOCK),
        TESOURA, List.of(PAPEL, LAGARTO),
        LAGARTO, List.of(SPOCK, PAPEL),
        SPOCK, List.of(TESOURA, PEDRA)
    );

    /**
     * Compara a jogada atual (this) com a jogada do oponente.
     * @param oponente A jogada do outro jogador.
     * @return 1 se esta jogada vence, -1 se perde, 0 se empata.
     */
    public int comparar(Jogada oponente) {
        if (this == oponente) {
            return 0; // Empate
        }
        if (REGRAS_VITORIA.get(this).contains(oponente)) {
            return 1; // Vitória
        }
        return -1; // Derrota
    }
}