package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.SelectGuam;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if Guam is currently selected
 */


public class IsGuamSelected extends BranchTask {

    //private ClickVial clickVial = new ClickVial();
    private IsVialSelected isVialSelected = new IsVialSelected();
    private SelectGuam selectGuam = new SelectGuam();

    @Override
    public boolean validate() {

        SpriteItem guamLeaf = Inventory.newQuery().names("Guam leaf").results().first();
        //SpriteItem selected = Inventory.getSelectedItem();

        //getLogger().debug(selected);

        if (guamLeaf.click()){
            getLogger().debug("Guam is selected");
            return true;
        }


//        if (selected != null){
//            if (selected.isValid()){
//                if (selected == guamLeaf){
//                    getLogger().debug("Guam is selected");
//                    return true;
//                }
//            }
//        }
        getLogger().debug("We need to select Guam");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return selectGuam;
    }

    @Override
    public TreeTask successTask() {
        return isVialSelected;
    }
}





