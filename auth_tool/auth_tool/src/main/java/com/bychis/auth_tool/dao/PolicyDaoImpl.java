package com.bychis.auth_tool.dao;
import org.jdom2.*;
import org.springframework.stereotype.Repository;

@Repository("Policy")
public class PolicyDaoImpl implements PolicyDao{
    static Element root_element;
    static int state = 0;

    @Override
    public void initiatePolicyDao() {
        Document doc = new Document();
        Element root = new Element("Policy");
        doc.setRootElement(root);
        dbXML.writeInXMLFILE(doc);
    }


    @Override
    public Element getPolicyTopRootElement() {
        if(state == 0){
            state = 1;
            return dbXML.useSAXBuilder().getRootElement();
        } else return root_element;
    }


}
