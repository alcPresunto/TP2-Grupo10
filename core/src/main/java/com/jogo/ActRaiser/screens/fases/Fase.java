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
import com.jogo.ActRaiser.screens.HUD;
import com.jogo.ActRaiser.screens.TelaDerrota;
import com.jogo.ActRaiser.logica.CriadorDePersonagens;
import com.jogo.ActRaiser.logica.GerenciadorDeColisoes;

public abstract class Fase implements Screen {
    protected final GameRunner gameRunner;

    protected MapaBase mapa;
    protected OrthographicCamera camera;
    protected HUD hud;

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
        camera = new OrthographicCamera();
        hud = new HUD();

        criadorDePersonagens = new CriadorDePersonagens();
        gerenciadorDeColisoes = new GerenciadorDeColisoes();

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
        if (player == null || camera == null)
            return;

        float cameraX = calculaPosicaoCameraX();
        float cameraY = calculaPosicaoCameraY();

        camera.position.set(cameraX, cameraY, 0);
        camera.update();
    }

    protected float calculaPosicaoCameraX() {
        if (player == null || mapa == null)
            return 0;

        TextureRegion frame = player.getFrameAtual();
        if (frame == null)
            return 0;

        float posicaoX = player.getPosicaoX() + frame.getRegionWidth() / 2f;
        float limiteMinimo = camera.viewportWidth / 2f;
        float limiteMaximo = mapa.getLarguraMapa() - limiteMinimo;

        return Math.max(limiteMinimo, Math.min(posicaoX, limiteMaximo));
    }

    protected float calculaPosicaoCameraY() {
        if (player == null || mapa == null)
            return 0;

        TextureRegion frame = player.getFrameAtual();
        if (frame == null)
            return 0;

        float posicaoY = player.getPosicaoY() + frame.getRegionHeight() / 2f;
        float limiteMinimo = camera.viewportHeight / 2f;
        float limiteMaximo = mapa.getAlturaMapa() - limiteMinimo;

        return Math.max(limiteMinimo, Math.min(posicaoY, limiteMaximo));
    }

    protected void atualizaPersonagens() {
        if (personagens == null)
            return;

        for (Personagem personagem : personagens) {
            if (personagem != null) {
                personagem.atualiza();
                limitarPosicaoNoMapa(personagem);
            }
        }
    }

    protected void removerInimigosMortos() {
        if (inimigos == null || personagens == null)
            return;

        inimigos.removeIf(inimigo -> inimigo == null || !inimigo.estaAtivo());
        personagens.removeIf(personagem -> personagem instanceof Inimigo &&
                (!((Inimigo) personagem).estaAtivo()));
    }

    protected void limitarPosicaoNoMapa(Personagem personagem) {
        if (personagem == null || mapa == null)
            return;

        float posicaoX = personagem.getPosicaoX();
        float posicaoY = personagem.getPosicaoY();
        float larguraPersonagem = personagem.getHitbox() != null ? personagem.getHitbox().getWidth() : 0;
        float alturaPersonagem = personagem.getHitbox() != null ? personagem.getHitbox().getHeight() : 0;

        if (larguraPersonagem <= 0 || alturaPersonagem <= 0)
            return;

        personagem.setPosicaoX(Math.max(0, Math.min(posicaoX, mapa.getLarguraMapa() - larguraPersonagem)));
        personagem.setPosicaoY(Math.max(0, Math.min(posicaoY, mapa.getAlturaMapa() - alturaPersonagem)));
    }

    protected void atualizaColisoes() {
        if (player == null || inimigos == null || gerenciadorDeColisoes == null)
            return;

        for (Inimigo inimigo : inimigos) {
            if (inimigo != null) {
                gerenciadorDeColisoes.verificarColisaoJogadorComObjeto(player, inimigo);
                gerenciadorDeColisoes.verificarColisaoProjetilComInimigo(player, inimigo);
            }
        }
    }

    protected void desenhaPersonagens(SpriteBatch batch) {
        if (personagens == null || batch == null)
            return;

        for (Personagem personagem : personagens) {
            if (personagem != null) {
                personagem.desenha(batch);
            }
        }
    }

    protected void desenhaHitboxes() {
        if (shapeRenderer == null || camera == null || player == null || inimigos == null)
            return;

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);

        desenhaHitbox(player);

        for (Personagem inimigo : inimigos) {
            if (inimigo != null) {
                desenhaHitbox(inimigo);
            }
        }

        shapeRenderer.end();
    }

    private void desenhaHitbox(Personagem personagem) {
        if (personagem == null || personagem.getHitbox() == null)
            return;

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
        if (player != null && personagens != null) {
            personagens.add(player);
        }
    }

    @Override
    public void render(float delta) {
        limpaTela();
        atualizaCamera();

        // Renderiza o jogo (com câmera normal)
        if (mapa != null) {
            mapa.render(camera);
        }

        verificaSpawnInimigos(delta);
        atualizaPersonagens();
        atualizaColisoes();
        removerInimigosMortos();

        if (player != null && player.getPontosVida() <= 0) {
            gameRunner.setScreen(new TelaDerrota(gameRunner));
            return;
        }

        // Renderização do mundo do jogo
        if (gameRunner.batch != null) {
            gameRunner.batch.setProjectionMatrix(camera.combined);
            gameRunner.batch.begin();
            desenhaPersonagens(gameRunner.batch);
            if (player != null) {
                player.atualizarProjeteis();
                player.desenharProjeteis(gameRunner.batch);
            }
            gameRunner.batch.end();
        }

        // Renderização da HUD (com câmera fixa)
        if (gameRunner.batch != null) {
            gameRunner.batch.setProjectionMatrix(gameRunner.cameraHUD.combined);
            gameRunner.batch.begin();
            if (hud != null && player != null) {
                hud.desenhar(gameRunner.batch, player);
            }
            gameRunner.batch.end();
        }

        desenhaHitboxes();
    }

    @Override
    public void dispose() {
        if (hud != null)
            hud.dispose();
        if (mapa != null)
            mapa.dispose();
        if (shapeRenderer != null)
            shapeRenderer.dispose();
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
}