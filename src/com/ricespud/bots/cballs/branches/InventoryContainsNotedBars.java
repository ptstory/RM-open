package com.ricespud.bots.cballs.branches;

import com.ricespud.bots.cballs.leafs.WithdrawNotedBars;
import com.runemate.game.api.hybrid.entities.GroundItem;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GroundItems;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks if noted bars are in inventory
 */
public class InventoryContainsNotedBars extends BranchTask {

    private InventoryContains25Bars inventoryContains25Bars = new InventoryContains25Bars();
    private WithdrawNotedBars withdrawNotedBars = new WithdrawNotedBars();

    public boolean validate() {
        if (Inventory.contains(2354)){
            getLogger().debug("We got noted steel bars");
            return true;
        }
        getLogger().debug("We need noted steel bars");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return withdrawNotedBars;
    }

    @Override
    public TreeTask successTask() {
        return inventoryContains25Bars;
    }
}
