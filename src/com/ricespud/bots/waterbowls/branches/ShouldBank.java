package com.ricespud.bots.waterbowls.branches;

import com.ricespud.bots.waterbowls.leafs.GoToBank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.waterbowls.leafs.ClickFountain;

/**
 * NOTES:
 * Checks if inventory is full of bowls of water.
 */
public class ShouldBank extends BranchTask {

    private GoToBank goToBank = new GoToBank();
    private ClickFountain clickFountain = new ClickFountain();

    @Override
    public boolean validate() {
        return Inventory.getQuantity("Bowl of water") == 28;
    }

    @Override
    public TreeTask failureTask() {
        return clickFountain;
    }

    @Override
    public TreeTask successTask() {
        getLogger().debug("Inventory contains " + Inventory.getQuantity("Bowl of water") + " Bowls of water.");
        return goToBank;
    }
}
