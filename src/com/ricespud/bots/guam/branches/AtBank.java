package com.ricespud.bots.guam.branches;

import com.ricespud.bots.guam.leafs.TravelToBank;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks if we are at bank
 */
public class AtBank extends BranchTask {

    private ShouldBank shouldBank = new ShouldBank();
    private TravelToBank travelToBank = new TravelToBank();

    private Player player = Players.getLocal();
    private final Area bank = new Area.Rectangular(new Coordinate(3161, 3492, 0), new Coordinate(3167, 3486, 0)); // Bank area

    @Override
    public boolean validate() {
        if (player != null && Distance.between(bank, player) < 10){
            getLogger().debug("We're at bank");
            return true;
        }
        getLogger().debug("We need to get to bank");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return travelToBank;
    }

    @Override
    public TreeTask successTask() {
        return shouldBank;
    }
}
