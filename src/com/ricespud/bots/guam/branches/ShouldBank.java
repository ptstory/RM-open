package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.DepositPotions;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class ShouldBank extends BranchTask {
    /**
     * NOTES:
     * Checks if inventory contains 14 "Guam potion (unf)"
     */

    private InventoryContainsVials inventoryContainsVials = new InventoryContainsVials();
    private DepositPotions depositPotions = new DepositPotions();

    @Override
    public boolean validate() {
        //SpriteItemQueryResults guamLeaf = Inventory.newQuery().names("Guam leaf").results();
        if (Inventory.getQuantity("Guam potion (unf)") == 14){
            getLogger().debug("We should deposit items");
            return true;
        }

        return false;
    }

    @Override
    public TreeTask failureTask() {
        return inventoryContainsVials;
    }

    @Override
    public TreeTask successTask() {
        return depositPotions;
    }
}


