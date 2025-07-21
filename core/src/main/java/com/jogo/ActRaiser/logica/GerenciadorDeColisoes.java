package com.jogo.ActRaiser.logica;

import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.modelos.objetos.ObjetoDoJogo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Inimigo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class GerenciadorDeColisoes {

    public void verificarColisao(Player player, ObjetoDoJogo objeto) {
        if (player == objeto || !player.getHitbox().overlaps(objeto.getHitbox()))
            return;

        if (objeto instanceof Inimigo) {
            Inimigo inimigo = (Inimigo) objeto;
            player.removePontosVida(inimigo.getPontosDano());
            resolverEmpurrao(player, objeto);
        }
    }

    private void resolverEmpurrao(Player player, ObjetoDoJogo objeto) {
        Rectangle playerHitbox = player.getHitbox();
        Rectangle objetoHitbox = objeto.getHitbox();
        float overlapX = calcularSobreposicaoEixoX(playerHitbox, objetoHitbox);
        float overlapY = calcularSobreposicaoEixoY(playerHitbox, objetoHitbox);

        if (overlapX < overlapY) {
            float direcaoX = playerHitbox.x < objetoHitbox.x ? -overlapX : overlapX;
            player.setPosicaoX(player.getPosicaoX() + direcaoX);
        } else {
            float direcaoY = playerHitbox.y < objetoHitbox.y ? -overlapY : overlapY;
            player.setPosicaoY(player.getPosicaoY() + direcaoY);
        }
    }

    private float calcularSobreposicaoEixoX(Rectangle hitboxA, Rectangle hitboxB) {
        return Math.min(
                hitboxA.x + hitboxA.width - hitboxB.x,
                hitboxB.x + hitboxB.width - hitboxA.x);
    }

    private float calcularSobreposicaoEixoY(Rectangle hitboxA, Rectangle hitboxB) {
        return Math.min(
                hitboxA.y + hitboxA.height - hitboxB.y,
                hitboxB.y + hitboxB.height - hitboxA.y);
    }

}
