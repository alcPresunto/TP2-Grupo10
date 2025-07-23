package com.jogo.ActRaiser.logica;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class ControladorDeSons {
    public static final AssetManager assetManager = new AssetManager();

    public static void carregarSons() {
        assetManager.load("assets/sounds/trilhaSonora.mp3", Music.class);
        assetManager.load("assets/sounds/telaVitoria.mp3", Music.class);
        assetManager.load("assets/sounds/telaDerrota.wav", Music.class);

        assetManager.load("assets/sounds/danoNoInimigo.wav", Sound.class);
        assetManager.load("assets/sounds/danoNoPlayer.wav", Sound.class);
        assetManager.load("assets/sounds/morteInimigo.wav", Sound.class);
        assetManager.load("assets/sounds/playerMorte.mp3", Sound.class);
        assetManager.load("assets/sounds/vidaExtra.mp3", Sound.class);
    }

    public static void finalizarCarregamento() {
        assetManager.finishLoading();
    }

    // Sons
    public static Sound getDanoNoInimigoSound() {
        return assetManager.get("assets/sounds/danoNoInimigo.wav", Sound.class);
    }

    public static Sound getDanoNoPlayerSound() {
        return assetManager.get("assets/sounds/danoNoPlayer.wav", Sound.class);
    }

    public static Sound getMorteInimigoSound() {
        return assetManager.get("assets/sounds/morteInimigo.wav", Sound.class);
    }

    public static Sound getPlayerMorteSound() {
        return assetManager.get("assets/sounds/playerMorte.mp3", Sound.class);
    }

    public static Sound getVidaExtraSound() {
        return assetManager.get("assets/sounds/vidaExtra.mp3", Sound.class);
    }

    // Músicas
    public static Music getTrilhaSonoraMusic() {
        return assetManager.get("assets/sounds/trilhaSonora.mp3", Music.class);
    }

    public static Music getTelaVitoriaMusic() {
        return assetManager.get("assets/sounds/telaVitoria.mp3", Music.class);
    }

    public static Music getTelaDerrotaMusic() {
        return assetManager.get("assets/sounds/telaDerrota.wav", Music.class);
    }

    public static void dispose() {
        assetManager.dispose();
    }
}
