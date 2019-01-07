package com.ricespud.bots.waterbowls.branches;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.waterbowls.leafs.GoToFountain;

/**
 * NOTES:
 * Checks if player is near fountain.
 */
public class AtFountain extends BranchTask {

    private BowlSelected bowlSelected = new BowlSelected();
    private GoToFountain goToFountain = new GoToFountain();

    public boolean validate() {
        Player player = Players.getLocal();
        GameObject fountain = GameObjects.newQuery().names("Fountain").results().nearest();
        if (player != null && fountain != null && fountain.isVisible()){
            getLogger().debug("Fountain is visible.");
            return true;
        }
        getLogger().debug("We need to get to fountain");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return goToFountain;
    }

    @Override
    public TreeTask successTask() {
        return bowlSelected;
    }
}
