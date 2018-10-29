package com.ricespud.bots.guam.leafs;

        import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
        import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
        import com.runemate.game.api.script.framework.tree.LeafTask;

public class SelectVial extends LeafTask {

    @Override
    public void execute(){
        getLogger().debug("Clicking vial");
        SpriteItem vialOfWater = Inventory.newQuery().names("Vial of water").results().first();
        if (vialOfWater != null){
            if (vialOfWater.isValid()){
                vialOfWater.click();
            }
        }
    }
}
