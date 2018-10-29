package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw cannonball mould
 */
public class WithdrawMould extends LeafTask {

    @Override
    public void execute() {
        if(Bank.contains("Ammo mould")){
            Bank.withdraw("Ammo mould", 1);
        } else {
            Environment.getBot().stop("No ammo mould in bank.");
        }

    }
}
