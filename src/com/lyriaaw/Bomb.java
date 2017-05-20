package com.lyriaaw;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lyriaaw on 20/05/17.
 */
public class Bomb {

    private int x, y;

    private long timePlaced;

    private Player owner;

    private boolean isExploding;

    private List<Position> areaOfEffects;

    public Bomb(Player owner) {
        this.owner = owner;
        this.x = owner.getMapX();
        this.y = owner.getMapY();
        this.timePlaced = new Date().getTime();
        this.isExploding = false;
    }

    public Bomb(int x, int y, Player owner) {
        this.x = x;
        this.y = y;
        this.owner = owner;
        this.timePlaced = new Date().getTime();
        this.isExploding = false;
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


    public void draw(Graphics graphics) {
        if (isExploding) {
            graphics.setColor(Color.black);
        } else {
            graphics.setColor(Color.red);
        }
        graphics.fillOval(x * Map.RATIO, y * Map.RATIO, Map.RATIO, Map.RATIO);
    }

    public void explode(Map map) {
        isExploding = true;
        areaOfEffects = generateAOE(map);

        areaOfEffects.forEach(map::destroyBlock);



    }

    private List<Position> generateAOE(Map map) {
        List<Position> aoe = new ArrayList<>();


        for (int it = 1; it <= 3; it++) {
            if (map.getBlockAt(x + it, y).getType() == BlockType.SOLID) break;
            aoe.add(new Position(x + it, y));
        }

        for (int it = 1; it <= 3; it++) {
            if (map.getBlockAt(x - it, y).getType() == BlockType.SOLID) break;
            aoe.add(new Position(x - it, y));
        }

        for (int it = 1; it <= 3; it++) {
            if (map.getBlockAt(x, y + it).getType() == BlockType.SOLID) break;
            aoe.add(new Position(x, y + it));
        }

        for (int it = 1; it <= 3; it++) {
            if (map.getBlockAt(x, y - it).getType() == BlockType.SOLID) break;
            aoe.add(new Position(x, y - it));
        }






        return aoe;
    }



}
