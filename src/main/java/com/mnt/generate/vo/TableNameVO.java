package com.mnt.generate.vo;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TableNameVO {
    private BooleanProperty check = new SimpleBooleanProperty(false);
    private StringProperty tableName = new SimpleStringProperty();
    private StringProperty remark = new SimpleStringProperty();

    public String getTableName() {
        return (String) this.tableName.get();
    }

    public void setTableName(String tableName) {
        this.tableName.setValue(tableName);
    }


    public BooleanProperty checkProperty() {
        return this.check;
    }

    public String getRemark() {
        return (String) this.remark.get();
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public Boolean getCheck() {
        return Boolean.valueOf(this.check.get());
    }

    public void setCheck(Boolean check) {
        this.check.set(check.booleanValue());
    }

    public String toString() {
        return "TableNameVO [tableName=" + getTableName() + ", remark=" + getRemark() + "]";
    }
}
