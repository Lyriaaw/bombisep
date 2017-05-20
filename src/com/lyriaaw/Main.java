package com.lyriaaw;

import com.sun.imageio.spi.RAFImageInputStreamSpi;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;

    public static void main(String[] args) {

        try {
            AppGameContainer appGameContainer = new AppGameContainer(new Game("Bomb'ISEP"));
            appGameContainer.setDisplayMode(Map.MAP_WIDTH * Map.RATIO, Map.MAP_HEIGHT * Map.RATIO, false);
            appGameContainer.setTargetFrameRate(60);
            appGameContainer.setShowFPS(false);
            appGameContainer.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }


        // write your code here
    }
}
