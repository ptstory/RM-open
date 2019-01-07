package com.ricespud.bots.waterbowls.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks if inventory contains 28 bowls.
 */
public class InventoryContainsBowls extends BranchTask {

    private AtFountain atFountain = new AtFountain();
    private AtBank atBank = new AtBank();

    @Override
    public boolean validate() {
        return Inventory.getQuantity("Bowl") == 28;
    }

    @Override
    public TreeTask failureTask() {
        return atBank;
    }

    @Override
    public TreeTask successTask() {
        return atFountain;
    }
}
