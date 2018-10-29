package com.ricespud.bots.Experiments.leafs;

import com.runemate.game.api.hybrid.GameEvents;
import com.runemate.game.api.hybrid.entities.Actor;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.queries.results.LocatableEntityQueryResults;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 * 
 */
public class WaitTillTargetDeath extends LeafTask {
    private final Area experimentArea = new Area.Rectangular(new Coordinate(3537, 9965, 0), new Coordinate(3553, 9953, 0));


    @Override
    public void execute() {
        GameEvents.OSRS.NPC_DISMISSER.disable();
        try {
            Actor target = Players.getLocal().getTarget();
            if (target != null) {
                getLogger().debug("Waiting for target death");
                SpriteItem potion = Inventory.newQuery().names("Combat potion(1)", "Combat potion(2)", "Combat potion(3)",
                        "Combat potion(4)").results().first();
                if (Skill.STRENGTH.getCurrentLevel() == Skill.STRENGTH.getBaseLevel()){
                    if (potion != null)
                        potion.interact("Drink");
                    Execution.delay(500, 1000);
                }

                Execution.delayUntil(() -> !target.isValid(), 800, 1200);
            } else {
                LocatableEntityQueryResults<Npc> experimentList = Npcs.newQuery().names("Experiment").within(experimentArea).filter(x -> x != null && x.getTarget() != null && x.getTarget().equals(Players.getLocal())).results();

                if (experimentList.size() > 0) {
                    Npc experiment = experimentList.first();
                    if (experiment != null && experiment.isValid()) {
                        getLogger().debug("Attack experiment that targets you");
                        experiment.interact("Attack");
                        Execution.delay(600, 800);
                    }
                }
            }
        } catch (Exception e) {
            getLogger().debug("Could not wait for target death death\n" + e.getMessage());
        }

    }
}
