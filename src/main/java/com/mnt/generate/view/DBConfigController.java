//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mnt.generate.view;

import com.mnt.generate.service.DBManagerService;
import com.mnt.generate.service.OperaRecordService;
import com.mnt.generate.utils.properties.DBPropertiesFileUtils;
import com.mnt.generate.utils.sql.PostgresUtils;
import com.mnt.gui.fx.base.BaseController;
import com.mnt.gui.fx.controls.dialog.DialogFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;

public class DBConfigController extends BaseController {
    @FXML
    private Button btnDelDB;
    @FXML
    private ListView<String> listDBConfigs;
    @FXML
    private TextField txtDBName;
    @FXML
    private TextField txtDBDriver;
    @FXML
    private TextField txtDBUrl;
    @FXML
    private TextField txtDBUserName;
    @FXML
    private PasswordField pwdDBPwd;
    private DBPropertiesFileUtils dbSavePropertiesService;
    private OperaRecordService operaPropertiesService;
    private ObservableList<String> itemDBConfigs;

    public DBConfigController() {
    }

    public void init() {
        super.init();
        this.operaPropertiesService = OperaRecordService.getInstance();
        this.itemDBConfigs = DBManagerService.getInstance().getItems();
        this.listDBConfigs.setItems(this.itemDBConfigs);
        this.listDBConfigs.getSelectionModel().selectedItemProperty().addListener(new DBConfigControllerOne());
        this.listDBConfigs.getSelectionModel().select(this.operaPropertiesService.getLastLinkDB());
    }

    @FXML
    void processAddDB(ActionEvent event) {
        this.txtDBName.clear();
        this.txtDBDriver.clear();
        this.txtDBUrl.clear();
        this.txtDBUserName.clear();
        this.pwdDBPwd.clear();
        this.listDBConfigs.getSelectionModel().clearSelection();
    }

    @FXML
    void processDBSave(ActionEvent event) {
        if (this.listDBConfigs.getSelectionModel().getSelectedItem() == null) {
            DBManagerService.getInstance().add(this.txtDBName.getText(), this.txtDBDriver.getText(), this.txtDBUrl.getText(), this.txtDBUserName.getText(), this.pwdDBPwd.getText());
            DialogFactory.getInstance().showSuccessMsg("添加成功", "添加数据库连接成功", () -> {
            });
        } else {
            this.dbSavePropertiesService.setDriver(this.txtDBDriver.getText());
            this.dbSavePropertiesService.setUrl(this.txtDBUrl.getText());
            this.dbSavePropertiesService.setUserName(this.txtDBUserName.getText());
            this.dbSavePropertiesService.setPassword(this.pwdDBPwd.getText());
            DialogFactory.getInstance().showSuccessMsg("保存成功", "保存数据库配置成功", () -> {
            });
        }

    }

    @FXML
    void processDBTest(ActionEvent event) {
        Connection collection = PostgresUtils.getConnection(this.txtDBUrl.getText(), this.txtDBUserName.getText(), this.pwdDBPwd.getText(), this.txtDBDriver.getText());
        if (collection != null) {
            DialogFactory.getInstance().showSuccessMsg("测试成功", "连接成功", () -> {
            });
        } else {
            DialogFactory.getInstance().showFaildMsg("测试失败", "连接失败", () -> {
            });
        }

    }

    @FXML
    void processDelDB(ActionEvent event) {
        String name = (String)this.listDBConfigs.getSelectionModel().getSelectedItem();
        if (name != null) {
            DBManagerService.getInstance().del(name);
            DialogFactory.getInstance().showSuccessMsg("删除成功", "配置删除成功", () -> {
            });
        }

    }

    private void selectDB(String name) {
        this.dbSavePropertiesService = DBPropertiesFileUtils.getInstance(name);
        this.txtDBName.setText(name);
        this.txtDBDriver.setText(this.dbSavePropertiesService.getDriver());
        this.txtDBUrl.setText(this.dbSavePropertiesService.getUrl());
        this.txtDBUserName.setText(this.dbSavePropertiesService.getUserName());
        this.pwdDBPwd.setText(this.dbSavePropertiesService.getPassword());
    }

    class DBConfigControllerOne implements ChangeListener<String> {

        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue != null) {
                selectDB(newValue);
                operaPropertiesService.saveLastLinkDB(newValue);
            }
        }
    }
}
