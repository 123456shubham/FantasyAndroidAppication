package com.aryan.stumps11.NewUiData.Activity.Model;

public class ModelBrowsePlayer {
   private String playerImageBrowsePlayer,playerNameBrowsePlayer,playerCountryName;

    public ModelBrowsePlayer(String playerImageBrowsePlayer, String playerNameBrowsePlayer, String playerCountryName) {
        this.playerImageBrowsePlayer = playerImageBrowsePlayer;
        this.playerNameBrowsePlayer = playerNameBrowsePlayer;
        this.playerCountryName = playerCountryName;
    }

    public String getPlayerImageBrowsePlayer() {
        return playerImageBrowsePlayer;
    }

    public void setPlayerImageBrowsePlayer(String playerImageBrowsePlayer) {
        this.playerImageBrowsePlayer = playerImageBrowsePlayer;
    }

    public String getPlayerNameBrowsePlayer() {
        return playerNameBrowsePlayer;
    }

    public void setPlayerNameBrowsePlayer(String playerNameBrowsePlayer) {
        this.playerNameBrowsePlayer = playerNameBrowsePlayer;
    }

    public String getPlayerCountryName() {
        return playerCountryName;
    }

    public void setPlayerCountryName(String playerCountryName) {
        this.playerCountryName = playerCountryName;
    }
}
