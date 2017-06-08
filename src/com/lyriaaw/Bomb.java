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

    private int size;

    public Bomb(Player owner) {
        this.owner = owner;
        this.size = owner.getBombSize();
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
            graphics.fillOval(x * Map.RATIO, y * Map.RATIO, Map.RATIO, Map.RATIO);

            graphics.setColor(Color.red);
            areaOfEffects.forEach((position) -> {
                graphics.fillRect(position.getX() * Map.RATIO, position.getY() * Map.RATIO, Map.RATIO, Map.RATIO);
            });



        } else {
            graphics.setColor(Color.red);
            graphics.fillOval(x * Map.RATIO, y * Map.RATIO, Map.RATIO, Map.RATIO);
        }
    }

    public void explode(Map map) {
        isExploding = true;
        areaOfEffects = generateAOE(map);

        areaOfEffects.forEach(map::destroyBlock);
        owner.setBombAmount(owner.getBombAmount() + 1);
    }

    private List<Position> generateAOE(Map map) {
        List<Position> aoe = new ArrayList<>();

        aoe.add(new Position(x, y));


        for (int it = 1; it <= size; it++) {
            if ( x + it == Map.MAP_WIDTH || map.getBlockAt(x + it, y).getType() == BlockType.SOLID && !owner.isSuperBomb()) break;  // If the block is solid, we stop the explosion propagation on this line
            aoe.add(new Position(x + it, y));
            if (map.getBlockAt(x + it, y).getType() == BlockType.BREAKABLE) break;  // If the block is solid, we stop the explosion propagation on this line

        }

        for (int it = 1; it <= size; it++) {
            if (x + it == 0 || map.getBlockAt(x - it, y).getType() == BlockType.SOLID && !owner.isSuperBomb()) break;
            aoe.add(new Position(x - it, y));
            if (map.getBlockAt(x - it, y).getType() == BlockType.BREAKABLE) break;  // If the block is solid, we stop the explosion propagation on this line
        }

        for (int it = 1; it <= size; it++) {
            if (y + it == Map.MAP_HEIGHT || map.getBlockAt(x, y + it).getType() == BlockType.SOLID && !owner.isSuperBomb()) break;
            aoe.add(new Position(x, y + it));
            if (map.getBlockAt(x, y + it).getType() == BlockType.BREAKABLE) break;  // If the block is solid, we stop the explosion propagation on this line
        }

        for (int it = 1; it <= size; it++) {
            if (y - it == 0 || map.getBlockAt(x, y - it).getType() == BlockType.SOLID && !owner.isSuperBomb()) break;
            aoe.add(new Position(x, y - it));
            if (map.getBlockAt(x, y - it).getType() == BlockType.BREAKABLE) break;  // If the block is solid, we stop the explosion propagation on this line
        }

        return aoe;
    }


    public boolean isExploding() {
        return isExploding;
    }

    public void setExploding(boolean exploding) {
        isExploding = exploding;
    }

    public List<Position> getAreaOfEffects() {
        return areaOfEffects;
    }

    public void setAreaOfEffects(List<Position> areaOfEffects) {
        this.areaOfEffects = areaOfEffects;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
