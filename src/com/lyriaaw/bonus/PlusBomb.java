package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class PlusBomb extends Bonus{

    public PlusBomb(Position position) {
        super(position, new Color(100, 100, 100), 10000);
    }

    @Override
    public void start() {
        owner.setMaxBombAmount(owner.getMaxBombAmount() + 2);
    }

    @Override
    public void finish() {
        owner.setMaxBombAmount(owner.getMaxBombAmount() - 2);
    }
}
