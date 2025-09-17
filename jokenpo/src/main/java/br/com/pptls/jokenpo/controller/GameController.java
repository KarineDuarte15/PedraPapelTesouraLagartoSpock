package br.com.pptls.jokenpo.controller;

import java.util.Map;
import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pptls.jokenpo.dto.JogadaRequest;
import br.com.pptls.jokenpo.dto.ResultadoResponse;
import br.com.pptls.jokenpo.model.Jogada;

@RestController
@RequestMapping("/api/jogo")
@CrossOrigin(origins = "*")
public class GameController {

    private final Random random = new Random();

    // Mapa que associa a jogada vencedora e a perdedora com a frase e a chave da imagem
    private static final Map<Jogada, Map<Jogada, String>> REGRAS_DESCRICAO = Map.of(
        Jogada.TESOURA, Map.of(Jogada.PAPEL, "Tesoura cortou o Papel", Jogada.LAGARTO, "Tesoura decapitou o Lagarto"),
        Jogada.PAPEL, Map.of(Jogada.PEDRA, "Papel cobriu a Pedra", Jogada.SPOCK, "Papel refutou o Spock"),
        Jogada.PEDRA, Map.of(Jogada.LAGARTO, "Pedra esmagou o Lagarto", Jogada.TESOURA, "Pedra quebrou a Tesoura"),
        Jogada.LAGARTO, Map.of(Jogada.SPOCK, "Lagarto envenenou o Spock", Jogada.PAPEL, "Lagarto comeu o Papel"),
        Jogada.SPOCK, Map.of(Jogada.TESOURA, "Spock Derreteu a Tesoura", Jogada.PEDRA, "Spock vaporizou a Pedra")
    );

    @PostMapping("/jogar")
    public ResponseEntity<ResultadoResponse> jogar(@RequestBody JogadaRequest request) {
        Jogada jogadaJogador;
        try {
            jogadaJogador = Jogada.valueOf(request.getJogador().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        Jogada[] todasAsJogadas = Jogada.values();
        Jogada jogadaComputador = todasAsJogadas[random.nextInt(todasAsJogadas.length)];

        int resultadoComparacao = jogadaJogador.comparar(jogadaComputador);
        
        String resultadoFinal;
        String descricao = "";
        String vitoriaKey = null;

        if (resultadoComparacao == 0) {
            resultadoFinal = "EMPATE";
            descricao = "Ambos escolheram " + jogadaJogador + ". Empate!";
        } else if (resultadoComparacao > 0) {
            resultadoFinal = "VOCÊ VENCEU";
            // Busca a descrição da vitória do jogador sobre o computador
            descricao = REGRAS_DESCRICAO.get(jogadaJogador).get(jogadaComputador);
            vitoriaKey = jogadaJogador.name() + "_" + jogadaComputador.name(); // Ex: "PEDRA_TESOURA"
        } else {
            resultadoFinal = "COMPUTADOR VENCEU";
            // Busca a descrição da vitória do computador sobre o jogador
            descricao = REGRAS_DESCRICAO.get(jogadaComputador).get(jogadaJogador);
            vitoriaKey = jogadaComputador.name() + "_" + jogadaJogador.name(); // Ex: "SPOCK_PEDRA"
        }

        ResultadoResponse response = new ResultadoResponse(jogadaJogador, jogadaComputador, resultadoFinal, descricao, vitoriaKey);
        return ResponseEntity.ok(response);
    }
}