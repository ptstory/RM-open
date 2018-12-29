package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.queries.SpriteItemQueryBuilder;
import com.runemate.game.api.hybrid.queries.results.SpriteItemQueryResults;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Use noted steel bars on bank teller / booth to fill inventory with bars
 */
public class UnnoteBars extends LeafTask {

    @Override
    public void execute() {
        SpriteItem notedSteelBar = Inventory.newQuery().ids(2354).results().first();

        if (Bank.isOpen()) {
            Bank.close();
        }

        if (notedSteelBar == null) {
            return;
        }

        final InterfaceComponent unNotedInterface = Interfaces.newQuery().texts("Un-note the banknotes?").results().first();
        if (unNotedInterface != null && unNotedInterface.isVisible()) {
            if (ChatDialog.getTitle().equals("Un-note the banknotes?")) {
                ChatDialog.Option yes = ChatDialog.getOption(1);
                if (yes != null) {
                    yes.select();
                }
            }
            Execution.delayUntil(() -> Inventory.contains(2353), 1800, 2400);
        } else {
            final SpriteItem item = Inventory.getSelectedItem();
            if (item == null || !item.equals(notedSteelBar)) {
                notedSteelBar.click();
            } else {
                final GameObject bankBooth = GameObjects.newQuery().names("Bank booth").results().nearest();
                if (bankBooth != null && bankBooth.click()) {
                    Execution.delayUntil(() -> unNotedInterface != null, 3000, 6000);
                }
            }
        }
    }
}

