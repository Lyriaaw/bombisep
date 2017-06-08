package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class Life extends Bonus {

    public Life(Position position) {
        super(position, new Color(247, 129, 216), 1);
    }

    @Override
    public void start() {
        owner.setLifeAmount(owner.getLifeAmount() + 1);
    }

    @Override
    public void finish() {

    }
}
