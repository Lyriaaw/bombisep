package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class SuperBomb extends Bonus {
    public SuperBomb(Position position) {
        super(position, new Color(138, 11, 11), 10000);
    }

    @Override
    public void start() {
        owner.setBombSize(10);
    }

    @Override
    public void finish() {
        owner.setBombSize(3);

    }
}
