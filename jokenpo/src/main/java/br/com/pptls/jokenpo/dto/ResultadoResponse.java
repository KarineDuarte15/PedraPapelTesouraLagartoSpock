package br.com.pptls.jokenpo.dto;

import br.com.pptls.jokenpo.model.Jogada;

public class ResultadoResponse {
    private Jogada jogadaJogador;
    private Jogada jogadaComputador;
    private String resultado;
    private String descricao; 
    private String vitoriaKey;

    public ResultadoResponse(Jogada jogadaJogador, Jogada jogadaComputador, String resultado) {
        this.jogadaJogador = jogadaJogador;
        this.jogadaComputador = jogadaComputador;
        this.resultado = resultado;
        this.descricao = descricao;
        this.vitoriaKey = vitoriaKey;
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
    public String getDescricao() {
        return descricao;
        
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getVitoriaKey() {
        return vitoriaKey;
    }
    public void setVitoriaKey(String vitoriaKey) {
        this.vitoriaKey = vitoriaKey;
    }
}