package com.lyriaaw;

/**
 *
 * Created by lyriaaw on 03/05/17.
 */
public class Block {

    private BlockType type;
    private int x, y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BlockType getType() {
        return type;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
