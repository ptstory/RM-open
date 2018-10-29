package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.queries.SpriteItemQueryBuilder;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class RockcakeToOneHP extends LeafTask {

    private boolean isOneHP(){
        return (Health.getCurrent() == 1);
    }

    private boolean overloadDurationSufficient() {
        Varbit varbit = Varbits.load(3955);
        if(varbit == null) {
            getLogger().debug("Varbit [overload duration] was null");
            return false;
        } else {
            return varbit.getValue() > 2;
        }
    }

    @Override
    public void execute() {
        SpriteItem rockCake = Inventory.newQuery().names("Dwarven rock cake").results().first();
        if (overloadDurationSufficient()) {
            if (!isOneHP()) {
                if (rockCake != null) {
                    if (rockCake.isValid()) {
                        rockCake.interact("Guzzle");
                        Execution.delay(2000, 3000);
//                        Execution.delayUntil(() -> rockCake.interact("Guzzle"), 1000, 2000);
                    }
                }

            }
        }
    }
}

