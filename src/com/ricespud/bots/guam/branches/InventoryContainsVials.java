package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.WithdrawVials;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if inventory contains 14 vials of water
 */
public class InventoryContainsVials extends BranchTask {

    private InventoryContainsGuam inventoryContainsGuam = new InventoryContainsGuam();
    private WithdrawVials withdrawVials = new WithdrawVials();

    @Override
    public boolean validate() {
        if (Inventory.getQuantity("Vial of water") == 14){
            getLogger().debug("we got vials");
            return true;
        }

        getLogger().debug("we need vials");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return withdrawVials;
    }

    @Override
    public TreeTask successTask() {
        return inventoryContainsGuam;
    }
}
