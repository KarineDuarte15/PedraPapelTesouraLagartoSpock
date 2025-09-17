// --- Elementos da Tela de Início e Jogo ---
const telaInicio = document.getElementById('inicio');
const telaJogo = document.getElementById('jogo');
const btnIniciar = document.getElementById('btn-iniciar');
const corpo = document.body;

// --- Elementos do Jogo ---
const API_URL = 'http://localhost:8080/api/jogo/jogar';
const botoesJogada = document.querySelectorAll('.btn-jogada');
const suaJogadaEl = document.getElementById('sua-jogada');
const computadorJogadaEl = document.getElementById('computador-jogada');
const vencedorEl = document.getElementById('vencedor');
const descricaoResultadoEl = document.getElementById('descricao-resultado');
const imagemResultadoEl = document.getElementById('imagem-resultado');

// --- Lógica de Transição de Tela ---
btnIniciar.addEventListener('click', () => {
    telaInicio.classList.add('hidden');
    telaJogo.classList.remove('hidden');
    corpo.classList.remove('tela-inicial');
    corpo.classList.add('tela-jogo');
});

// --- Lógica do Jogo ---
botoesJogada.forEach(botao => {
    botao.addEventListener('click', () => {
        const jogada = botao.dataset.jogada;
        enviarJogada(jogada);
    });
});

async function enviarJogada(jogada) {
    vencedorEl.textContent = 'Processando...';
    descricaoResultadoEl.textContent = '';
    imagemResultadoEl.classList.add('hidden');
    suaJogadaEl.textContent = '--';
    computadorJogadaEl.textContent = '--';

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ jogador: jogada })
        });

        if (!response.ok) {
            throw new Error('Erro ao se comunicar com o servidor.');
        }

        const resultado = await response.json();
        atualizarUI(resultado);

    } catch (error) {
        console.error('Falha na requisição:', error);
        vencedorEl.textContent = 'Erro! Tente novamente.';
    }
}

function atualizarUI(resultado) {
    suaJogadaEl.textContent = resultado.jogadaJogador;
    computadorJogadaEl.textContent = resultado.jogadaComputador;
    vencedorEl.textContent = resultado.resultado;
    descricaoResultadoEl.textContent = resultado.descricao;

    // Se houver uma chave de vitória, mostra a imagem correspondente
    if (resultado.vitoriaKey) {
        imagemResultadoEl.src = `imagens/${resultado.vitoriaKey}.png`;
        imagemResultadoEl.classList.remove('hidden');
    } else {
        imagemResultadoEl.classList.add('hidden'); // Esconde a imagem em caso de empate
    }
}