package com.mnt.generate.model;

import java.util.List;

public class DBModel {
    private String tableName;
    private String remark;
    private List<DBCloumn> dbCloumns;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<DBCloumn> getDbCloumns() {
        return this.dbCloumns;
    }

    public void setDbCloumns(List<DBCloumn> dbCloumns) {
        this.dbCloumns = dbCloumns;
    }
}