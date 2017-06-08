package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 29/05/17.
 */
public class MorePowerfulBomb extends Bonus {
    public MorePowerfulBomb(Position position) {
        super(position, new Color(20, 20, 20), 5000);
    }

    @Override
    public void start() {
        owner.setBombSize(4);
    }

    @Override
    public void finish() {
        owner.setBombSize(3);

    }
}
