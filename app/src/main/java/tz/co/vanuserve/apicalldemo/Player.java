package tz.co.vanuserve.apicalldemo;

import com.google.gson.annotations.SerializedName;

public class Player {
    private String name;
    private String nation;

    @SerializedName("avatar")
    private String image;

    public Player(String name, String image,String nation) {
        this.name = name;
        this.image = image;
        this.nation=nation;
    }

    public String getName() {
        return name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
