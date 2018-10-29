package com.ricespud.bots.cballs.branches;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.cballs.leafs.SmeltBalls;
import com.ricespud.bots.cballs.leafs.GoToFurnace;

/**
 * NOTES:
 * Checks if player is at the furnace
 */
public class AtFurnace extends BranchTask {

    private SmeltBalls smeltBalls = new SmeltBalls();
    private GoToFurnace goToFurnace = new GoToFurnace();

    private Player player = Players.getLocal();
    private GameObject furnace;



    @Override
    public boolean validate() {
        furnace = GameObjects.newQuery().names("Furnace").results().nearest();
        if (player != null && furnace.isVisible()){   // not sure if i want to check visibility or distance from player
            getLogger().debug("Furnace is visible.");
            return true;
        }
        getLogger().debug("We need to get to furnace");
        return false;
    }


    @Override
    public TreeTask failureTask() {
        return goToFurnace;
    }

    @Override
    public TreeTask successTask() {
        return smeltBalls;
    }
}
