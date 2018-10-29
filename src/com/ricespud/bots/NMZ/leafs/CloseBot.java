package com.ricespud.bots.NMZ.leafs;

import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * 
 */
public class CloseBot extends LeafTask {

    @Override
    public void execute() {
        Environment.getBot().stop("We are not in dream.");
    }
}
