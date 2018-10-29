package com.ricespud.bots.NMZ.branches;

import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.NMZ.branches.AbsorbsLow;
import com.ricespud.bots.NMZ.leafs.ClickOverload;

/**
 * NOTES:
 * 
 */
public class OverloadsActive extends BranchTask {

    private AbsorbsLow absorbsLow = new AbsorbsLow();
    private ClickOverload clickOverload = new ClickOverload();

    //    Varbit overloadDuration = Varbits.load(3955);

    private boolean overloadActive() {
        Varbit varbit = Varbits.load(3955);
        if(varbit == null) {
            getLogger().debug("Varbit [overload duration] was null");
            return false;
        } else {
            return varbit.getValue() > 0;
        }
    }

    @Override
    public boolean validate() {
        return overloadActive();
    }

    @Override
    public TreeTask failureTask() {
        getLogger().debug("Need to click overload.");
        return clickOverload;
    }

    @Override
    public TreeTask successTask() {
        getLogger().debug("Overloads are active.");
        return absorbsLow;
    }
}
