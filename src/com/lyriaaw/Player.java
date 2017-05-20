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

    private int bombAmount, lifeAmount;

    private int size;

    private int up, down, left, right, bomb;

    private Position uiPlace;

    private String name;

    public Player(int up, int down, int left, int right, int bomb, Position uiPLace, String name) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.bomb = bomb;

        this.uiPlace = uiPLace;

        this.speed = 5;
        this.size = Map.RATIO - 10;
        this.bombAmount = 3;
        this.lifeAmount = 3;

        this.name = name;

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

        if (input.isKeyPressed(bomb) && bombAmount > 0) {
            if (bombAmount <= 3) map.placeBomb(this);
            bombAmount--;
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




        graphics.setColor(Color.lightGray);
        graphics.fillRect(uiPlace.getX(), uiPlace.getY(), 100, 50);

        graphics.setColor(color);
        graphics.fillRect(uiPlace.getX(), uiPlace.getY() + 45, 100, 5);

        graphics.setColor(Color.red);
        for (int it = 0; it < lifeAmount; it++) {
            graphics.fillRect(uiPlace.getX() + 20 + (it * 20), uiPlace.getY() + 20, 15, 15);
        }



    }

    public void spawn(int mapX, int mapY, Color color) {
        this.setMapX(mapX);
        this.setMapY(mapY);
        this.color = color;
    }

    public void hurt() {
        lifeAmount--;
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

    public int getLifeAmount() {
        return lifeAmount;
    }

    public void setLifeAmount(int lifeAmount) {
        this.lifeAmount = lifeAmount;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getBombAmount() {
        return bombAmount;
    }

    public void setBombAmount(int bombAmount) {
        this.bombAmount = bombAmount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getBomb() {
        return bomb;
    }

    public void setBomb(int bomb) {
        this.bomb = bomb;
    }

    public Position getUiPlace() {
        return uiPlace;
    }

    public void setUiPlace(Position uiPlace) {
        this.uiPlace = uiPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
