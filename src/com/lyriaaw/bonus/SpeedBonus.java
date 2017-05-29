package com.lyriaaw.bonus;

import com.lyriaaw.Position;

import org.newdawn.slick.Color;


/**
 * Created by lyriaaw on 21/05/17.
 */
public class SpeedBonus extends Bonus {

    public SpeedBonus(Position position) {
        super(position, Color.red, 10000);
    }

    @Override
    public void start() {
        owner.setSpeed(owner.getSpeed() * 2);
    }

    @Override
    public void finish() {
        owner.setSpeed(owner.getSpeed() / 2);
    }


}
