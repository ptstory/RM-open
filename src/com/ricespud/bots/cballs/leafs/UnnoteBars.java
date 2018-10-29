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

    private InterfaceComponent unNotedInterface;
    private GameObject bankBooth;
    private Player player;


    @Override
    public void execute() {
        player = Players.getLocal();
        SpriteItem notedSteelBar = Inventory.newQuery().ids(2354).results().first();

        if(Bank.isOpen()){
            Bank.close();
        }

        if (notedSteelBar != null) {
            if (notedSteelBar.isValid()) {
                if (notedSteelBar.click()) {
                    Execution.delayUntil(() -> Inventory.getSelectedItem() != null, 800, 1200);
                    getLogger().debug("Noted steel bar selected.");
                    bankBooth = GameObjects.newQuery().names("Bank booth").results().nearest();
                    if (bankBooth != null) {
                        if (bankBooth.isValid()) {
                            if (bankBooth.isVisible()) {
                                bankBooth = GameObjects.newQuery().names("Bank booth").results().nearest();
                                if (bankBooth.click()) {
                                    unNotedInterface = Interfaces.getAt(219, 0, 1);
                                    Execution.delayUntil(() -> unNotedInterface != null, 3000, 6000);
                                    unNotedInterface = Interfaces.getAt(219, 0, 1);
                                    if (unNotedInterface != null) {
                                        Execution.delayUntil(() -> unNotedInterface.isValid(), 1000, 2000);
                                        if (unNotedInterface.click()) {
                                            getLogger().debug("Successfully clicked 'Un-noted the banknotes'");
                                            Execution.delayUntil(() -> Inventory.contains(2353), 2000, 4000);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

