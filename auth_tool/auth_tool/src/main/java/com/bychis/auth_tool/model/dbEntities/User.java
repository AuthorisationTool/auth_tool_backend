package com.bychis.auth_tool.model.dbEntities;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userID;

    @Column(name = "name")
    private String name;

    @Column(name = "confidence")
    private String confidence;

    @Column(name = "mobility")
    private String mobility;

    @Column(name = "memberOf", updatable = true, nullable = true)
    private String memberOf;


    public User() {
    }

    public User(String name, String confidence, String mobility, String memberOf) {

        this.name = name;
        this.confidence = confidence;
        this.mobility = mobility;
        this.memberOf = memberOf;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public User(String name) {
        this.name = name;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }
}
