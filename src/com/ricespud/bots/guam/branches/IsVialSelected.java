package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.SelectVial;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if vial has been clicked
 */

public class IsVialSelected extends BranchTask {

    private IsMakePotionInterfaceOpen isMakePotionInterfaceOpen = new IsMakePotionInterfaceOpen();
    private SelectVial selectVial = new SelectVial();

    @Override
    public boolean validate(){
        SpriteItem vialOfWater = Inventory.newQuery().names("Vial of water").results().first();

        if (vialOfWater.click()){
            getLogger().debug("Vial of water is clicked");
            return true;
        }

        getLogger().debug("Need to click vial");
        return false;
    }

        @Override
    public TreeTask failureTask() {
        return selectVial;
    }

    @Override
    public TreeTask successTask() {
        return isMakePotionInterfaceOpen;
    }
}








