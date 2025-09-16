package br.com.pptls.jokenpo.dto;

import br.com.pptls.jokenpo.model.Jogada;

public class ResultadoResponse {
    private Jogada jogadaJogador;
    private Jogada jogadaComputador;
    private String resultado;

    public ResultadoResponse(Jogada jogadaJogador, Jogada jogadaComputador, String resultado) {
        this.jogadaJogador = jogadaJogador;
        this.jogadaComputador = jogadaComputador;
        this.resultado = resultado;
    }

    // Getters e Setters
    public Jogada getJogadaJogador() {
        return jogadaJogador;
    }

    public void setJogadaJogador(Jogada jogadaJogador) {
        this.jogadaJogador = jogadaJogador;
    }

    public Jogada getJogadaComputador() {
        return jogadaComputador;
    }

    public void setJogadaComputador(Jogada jogadaComputador) {
        this.jogadaComputador = jogadaComputador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}