package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.ClickMakePotion;
import com.ricespud.bots.guam.leafs.SelectGuam;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if "Make Potion" interface is open
 */

public class IsMakePotionInterfaceOpen extends BranchTask {
    private ClickMakePotion clickMakePotion = new ClickMakePotion();
    private SelectGuam selectGuam = new SelectGuam();

    @Override
    public boolean validate(){
        getLogger().debug("Ready to make potion");

        InterfaceComponent makePotionInterface = Interfaces.newQuery().actions("Make").results().first();
        if (makePotionInterface != null){
            if (makePotionInterface.isValid()){
                getLogger().debug("'Make Potion' interface is open");
                return true;
            }
        } return false;
    }

    @Override
    public TreeTask failureTask() {
        return selectGuam;
    }

    @Override
    public TreeTask successTask() {
        return clickMakePotion;
    }
}


















//public class IsGuamSelected extends BranchTask {
//
//    private ClickVial clickVial = new ClickVial();
//    private SelectGuam selectGuam = new SelectGuam();
//
//    @Override
//    public boolean validate() {
//
//        SpriteItem guamLeaf = Inventory.newQuery().names("Guam leaf").results().first();
//        SpriteItem selected = Inventory.getSelectedItem();
//
//        if (selected != null){
//            if (selected == guamLeaf){
//                getLogger().debug("Guam is selected");
//                return true;
//            }
//        }
//        getLogger().debug("We need to select Guam");
//        return false;
//    }
//
//    @Override
//    public TreeTask failureTask() {
//        return selectGuam;
//    }
//
//    @Override
//    public TreeTask successTask() {
//        return clickVial;
//    }
//}