package com.ricespud.bots.guam.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.logging.Logger;

/**
 * NOTES:
 * Opens bank and withdraws 14 "Guam leaf"
 */
public class WithdrawGuam extends LeafTask {

    @Override
    public void execute() {
        getLogger().debug("Withdrawing guam");
        // add if bank not open
        // check if bank contains guam
        if (!Bank.isOpen()){Bank.open();}
        Execution.delay(300, 600);
        Bank.withdraw("Guam leaf", 14);
        Execution.delay(300, 600);
        //Execution.delayWhile(() -> !Inventory.contains("Guam leaf"));
        if (Inventory.contains("Vial of water") && Inventory.contains("Guam leaf")){
            getLogger().debug("closing bank");
            Bank.close();
        }

    }
}
