package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Traverses to fountain.
 */
public class GoToFountain extends LeafTask {
    private GameObject fountain;
    private Coordinate destination = new Coordinate(3209, 3427, 0);

    @Override
    public void execute() {
        fountain = GameObjects.newQuery().names("Fountain").results().nearest();
        RegionPath regionPath = RegionPath.buildTo(destination);
        if(fountain!= null && regionPath != null){
            regionPath.step();
            Execution.delayUntil(()->fountain.isVisible(), 1000, 2000);
        } else {
            getLogger().warn("regionPath was null in Walk");
        }
    }
}

