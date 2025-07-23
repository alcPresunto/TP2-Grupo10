package com.jogo.ActRaiser.modelos.mapas;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MapaSegundaFase extends MapaBase {

    @Override
    protected void carregarMapa() {
        mapa = new TmxMapLoader().load("assets/mapas/Mapa2.tmx");
    }

    @Override
    public float getLarguraMapa() {
        MapProperties propriedades = mapa.getProperties();
        int quantidadeDeTilesNaHorizontal = propriedades.get("width", Integer.class);
        int larguraDeCadaTile = propriedades.get("tilewidth", Integer.class);
        return quantidadeDeTilesNaHorizontal * larguraDeCadaTile;
    }

    @Override
    public float getAlturaMapa() {
        MapProperties propriedades = mapa.getProperties();
        int quantidadeDeTilesNaVertical = propriedades.get("height", Integer.class);
        int alturaDeCadaTile = propriedades.get("tileheight", Integer.class);
        return quantidadeDeTilesNaVertical * alturaDeCadaTile;
    }
}
