package com.mnt.generate.view;

import com.mnt.gui.fx.base.BaseController;
import com.mnt.gui.fx.loader.FXMLLoaderUtil;
import com.mnt.gui.fx.view.anno.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;


@MainView(appName = "代码生成工具")
public class MainController extends BaseController {
    @FXML
    private Tab tabGenerateCode;
    @FXML
    private Tab tabDBConfig;
    @FXML
    private Tab tabCodeSaveConfig;

    public void init() {
        super.init();
        this.tabGenerateCode.setContent(FXMLLoaderUtil.load(CodeGenerateController.class));
        this.tabDBConfig.setContent(FXMLLoaderUtil.load(DBConfigController.class));
        this.tabCodeSaveConfig.setContent(FXMLLoaderUtil.load(CodeSaveConfigController.class));
    }
}
