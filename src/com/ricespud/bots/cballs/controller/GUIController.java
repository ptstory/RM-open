package com.ricespud.bots.cballs.controller;

         import com.ricespud.bots.cballs.Cballs;
         import com.runemate.game.api.client.ClientUI;
         import com.runemate.game.api.hybrid.util.Resources;
         import com.runemate.game.api.hybrid.util.calculations.CommonMath;
         import com.runemate.game.api.script.framework.core.BotPlatform;
         import com.runemate.game.api.script.framework.core.LoopingThread;
         import javafx.application.Platform;
         import javafx.collections.FXCollections;
         import javafx.collections.ObservableList;
         import javafx.event.ActionEvent;
         import javafx.event.EventHandler;
         import javafx.fxml.FXML;
         import javafx.fxml.Initializable;
         import javafx.scene.control.Button;
         import javafx.scene.control.ComboBox;
         import javafx.scene.control.Label;
         import javafx.scene.control.RadioButton;
         import javafx.scene.image.Image;
         import javafx.scene.image.ImageView;

         import java.io.InputStream;
         import java.net.URL;
         import java.util.Objects;
         import java.util.ResourceBundle;
         import java.util.concurrent.TimeUnit;

public class GUIController implements Initializable {

    // Declaring all of the objects that are in the GUI
    @FXML
    private ComboBox<String> logSelector, locationSelector;
    @FXML
    private Button saveButton, startButton;
    @FXML
    private Label statusLabel, runtimeLabel, logLabel, locationLabel, presetLabel, controlLabel, currentStatusLabel, planksMadeLabel, planksPerHourLabel;
    @FXML
    private RadioButton firstPreset, secondPreset, leechRadio, hostRadio;
    @FXML
    private ImageView settingsIcon, informationIcon;
    @FXML
    private Label profitMadeLabel, profitPerHourLabel;

    // Making a variable to hold the bot instance
    private Cballs bot;
    // Making a variable to hold the bot platform
    private BotPlatform botPlatform;
    // Creating a list of the logs that are used
    private ObservableList<String> logs = FXCollections.observableArrayList("Logs", "Oak logs", "Teak logs", "Mahogany logs");
    // Creating a list of the locations that can be used
    private ObservableList<String> locations = FXCollections.observableArrayList("Al-Kharid", "Barbarian Outpost", "Combat Academy");

    public GUIController(BotPlatform botPlatform, Cballs bot) {
        this.botPlatform = botPlatform;
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startButton.setOnAction(startBot());

        new LoopingThread(this::updateTime, 1000).start();
        new LoopingThread(this::updateBallsMade, 500).start();
        new LoopingThread(this::updateProfitMade, 500).start();

        botPlatform.invokeLater(() -> {
            InputStream settingsResource = Resources.getAsStream("com/ricespud/bots/cballs/resources/settings_icon.png");
            InputStream informationResource = Resources.getAsStream("com/ricespud/bots/cballs/resources/information_icon.png");
            Platform.runLater(() -> {
            });
        });

    }


    private EventHandler<ActionEvent> startBot() {
        return event -> {
                bot.resume();
        };
    }

    private void updateTime() {
        Platform.runLater(() -> runtimeLabel.setText(bot.getRuntime())); // Updating the time label to the new time
    }

    private void updateBallsMade() {
        Platform.runLater(() -> {
            int perHour = (int) CommonMath.rate(TimeUnit.HOURS, bot.getRuntimeLong(), bot.getBallsMade());
            planksPerHourLabel.setText(perHour + "");
            planksMadeLabel.setText(bot.getBallsMade() + "");
        });
    }

    private void updateProfitMade() {
        Platform.runLater(() -> {
            profitMadeLabel.setText(bot.calculateProfitMade() + "");
            int perHour = (int) CommonMath.rate(TimeUnit.HOURS, bot.getRuntimeLong(), bot.calculateProfitMade());
            profitPerHourLabel.setText(perHour + "");
        });
    }
}
