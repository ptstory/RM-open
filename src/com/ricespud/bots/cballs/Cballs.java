package com.ricespud.bots.cballs;

import com.ricespud.bots.cballs.branches.InventoryContainsMould;
import com.ricespud.bots.cballs.controller.GUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.entities.definitions.ItemDefinition;
import com.runemate.game.api.hybrid.net.GrandExchange;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Cballs extends TreeBot implements EmbeddableUI, InventoryListener {

    private int ballsMade, barsUsed;
    private int barPrice, ballPrice;

    // Generating UI variables/objects
    private ObjectProperty<Node> botInterface;
    private StopWatch watch = new StopWatch();


    public String getRuntime() {
        return watch.getRuntimeAsString();
    }

    public long getRuntimeLong() {
        return watch.getRuntime();
    }

    public int getBallsMade() {
        return ballsMade;
    }

    private void setPrices() {
        barPrice = GrandExchange.lookup(2353).getPrice();
        ballPrice = GrandExchange.lookup(2).getPrice();
    }

    public Cballs() {
        setEmbeddableUI(this);
    }

    // Creating the GUI
    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterface == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new GUIController(getPlatform(), this));
            try {
                javafx.scene.Node node = loader.load(getPlatform().invokeAndWait(() -> Resources.getAsStream("com/ricespud/bots/cballs/ui/CballGUI.fxml")));
                botInterface = new SimpleObjectProperty<>(node);
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return botInterface;
    }

    @Override
    public void onStart(String... strings) {

        ballsMade = 0;
        barsUsed = 0;
        setLoopDelay(300, 500);
        getEventDispatcher().addListener(this);
        setPrices();
        watch.start();
        pause(); // pausing until user clicks "Make Balls"
    }

    @Override
    public void onItemAdded(ItemEvent event) {
        ItemDefinition item = event.getItem().getDefinition(); // Storing the new item's definition in the 'item' object
        if (item != null) {
            if (item.getName().contains("ball")) {
                ballsMade+=4;
                barsUsed++;
                getLogger().debug("Price per ball: " + ballPrice);
                getLogger().debug("Price per bar: " + barPrice);
                getLogger().debug("Profit per bar: " + ((ballPrice*4) - barPrice));
                getLogger().debug("Bars used: " + barsUsed);
            }
        }
    }


    @Override
    public TreeTask createRootTask() {
        return new InventoryContainsMould();
    }

    public int calculateProfitMade() {
        return (ballsMade*ballPrice) - (barsUsed*barPrice);
    }
}
//}