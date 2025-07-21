package com.jogo.ActRaiser.screens.fases;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jogo.ActRaiser.GameRunner;
import com.jogo.ActRaiser.modelos.mapas.MapaBase;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.Personagem;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.inimigos.Inimigo;
import com.jogo.ActRaiser.modelos.objetos.moveis.personagens.player.Player;
import com.jogo.ActRaiser.logica.CriadorDePersonagens;
import com.jogo.ActRaiser.logica.GerenciadorDeColisoes;

public abstract class Fase implements Screen {

    protected final GameRunner gameRunner;

    protected MapaBase mapa;
    protected OrthographicCamera camera;

    protected CriadorDePersonagens criadorDePersonagens;
    protected GerenciadorDeColisoes gerenciadorDeColisoes;

    protected ArrayList<Personagem> personagens;
    protected ArrayList<Inimigo> inimigos;
    protected Player player;

    protected ShapeRenderer shapeRenderer;

    public Fase(GameRunner gameRunner) {
        this.gameRunner = gameRunner;
    }

    protected void inicializaBase() {
        mapa = criaMapa();
        criadorDePersonagens = new CriadorDePersonagens();
        gerenciadorDeColisoes = new GerenciadorDeColisoes();

        camera = new OrthographicCamera();
        personagens = new ArrayList<>();
        inimigos = new ArrayList<>();

        shapeRenderer = new ShapeRenderer();
    }

    protected abstract MapaBase criaMapa();

    protected abstract Player criaPlayer();

    protected abstract void verificaSpawnInimigos(float delta);

    protected void configuraCamera() {
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
    }

    protected void atualizaCamera() {
        float cameraX = calculaPosicaoCameraX();
        float cameraY = calculaPosicaoCameraY();

        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }

    protected float calculaPosicaoCameraX() {
        TextureRegion frame = player.getFrameAtual();
        float posicaoX = player.getPosicaoX() + frame.getRegionWidth() / 2f;

        float limiteMinimo = camera.viewportWidth / 2f;
        float limiteMaximo = mapa.getLarguraMapa() - limiteMinimo;

        if (posicaoX < limiteMinimo)
            return limiteMinimo;
        if (posicaoX > limiteMaximo)
            return limiteMaximo;
        return posicaoX;
    }

    protected float calculaPosicaoCameraY() {
        TextureRegion frame = player.getFrameAtual();
        float posicaoY = player.getPosicaoY() + frame.getRegionHeight() / 2f;

        float limiteMinimo = camera.viewportHeight / 2f;
        float limiteMaximo = mapa.getAlturaMapa() - limiteMinimo;

        if (posicaoY < limiteMinimo)
            return limiteMinimo;
        if (posicaoY > limiteMaximo)
            return limiteMaximo;
        return posicaoY;
    }

    protected void atualizaPersonagens() {
        for (Personagem personagem : personagens) {
            personagem.atualiza();
            limitarPosicaoNoMapa(personagem);
        }
    }

    protected void limitarPosicaoNoMapa(Personagem personagem) {
        float posicaoX = personagem.getPosicaoX();
        float posicaoY = personagem.getPosicaoY();

        float larguraMapa = mapa.getLarguraMapa();
        float alturaMapa = mapa.getAlturaMapa();

        float larguraPersonagem = personagem.getHitbox().getWidth();
        float alturaPersonagem = personagem.getHitbox().getHeight();

        if (posicaoX < 0)
            posicaoX = 0;
        else if (posicaoX + larguraPersonagem > larguraMapa)
            posicaoX = larguraMapa - larguraPersonagem;

        if (posicaoY < 0)
            posicaoY = 0;
        else if (posicaoY + alturaPersonagem > alturaMapa)
            posicaoY = alturaMapa - alturaPersonagem;

        personagem.setPosicaoX(posicaoX);
        personagem.setPosicaoY(posicaoY);
    }

    protected void atualizaColisoes() {
        for (Inimigo inimigo : inimigos) {
            gerenciadorDeColisoes.verificarColisaoJogadorComObjeto(player, inimigo);
            gerenciadorDeColisoes.verificarColisaoProjetilComInimigo(player, inimigo);
        }
    }

    protected void desenhaPersonagens(SpriteBatch batch) {
        for (Personagem personagem : personagens) {
            personagem.desenha(batch);
        }
    }

    protected void desenhaHitboxes() {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        desenhaHitbox(player);

        for (Personagem inimigo : inimigos) {
            desenhaHitbox(inimigo);
        }

        shapeRenderer.end();
    }

    private void desenhaHitbox(Personagem personagem) {
        shapeRenderer.rect(
                personagem.getHitbox().getX(),
                personagem.getHitbox().getY(),
                personagem.getHitbox().getWidth(),
                personagem.getHitbox().getHeight());
    }

    protected void limpaTela() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void show() {
        inicializaBase();
        configuraCamera();

        player = criaPlayer();
        personagens.add(player);
    }

    @Override
    public void render(float delta) {
        limpaTela();
        atualizaCamera();

        mapa.render(camera);

        verificaSpawnInimigos(delta);

        atualizaPersonagens();
        atualizaColisoes();

        gameRunner.batch.setProjectionMatrix(camera.combined);
        gameRunner.batch.begin();

        desenhaPersonagens(gameRunner.batch);

        player.atualizarProjeteis();
        player.desenharProjeteis(gameRunner.batch);

        gameRunner.batch.end();

        desenhaHitboxes();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
        mapa.dispose();
        shapeRenderer.dispose();
    }
}
