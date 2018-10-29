package com.ricespud.bots.Experiments.leafs;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class DrinkPotion extends LeafTask {

    @Override
    public void execute() {
        GameEvents.OSRS.NPC_DISMISSER.disable();
                SpriteItem potion = Inventory.newQuery().names("Combat potion(1)", "Combat potion(2)", "Combat potion(3)",
                "Combat potion(4)").results().first();
        if (potion != null)
            potion.interact("Drink");
            Execution.delay(500, 1000);
    }
}

