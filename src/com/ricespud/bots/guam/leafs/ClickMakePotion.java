package com.ricespud.bots.guam.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ClickMakePotion extends LeafTask {

    @Override
    public void execute(){
        getLogger().debug("Clicking 'Make Potion'");
        InterfaceComponent makePotionInterface = Interfaces.newQuery().actions("Make").results().first();
        if (makePotionInterface != null){
            if (makePotionInterface.isValid()){
                makePotionInterface.click();
            }
        }
        Execution.delayUntil(() -> (Inventory.getQuantity("Guam potion (unf)") == 14));
    }
}
