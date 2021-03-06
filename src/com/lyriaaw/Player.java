package com.lyriaaw;


import com.lyriaaw.bonus.Bonus;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by lyriaaw on 10/05/17.
 */
public class Player {


    private int x, y;
    private int mapX, mapY;
    private int speed;
    private Color color;

    private int bombAmount, lifeAmount;

    private int size, bombSize;

    private int up, down, left, right, bomb;

    private Position uiPlace;

    private String name;

    private List<Bonus> bonusList;

    private boolean superBomb = false;
    private int maxBombAmount = 3;

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
        this.bombSize = 3;

        this.name = name;

        bonusList = new ArrayList<>();

    }

    public void manageInputs(Input input, Map map) {

        if (input.isKeyDown(up))
            moveUp(map);
        if (input.isKeyDown(down))
            moveDown(map);
        if (input.isKeyDown(left))
            moveLeft(map);
        if (input.isKeyDown(right))
            moveRight(map);

        if (input.isKeyPressed(bomb) && bombAmount > 0) {
            if (bombAmount <= maxBombAmount) map.placeBomb(this);
            bombAmount--;
        }

        this.mapX = x / Map.RATIO;
        this.mapY = y / Map.RATIO;
    }



    public void updateBonues() {
        List<Bonus> finishedBonues = new ArrayList<>();
        bonusList.forEach((bonus) -> {
            if (new Date().getTime() >= bonus.getDateTaken() + bonus.getDuration()) {
                finishedBonues.add(bonus);
                bonus.finish();
            }
        });

        bonusList.removeAll(finishedBonues);
    }



    /*
    Moves functions
    I love Magic numbers
     */
    private void moveRight(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (!map.canWalkHere(mapX + freeSpace, map.getMapRatio(y - (size / 2)), freeSpace))
                obstacleFound = true;
            else if (!map.canWalkHere(mapX + freeSpace, map.getMapRatio(y + (size / 2) - 1), freeSpace))
                obstacleFound = true;
            else
                freeSpace++;
        }

        freeSpace *= Map.RATIO;
        freeSpace += (mapX) * Map.RATIO - (x + size / 2);

        this.x += Math.min(speed, freeSpace);
    }

    private void moveLeft(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (!map.canWalkHere(mapX - freeSpace, map.getMapRatio(y - (size / 2)), freeSpace))
                obstacleFound = true;
            else if (!map.canWalkHere(mapX - freeSpace, map.getMapRatio(y + (size / 2) - 1), freeSpace))
                obstacleFound = true;
            else
                freeSpace++;
        }

        freeSpace *= Map.RATIO;
        freeSpace += (x - size / 2) - (mapX + 1) * Map.RATIO;

        this.x -= Math.min(speed, Math.abs(freeSpace));
    }

    private void moveUp(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (!map.canWalkHere(map.getMapRatio(x - (size / 2)), mapY - freeSpace, freeSpace))
                obstacleFound = true;
            else if (!map.canWalkHere(map.getMapRatio(x + (size / 2) - 1), mapY - freeSpace, freeSpace))
                obstacleFound = true;
            else
                freeSpace++;
        }

        freeSpace *= Map.RATIO;
        freeSpace += (y - size / 2) - ((mapY + 1) * Map.RATIO);

        this.y -= Math.min(speed, Math.abs(freeSpace));
    }

    private void moveDown(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (!map.canWalkHere(map.getMapRatio(x - (size / 2)), mapY + freeSpace, freeSpace))
                obstacleFound = true;
            else if (!map.canWalkHere(map.getMapRatio(x + (size / 2) - 1), mapY + freeSpace, freeSpace))
                obstacleFound = true;
            else
                freeSpace++;
        }

        freeSpace *= Map.RATIO;
        freeSpace += ((mapY) * Map.RATIO) - (y + size / 2);

        this.y += Math.min(speed, Math.abs(freeSpace));
    }



    public void draw(Graphics graphics) {

        graphics.setColor(Color.black);
        graphics.fillRect(x - (size / 2), y - (size / 2), size, size);

        graphics.setColor(color);
        graphics.fillOval(x - (size / 2), y - (size / 2), size, size);

        graphics.setColor(Color.lightGray);
        graphics.fillRect(uiPlace.getX(), uiPlace.getY(), 100, 100);

        graphics.setColor(color);
        graphics.fillRect(uiPlace.getX(), uiPlace.getY() + 95, 100, 5);

        graphics.setColor(Color.red);
        for (int it = 0; it < lifeAmount; it++) {
            graphics.fillRect(uiPlace.getX() + 20 + (it * 20), uiPlace.getY() + 20, 15, 15);
        }

        for (int it = 0; it < bonusList.size(); it++) {
            bonusList.get(it).setPosition(new Position(uiPlace.getX() + 5 + (it * 20), uiPlace.getY() + 50));
            bonusList.get(it).draw(graphics);
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

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void setBonusList(List<Bonus> bonusList) {
        this.bonusList = bonusList;
    }

    public int getBombSize() {
        return bombSize;
    }

    public void setBombSize(int bombSize) {
        this.bombSize = bombSize;
    }

    public void addBonus(Bonus bonus) {
        this.bonusList.add(bonus);
        bonus.setOwner(this);
        bonus.start();
    }

    public boolean isSuperBomb() {
        return superBomb;
    }

    public void setSuperBomb(boolean superBomb) {
        this.superBomb = superBomb;
    }

    public int getMaxBombAmount() {
        return maxBombAmount;
    }

    public void setMaxBombAmount(int maxBombAmount) {
        this.maxBombAmount = maxBombAmount;
    }
}
