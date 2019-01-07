package com.ricespud.bots.waterbowls;


import com.ricespud.bots.waterbowls.branches.InventoryContainsBowls;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class WaterBowls extends TreeBot {
    @Override
    public void onStart(String... strings) {
        Mouse.setPathGenerator(Mouse.MLP_PATH_GENERATOR);
    }

    @Override
    public TreeTask createRootTask() { return new InventoryContainsBowls();
    }
}

