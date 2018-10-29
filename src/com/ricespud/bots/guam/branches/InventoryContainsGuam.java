package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.branches.IsGuamSelected;
import com.ricespud.bots.guam.leafs.WithdrawGuam;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;



/**
 * NOTES:
 * Checks if inventory contains 14 Guam leafs
 */
public class InventoryContainsGuam extends BranchTask {

    private IsGuamSelected isGuamSelected = new IsGuamSelected();
    private WithdrawGuam withdrawGuam = new WithdrawGuam();

    @Override
    public boolean validate() {
        //SpriteItemQueryResults guamLeaf = Inventory.newQuery().names("Guam leaf").results();
        if (Inventory.getQuantity("Guam leaf") == 14){
            getLogger().debug("We got guam");
            return true;
        }
        getLogger().debug("We need guams");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return withdrawGuam;
    }

    @Override
    public TreeTask successTask() {
        return isGuamSelected;
    }
}
