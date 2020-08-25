package com.bychis.auth_tool.model.ContextBasedModel.constraint_types;

public class AgentLocation implements EnvironmentConstraintType{

    private float latitude;
    private float longitude;

    public AgentLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public AgentLocation(){}
    @Override
    public String getEnvironmentConstraintTypeName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean evaluateValue(String value) {
        String[] splitStr = value.split("\\s+");
        return (Float.valueOf(splitStr[0]) == this.latitude
                &&
                Float.valueOf(splitStr[1]) == this.longitude);
    }
}
