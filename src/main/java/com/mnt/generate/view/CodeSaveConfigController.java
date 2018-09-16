package com.mnt.generate.view;

import com.mnt.generate.service.OperaRecordService;
import com.mnt.gui.fx.base.BaseController;
import com.mnt.gui.fx.controls.dialog.DialogFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CodeSaveConfigController extends BaseController {
    @FXML
    private TextField txtPorjectPath;
    @FXML
    private TextField txtBizPath;
    @FXML
    private TextField txtApiPath;
    @FXML
    private TextField txtProviderPath;
    @FXML
    private TextField txtControllerPath;
    @FXML
    private TextField txtPagesPath;
    @FXML
    private TextField txtUser;
    private OperaRecordService operaPropertiesService;

    public CodeSaveConfigController() {
    }

    public void init() {
        super.init();
        this.operaPropertiesService = OperaRecordService.getInstance();
        this.txtPorjectPath.setText(this.operaPropertiesService.getProjectPath());
        this.txtBizPath.setText(this.operaPropertiesService.getBizPath());
        this.txtApiPath.setText(this.operaPropertiesService.getApiPath());
        this.txtProviderPath.setText(this.operaPropertiesService.getProviderPath());
        this.txtControllerPath.setText(this.operaPropertiesService.getControllerPath());
        this.txtPagesPath.setText(this.operaPropertiesService.getPagesPath());
        this.txtUser.setText(this.operaPropertiesService.getUser());
    }

    @FXML
    void processConfSave(ActionEvent event) {
        this.operaPropertiesService.saveProjectPath(this.txtPorjectPath.getText());
        this.operaPropertiesService.saveBizPath(this.txtBizPath.getText());
        this.operaPropertiesService.saveApiPath(this.txtApiPath.getText());
        this.operaPropertiesService.saveProviderPath(this.txtProviderPath.getText());
        this.operaPropertiesService.saveControllerPath(this.txtControllerPath.getText());
        this.operaPropertiesService.savePagesPath(this.txtPagesPath.getText());
        this.operaPropertiesService.saveUser(this.txtUser.getText());
        DialogFactory.getInstance().showSuccessMsg("保存成功", "配置保存成功", () -> {
        });
    }
}
