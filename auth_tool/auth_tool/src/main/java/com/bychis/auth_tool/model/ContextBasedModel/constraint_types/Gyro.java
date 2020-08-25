package com.bychis.auth_tool.model.ContextBasedModel.constraint_types;

public class Gyro implements EnvironmentConstraintType{
    private float min_x,min_y,min_z;
    private float max_x,max_y,max_z;

    public Gyro(float min_x, float min_y, float min_z, float max_x, float max_y, float max_z) {
        this.min_x = min_x;
        this.min_y = min_y;
        this.min_z = min_z;
        this.max_x = max_x;
        this.max_y = max_y;
        this.max_z = max_z;
    }

    public float getMin_x() {
        return min_x;
    }

    public void setMin_x(float min_x) {
        this.min_x = min_x;
    }

    public float getMin_y() {
        return min_y;
    }

    public void setMin_y(float min_y) {
        this.min_y = min_y;
    }

    public float getMin_z() {
        return min_z;
    }

    public void setMin_z(float min_z) {
        this.min_z = min_z;
    }

    public float getMax_x() {
        return max_x;
    }

    public void setMax_x(float max_x) {
        this.max_x = max_x;
    }

    public float getMax_y() {
        return max_y;
    }

    public void setMax_y(float max_y) {
        this.max_y = max_y;
    }

    public float getMax_z() {
        return max_z;
    }

    public void setMax_z(float max_z) {
        this.max_z = max_z;
    }

    @Override
    public String getEnvironmentConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {

        String[] splitStr = value.split("\\s+");
        float x = Float.valueOf(splitStr[0]);
        float y = Float.valueOf(splitStr[1]);
        float z = Float.valueOf(splitStr[2]);

        return (x >= this.min_x && x<= this.max_x
                &&
                y >= this.min_y && x<= this.max_y
                &&
                z >= this.min_z && x<= this.max_z);
    }
}
