package com.ricespud.bots.NMZ.branches;

import com.ricespud.bots.NMZ.branches.OverloadsActive;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.NMZ.branches.OverloadsActive;
import com.ricespud.bots.NMZ.leafs.CloseBot;

/**
 * NOTES:
 * This is the root node.

Add children of this branch using the settings to the right.
 */
public class InDream extends BranchTask {

    private OverloadsActive overloadsActive = new OverloadsActive();
    private CloseBot closeBot = new CloseBot();

    private Player player = Players.getLocal();
//    private final Area dream = new Area.Rectangular(new Coordinate(3161, 3492, 0), new Coordinate(3167, 3486, 0)); // Bank area

    public boolean validate() {
        if (player != null && player.getPosition().getPlane() == 3){
//        if (player != null && Distance.between(dream, player) > 10){
            getLogger().debug("We're in dream");
            return true;
        }
        getLogger().debug("We need to close bot");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return closeBot;
    }

    @Override
    public TreeTask successTask() {
        return overloadsActive;
    }
}
