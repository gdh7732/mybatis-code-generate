package com.mnt.generate.utils.properties;


public class DBPropertiesFileUtils {
    public static final String FILE_SUFFIX = ".jdbc.properties";


    private PropertiesUtil propertiesUtil;


    private DBPropertiesFileUtils(String fileName, boolean isCreate) {
        if (isCreate) {
            PropertiesUtil.createPropertiesFile(fileName + ".jdbc.properties");
        }

        this.propertiesUtil = PropertiesUtil.getInstance(fileName + ".jdbc.properties");
    }


    public static DBPropertiesFileUtils getInstance(String fileName) {
        return new DBPropertiesFileUtils(fileName, false);
    }


    public static DBPropertiesFileUtils createInstance(String fileName) {
        return new DBPropertiesFileUtils(fileName, true);
    }


    private String getByName(String key) {
        return this.propertiesUtil.getValueByKey(key).trim();
    }


    private void setByName(String key, String value) {
        this.propertiesUtil.setValue(key, value);
    }


    public String getUrl() {
        return getByName("jdbc.url");
    }


    public String getUserName() {
        return getByName("jdbc.username");
    }


    public String getPassword() {
        return getByName("jdbc.password");
    }


    public String getDriver() {
        return getByName("jdbc.driver");
    }


    public void setUrl(String url) {
        setByName("jdbc.url", url);
    }

    public void setUserName(String userName) {
        setByName("jdbc.username", userName);
    }

    public void setPassword(String password) {
        setByName("jdbc.password", password);
    }

    public void setDriver(String driver) {
        setByName("jdbc.driver", driver);
    }
}
