package com.ricespud.bots.waterbowls.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.Objects;

/**
 * NOTES:
 * Clicks the fountain.
 */
public class ClickFountain extends LeafTask {
    private boolean isAnimating() {
        return Objects.requireNonNull(Players.getLocal()).getAnimationId() != -1;
    }

    @Override
    public void execute() {
        GameObject fountain = GameObjects.getLoaded("Fountain").nearest();
        if (fountain != null){
            fountain.interact("Use", Inventory.getSelectedItem() + " -> " + Objects.requireNonNull(fountain.getDefinition()).getName());
            Execution.delayUntil(() -> !Inventory.contains("Bowl"), this::isAnimating, 2000, 4000);
        }
    }
}
