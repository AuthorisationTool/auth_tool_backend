package com.bychis.auth_tool.api.request_holders;

public class LevelRequestHolder {

    private int id;
    private String levelModel;

    public LevelRequestHolder(int id, String levelModel) {
        this.id = id;
        this.levelModel = levelModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevelModel() {
        return levelModel;
    }

    public void setLevelModel(String levelModel) {
        this.levelModel = levelModel;
    }

}
