package com.bychis.auth_tool.service;

import com.bychis.auth_tool.dao.LevelDao;
import com.bychis.auth_tool.model.Common.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelService {
    private final LevelDao levelDao;

    @Autowired
    public LevelService(LevelDao levelDao) {
        this.levelDao = levelDao;
    }

    public void createLevel(String roleID, Level level){
        this.levelDao.createLevel(roleID,level);
    }

    public void deleteLevel(String roleID, int id){
        this.levelDao.deleteLevel(roleID,id);
    }

    public void updateLevel(String roleID, int id, Level level){
        this.levelDao.updateLevel(roleID,id,level);
    }

    public Level getLevel(String roleID, int id){
        return this.levelDao.getLevel(roleID,id);
    }

    public List<Level> getLevelsAll(String roleID){
        return this.levelDao.getLevelsAll(roleID);
    }

}
