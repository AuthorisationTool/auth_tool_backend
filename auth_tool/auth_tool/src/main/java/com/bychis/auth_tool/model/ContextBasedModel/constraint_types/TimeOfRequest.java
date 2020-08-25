package com.bychis.auth_tool.model.ContextBasedModel.constraint_types;

import java.util.Date;

public class TimeOfRequest implements EnvironmentConstraintType{
    private Date date;

    public TimeOfRequest(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TimeOfRequest(){}
    @Override
    public String getEnvironmentConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {
        return this.date.toString().equals(value);
    }
}
