package net.foursure.potter4sure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spell {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("spell")
    @Expose
    private String spell;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("effect")
    @Expose
    private String effect;

    public Spell() {
    }

    public Spell(String id, String spell, String type, String effect) {
        this.id = id;
        this.spell = spell;
        this.type = type;
        this.effect = effect;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "spell='" + spell + '\'' +
                ", type='" + type + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }
}
