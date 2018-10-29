package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.ThreadLocalRandom;

/**
 * NOTES:
 * 
 */
public class Idle extends LeafTask {
    private int randomNum = ThreadLocalRandom.current().nextInt(1000, 3000);


    @Override
    public void execute() {
        getLogger().debug("Idling for " + randomNum/1000 + " seconds.");
        Execution.delay(randomNum);
    }
}
