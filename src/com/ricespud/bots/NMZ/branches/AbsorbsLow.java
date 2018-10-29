package com.ricespud.bots.NMZ.branches;

import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import java.util.concurrent.ThreadLocalRandom;

import com.ricespud.bots.NMZ.branches.IsOneHP;
import com.ricespud.bots.NMZ.leafs.SpamAbsorbs;

/**
 * NOTES:
 * 
 */
public class AbsorbsLow extends BranchTask {

    private IsOneHP isOneHP = new IsOneHP();
    private SpamAbsorbs spamAbsorbs = new SpamAbsorbs();

    private int getAbsorptionPoints () {
        Varbit varbit = Varbits.load(3956);
        if(varbit == null) {
            getLogger().debug("Varbit [absorps] was null");
            return -1;
        } else {
            return varbit.getValue();
        }
    }

    private int absorptionPoints = getAbsorptionPoints();
    private int randomNum = ThreadLocalRandom.current().nextInt(205, 550);

    @Override
    public boolean validate() {

        if (!Inventory.containsAnyOf(11734, 11735, 11736, 11737)){
            getLogger().debug("No more absorp pots in inventory");
            return true;
        }

        absorptionPoints = getAbsorptionPoints();
        randomNum = ThreadLocalRandom.current().nextInt(205, 550);
        if (absorptionPoints <= randomNum){
            getLogger().debug("Absorption points are low. We need to spam absorption pots.");
            return false;
        } else {
            getLogger().debug("We are not low on absorption points");
            return true;
        }
    }

    @Override
    public TreeTask failureTask() {
        return spamAbsorbs;
    }

    @Override
    public TreeTask successTask() {
        return isOneHP;
    }
}
