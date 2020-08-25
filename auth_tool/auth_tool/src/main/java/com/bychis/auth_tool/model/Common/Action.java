package com.bychis.auth_tool.model.Common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class Action {
    private boolean read;
    private boolean write;
    private boolean copy;
    private boolean delete;
    private boolean all = false;

    public Action(){

    }

    public Action(boolean read, boolean write, boolean copy, boolean delete) {
        this.read = read;
        this.write = write;
        this.copy = copy;
        this.delete = delete;
    }

    public Action(boolean all) {
        this.all = all;
        this.read = all;
        this.write = all;
        this.copy = all;
        this.delete = all;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    public boolean isCopy() {
        return copy;
    }

    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
        this.read = all;
        this.write = all;
        this.copy = all;
        this.delete =all;
    }
}
