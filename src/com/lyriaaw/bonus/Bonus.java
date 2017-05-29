package com.lyriaaw.bonus;

import com.lyriaaw.Map;
import com.lyriaaw.Player;
import com.lyriaaw.Position;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.Date;

/**
 * Created by lyriaaw on 21/05/17.
 */
public abstract class Bonus {

    private Position position;

    protected Player owner;

    private long dateTaken;
    private long duration;
    private Color color;

    public Bonus(Position position, Color color, long duration) {
        this.position = position;
        this.color = color;
        this.duration = duration;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
        this.dateTaken = new Date().getTime();
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void drawOnMap(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRoundRect(position.getX() * Map.RATIO, position.getY() * Map.RATIO, Map.RATIO, Map.RATIO, Map.RATIO / 5);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRoundRect(position.getX(), position.getY(), 20, 20, 5);
    }

    public void update() {

    }

    public abstract void start();

    public abstract void finish();














}
