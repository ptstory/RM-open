package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Traverses to bank.
 */
public class GoToBank extends LeafTask {

    private Coordinate destination = new Coordinate(3185, 3439, 0);
    private GameObject bankBooth;

    @Override
    public void execute() {
        bankBooth = GameObjects.newQuery().names("Bank booth").results().nearest();
        RegionPath regionPath = RegionPath.buildTo(destination);
        if(bankBooth!= null && regionPath != null){
            regionPath.step();
            Execution.delayUntil(()->bankBooth.isVisible(), 1000, 2000);
        } else {
            getLogger().warn("regionPath was null in Walk");
        }
    }
}


