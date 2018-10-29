package com.ricespud.bots.NMZ.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.NMZ.branches.ShouldFlickQuickPrayer;
import com.ricespud.bots.NMZ.leafs.RockcakeToOneHP;

/**
 * NOTES:
 * 
 */
public class IsOneHP extends BranchTask {

    private ShouldFlickQuickPrayer shouldFlickQuickPrayer = new ShouldFlickQuickPrayer();
    private RockcakeToOneHP rockcakeToOneHP = new RockcakeToOneHP();

    @Override
    public boolean validate() {
        if(Health.getCurrent() != 1){
            getLogger().debug("Our health is not one. Need to rock cake.");
            Execution.delay(6000, 10000);
            return false;
        } else {
            getLogger().debug("Our health is 1. All is well.");
            return true;
        }
    }

    @Override
    public TreeTask failureTask() {
        return rockcakeToOneHP;
    }

    @Override
    public TreeTask successTask() {
        return shouldFlickQuickPrayer;
    }
}
