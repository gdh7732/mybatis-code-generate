package com.mnt.generate.service;

import com.mnt.generate.utils.properties.PropertiesUtil;


public class OperaRecordService {
    private PropertiesUtil propertiesUtil;
    private static final OperaRecordService INSTANCE = new OperaRecordService();

    private OperaRecordService() {
        this.propertiesUtil = PropertiesUtil.getInstance("record.properties");
    }

    public static OperaRecordService getInstance() {
        return INSTANCE;
    }


    public void saveLastLinkDB(String dbName) {
        this.propertiesUtil.setValue("last.db.name", dbName);
    }

    public void saveProjectPath(String projectPath) {
        this.propertiesUtil.setValue("project.path", projectPath);
    }

    public void saveBizPath(String bizPath) {
        this.propertiesUtil.setValue("biz.path", bizPath);
    }

    public void saveApiPath(String apiPath) {
        this.propertiesUtil.setValue("api.path", apiPath);
    }

    public void saveProviderPath(String providerPath) {
        this.propertiesUtil.setValue("provider.path", providerPath);
    }

    public void saveControllerPath(String controllerPath) {
        this.propertiesUtil.setValue("controller.path", controllerPath);
    }

    public void savePagesPath(String pagesPath) {
        this.propertiesUtil.setValue("pages.path", pagesPath);
    }

    public void saveUser(String user) {
        this.propertiesUtil.setValue("user", user);
    }


    public String getLastLinkDB() {
        return this.propertiesUtil.getValueByKey("last.db.name");
    }

    public String getProjectPath() {
        return this.propertiesUtil.getValueByKey("project.path");
    }

    public String getBizPath() {
        return this.propertiesUtil.getValueByKey("biz.path");
    }

    public String getApiPath() {
        return this.propertiesUtil.getValueByKey("api.path");
    }

    public String getProviderPath() {
        return this.propertiesUtil.getValueByKey("provider.path");
    }

    public String getControllerPath() {
        return this.propertiesUtil.getValueByKey("controller.path");
    }

    public String getPagesPath() {
        return this.propertiesUtil.getValueByKey("pages.path");
    }

    public String getUser() {
        return this.propertiesUtil.getValueByKey("user");
    }
}
