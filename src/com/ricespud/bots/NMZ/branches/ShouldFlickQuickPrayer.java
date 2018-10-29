package com.ricespud.bots.NMZ.branches;

import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.NMZ.leafs.FlickQuickPrayer;
import com.ricespud.bots.NMZ.leafs.Idle;

import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

/**
 * NOTES:
 * 
 */
public class ShouldFlickQuickPrayer extends BranchTask {

    private FlickQuickPrayer flickQuickPrayer = new FlickQuickPrayer();
    private Idle idle = new Idle();

//    private OverloadsActive overloadsActive = new OverloadsActive(); THIS CAUSES STACK OVERFLOW WHY???

//    int randomNum = ThreadLocalRandom.current().nextInt(25000, 49000);

//    private int randomNum = ThreadLocalRandom.current().nextInt(10000, 15000);

    @Override
    public boolean validate() {
        int randomNum = ThreadLocalRandom.current().nextInt(25000, 49000);

        getLogger().debug((Instant.now().toEpochMilli() - flickQuickPrayer.getLastFlick())/1000 + " seconds since last flick");

        if (flickQuickPrayer.getLastFlick() == 0) {
            return true;
        }

        if(Instant.now().toEpochMilli() >= flickQuickPrayer.getLastFlick()+ randomNum) {
            getLogger().debug(randomNum/1000 + " seconds reached - need to flick.");
            return true;
        }
         return false;
        }

    @Override
    public TreeTask failureTask() {
        return idle;
//        return overloadsActive;
    }

    @Override
    public TreeTask successTask() {
        return flickQuickPrayer;
    }
}
