package com.ricespud.bots.waterbowls.branches;

import com.ricespud.bots.waterbowls.leafs.*;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks if bank is open.
 */
public class BankOpen extends BranchTask {

    private WithdrawBowls withdrawBowls = new WithdrawBowls();
    private OpenBank openBank = new OpenBank();

    @Override
    public boolean validate() {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask() {
        return openBank;
    }

    @Override
    public TreeTask successTask() {
        return withdrawBowls;
    }
}
