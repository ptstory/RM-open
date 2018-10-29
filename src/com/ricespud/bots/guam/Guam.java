package com.ricespud.bots.guam;

import com.ricespud.bots.guam.branches.AtBank;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Guam  extends TreeBot {

    @Override
    public TreeTask createRootTask() { return new AtBank();
    }
}

