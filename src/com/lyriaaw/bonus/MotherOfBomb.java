package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class MotherOfBomb extends Bonus {

    public MotherOfBomb(Position position) {
        super(position, new Color(11, 97, 11), 10000);
    }

    @Override
    public void start() {
        owner.setSuperBomb(true);
    }

    @Override
    public void finish() {
        owner.setSuperBomb(false);
    }
}
