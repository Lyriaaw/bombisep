package com.lyriaaw;

import org.newdawn.slick.*;

/**
 * Created by lyriaaw on 03/05/17.
 */
public class Game extends BasicGame {

    public Game(String title) {
        super(title);
    }


    private Map map;

    private Player player1, player2;



    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        map = new Map();
        map.generateField();

        player1 = new Player(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RSHIFT);
        player1.spawn(1, 15, Color.blue);

        player2 = new Player(Input.KEY_Z, Input.KEY_S, Input.KEY_Q, Input.KEY_D, Input.KEY_LSHIFT);
        player2.spawn(19, 1, Color.green);

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();

        player1.manageInputs(input, map);
        player2.manageInputs(input, map);

        map.updateBombs();



    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        map.draw(graphics);

        player1.draw(graphics);
        player2.draw(graphics);

    }
//
//    @Override
//    public void keyPressed(int key, char c) {
//        System.out.println("Pressed : " + key);
//    }
//
//    @Override
//    public void keyReleased(int key, char c) {
//        System.out.println("Released : " + key);
//    }
}
