package com.bychis.auth_tool.model.dbEntities;

import javax.persistence.*;


@Entity
@Table(name = "bluetoothbadge")
public class BluetoothBadge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name= "bid")
     private String bid;
    @Column(name= "holder_uid",nullable = true,updatable = true)
     private String holder_uid;


    public BluetoothBadge(String bid,String holder_uid){
        this.bid=bid;
        this.holder_uid=holder_uid;
    }
    public BluetoothBadge(){
        super();
    }

    public String getHolder_uid() {
        return holder_uid;
    }

    public void setHolder_uid(String holder_uid) {
        this.holder_uid = holder_uid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
