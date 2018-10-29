package com.ricespud.bots.guam.leafs;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Travels to bank
 */
public class TravelToBank extends LeafTask {
    private final Area bank = new Area.Rectangular(new Coordinate(3161, 3492, 0), new Coordinate(3167, 3486, 0)); // Bank area


    @Override
    public void execute() {
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bank);
           path.step(true);
    }
}
