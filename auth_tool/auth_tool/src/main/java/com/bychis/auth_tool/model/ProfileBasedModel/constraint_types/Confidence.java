package com.bychis.auth_tool.model.ProfileBasedModel.constraint_types;

import com.bychis.auth_tool.rules.Value;

public class Confidence implements ProfileConstraintType{

    private int percentage_min;
    private int percentage_max;

    public Confidence(){
    }

    public Confidence(int percentage_min, int percentage_max) {
        this.percentage_min = percentage_min;
        this.percentage_max = percentage_max;
    }

    public int getPercentage_min() {
        return percentage_min;
    }

    public void setPercentage_min(int percentage_min) {
        this.percentage_min = percentage_min;
    }

    public int getPercentage_max() {
        return percentage_max;
    }

    public void setPercentage_max(int percentage_max) {
        this.percentage_max = percentage_max;
    }

    @Override
    public String getProfileConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {
        int a = Integer.parseInt(value);
        return (a >= this.getPercentage_min()
                &&
                a <= this.getPercentage_min());
    }


}
