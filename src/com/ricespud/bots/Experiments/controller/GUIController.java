package com.ricespud.bots.Experiments.controller;

         import com.ricespud.bots.Experiments.Experiments;
         import com.runemate.game.api.client.ClientUI;
         import com.runemate.game.api.hybrid.local.Skill;
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

    @FXML
    private Label runtimeLabel;
    @FXML
    private Label attackLabel, strengthLabel, defenseLabel;
    @FXML
    private Label attackLevelLabel, strengthLevelLabel, defenseLevelLabel;
    @FXML
    private Label attackLevelGainedLabel, strengthLevelGainedLabel, defenseLevelGainedLabel;
    @FXML
    private ImageView settingsIcon, informationIcon;


    // Making a variable to hold the bot instance
    private Experiments bot;
    // Making a variable to hold the bot platform
    private BotPlatform botPlatform;


    public GUIController(BotPlatform botPlatform, Experiments bot) {
        this.botPlatform = botPlatform;
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {



        new LoopingThread(this::updateTime, 1000).start();
        new LoopingThread(this::updateAttackXP, 500).start();
        new LoopingThread(this::updateDefenseXP, 500).start();
        new LoopingThread(this::updateStrengthXP, 500).start();

        //new LoopingThread({... every update called here}, 1000).start()

        //        new LoopingThread(this::updateAttackLevel, 500).start();


        /* Whenever images are using the gui the resources must be called on
        the bot platform and the images must be set on the FX Platform.
        As such, we use the botPlatform variable we created in our constructor
        to use at the Bot Platform. We load the images from resources in this .invokeLater method.
        Inside the invoke later, we assign the images to their respective elements inside of the
        FX Platform.
         */
        botPlatform.invokeLater(() -> {
            InputStream settingsResource = Resources.getAsStream("com/ricespud/bots/cballs/resources/settings_icon.png");
            InputStream informationResource = Resources.getAsStream("com/ricespud/bots/cballs/resources/information_icon.png");
            Platform.runLater(() -> {
//                settingsIcon.setImage(new Image(settingsResource));
//                informationIcon.setImage(new Image(informationResource));
            });
        });

    }

    private void updateTime() {
        Platform.runLater(() -> runtimeLabel.setText(bot.getRuntime())); // Updating the time label to the new time
    }


    private void updateAttackXP() {
        Platform.runLater(() -> {
            int perHour = (int) CommonMath.rate(TimeUnit.HOURS, bot.getRuntimeLong(), bot.getAttackXPGained());
            attackLabel.setText(perHour + "");
        });
    }

//    private void updateAttackLevel() {
//        Platform.runLater(() -> {
//            int currentAttackLvl = Skill.ATTACK.getBaseLevel();
//            attackLevelLabel.setText(currentAttackLvl + "(+");
//        });
//    }

    private void updateStrengthXP() {
        Platform.runLater(() -> {
            int perHour = (int) CommonMath.rate(TimeUnit.HOURS, bot.getRuntimeLong(), bot.getStrengthXPGained());
            strengthLabel.setText(perHour + "");
        });
    }

    private void updateDefenseXP() {
        Platform.runLater(() -> {
            int perHour = (int) CommonMath.rate(TimeUnit.HOURS, bot.getRuntimeLong(), bot.getDefenseXPGained());
            defenseLabel.setText(perHour + "");
        });
    }

}
