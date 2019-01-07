package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Selects bowl in inventory.
 */
public class OpenBank extends LeafTask {

    @Override
    public void execute() {
        Bank.open();
    }
}
