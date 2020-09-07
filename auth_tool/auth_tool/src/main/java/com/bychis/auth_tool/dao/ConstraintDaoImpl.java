package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.Common.Action;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.model.Common.Permission;
import com.bychis.auth_tool.model.ContextBasedModel.model_structure.EnvironmentConstraint;
import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileConstraint;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("constraintDao")
public class ConstraintDaoImpl implements ConstraintDao {

    @Override
    public void createConstraint(String roleID, int levelID, Constraint constraint) {
        boolean webapp = false;
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        outerLoop: for(Element role : rootE.getChildren("Role")){
            if(role.getAttributeValue("roleID").equals(roleID)){
                Element metadataE = role.getChild("Metadata");
                if(metadataE.getAttributeValue("classification").equals("type")){
                    webapp = metadataE.getAttributeValue("type").equals("web_application");
                }
                Element levelListE = role.getChild("levels");
                for(Element level : levelListE.getChildren("level")){
                    if(level.getAttributeValue("id").equals(String.valueOf(levelID))){
                        Element constraintListE = level.getChild("constraints");
                        Element constraintE = new Element(constraint.getConstraintTypeName());

                        constraintE.setAttribute("id",String.valueOf(constraint.getConstraintID()));
                        constraintE.setAttribute("type",constraint.getConstraintTypeSpecificName());
                        Element argE = new Element("arg");
                        argE.setText(constraint.getConstraintArg());
                        constraintE.addContent(argE);
                        if(webapp){
                            Permission permission = constraint.getPermission();
                            Element permissionE = new Element("permission");
                            permissionE.setAttribute("value", permission.name());
                            constraintE.addContent(permissionE);
                        }else{
                            Action action = constraint.getAction();
                            Element actionE = new Element("action");
                            actionE.setAttribute("read",String.valueOf(action.isRead()));
                            actionE.setAttribute("write",String.valueOf(action.isWrite()));
                            actionE.setAttribute("copy",String.valueOf(action.isCopy()));
                            actionE.setAttribute("delete",String.valueOf(action.isDelete()));
                            constraintE.addContent(actionE);}


                        constraintListE.addContent(constraintE);
                        break outerLoop;
                    }
                }
            }
        }
        dbXML.writeInXMLFILE(doc);
    }

    @Override
    public void deleteConstraint(String roleID, int levelID, int constraintID) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        String constraintType = null;
        loop: for(Element role : rootE.getChildren("Role")){
            if(role.getAttributeValue("roleID").equals(roleID)){
                Element levelListE = role.getChild("levels");
                for(Element level : levelListE.getChildren("level")){
                    if(level.getAttributeValue("id").equals(String.valueOf(levelID))){
                        switch(level.getAttributeValue("model")){
                            case "ProfileBasedAccessControl": constraintType = "ProfileConstraint"; break;
                            case "ContextBasedAccessControl": constraintType = "EnvironmentConstraint"; break;
                        }
                        Element constraintListE = level.getChild("constraints");
                        for(Element constraintE : constraintListE.getChildren(constraintType)){
                            if(constraintE.getAttributeValue("id").equals(String.valueOf(constraintID))){
                                constraintListE.removeContent(constraintE);
                                dbXML.writeInXMLFILE(doc);
                                break loop;
                            }
                        }
                    }
                }
            }
        }

    }


    public static List<Constraint> getConstraintsAll(String roleID, int levelID) {
        boolean webapp = false;
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        List<Constraint> constraintList = new ArrayList<>();
        String type_of_constraints = null;

        loop:
        for (Element roleE : rootE.getChildren("Role")) {
            Element metadataE = roleE.getChild("Metadata");
            if (metadataE.getAttributeValue("classification").equals("type")) {
                webapp = metadataE.getAttributeValue("type").equals("web_application");
            }
            if (roleE.getAttributeValue("roleID").equals(roleID)) {
                Element levelListE = roleE.getChild("levels");
                for (Element levelE : levelListE.getChildren("level")) {
                    if (levelE.getAttributeValue("id").equals(String.valueOf(levelID))) {
                        switch (levelE.getAttributeValue("model")) {
                            case "ContextBasedAccessControl":
                                type_of_constraints = "EnvironmentConstraint";
                                break;
                            case "ProfileBasedAccessControl":
                                type_of_constraints = "ProfileConstraint";
                                break;
                        }
                        Element constraintListE = levelE.getChild("constraints");
                        for (Element constraintE : constraintListE.getChildren()) {
                            switch (type_of_constraints) {
                                case "EnvironmentConstraint":
                                    EnvironmentConstraint ec = null;
                                    if (webapp) {
                                        System.out.println(" constraint list ");
                                        ec = new EnvironmentConstraint(Integer.parseInt(constraintE.getAttributeValue("id")),
                                                constraintE.getChild("permission").getAttributeValue("value"),
                                                constraintE.getAttributeValue("type"),
                                                constraintE.getChild("arg").getText());
                                                constraintList.add(ec);
                                    } else {
                                        Element actionE = constraintE.getChild("action");
                                        Action action = new Action(Boolean.getBoolean(actionE.getAttributeValue("read")),
                                                Boolean.getBoolean(actionE.getAttributeValue("write")),
                                                Boolean.getBoolean(actionE.getAttributeValue("copy")),
                                                Boolean.getBoolean(actionE.getAttributeValue("delete")));
                                        ec = new EnvironmentConstraint(Integer.parseInt(constraintE.getAttributeValue("id")),
                                                action,
                                                constraintE.getAttributeValue("type"),
                                                constraintE.getText());
                                                constraintList.add(ec);
                                    } break;
                                case "ProfileConstraint":
                                    ProfileConstraint pc = null;
                                    if (webapp) {
                                        pc = new ProfileConstraint(Integer.parseInt(constraintE.getAttributeValue("id")),
                                                constraintE.getChild("permission").getAttributeValue("value"),
                                                constraintE.getAttributeValue("type"),
                                                constraintE.getChild("arg").getText());
                                        constraintList.add(pc);
                                    }else{
                                        Element actionE = constraintE.getChild("action");
                                        Action action = new Action(Boolean.getBoolean(actionE.getAttributeValue("read")),
                                                Boolean.getBoolean(actionE.getAttributeValue("write")),
                                                Boolean.getBoolean(actionE.getAttributeValue("copy")),
                                                Boolean.getBoolean(actionE.getAttributeValue("delete")));
                                        pc = new ProfileConstraint(Integer.parseInt(constraintE.getAttributeValue("id")),
                                                action,
                                                constraintE.getName(),
                                                constraintE.getChild("arg").getText());
                                    } break;
                            }
                        }
                        break loop;
                    }
                }
            }

        }
        return constraintList;
    }

    @Override
    public Constraint getConstraintByID(String roleID, int levelID, int constraintID) {
        List<Constraint> constraintList = this.getConstraintsAll(roleID,levelID);
        Constraint constraint = null;
        loop: for(Constraint tmp : constraintList){
            if(tmp.getConstraintID() == constraintID){
                constraint = tmp;
                break loop;
            }
        }
        return constraint;
    }

    @Override
    public void updateConstraint(String roleID, int levelID, int constraintID, Constraint constraint) {

    }

}
