package com.ricespud.bots.guam.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositPotions extends LeafTask{
    /**
     * NOTES:
     * Opens bank and deposits all"
     */

    @Override
    public void execute() {
        if (!Bank.isOpen()){Bank.open();}
        Execution.delay(300, 600);
        getLogger().debug("Depositing inventory.");
        InterfaceComponent depositInventoryInterface = Interfaces.newQuery().actions("Deposit inventory").results().first();
        System.out.println(depositInventoryInterface);
        if (depositInventoryInterface != null){
            getLogger().debug("Clicking deposit inventory interface");
            depositInventoryInterface.click();
        }
        //Bank.depositInventory(); // not working????
        Execution.delay(300, 600);

    }
}

