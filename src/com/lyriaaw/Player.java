package com.lyriaaw;


import com.lyriaaw.bonus.Bonus;
import org.newdawn.slick.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<Bonus> bonusList;

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

        bonusList = new ArrayList<>();

    }

    public void manageInputs(Input input, Map map) {

        if (input.isKeyDown(up)) {
//            moveV(map, y - (size / 2) - 5, -1);
            moveUp(map);

        }
        if (input.isKeyDown(down)) {
            moveDown(map);

//            moveV(map, y + (size / 2), 1);

        }
        if (input.isKeyDown(left)) {
//            System.out.println(name + " left");
            moveLeft(map);

        }
        if (input.isKeyDown(right)) {
            moveRight(map);
            //moveH(map, x, 1);
        }

        if (input.isKeyPressed(bomb) && bombAmount > 0) {
            if (bombAmount <= 3) map.placeBomb(this);
            bombAmount--;
        }

//        manageBlockOverrun(map);


        this.mapX = x / Map.RATIO;
        this.mapY = y / Map.RATIO;

//        if (this.name.equals("Player 2")) System.out.print(x + " - " + y + " // " + mapX + " - " + mapY + " --> ");
//        System.out.println(size);
    }

    /**
     * So, we will hope that it'll never crash
     * It takes care of the player position on the grid when he has a speed bonus.
     * If the player goes on a solid / breakeable block, this function makes him goes back
     * WE LOVE MAGIC NUMBERS
     *
     * It is currently wordking, please don't try to change it !! 
     */
    public void manageBlockOverrun(Map map) {
        if (!map.canWalkHere(x - (size / 2) + 1, y - (size / 2)) && !map.canWalkHere(x - (size / 2) + 1, y + (size / 2))) {
            x = mapX * Map.RATIO + (size / 2);
        }
        if (!map.canWalkHere(x + (size / 2) - 1, y - (size / 2)) && !map.canWalkHere(x + (size / 2) - 1, y + (size / 2))) {
            x = mapX * Map.RATIO - (size / 2);
        }
        if (!map.canWalkHere(x - (size / 2), y - (size / 2) + 1) && !map.canWalkHere(x + (size / 2), y - (size / 2) +1)) {
            y = mapY * Map.RATIO + (size / 2);
        }
        if (!map.canWalkHere(x - (size / 2), y + (size / 2) - 1) && !map.canWalkHere(x + (size / 2), y + (size / 2) -1)) {
            y = mapY * Map.RATIO - (size / 2);
        }
    }

    public void updateBonues() {
        List<Bonus> finishedBonues = new ArrayList<>();
        bonusList.forEach((bonus) -> {
//            bonus.update();
            if (new Date().getTime() >= bonus.getDateTaken() + bonus.getDuration()) {
                finishedBonues.add(bonus);
                bonus.finish();
            }
        });

        bonusList.removeAll(finishedBonues);
    }

    private void moveRight(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (map.getBlockAt(mapX + freeSpace, map.getMapRatio(y - (size / 2))).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else if (map.getBlockAt(mapX + freeSpace, map.getMapRatio(y + (size / 2) - 1)).getType() != BlockType.EMPTY)
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
            if (map.getBlockAt(mapX - freeSpace, map.getMapRatio(y - (size / 2))).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else if (map.getBlockAt(mapX - freeSpace, map.getMapRatio(y + (size / 2) - 1)).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else
                freeSpace++;

        }

        freeSpace *= Map.RATIO;
        freeSpace += (x - size / 2) - (mapX + 1) * Map.RATIO;

        System.out.println(freeSpace);

        this.x -= Math.min(speed, Math.abs(freeSpace));
    }

    private void moveUp(Map map) {
        int freeSpace = 0;
        boolean obstacleFound = false;

        while  (!obstacleFound) {
            if (map.getBlockAt(map.getMapRatio(x - (size / 2)), mapY - freeSpace).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else if (map.getBlockAt(map.getMapRatio(x + (size / 2) - 1), mapY - freeSpace).getType() != BlockType.EMPTY)
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
            if (map.getBlockAt(map.getMapRatio(x - (size / 2)), mapY + freeSpace).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else if (map.getBlockAt(map.getMapRatio(x + (size / 2) - 1), mapY + freeSpace).getType() != BlockType.EMPTY)
                obstacleFound = true;
            else
                freeSpace++;

        }

        freeSpace *= Map.RATIO;
        freeSpace += ((mapY) * Map.RATIO) - (y + size / 2);

        System.out.println(freeSpace);
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

    public void addBonus(Bonus bonus) {
        this.bonusList.add(bonus);
        bonus.setOwner(this);
        bonus.start();
    }
}
