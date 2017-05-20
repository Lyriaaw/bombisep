package com.lyriaaw;


import org.newdawn.slick.*;

/**
 * Created by lyriaaw on 10/05/17.
 */
public class Player {


    private int x, y;
    private int mapX, mapY;
    private int speed;
    private Color color;

    private int bombAmount;

    private int size;

    private int up, down, left, right, bomb;

    public Player(int up, int down, int left, int right, int bomb) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bomb = bomb;

        this.speed = 5;
        this.size = Map.RATIO - 10;
        this.bombAmount = 0;
    }

    public void manageInputs(Input input, Map map) {

        if (input.isKeyDown(up)) {
            moveV(map, y - (size / 2) - 5, -1);

        }
        if (input.isKeyDown(down)) {

            moveV(map, y + (size / 2), 1);

        }
        if (input.isKeyDown(left)) {
            moveH(map, x - (size / 2) - 5, -1);

        }
        if (input.isKeyDown(right)) {
            moveH(map, x + (size / 2), 1);
        }

        if (input.isKeyPressed(bomb)) {
            if (bombAmount <= 3) map.placeBomb(this);
        }


        this.mapX = x / Map.RATIO;
        this.mapY = y / Map.RATIO;

        //System.out.println(x + " - " + y + " // " + mapX + " - " + mapY);
    }

    private void moveH(Map map, int x, int dir) {
        if (map.canWalkHere(x, y - (size / 2)) && map.canWalkHere(x, y + (size / 2) - 1)) {
            this.x += dir * speed;
        }
    }

    private void moveV(Map map, int y, int dir) {
        if (map.canWalkHere(x - (size / 2), y) && map.canWalkHere(x + (size / 2) - 1, y)) {
            this.y += dir * speed;
        }
    }

    public void draw(Graphics graphics) {

        graphics.setColor(Color.black);
        graphics.fillRect(x - (size / 2), y - (size / 2), size, size);

        graphics.setColor(color);
        graphics.fillOval(x - (size / 2), y - (size / 2), size, size);

    }

    public void spawn(int mapX, int mapY, Color color) {
        this.setMapX(mapX);
        this.setMapY(mapY);
        this.color = color;
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

    public int getMapX() {
        return mapX;
    }

    public void setMapX(int mapX) {
        this.mapX = mapX;
        this.x = (mapX * Map.RATIO) + Map.RATIO / 2;
    }

    public int getMapY() {
        return mapY;
    }

    public void setMapY(int mapY) {
        this.mapY = mapY;
        this.y = mapY * Map.RATIO + Map.RATIO / 2;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }


}
