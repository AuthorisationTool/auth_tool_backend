package com.bychis.auth_tool.api.request_holders;

public class RoleRequestHolder {
    private String roleName;
    private String classification;
    private String ClassificationArg;

    public RoleRequestHolder(String roleName, String classification, String classificationArg) {
        this.roleName = roleName;
        this.classification = classification;
        ClassificationArg = classificationArg;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassificationArg() {
        return ClassificationArg;
    }

    public void setClassificationArg(String classificationArg) {
        ClassificationArg = classificationArg;
    }
}
