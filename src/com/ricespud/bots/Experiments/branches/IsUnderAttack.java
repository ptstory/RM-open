package com.ricespud.bots.Experiments.branches;

import com.ricespud.bots.Experiments.branches.IsTargetVisible;
import com.ricespud.bots.Experiments.leafs.WaitTillTargetDeath;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * This is the root node.

 Add children of this branch using the settings to the right.
 */
public class IsUnderAttack extends BranchTask {

    private WaitTillTargetDeath waitTillTargetDeath = new WaitTillTargetDeath();
    private IsTargetVisible isTargetVisible = new IsTargetVisible();

    @Override
    public boolean validate()
    {
            //Get the player always.. if the user pauses and logout the stored player variable aint the same anymore.
            Player player = Players.getLocal();

            // IF any of the chickens is targeting the player, he should be under atk but is not temporarily
            // Maybe because he moved away or something
            String targetName = "Experiment";
            boolean playerIsTargeted = !Npcs.newQuery().names(targetName).filter(x -> x != null && x.getTarget() != null && x.getTarget().equals(player)).results().isEmpty();
            return (player != null && player.getTarget() != null) || playerIsTargeted;

    }

    @Override
    public TreeTask failureTask() {
        return isTargetVisible;
    }

    @Override
    public TreeTask successTask() {
        return waitTillTargetDeath;
    }
}