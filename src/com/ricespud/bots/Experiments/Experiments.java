package com.ricespud.bots.Experiments;

import com.ricespud.bots.Experiments.branches.IsUnderAttack;
import com.ricespud.bots.Experiments.controller.GUIController;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.Resources;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class Experiments extends TreeBot implements EmbeddableUI, SkillListener {


    private int startAttackXP = Skill.ATTACK.getExperience();
    private int startDefensekXP = Skill.DEFENCE.getExperience();
    private int startStrengthXP = Skill.STRENGTH.getExperience();

    private int attackXPGained = 0;
    private int defenseXPGained = 0;
    private int strengthXPGained = 0;

    private int attackLevelsGained = 0;
    private int strengthLevelsGained = 0;
    private int defenseLevelsGained = 0;

    // Generating UI variables/objects
    private ObjectProperty<Node> botInterface;
    private StopWatch watch = new StopWatch();

    public String getRuntime() {
        return watch.getRuntimeAsString();
    }

    public long getRuntimeLong() {
        return watch.getRuntime();
    }

    public int getAttackXPGained() {
        return attackXPGained;
    }
    public int getStrengthXPGained() {
        return strengthXPGained;
    }
    public int getDefenseXPGained() {
        return defenseXPGained;
    }

    public int getAttackLevelsGained() {
        return attackLevelsGained;
    }
    public int getStrengthLevelsGained() {
        return strengthLevelsGained;
    }
    public int getDefenseLevelsGained() {
        return defenseLevelsGained;
    }

    public Experiments() {
        setEmbeddableUI(this);
    }

    // Creating the GUI
    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty() {
        if (botInterface == null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(new GUIController(getPlatform(), this));
            try {
                javafx.scene.Node node = loader.load(getPlatform().invokeAndWait(() -> Resources.getAsStream("com/ricespud/bots/Experiments/ui/ExperimentsGUI.fxml")));
                botInterface = new SimpleObjectProperty<>(node);
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return botInterface;
    }

    @Override
    public void onStart(String... strings) {
        attackXPGained = 0;
        strengthXPGained = 0;
        defenseXPGained = 0;
        setLoopDelay(300, 500);
        getEventDispatcher().addListener(this);
        watch.start();
    }

    @Override
    public void onLevelUp(SkillEvent e) {
        if (e.getType() == SkillEvent.Type.LEVEL_GAINED) {
            if (e.getSkill() == Skill.ATTACK) {
                ++attackLevelsGained;
            } else if (e.getSkill() == Skill.STRENGTH){
                ++strengthLevelsGained;
            } else if (e.getSkill() == Skill.DEFENCE){
                ++defenseLevelsGained;
            }
        }
    }

    @Override
    public void onExperienceGained(SkillEvent e) {
        if (e.getType() == SkillEvent.Type.EXPERIENCE_GAINED) {
            if (e.getSkill() == Skill.ATTACK) {
                attackXPGained = Skill.ATTACK.getExperience() - startAttackXP;
            } else if (e.getSkill() == Skill.STRENGTH){
                strengthXPGained = Skill.STRENGTH.getExperience() - startStrengthXP;
            } else if (e.getSkill() == Skill.DEFENCE){
                defenseXPGained = Skill.DEFENCE.getExperience() - startDefensekXP;
            }
        }
    }

    @Override
    public TreeTask createRootTask() { return new IsUnderAttack();
    }
}










