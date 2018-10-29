package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.osrs.local.hud.interfaces.Prayer;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.time.Instant;

/**
 * NOTES:
 * 
 */
public class FlickQuickPrayer extends LeafTask {

    private long lastFlick = Instant.now().toEpochMilli();

    public long getLastFlick() {
        return this.lastFlick;
    }

    public void setLastFlick(long lastFlick) {
        this.lastFlick = lastFlick;
    }

    @Override
    public void execute() {

        if(!Prayer.isQuickPraying()){
            getLogger().debug("Toggling quick prayer ON");
            Prayer.toggleQuickPrayers();
        }

        this.setLastFlick(Instant.now().toEpochMilli());
        Execution.delay(200, 800);

        if(Prayer.isQuickPraying()){
            getLogger().debug("Toggling quick prayer OFF");
            Prayer.toggleQuickPrayers();
        }
    }
}
