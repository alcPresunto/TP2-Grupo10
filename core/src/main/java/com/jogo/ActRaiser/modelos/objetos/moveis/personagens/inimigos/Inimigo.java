package com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.jogo.ActRaiser.animacoes.AnimacaoGenerica;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;

public class Inimigo extends Personagem {
    protected Player player;
    protected AnimacaoGenerica animacao;

    public Inimigo(float posicaoX, float posicaoY, Texture texture, Rectangle hitbox, float velocidade, int vida,
                   int magia, int dano, Player player, AnimacaoGenerica animacao) {
        super(posicaoX, posicaoY, texture, hitbox, velocidade, vida, magia, dano);
        this.player = player;
        this.animacao = animacao;
    }

    @Override
    public void mover() {
        float delta = Gdx.graphics.getDeltaTime();

        if (posicaoX < player.getPosicaoX()) posicaoX += velocidade * delta;
        else if (posicaoX > player.getPosicaoX()) posicaoX -= velocidade * delta;

        if (posicaoY < player.getPosicaoY()) posicaoY += velocidade * delta;
        else if (posicaoY > player.getPosicaoY()) posicaoY -= velocidade * delta;

        atualizarHitbox();
        animacao.atualizar(delta);
    }

    @Override
    public void desenha(SpriteBatch batch) {
        TextureRegion frame = animacao.getFrame();
        batch.draw(frame, posicaoX, posicaoY);
    }

    private void atualizarHitbox() {
        TextureRegion frame = animacao.getFrame();
        float frameWidth = frame.getRegionWidth();
        float frameHeight = frame.getRegionHeight();

        float hitboxX = posicaoX + (frameWidth - hitbox.getWidth()) / 2f;
        float hitboxY = posicaoY + (frameHeight - hitbox.getHeight()) / 2f;

        hitbox.setPosition(hitboxX, hitboxY);
    }
}
