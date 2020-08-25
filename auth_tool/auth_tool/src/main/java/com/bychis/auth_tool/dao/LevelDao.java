package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.Common.Level;

import java.util.List;

public interface LevelDao {

    void createLevel(String roleID, Level level);
    void updateLevel(String roleID, int n, Level level);
    void deleteLevel(String roleID, int n);
    List<Level> getLevelsAll(String roleID);
    Level getLevel(String roleID, int n);

}
