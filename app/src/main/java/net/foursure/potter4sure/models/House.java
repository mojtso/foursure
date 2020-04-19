package net.foursure.potter4sure.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class House {

    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mascot")
    @Expose
    private String mascot;

    @SerializedName("headOfHouse")
    @Expose
    private String headOfHouse;

    @SerializedName("houseGhost")
    @Expose
    private String houseGhost;

    @SerializedName("founder")
    @Expose
    private String founder;

    @SerializedName("school")
    @Expose
    private String school;

    @SerializedName("members")
    @Expose
    private String[] members;

    @SerializedName("values")
    @Expose
    private String[] strength;

    @SerializedName("colors")
    @Expose
    private String[] colors;

    public House(String id, String name, String mascot, String headOfHouse, String houseGhost, String founder, String school, String[] members, String[] strength, String[] colors) {
        this.id = id;
        this.name = name;
        this.mascot = mascot;
        this.headOfHouse = headOfHouse;
        this.houseGhost = houseGhost;
        this.founder = founder;
        this.school = school;
//        this.members = members;
//        this.strength = strength;
//        this.colors = colors;
    }

    public House() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    public String getHeadOfHouse() {
        return headOfHouse;
    }

    public void setHeadOfHouse(String headOfHouse) {
        this.headOfHouse = headOfHouse;
    }

    public String getHouseGhost() {
        return houseGhost;
    }

    public void setHouseGhost(String houseGhost) {
        this.houseGhost = houseGhost;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(String[] members) {
        this.members = members;
    }

    public String[] getStrength() {
        return strength;
    }

    public void setStrength(String[] strength) {
        this.strength = strength;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "House {" +
                "name='" + name + '\'' +
                ", mascot='" + mascot + '\'' +
                ", headOfHouse='" + headOfHouse + '\'' +
                ", houseGhost='" + houseGhost + '\'' +
                ", founder='" + founder + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
