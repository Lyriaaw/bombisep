package com.lyriaaw;

import com.lyriaaw.bonus.BombImproveBonus;
import com.lyriaaw.bonus.Bonus;
import com.lyriaaw.bonus.SlowBonus;
import com.lyriaaw.bonus.SpeedBonus;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * Created by lyriaaw on 03/05/17.
 */
public class Map {

    public static final int MAP_WIDTH = 21;
    public static final int MAP_HEIGHT = 17;

    public static final int RATIO = 30;

    private Block[][] field;

    private List<Bomb> bombs;

    private List<Player> players;

    private List<Bonus> bonusList;

    private Random random;

    public Map() {
        field = new Block[17][21];
        bombs = new ArrayList<>();
        players = new ArrayList<>();
        bonusList = new ArrayList<>();

        random = new Random();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void draw(Graphics graphics) {
        for (Block[] line : field) {
            for (Block currentBlock : line) {

                switch(currentBlock.getType()) {
                    case SOLID :
                        graphics.setColor(Color.gray);
                        break;
                    case BREAKABLE:
                        graphics.setColor(Color.orange);
                        break;
                    case BOMB :
                        graphics.setColor(Color.red);
                        break;
                    default :
                        graphics.setColor(Color.green);
                        break;

                }

                graphics.fillRect(currentBlock.getX() * RATIO, currentBlock.getY() * RATIO, RATIO, RATIO);
            }
        }

        bombs.forEach((bomb) -> {
            bomb.draw(graphics);
        });

        bonusList.forEach(bonus -> bonus.drawOnMap(graphics));
    }


    public Block getBlockAt(int x, int y) {
        return field[y][x];
    }

    public boolean canWalkHere(int x, int y) {
        int mapX = getMapRatio(x);
        int mapY = getMapRatio(y);


        /*
         * Let 500 milisecond to player to escape the bomb place
         */
        long currentTime = new Date().getTime();
        for (Bomb bomb : bombs) {
            if (bomb.getX() != mapX || bomb.getY() != mapY) continue;
            if (currentTime > bomb.getTimePlaced() + 500) return false;
        }


        return (field[mapY][mapX].getType() == BlockType.EMPTY);
    }

    public int getMapRatio(int value) {
        return value / Map.RATIO;
    }


    public boolean placeBomb(Player player) {
        Bomb bomb = new Bomb(player);
        bombs.add(bomb);


        return true;
    }

    public void update() {
        List<Bonus> bonusAttribued = new ArrayList<>();
        bonusList.forEach((bonus) -> {
            players.forEach((player) -> {
                if (bonus.getPosition().getX() == player.getMapX() && bonus.getPosition().getY() == player.getMapY() && player.getBonusList().size() < 4) {
                    player.addBonus(bonus);
                    bonusAttribued.add(bonus);
                }
            });
        });
        bonusList.removeAll(bonusAttribued);



    }

    public void updateBombs() {
        List<Bomb> bombToDelete = new ArrayList<>();
        final long currentTime = new Date().getTime();
        bombs.forEach((bomb) -> {
            if (currentTime >= (bomb.getTimePlaced() + 5000) && !bomb.isExploding()) {
                bomb.explode(this);
            }
            if (currentTime >= (bomb.getTimePlaced() + 5500)) {
               bombToDelete.add(bomb);
            }
        });

        bombs.removeAll(bombToDelete);
    }

    public void destroyBlock(Position position) {
        Block block = getBlockAt(position.getX(), position.getY());
        if (block.getType() == BlockType.BREAKABLE) {
            block.setType(BlockType.EMPTY);

            generateBonus(position);

        }

        players.forEach((player) -> {
            if (player.getMapX() == position.getX() && player.getMapY() == position.getY()) player.hurt();
        });
        bombs.forEach((bomb) -> {
            if (bomb.getX() == position.getX() && bomb.getY() == position.getY() && !bomb.isExploding()) bomb.explode(this);
        });


    }

    public void generateBonus(Position position) {
        int randValue = random.nextInt(100) + 1;


        if (randValue >= 80) {
            switch (random.nextInt(3) + 1) {
                case 1 :
                    bonusList.add(new BombImproveBonus(position));
                    break;
                case 2 :
                    bonusList.add(new SlowBonus(position));
                    break;
                case 3 :
                    bonusList.add(new SpeedBonus(position));
                    break;

            }

        }

    }
































    /**
     * Map Generation
     */

    public void generateField() {
        for (int it = 0; it < MAP_HEIGHT; it++) {
            for (int jt = 0; jt < MAP_WIDTH; jt++) {

                Block block = new Block(jt, it);

                if (it == 0 || it == MAP_HEIGHT - 1 || jt == 0 || jt == MAP_WIDTH - 1) {
                    block.setType(BlockType.SOLID);
                } else if (it % 2 == 0 && jt % 2 == 0) {
                    block.setType(BlockType.SOLID);
                } else if (existInEmptyBlocks(jt, it)) {
                    block.setType(BlockType.EMPTY);
                } else {
                    block.setType(BlockType.BREAKABLE);
                }
                field[it][jt] = block;
            }
        }
    }

    private static List<Integer[]> getAlreadyEmptyBlocks() {
        List<Integer[]> blankBlocks = new ArrayList<>();

        // bottom left
        blankBlocks.add(makeArray(1, 13));
        blankBlocks.add(makeArray(1, 14));
        blankBlocks.add(makeArray(1, 15));
        blankBlocks.add(makeArray(2, 13));
        blankBlocks.add(makeArray(2, 14));
        blankBlocks.add(makeArray(2, 15));
        blankBlocks.add(makeArray(3, 14));
        blankBlocks.add(makeArray(3, 15));

        // top right
        blankBlocks.add(makeArray(17, 1));
        blankBlocks.add(makeArray(17, 2));
        blankBlocks.add(makeArray(18, 1));
        blankBlocks.add(makeArray(18, 2));
        blankBlocks.add(makeArray(18, 3));
        blankBlocks.add(makeArray(19, 1));
        blankBlocks.add(makeArray(19, 2));
        blankBlocks.add(makeArray(19, 3));




        return blankBlocks;
    }

    private static Integer[] makeArray(int x, int y) {
        return new Integer[]{x, y};
    }

    private boolean existInEmptyBlocks(int x, int y) {
       return getAlreadyEmptyBlocks().stream().anyMatch(value -> (value[0] == x && value[1] == y));
    }


}
