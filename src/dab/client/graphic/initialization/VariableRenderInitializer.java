package dab.client.graphic.initialization;

import java.io.IOException;

import dab.client.graphic.drawable.player.PlayerDrawable;
import dab.client.graphic.renderRegistry.VariableRenderRegistry;

public class VariableRenderInitializer {
    
    private static PlayerDrawable playerDrawable;
    
    public static void preInit() throws IOException {
        playerDrawable = new PlayerDrawable("/resource/img/player/player.png");
    }
    
    public static void init() {
        VariableRenderRegistry.register("dab:player", playerDrawable);
    }
    
}