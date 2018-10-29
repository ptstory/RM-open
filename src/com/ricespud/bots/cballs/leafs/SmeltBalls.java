package com.ricespud.bots.cballs.leafs;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.InterfaceComponent;
import com.runemate.game.api.hybrid.local.hud.interfaces.Interfaces;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Use steel bars on furnace to "make all" cannonballs
 */
public class SmeltBalls extends LeafTask {

    private boolean isAnimating(){
        return Players.getLocal().getAnimationId() != -1;
    }

    private GameObject furnace;
    private InterfaceComponent makeCannonballsInterface;
    private Player player;

    @Override
    public void execute() {
        furnace = GameObjects.newQuery().names("Furnace").results().nearest();

        makeCannonballsInterface = Interfaces.getAt(270, 14);
        player = Players.getLocal();


        if (furnace.isVisible()){
            if (furnace.isValid()){
                if (furnace != null){
                    if(furnace.interact("Smelt")){
                        makeCannonballsInterface = Interfaces.getAt(270, 14);
                        Execution.delayUntil(() -> makeCannonballsInterface != null && makeCannonballsInterface.isVisible(), 1000, 2000);
                        makeCannonballsInterface = Interfaces.getAt(270, 14);
                        if (makeCannonballsInterface != null){
                            if(makeCannonballsInterface.interact("Make sets:")){
                                getLogger().debug("Successfully clicked 'Make sets'");
                                Execution.delayUntil(() -> !Inventory.contains(2353), this::isAnimating, 2000, 4000);
                            }
                        }
                    }
                }
            }
        }
    }
}

