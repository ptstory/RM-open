package com.ricespud.bots.cballs.branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.cballs.leafs.WithdrawMould;
import com.ricespud.bots.cballs.leafs.OpenBank;

/**
 * NOTES:
 * Checks if bank is open
 */
public class BankOpen extends BranchTask {

    private WithdrawMould withdrawMould = new WithdrawMould();
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
        return withdrawMould;
    }
}
