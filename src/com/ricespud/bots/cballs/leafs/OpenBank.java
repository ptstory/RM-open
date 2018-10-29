package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Open bank
 */
public class OpenBank extends LeafTask {

    @Override
    public void execute() {
        Bank.open();
    }
}
