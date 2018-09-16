package com.mnt.generate.service;

import com.mnt.generate.utils.properties.DBPropertiesFileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileFilter;

public class DBManagerService {
    private ObservableList<String> listDBNames = FXCollections.observableArrayList();
    private static final DBManagerService INSTANCE = new DBManagerService();

    private DBManagerService() {
        File fileDir = new File(System.getProperty("user.dir") + "/conf");
        File[] files = fileDir.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".jdbc.properties")) {
                    return true;
                }
                return false;
            }
        });
        File[] arrayOfFile1;
        int j = (arrayOfFile1 = files).length;
        for (int i = 0; i < j; i++) {
            File file = arrayOfFile1[i];
            this.listDBNames.add(file.getName().replace(".jdbc.properties", ""));
        }
    }

    private File getFile(String name) {
        return new File(System.getProperty("user.dir") + "/conf" + name + ".jdbc.properties");
    }

    public static DBManagerService getInstance() {
        return INSTANCE;
    }

    public ObservableList<String> getItems() {
        return this.listDBNames;
    }

    public void del(String name) {
        this.listDBNames.remove(name);
        getFile(name).delete();
    }

    public void add(String name, String driver, String url, String userName, String password) {
        DBPropertiesFileUtils dbPropertiesService = DBPropertiesFileUtils.createInstance(name);
        dbPropertiesService.setDriver(driver);
        dbPropertiesService.setUrl(url);
        dbPropertiesService.setUserName(userName);
        dbPropertiesService.setPassword(password);
        this.listDBNames.add(name);
    }
}
