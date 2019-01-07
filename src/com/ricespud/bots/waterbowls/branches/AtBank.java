package com.ricespud.bots.waterbowls.branches;

import com.ricespud.bots.waterbowls.leafs.GoToBank;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks if player is near bank.
 */
public class AtBank extends BranchTask {


    private GoToBank goToBank = new GoToBank();
    private BankOpen bankOpen = new BankOpen();

    public boolean validate() {
        Player player = Players.getLocal();
        GameObject bankBooth = GameObjects.newQuery().names("Bank booth").results().nearest();
        if (player != null && bankBooth != null && bankBooth.isVisible()){
            getLogger().debug("Bank booth is visible.");
            return true;
        }
        getLogger().debug("We need to get to bank booth");
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return goToBank;
    }

    @Override
    public TreeTask successTask() {
        return bankOpen;
    }
}
