package com.ricespud.bots.Experiments.leafs;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class Attack extends LeafTask {
    private final Area experimentArea = new Area.Rectangular(new Coordinate(3537, 9965, 0), new Coordinate(3553, 9953, 0));


    @Override
    public void execute() {
        GameEvents.OSRS.NPC_DISMISSER.disable();
        Execution.delay(850, 3500);
        Npc experiment = Npcs.newQuery().names("Experiment").within(experimentArea).visible().filter(x -> x != null && x.getHealthGauge() == null && x.getTarget() == null && x.getLevel() == 25).results().nearest();

        if (experiment != null)
        {
            if (experiment.isValid() && experiment.getTarget() == null && experiment.isVisible())
            {
                if (experiment.interact("Attack"))
                {
                    try
                    {
                        Execution.delay(500, 800);
                        Execution.delayWhile(Players.getLocal()::isMoving, 1000, 2000);
                    }
                    catch(Exception e)
                    {
                        getLogger().debug("Could not delay while attacking gargoyle\n" + e);
                    }

//                    return;
                }

//                Execution.delay(1000, 1600);
            }
        }


    }
}

