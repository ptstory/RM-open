package com.ricespud.bots.cballs.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.cballs.branches.InventoryContainsNotedBars;
import com.ricespud.bots.cballs.branches.BankOpen;

/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class InventoryContainsMould extends BranchTask {

    private InventoryContainsNotedBars inventoryContainsNotedBars = new InventoryContainsNotedBars();
    private BankOpen bankOpen = new BankOpen();

    public boolean validate() {
        if (Inventory.contains("Ammo mould")){
            getLogger().debug("We got mould");
            return true;
        }
        getLogger().debug("We need mould");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return bankOpen;
    }

    @Override
    public TreeTask successTask() {
        return inventoryContainsNotedBars;
    }
}
