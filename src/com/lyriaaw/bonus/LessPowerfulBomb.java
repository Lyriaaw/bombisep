package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class LessPowerfulBomb extends Bonus {

    public LessPowerfulBomb(Position position) {
        super(position, new Color(169, 188, 245), 10000);
    }

    @Override
    public void start() {
        owner.setBombSize(2);
    }

    @Override
    public void finish() {
        owner.setBombSize(3);
    }
}
