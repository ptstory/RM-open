package com.ricespud.bots.guam.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;
import com.runemate.game.api.script.Execution;

public class SelectGuam extends LeafTask {

    @Override
    public void execute() {
        getLogger().debug("Selecting guam");
        SpriteItem guamLeaf = Inventory.newQuery().names("Guam leaf").results().first();
        SpriteItem vialOfWater = Inventory.newQuery().names("Guam leaf").results().first();
        if (guamLeaf != null) {
            if (guamLeaf.isValid()) {
                guamLeaf.click();
//                if(vialOfWater!=null){
//                    if(vialOfWater.isValid()){
//                        vialOfWater.interact("Use", "Vial of water");
//                        Execution.delay(300, 600);
//                        vialOfWater.click();
//                    }
//                }
//                vialOfWater.interact("Use", "Vial of water");
//                Execution.delay(300, 600);
//                vialOfWater.click();
            }
        }
    }
}
