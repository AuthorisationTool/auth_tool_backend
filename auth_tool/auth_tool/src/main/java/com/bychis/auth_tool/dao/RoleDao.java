package com.bychis.auth_tool.dao;

import com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel.Role;
import org.jdom2.Document;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public interface RoleDao {

    void createRole(Role role);
    void updateRole(String id, Role role);
    void deleteRole(String id);
    Role getRole(String id);
    List<Role> getRoles();
    int getLevelsCount(String id);

    static List<String> getRoleIDs() {
        List<String> roleIDs = new ArrayList<String>();
        Document doc = dbXML.useSAXBuilder();
        Element rootElement = doc.getRootElement();
        for(Element element: rootElement.getChildren("Role")){
            String roleID = element.getAttributeValue("roleID");
            roleIDs.add(roleID);
        }
        return roleIDs;
    }

}
