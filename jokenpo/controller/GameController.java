package br.com.seuprojeto.jokenpo.controller;

import br.com.seuprojeto.jokenpo.dto.JogadaRequest;
import br.com.seuprojeto.jokenpo.dto.ResultadoResponse;
import br.com.seuprojeto.jokenpo.model.Jogada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/jogo")
@CrossOrigin(origins = "*") 
public class GameController {

    private final Random random = new Random();

    @PostMapping("/jogar")
    public ResponseEntity<ResultadoResponse> jogar(@RequestBody JogadaRequest request) {
        // 1. Validar e converter a jogada do jogador
        Jogada jogadaJogador;
        try {
            jogadaJogador = Jogada.valueOf(request.getJogador().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Retorna um erro se a jogada for inválida
            return ResponseEntity.badRequest().build();
        }

        // 2. Gerar a jogada do computador
        Jogada[] todasAsJogadas = Jogada.values();
        Jogada jogadaComputador = todasAsJogadas[random.nextInt(todasAsJogadas.length)];

        // 3. Determinar o resultado
        int resultadoComparacao = jogadaJogador.comparar(jogadaComputador);
        String resultadoFinal;

        if (resultadoComparacao == 0) {
            resultadoFinal = "EMPATE";
        } else if (resultadoComparacao > 0) {
            resultadoFinal = "VOCÊ VENCEU";
        } else {
            resultadoFinal = "COMPUTADOR VENCEU";
        }

        // 4. Montar e retornar a resposta
        ResultadoResponse response = new ResultadoResponse(jogadaJogador, jogadaComputador, resultadoFinal);
        return ResponseEntity.ok(response);
    }
}