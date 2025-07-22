package com.jogo.ActRaiser.logica;

import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.ObjetoDoJogo;
import com.jogo.ActRaiser.modelos.objetos.moveis.Projetil;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Inimigo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class GerenciadorDeColisoes {

    public void verificarColisaoJogadorComObjeto(Player jogador, ObjetoDoJogo objeto) {
        if (jogador == objeto) {
            return;
        }
        if (!jogador.getHitbox().overlaps(objeto.getHitbox())) {
            return;
        }

        if (objeto instanceof Inimigo) {
            processarDanoDoInimigoNoJogador(jogador, (Inimigo) objeto);
            aplicarEmpurraoEntreJogadorEObjeto(jogador, objeto);
            ((Inimigo)objeto).morrer();
        }
    }

    public void verificarColisaoProjetilComInimigo(Player jogador, Inimigo inimigo) {
        for (Projetil projetil : jogador.getProjeteis()) {
            if (projetil.estaAtivo() && projetil.getHitbox().overlaps(inimigo.getHitbox())) {
                aplicarDanoDoProjetilNoInimigo(inimigo, jogador.getPontosDano());
                desativarProjetil(projetil);

                if (inimigo.estaMorto()) {
                    inimigo.morrer();
                    jogador.adicionaPontosMagia(inimigo.getPontosMagia());
                    jogador.adicionaPontuacao(inimigo.getPontuacao());
                }
            }
        }
    }

    private void processarDanoDoInimigoNoJogador(Player jogador, Inimigo inimigo) {
        int dano = inimigo.getPontosDano();
        jogador.removePontosVida(dano);
    }

    private void aplicarDanoDoProjetilNoInimigo(Inimigo inimigo, int dano) {
        inimigo.removePontosVida(dano);
    }

    private void desativarProjetil(Projetil projetil) {
        projetil.destruir();
    }

    private void aplicarEmpurraoEntreJogadorEObjeto(Player jogador, ObjetoDoJogo objeto) {
        Rectangle hitboxJogador = jogador.getHitbox();
        Rectangle hitboxObjeto = objeto.getHitbox();

        float sobreposicaoX = calcularSobreposicaoEixoX(hitboxJogador, hitboxObjeto);
        float sobreposicaoY = calcularSobreposicaoEixoY(hitboxJogador, hitboxObjeto);

        if (sobreposicaoX < sobreposicaoY) {
            float direcaoX = hitboxJogador.x < hitboxObjeto.x ? -sobreposicaoX : sobreposicaoX;
            jogador.setPosicaoX(jogador.getPosicaoX() + direcaoX);
        } else {
            float direcaoY = hitboxJogador.y < hitboxObjeto.y ? -sobreposicaoY : sobreposicaoY;
            jogador.setPosicaoY(jogador.getPosicaoY() + direcaoY);
        }
    }

    private float calcularSobreposicaoEixoX(Rectangle hitboxA, Rectangle hitboxB) {
        float sobreposicaoA = hitboxA.x + hitboxA.width - hitboxB.x;
        float sobreposicaoB = hitboxB.x + hitboxB.width - hitboxA.x;
        return Math.min(sobreposicaoA, sobreposicaoB);
    }

    private float calcularSobreposicaoEixoY(Rectangle hitboxA, Rectangle hitboxB) {
        float sobreposicaoA = hitboxA.y + hitboxA.height - hitboxB.y;
        float sobreposicaoB = hitboxB.y + hitboxB.height - hitboxA.y;
        return Math.min(sobreposicaoA, sobreposicaoB);
    }
}
