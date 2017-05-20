package com.lyriaaw;

import java.util.Date;

/**
 * Created by lyriaaw on 20/05/17.
 */
public class Bomb {

    private int x, y;

    private long timePlaced;

    private Player owner;

    public Bomb(Player owner) {
        this.owner = owner;
        this.x = owner.getMapX();
        this.y = owner.getMapY();
        this.timePlaced = new Date().getTime();
    }

    public Bomb(int x, int y, Player owner) {
        this.x = x;
        this.y = y;
        this.owner = owner;
        this.timePlaced = new Date().getTime();
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

    public long getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(long timePlaced) {
        this.timePlaced = timePlaced;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
