package com.ricespud.bots.Experiments.branches;

import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

import com.ricespud.bots.Experiments.leafs.DrinkPotion;
import com.ricespud.bots.Experiments.leafs.Attack;

import java.util.concurrent.ThreadLocalRandom;

/**
 * NOTES:
 * 
 */
public class ShouldPot extends BranchTask {

    private DrinkPotion drinkPotion = new DrinkPotion();
    private Attack attack = new Attack();

    @Override
    public boolean validate()
    {
        if (!Inventory.containsAnyOf(9739, 9741, 9743, 9745)) {
            getLogger().debug("No combat pots in inventory.");
            return false;
        }
//        int boost = ThreadLocalRandom.current().nextInt(4, 7);
        if (Skill.STRENGTH.getCurrentLevel() == Skill.STRENGTH.getBaseLevel()){
            getLogger().debug("Need to repot.");
            return true;
        }
        return false;
    }

    @Override
    public TreeTask failureTask() {
        return attack;
    }

    @Override
    public TreeTask successTask() {
        return drinkPotion;
    }
}
