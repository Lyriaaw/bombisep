package com.lyriaaw.bonus;

import com.lyriaaw.Position;
import org.newdawn.slick.Color;

/**
 * Created by lyriaaw on 08/06/17.
 */
public class LessBomb extends Bonus{

    public LessBomb(Position position) {
        super(position, new Color(230, 230, 230), 10000);
    }

    @Override
    public void start() {
        owner.setMaxBombAmount(owner.getMaxBombAmount() - 2);
    }

    @Override
    public void finish() {
        owner.setMaxBombAmount(owner.getMaxBombAmount() + 2);

    }
}
