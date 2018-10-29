package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw all steel bars as noted
 *
 * TO-DO: Collect bars from Collection Box if present
 */
public class WithdrawNotedBars extends LeafTask {

    @Override
    public void execute() {
        if (!Bank.isOpen()){Bank.open();}
        Execution.delayUntil(Bank::isOpen, 1000, 2000);
        if (!Bank.contains("Steel bar")){
            Environment.getBot().stop("No steel bars in bank.");
        } else {
            if(Bank.setWithdrawMode(Bank.WithdrawMode.NOTE)){
                Execution.delayUntil(() -> Bank.setWithdrawMode(Bank.WithdrawMode.NOTE), 1000, 2000);
                Bank.withdraw("Steel bar", 0);
                Execution.delayUntil(() -> Inventory.contains(2354), 2000, 3000);
            }
            if (Inventory.contains(2354)){
                getLogger().debug("closing bank");
                Bank.close();
            }
        }
    }
}
