package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.ContextBasedModel.model_structure.ContextBasedAccessControl;
import com.bychis.auth_tool.model.Common.Level;
import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileBasedAccessControl;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("levelDao")
public class LevelDaoImpl implements LevelDao{

    @Override
    public void createLevel(String roleID, Level level) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        outerLoop: for( Element role : rootE.getChildren("Role")){
            if(role.getAttributeValue("roleID").equals(roleID)){
                Element levelE = new Element("level");
                levelE.setAttribute("id",String.valueOf(level.getId()));
                levelE.setAttribute("model",level.getAcm().getModelName());
                levelE.addContent(new Element("constraints"));
                role.getChild("levels").addContent(levelE);
                dbXML.writeInXMLFILE(doc);
                break outerLoop;
            }
        }
    }

    @Override
    public void updateLevel(String roleID, int n, Level level) {
        this.deleteLevel(roleID,n);
        this.createLevel(roleID,level);
    }

    @Override
    public void deleteLevel(String roleID, int n) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        outerLoop: for( Element role : rootE.getChildren("Role")){
            if(role.getAttributeValue("roleID").equals(roleID)){
                Element levelsListE = role.getChild("levels");
                // role.removeChild("levels");
                for(Element level : levelsListE.getChildren("level")){
                    if(level.getAttributeValue("id").equals(String.valueOf(n))){
                        level.detach();
                        break outerLoop;
                    }
                }
            }
        }
    dbXML.writeInXMLFILE(doc);
    }

    @Override
    public List<Level> getLevelsAll(String roleID) {
        List<Level> levelList = new ArrayList<>();
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        outerLoop: for( Element role : rootE.getChildren("Role")){
            if(role.getAttributeValue("roleID").equals(roleID)){
                Element levelListE = role.getChild("levels");
                for(Element levelE : levelListE.getChildren("level")){
                    Level level = null;
                    int id = Integer.parseInt(levelE.getAttributeValue("id"));
                    String levelModel = levelE.getAttributeValue("model");
                    switch (levelModel){
                        case "ContextBasedAccessControl":
                            level = new Level(id,new ContextBasedAccessControl());
                            break;
                        case "ProfileBasedAccessControl":
                            level = new Level(id,new ProfileBasedAccessControl());
                            break;
                    }
                    levelList.add(level);
                }
                break outerLoop;
            }

        }
        return levelList;
    }

    @Override
    public Level getLevel(String roleID, int n) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        Level level = null;
        outerLoop:
        for (Element role : rootE.getChildren("Role")) {
            if (role.getAttributeValue("roleID").equals(roleID)) {
                Element levelListE = role.getChild("levels");
                for (Element levelE : levelListE.getChildren("level")) {
                    if (levelE.getAttributeValue("id").equals(String.valueOf(n))) {
                        int id = Integer.parseInt(levelE.getAttributeValue("id"));
                        String levelModel = levelE.getAttributeValue("model");
                        switch (levelModel) {
                            case "ContextBasedAccessControl":
                                level = new Level(id, new ContextBasedAccessControl());
                                break;
                            case "ProfileBasedAccessControl":
                                level = new Level(id, new ProfileBasedAccessControl());
                                break;
                        }
                    }
                    break outerLoop;
                }

            }
        }
        return level;
    }

}
