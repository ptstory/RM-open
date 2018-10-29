package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.ThreadLocalRandom;

/**
 * NOTES:
 * 
 */
public class SpamAbsorbs extends LeafTask {

    private SpriteItem absorptionPotion = Inventory.newQuery().names("Absorption (1)", "Absorption (2)", "Absorption (3)", "Absorption (4)").results().first();

//    private int getAbsorptionPoints () {
//        Varbit varbit = Varbits.load(3956);
//        if(varbit == null) {
//            getLogger().debug("Varbit [absorps] was null");
//            return -1;
//        } else {
//            return varbit.getValue();
//        }
//    }

//    private int absorptionPoints = getAbsorptionPoints();
    private int randomNum = ThreadLocalRandom.current().nextInt(4, 11);


    @Override
    public void execute() {
//        if(absorptionPoints <= randomNum) {
//            if (absorptionPotion != null) {
//                if (absorptionPotion.isValid()) {
//                    absorptionPotion.interact("Drink");
//                    Execution.delayUntil(() -> absorptionPotion.interact("Drink"), 500, 900);
//                }
//            }
//        }

//        for (int i = 0; i < randomNum && absorptionPotion.isValid(); i++) {
//        for (int i = 0; i < 5 && absorptionPotion.isValid(); i++) {
//            getLogger().debug("Clicking absorption pot");
//            absorptionPotion.click();
//        }
//        Execution.delay(300, 800);

        for (int i = 0; i < randomNum; i++) {
            SpriteItem pot = Inventory.newQuery().ids(11734, 11735, 11736, 11737).results().first();

            if (pot == null)
                break;

            Execution.delayWhile(Mouse::isMoving, 300, 1500);
            getLogger().debug("Drinking absorption");
            pot.interact("Drink");


        }
    }
}
