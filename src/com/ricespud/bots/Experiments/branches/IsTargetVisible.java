package com.ricespud.bots.Experiments.branches;

import com.MouseysBots.FeatherPickerBot.StoredVariables;
import com.ricespud.bots.Experiments.leafs.WaitTillTargetDeath;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.Experiments.leafs.WalkToTarget;

/**
 * NOTES:
 * 
 */
public class IsTargetVisible extends BranchTask {

    private ShouldPot shouldPot = new ShouldPot();
    private WalkToTarget walkToTarget = new WalkToTarget();

    private final Area experimentArea = new Area.Rectangular(new Coordinate(3537, 9965, 0), new Coordinate(3553, 9953, 0));

//    public long getNPC() {
//        return this.lastFlick;
//    }
//
//    public void setLastFlick(long lastFlick) {
//        this.lastFlick = lastFlick;
//    }


    @Override
    public boolean validate() {
        Npc experiment = Npcs.newQuery().names("Experiment").within(experimentArea).filter(x -> x != null && x.getHealthGauge() == null && x.getTarget() == null && x.getLevel() == 25).results().nearest();
        if (experiment != null){
            if (experiment.isValid() && experiment.isVisible()){
                getLogger().debug("Experiment is visible. No need to move towards it.");
                return true;
            }
        }
        getLogger().debug("Experiment is not visible. We need to run towards it.");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return walkToTarget;
    }

    @Override
    public TreeTask successTask() {
        return shouldPot;
    }

}
