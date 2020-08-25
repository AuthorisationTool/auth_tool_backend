package com.bychis.auth_tool.api.request_holders;

import com.bychis.auth_tool.model.Common.Action;
import com.bychis.auth_tool.model.Common.Constraint;
import com.bychis.auth_tool.model.ContextBasedModel.model_structure.EnvironmentConstraint;
import com.bychis.auth_tool.model.ProfileBasedModel.model_structure.ProfileConstraint;

public class ConstraintRequestHolder {

    private int id;
    private String permission_or_action;
    private String constraint_type;
    private String specific_type;
    private String arg;

    public ConstraintRequestHolder(int id, String permission_or_action,
                                   String constraint_type,
                                   String specific_type, String arg) {

        this.id = id;
        this.permission_or_action = permission_or_action; // allow, deny,
        this.constraint_type = constraint_type;
        this.specific_type = specific_type;
        this.arg = arg;
    }

    public String getPermission_or_action() {
        return permission_or_action;
    }

    public void setPermission_or_action(String permission_or_action) {
        this.permission_or_action = permission_or_action;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstraint_type() {
        return constraint_type;
    }

    public void setConstraint_type(String constraint_type) {
        this.constraint_type = constraint_type;
    }

    public String getSpecific_type() {
        return specific_type;
    }

    public void setSpecific_type(String specific_type) {
        this.specific_type = specific_type;
    }

    public String getArg() {
        return arg;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public Constraint forgeConstraint() {
        Constraint constraint = null;
        if (this.getConstraint_type().equals("ProfileConstraint")) {
            outerSwitch:
            switch (permission_or_action) {
                case "allow":
                case "deny":
                    constraint = new ProfileConstraint(this.getId(),
                            this.permission_or_action,
                            this.specific_type,
                            this.arg);
                    break outerSwitch;
                default:
                    Action action = new Action(false);
                    loop:
                    for (int i = 0; i < permission_or_action.length(); i++) {
                        innerSwitch:
                        switch (permission_or_action.charAt(i)) {
                            case 'r':
                                action.setRead(true);
                                break innerSwitch;
                            case 'w':
                                action.setWrite(true);
                                break innerSwitch;
                            case 'd':
                                action.setDelete(true);
                                break innerSwitch;
                            case 'c':
                                action.setCopy(true);
                                break innerSwitch;
                            case 'a':
                                action.setAll(true);
                                break loop;
                            default:
                                System.out.println("Error in action in class ConstraintRequestHolder");
                        }
                        constraint = new ProfileConstraint(this.getId(),
                                action,
                                this.specific_type,
                                this.getArg());
                        break outerSwitch;

                    }

            }
        } else if (this.getConstraint_type().equals("EnvironmentConstraint")) {
            outerSwitch:
            switch (permission_or_action) {
                case "allow":
                case "deny":
                    constraint = new EnvironmentConstraint(this.getId(),
                            this.permission_or_action,
                            this.specific_type,
                            this.arg);
                    break outerSwitch;
                default:
                    Action action = new Action(false);
                    loop:
                    for (int i = 0; i < permission_or_action.length(); i++) {

                        switch (permission_or_action.charAt(i)) {
                            case 'r':
                                action.setRead(true);
                                break;
                            case 'w':
                                action.setWrite(true);
                                break;
                            case 'd':
                                action.setDelete(true);
                                break;
                            case 'c':
                                action.setCopy(true);
                                break;
                            case 'a':
                                action.setAll(true);
                                break loop;
                            default:
                                System.out.println("Error in action in class ConstraintRequestHolder");
                                break outerSwitch;
                        }
                    }
                    constraint = new EnvironmentConstraint(this.getId(),
                            action,
                            this.specific_type,
                            this.getArg());
                    break outerSwitch;

            }
        }
        return constraint;
    }
    }

