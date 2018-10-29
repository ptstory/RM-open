package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Go to furnace
 */
public class GoToFurnace extends LeafTask {
    private GameObject furnace;
    private final Area furnaceArea = new Area.Rectangular(new Coordinate(3104, 3497, 0), new Coordinate(3109, 3499, 0));
    private final Coordinate destination = furnaceArea.getRandomCoordinate();



    @Override
    public void execute() {
        furnace = GameObjects.newQuery().names("Furnace").results().nearest();
        RegionPath regionPath = RegionPath.buildTo(destination);
        if(regionPath != null){
            regionPath.step(true);
            Execution.delayUntil(()->furnace.isVisible(), 800, 1000);
        } else {
            getLogger().debug("regionPath was null in Walk");
        }
    }
}
