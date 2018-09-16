package com.mnt.generate.model;

public class DBCloumn {
    private String cloumnName;
    private Integer length;
    private String cloumnType;
    private String remark;
    private String cloumnJavaName;
    private String cloumnJavaType;
    private String cloumnJdbcType;
    private String methodName;

    public String getCloumnName() {
        return this.cloumnName;
    }

    public void setCloumnName(String cloumnName) {
        this.cloumnName = cloumnName;
    }

    public Integer getLength() {
        return this.length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getCloumnType() {
        return this.cloumnType;
    }

    public void setCloumnType(String cloumnType) {
        this.cloumnType = cloumnType;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCloumnJavaName() {
        return this.cloumnJavaName;
    }

    public void setCloumnJavaName(String cloumnJavaName) {
        this.cloumnJavaName = cloumnJavaName;
    }

    public String getCloumnJavaType() {
        return this.cloumnJavaType;
    }

    public void setCloumnJavaType(String cloumnJavaType) {
        this.cloumnJavaType = cloumnJavaType;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCloumnJdbcType() {
        return this.cloumnJdbcType;
    }

    public void setCloumnJdbcType(String cloumnJdbcType) {
        this.cloumnJdbcType = cloumnJdbcType;
    }
}
