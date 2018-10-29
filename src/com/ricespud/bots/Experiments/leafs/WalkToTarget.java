package com.ricespud.bots.Experiments.leafs;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class WalkToTarget extends LeafTask {
    private final Area experimentAreaTOP = new Area.Rectangular(new Coordinate(3531, 9969, 0), new Coordinate(3544, 9957, 0));
//    private final Area experimentAreaBOTTOM = new Area.Rectangular(new Coordinate(3548, 9958, 0), new Coordinate(3552, 9950, 0));
    private final Area experimentAreaBOTTOM = new Area.Circular(new Coordinate(3550, 9956, 0), 5);
    private final Area experimentArea = new Area.Rectangular(new Coordinate(3537, 9965, 0), new Coordinate(3553, 9953, 0));



    @Override
    public void execute() {
        GameEvents.OSRS.NPC_DISMISSER.disable();
        Execution.delay(1500, 6000);

//        Coordinate destination = (experimentAreaTOP.contains(Players.getLocal()) ? experimentAreaBOTTOM.getRandomCoordinate() : experimentAreaTOP.getRandomCoordinate());


        Npc experiment = Npcs.newQuery().names("Experiment").within(experimentArea).filter(x -> x != null && x.getHealthGauge() == null && x.getTarget() == null && x.getLevel() == 25).results().nearest();
        if(experiment!=null) {
            Coordinate destination = (experimentAreaTOP.contains(Players.getLocal()) ? experimentAreaBOTTOM.getRandomCoordinate() : experimentAreaTOP.getRandomCoordinate());


            if (destination != null && destination.isReachable()) {
                BresenhamPath path = BresenhamPath.buildTo(destination);
                if (path != null) {
                    path.step(true);
                    if (experiment.isValid() && experiment.getTarget() == null && experiment.isVisible())
                    {
                        getLogger().debug("Experiment has become visible. Attacking!");
                        experiment.interact("Attack");
                        Execution.delayWhile(Players.getLocal()::isMoving, 1000, 2000);
                    }
                    Execution.delayWhile(() -> !experiment.isVisible(), Players.getLocal()::isMoving,800, 1500);
                } else {
                    getLogger().debug("Path was null");
                }
            }
        }


    }
}
