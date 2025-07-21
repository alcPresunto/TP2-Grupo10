package com.jogo.ActRaiser.modelos.mapas;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapaPrimeiraFase extends MapaBase {

    @Override
    protected void carregarMapa() {
        mapa = new TmxMapLoader().load("Mapa1.tmx");
    }

    @Override
    public float getLarguraMapa() {
        MapProperties prop = mapa.getProperties();
        int width = prop.get("width", Integer.class);
        int tileWidth = prop.get("tilewidth", Integer.class);
        return width * tileWidth;
    }

    @Override
    public float getAlturaMapa() {
        MapProperties prop = mapa.getProperties();
        int height = prop.get("height", Integer.class);
        int tileHeight = prop.get("tileheight", Integer.class);
        return height * tileHeight;
    }
}
