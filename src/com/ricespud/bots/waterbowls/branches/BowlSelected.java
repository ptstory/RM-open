package com.ricespud.bots.waterbowls.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;
import com.ricespud.bots.waterbowls.leafs.SelectBowl;

/**
 * NOTES:
 * Checks if bowl is currently selected item.
 */
public class BowlSelected extends BranchTask {

    private ShouldBank shouldBank = new ShouldBank();
    private SelectBowl selectBowl = new SelectBowl();

    @Override
    public boolean validate() {
        SpriteItem bowl = Inventory.newQuery().names("Bowl").results().first();
        final SpriteItem item = Inventory.getSelectedItem();
        if(item != null){
            return item.equals(bowl);
        } else {
            return false;
        }
    }

    @Override
    public TreeTask failureTask() {
        return selectBowl;
    }

    @Override
    public TreeTask successTask() {
        return shouldBank;
    }
}
