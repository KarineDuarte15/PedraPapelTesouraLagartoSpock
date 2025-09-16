// URL da nossa API no back-end Spring Boot
const API_URL = 'http://localhost:8080/api/jogo/jogar';

// Seleciona todos os botões de jogada
const botoesJogada = document.querySelectorAll('.btn-jogada');

// Seleciona os elementos onde o resultado será exibido
const suaJogadaEl = document.getElementById('sua-jogada');
const computadorJogadaEl = document.getElementById('computador-jogada');
const vencedorEl = document.getElementById('vencedor');

// Adiciona um "ouvinte de evento" de clique para cada botão
botoesJogada.forEach(botao => {
    botao.addEventListener('click', () => {
        // Pega a jogada do atributo 'data-jogada' do botão clicado
        const jogada = botao.dataset.jogada;
        enviarJogada(jogada);
    });
});

async function enviarJogada(jogada) {
    // Exibe um feedback para o usuário
    vencedorEl.textContent = 'Processando...';
    suaJogadaEl.textContent = '--';
    computadorJogadaEl.textContent = '--';

    try {
        // Faz a requisição POST para a API
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            // Converte o objeto JavaScript em uma string JSON
            body: JSON.stringify({ jogador: jogada })
        });

        // Se a resposta não for OK (ex: erro 400), lança um erro
        if (!response.ok) {
            throw new Error('Erro ao se comunicar com o servidor.');
        }

        // Converte a resposta JSON da API em um objeto JavaScript
        const resultado = await response.json();

        // Atualiza a tela com os dados recebidos da API
        atualizarUI(resultado);

    } catch (error) {
        // Em caso de erro, exibe uma mensagem no console e na tela
        console.error('Falha na requisição:', error);
        vencedorEl.textContent = 'Erro! Tente novamente.';
    }
}

function atualizarUI(resultado) {
    suaJogadaEl.textContent = resultado.jogadaJogador;
    computadorJogadaEl.textContent = resultado.jogadaComputador;
    vencedorEl.textContent = resultado.resultado;
}