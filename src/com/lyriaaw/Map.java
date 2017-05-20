package com.lyriaaw;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

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

    public Map() {
        field = new Block[17][21];
        bombs = new ArrayList<>();
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
    }


    public Block getBlockAt(int x, int y) {
        return field[y][x];
    }

    public boolean canWalkHere(int x, int y) {
        int mapX = getMapRatio(x);
        int mapY = getMapRatio(y);

        //System.out.println(x + " - " + y + " // " + mapX + " - " + mapY + " --> " + (field[mapY][mapX].getType() != BlockType.SOLID));

        return (field[mapY][mapX].getType() == BlockType.EMPTY);
    }

    private int getMapRatio(int value) {
        return value / Map.RATIO;
    }


    public boolean placeBomb(Player player) {
        Bomb bomb = new Bomb(player);

        return true;
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
