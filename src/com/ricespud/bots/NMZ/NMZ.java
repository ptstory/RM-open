package com.ricespud.bots.NMZ;

import com.ricespud.bots.NMZ.branches.InDream;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class NMZ extends TreeBot{


    @Override
    public TreeTask createRootTask() { return new InDream();
    }
}

