package com.ricespud.bots.SafeSpotter;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.definitions.GameObjectDefinition;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.BresenhamPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.LoopingBot;

import java.util.concurrent.ThreadLocalRandom;

public class SafeSpotter extends LoopingBot {

    //private final Coordinate safespot = new Coordinate(1438, 3616, 0); // open area
    //private final Area safeSpotArea = new Area.Rectangular(new Coordinate(1439, 3617, 0), new Coordinate(1438, 3616, 0)); // open area

    private final Coordinate safespot = new Coordinate(2529, 3371, 0); // caged area
    private final Area safeSpotArea = new Area.Rectangular(new Coordinate(2523, 3372, 0), new Coordinate(2532, 3369, 0)); // caged area

    @Override
    public void onLoop() {
        int randomHealthPercentage = ThreadLocalRandom.current().nextInt(30, 60);
        int randomDelay = ThreadLocalRandom.current().nextInt(25000, 51000);
        GameObject cannon = GameObjects.newQuery().names("Dwarf multicannon").results().nearest();
        GameObjectDefinition cannonDef = GameObjectDefinition.get(6);
        GameObject brokenCannon = GameObjects.newQuery().names("Broken multicannon").results().nearest(); //getID=14916
        SpriteItem swordfish = Inventory.newQuery().names("Swordfish").results().first();

        if (Health.getCurrentPercent() <= randomHealthPercentage){
            getLogger().debug("Health has reached" + randomHealthPercentage + " percent. Need to eat.");
            if (swordfish != null){
                if (swordfish.isValid()){
                    getLogger().debug("Eating swordfish.");
                    swordfish.interact("Eat");
                    Execution.delayUntil(()->swordfish.interact("Eat"), 300, 900);
                }
            }

        }
          if(safeSpotArea.contains(Players.getLocal())){

              if(brokenCannon != null){
                  if(brokenCannon.isValid()){
                      getLogger().debug("Repairing cannon!");
                      brokenCannon.interact("Repair");
                      Execution.delayUntil(()->brokenCannon.interact("Repair"), 300, 900);
                  }
              } else{
                  getLogger().debug("Broken cannon is null");
              }
              Execution.delay(2000, 3000);


            getLogger().debug("We are within safespot. Delaying " + randomDelay/1000 + " seconds.");
            Execution.delay(randomDelay);
            getLogger().debug("Refilling cannon.");



            if(cannon != null){
                if(cannon.isValid()){
                    cannon.interact("Fire");
                    Execution.delayUntil(()->cannon.interact("Fire"), 300, 900);
                }
            }
              Execution.delay(2000, 3000);
              getLogger().debug("Running to safespot");
              BresenhamPath bresPath = BresenhamPath.buildTo(safespot);

              if(bresPath != null){
                  bresPath.step();
              } else {
                  getLogger().debug("bresPath was null in Walk");
              }
        }else{
            getLogger().debug("Running to safespot");
            BresenhamPath bresPath = BresenhamPath.buildTo(safespot);

            if(bresPath != null){
                bresPath.step();
            } else {
                getLogger().debug("bresPath was null in Walk");
            }
        }
    }
}
