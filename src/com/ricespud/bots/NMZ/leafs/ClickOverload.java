package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class ClickOverload extends LeafTask {
    private SpriteItem overloadPotion; // = Inventory.newQuery().names("Overload (1)", "Overload (2)", "Overload (3)", "Overload (4)").results().first();

    @Override
    public void execute() {
        overloadPotion = Inventory.newQuery().names("Overload (1)", "Overload (2)", "Overload (3)", "Overload (4)").results().first();
        getLogger().debug("Querying overload");
        if(overloadPotion != null){
            getLogger().debug("Overload is not null");
            if (overloadPotion.isValid()){
                getLogger().debug("Overload is valid, drinking overload.");
                overloadPotion.interact("Drink");
                Execution.delayUntil(()->overloadPotion.interact("Drink"), 500, 900);
            }
        }
    }
}
