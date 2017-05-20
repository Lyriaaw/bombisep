package com.lyriaaw;

import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyriaaw on 03/05/17.
 */
public class Game extends BasicGame {

    public Game(String title) {
        super(title);
    }


    private Map map;

    private List<Player> playerList;

    private boolean gameFinished;
    private Player winner;



    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        map = new Map();
        map.generateField();



        Player player1 = new Player(Input.KEY_UP, Input.KEY_DOWN, Input.KEY_LEFT, Input.KEY_RIGHT, Input.KEY_RSHIFT,  new Position(Map.MAP_WIDTH * Map.RATIO - 200, Map.MAP_HEIGHT * Map.RATIO), "Player 1");
        player1.spawn(19, 1, Color.green);

        Player player2 = new Player(Input.KEY_Z, Input.KEY_S, Input.KEY_Q, Input.KEY_D, Input.KEY_LSHIFT, new Position(100, Map.MAP_HEIGHT * Map.RATIO), "Player 2");
        player2.spawn(1, 15, Color.blue);

        playerList = new ArrayList<>();
        playerList.add(player1);
        playerList.add(player2);

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        map.setPlayers(players);

    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        Input input = gameContainer.getInput();

        playerList.forEach(player -> player.manageInputs(input, map));

        map.updateBombs();

        playerList.forEach(player -> {
            if (player.getLifeAmount() == 0) {
                winner = player;
                gameFinished = true;
            }
        });

    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        if (gameFinished) {
            graphics.setColor(Color.white);
            graphics.drawString(winner.getName() + " Won !!", 200, 200);
        } else {
            map.draw(graphics);

            playerList.forEach(player -> player.draw(graphics));
        }


    }
}
