package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraws all (28) bowls from bank.
 */
public class WithdrawBowls extends LeafTask {

    @Override
    public void execute() {
        if(!Bank.isOpen()){
            Bank.open();
        }

        if(!Bank.contains("Bowl")){
            Environment.getBot().stop("No more bowls in bank.");
        }

        Bank.depositInventory();
        Execution.delayUntil(() ->Inventory.getEmptySlots() != 0, 300, 900);
        Bank.withdraw("Bowl", 0);
    }
}
