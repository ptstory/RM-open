package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Selects bowl in inventory.
 */
public class SelectBowl extends LeafTask {

    @Override
    public void execute() {
        SpriteItem bowl = Inventory.newQuery().names("Bowl").results().first();
        if (bowl != null){
            bowl.interact("Use");
        }
    }
}
