package com.ricespud.bots.cballs.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.cballs.branches.AtFurnace;
import com.ricespud.bots.cballs.leafs.UnnoteBars;

/**
 * NOTES:
 * Checks if there are ~25 steel bars in inventory
 */
public class InventoryContains25Bars extends BranchTask {

    private AtFurnace atFurnace = new AtFurnace();
    private UnnoteBars unnoteBars = new UnnoteBars();

    public boolean validate() {
        if (Inventory.contains(2353)){
            getLogger().debug("We got at least 25 un-noted steel bars");
            return true;
        }
        getLogger().debug("We need un-noted steel bars");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return unnoteBars;
    }

    @Override
    public TreeTask successTask() {
        return atFurnace;
    }
}
