package com.bychis.auth_tool.model.RoleAndMetadataClassificationBasedModel;


public class RessourcesSpecificConstraintList {
    private Classification classification;
    private String classification_arg;

    public RessourcesSpecificConstraintList() {
        this.classification = Classification.category;
    }

    public RessourcesSpecificConstraintList(Classification classification, String classification_arg){
        this.classification = classification;
        this.classification_arg = classification_arg;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public String getClassification_arg() {
        return classification_arg;
    }

    public void setClassification_arg(String classification_arg) {
        this.classification_arg = classification_arg;
    }
}
