package com.ricespud.bots.guam.leafs;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Opens bank and withdraws 14 "Vials of water"
 */
public class WithdrawVials extends LeafTask {

    @Override
    public void execute() {
        getLogger().debug("grabbing vials");
        if (!Bank.isOpen()){Bank.open();}
        Execution.delay(300, 600);
        Bank.withdraw("Vial of water", 14);
        //Execution.delayWhile(() -> !Inventory.contains("Vial of water")); // what is happening here to make bot freeze
        Execution.delay(300, 600);
        if (Inventory.contains("Vial of water") && Inventory.contains("Guam leaf")){
            getLogger().debug("closing bank");
            Bank.close();
        }

    }
}
