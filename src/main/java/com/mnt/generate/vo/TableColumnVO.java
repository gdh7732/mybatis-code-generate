package com.mnt.generate.vo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class TableColumnVO {
    private StringProperty cloumnName = new SimpleStringProperty();
    private StringProperty columnKey = new SimpleStringProperty();
    private StringProperty cloumnType = new SimpleStringProperty();
    private StringProperty remark = new SimpleStringProperty();

    public String getCloumnName() {
        return (String) this.cloumnName.get();
    }

    public void setCloumnName(String cloumnName) {
        this.cloumnName.set(cloumnName);
    }


    public StringProperty cloumnNameProperty() {
        return this.cloumnName;
    }

    public Integer getColumnKey() {
        return Integer.valueOf(this.columnKey.get());
    }

    public void setColumnKey(String columnKey) {
        this.columnKey.set(columnKey);
    }


    public StringProperty columnKeyProperty() {
        return this.columnKey;
    }

    public String getCloumnType() {
        return (String) this.cloumnType.get();
    }

    public void setCloumnType(String cloumnType) {
        this.cloumnType.set(cloumnType);
    }


    public StringProperty cloumnTypeProperty() {
        return this.cloumnType;
    }

    public String getRemark() {
        return (String) this.remark.get();
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }


    public StringProperty remarkProperty() {
        return this.remark;
    }

    public String toString() {
        return
                "TableColumnVO [cloumnName=" + getCloumnName() + ", columnKey=" + getColumnKey() + ", cloumnType=" + getCloumnType() + ", remark=" + getRemark() + "]";
    }
}
