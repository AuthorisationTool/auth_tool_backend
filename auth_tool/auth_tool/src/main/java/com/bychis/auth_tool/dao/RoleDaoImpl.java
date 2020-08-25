package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel.Role;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Role")
public class RoleDaoImpl implements RoleDao{

    @Override
    public void createRole(Role role) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        Element roleE = new Element("Role");

        // Add the id and name of the role to the DOM
        roleE.setAttribute("roleID",role.getRoleId());
        roleE.setAttribute("roleName",role.getRoleName());

        // Add metadata element to DOM
        Element metadata = new Element("Metadata");
        metadata.setAttribute("classification",role.getRscl().getClassification().toString());
        metadata.setAttribute(role.getRscl().getClassification().toString(),
                role.getRscl().getClassification_arg());
        roleE.addContent(metadata);

        // Add the levels list TOP element to the DOM
        Element levelListElement = new Element("levels");
        roleE.addContent(levelListElement);

        // Add all the contents to the root Element in the DOM
        rootE.addContent(roleE);
        dbXML.writeInXMLFILE(doc);
    }

    @Override
    public void updateRole(String id, Role role) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        Element roleNew = new Element("Role");
        outerLoop: for (Element roleE : rootE.getChildren("Role")){
            if (roleE.getAttributeValue("roleID").equals(id)){

                roleNew.setAttribute("roleID",id);
                roleNew.setAttribute("roleName",role.getRoleName());
                Element levelList = roleE.getChild("levels");
                levelList.detach();
                Element metadata = new Element("Metadata");
                metadata.setAttribute("classification",role.getRscl().getClassification().toString());
                metadata.setAttribute(role.getRscl().getClassification().toString(),
                        role.getRscl().getClassification_arg());
                roleNew.addContent(metadata);
                roleNew.addContent(levelList);
                rootE.removeContent(roleE);
                rootE.addContent(roleNew);
                break outerLoop;
                }
        }
        dbXML.writeInXMLFILE(doc);
    }

    @Override
    public void deleteRole(String id) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();

        loop: for(Element tmp : rootE.getChildren("Role")){
            if(tmp.getAttributeValue("roleID").equals(id)){
                rootE.removeContent(tmp);
                dbXML.writeInXMLFILE(doc);
                break loop;
            }}


    }




    @Override
    public Role getRole(String id) {
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        Role role = null;
        for(Element tmp : rootE.getChildren("Role")){
            if (tmp.getAttributeValue("roleID").equals(id)){

                String roleName = tmp.getAttributeValue("roleName");
                String classification = tmp.getChild("Metadata").getAttributeValue("classification");
                String classificationArg = tmp.getChild("Metadata").getAttributeValue(classification);
                // Creating the Role instance to return with the all of it's contents
                role = new Role(roleName,classification,classificationArg);
                role.setRoleId(id);
            }
        }
        return role;
    }

    @Override
    public synchronized List<Role> getRoles() {
        List<Role> roleList = new ArrayList<>();
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();

        for(Element tmp : rootE.getChildren("Role")){
                String roleName = tmp.getAttributeValue("roleName");
                String classification = tmp.getChild("Metadata").getAttributeValue("classification");
                String classificationArg = tmp.getChild("Metadata").getAttributeValue(classification);
                Role role = new Role(roleName,classification,classificationArg);
                role.setRoleId(tmp.getAttributeValue("roleID"));
                roleList.add(role);
        }
        return roleList;
    }

    @Override
    public synchronized int getLevelsCount(String id) {
        int n=0;
        Document doc = dbXML.useSAXBuilder();
        Element rootE = doc.getRootElement();
        outerloop: for(Element roleE: rootE.getChildren("Role")){
            if(roleE.getAttributeValue("roleID").equals(id)){
                Element levelListE = roleE.getChild("levels");
                n = levelListE.getChildren("level").size();
                System.out.println("***********"+n);
                break outerloop;
            }
        }
        return n;
    }





}
