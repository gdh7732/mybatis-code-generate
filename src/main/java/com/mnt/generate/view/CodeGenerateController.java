package com.mnt.generate.view;

import com.mnt.generate.core.GenerateClassLoad;
import com.mnt.generate.model.DBCloumn;
import com.mnt.generate.model.DBModel;
import com.mnt.generate.service.DBManagerService;
import com.mnt.generate.service.DBPropertiesService;
import com.mnt.generate.service.OperaRecordService;
import com.mnt.generate.utils.generate.GenerateDataTypeUtils;
import com.mnt.generate.utils.generate.GenerateNameUtils;
import com.mnt.generate.vo.TableColumnVO;
import com.mnt.generate.vo.TableNameVO;
import com.mnt.gui.fx.base.BaseController;
import com.mnt.gui.fx.controls.dialog.DialogFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CodeGenerateController extends BaseController {
    @FXML
    private ChoiceBox<String> choiceDBList;
    @FXML
    private ListView<TableNameVO> listTableName;
    @FXML
    private TableView<TableColumnVO> tableFields;
    @FXML
    private TableColumn<TableColumnVO, String> tcolCloumnName;
    @FXML
    private TableColumn<TableColumnVO, String> tcolRemark;
    @FXML
    private TableColumn<TableColumnVO, String> tcolCloumnType;
    @FXML
    private TableColumn<TableColumnVO, Integer> tcolLength;
    @FXML
    private TextField txtSearch;
    private ObservableList<TableNameVO> itemTableNames;
    private DBPropertiesService dbPropertiesService;
    private ObservableList<String> itemDBConfigs;
    private OperaRecordService operaPropertiesService;
    private ObservableList<TableNameVO> searchItemTableNames = FXCollections.observableArrayList();

    public CodeGenerateController() {
    }

    public void init() {
        super.init();
        this.operaPropertiesService = OperaRecordService.getInstance();
        this.itemDBConfigs = DBManagerService.getInstance().getItems();
        this.tcolCloumnName.setCellValueFactory(new PropertyValueFactory("cloumnName"));
        this.tcolRemark.setCellValueFactory(new PropertyValueFactory("remark"));
        this.tcolCloumnType.setCellValueFactory(new PropertyValueFactory("cloumnType"));
        this.tcolLength.setCellValueFactory(new PropertyValueFactory("length"));
        this.listTableName.setCellFactory((vo) -> {
            return new TableNameCell();
        });
        this.listTableName.getSelectionModel().selectedItemProperty().addListener(new TableNameChangeListener());
        this.choiceDBList.setItems(this.itemDBConfigs);
        this.choiceDBList.getSelectionModel().selectedItemProperty().addListener(new DBChangeListener());
        this.choiceDBList.getSelectionModel().select(this.operaPropertiesService.getLastLinkDB());
        this.txtSearch.textProperty().addListener(new SearchChangeListener());
    }

    @FXML
    void processGenerate(ActionEvent event) {
        if (this.itemTableNames != null) {
            if (this.itemTableNames.isEmpty()) {
                DialogFactory.getInstance().showFaildMsg("生成错误", "当前数据库没有表", () -> {
                });
            } else {
                List<TableNameVO> selectTables = new ArrayList();
                this.itemTableNames.forEach((vo) -> {
                    if (vo.getCheck().booleanValue()) {
                        selectTables.add(vo);
                    }

                });
                if (selectTables.isEmpty()) {
                    DialogFactory.getInstance().showFaildMsg("生成错误", "请选择生成的数据库表格", () -> {
                    });
                } else {
                    try {
                        List<DBModel> dbModels = new ArrayList();
                        for (TableNameVO tableNameVO : selectTables) {
                            DBModel dbModel = new DBModel();
                            dbModel.setTableName(tableNameVO.getTableName());
                            dbModel.setRemark(tableNameVO.getRemark());
                            List<DBCloumn> dbCloumns = new ArrayList();
                            dbModel.setDbCloumns(dbCloumns);
                            List<TableColumnVO> tableColumnVOs = this.dbPropertiesService.listTableCloumn(tableNameVO.getTableName());
                            for (TableColumnVO tableColumnVO : tableColumnVOs) {
                                DBCloumn dbCloumn = new DBCloumn();
                                dbCloumn.setCloumnName(tableColumnVO.getCloumnName());
                                dbCloumn.setCloumnType(tableColumnVO.getCloumnType());
                                dbCloumn.setRemark(tableColumnVO.getRemark());
                                dbCloumn.setCloumnJavaName(GenerateNameUtils.getJavaName(tableColumnVO.getCloumnName()));
                                dbCloumn.setCloumnJavaType(GenerateDataTypeUtils.getJavaTypeByPostgresql(tableColumnVO.getCloumnType()));
                                dbCloumn.setMethodName(GenerateNameUtils.getClassFileName(tableColumnVO.getCloumnName()));
                                dbCloumn.setCloumnJdbcType(GenerateDataTypeUtils.getJdbcTypeByPostgresql(tableColumnVO.getCloumnType()));
                                dbCloumns.add(dbCloumn);
                            }
                            dbModels.add(dbModel);
                        }
                        GenerateClassLoad.BASE_CODE_GENERATE_LOAD.getScripts().forEach((script) -> {
                            script.generate(dbModels);
                        });
                        DialogFactory.getInstance().showSuccessMsg("保存成功", "代码生成成功", () -> {
                        });
                    } catch (Exception e) {
                        DialogFactory.getInstance().showSuccessMsg("异常", e.getCause().toString(), () -> {
                        });
                    } finally {
                    }
                }
            }
        } else {
            DialogFactory.getInstance().showFaildMsg("生成错误", "当前数据库没有表", () -> {
            });
        }

    }

    @FXML
    void processSelAll(ActionEvent event) {
        if (this.itemTableNames != null) {
            Iterator var3 = this.itemTableNames.iterator();

            while (var3.hasNext()) {
                TableNameVO tableNameVO = (TableNameVO) var3.next();
                tableNameVO.setCheck(true);
            }
        }

    }

    @FXML
    void processUnsel(ActionEvent event) {
        if (this.itemTableNames != null) {
            Iterator var3 = this.itemTableNames.iterator();

            while (var3.hasNext()) {
                TableNameVO tableNameVO = (TableNameVO) var3.next();
                tableNameVO.setCheck(false);
            }
        }

    }

    @FXML
    void processUpdate(ActionEvent event) {
        if (this.dbPropertiesService != null) {
            this.itemTableNames = this.dbPropertiesService.listTableName();
            this.listTableName.setItems(this.itemTableNames);
            DialogFactory.getInstance().showSuccessMsg("刷新成功", "数据刷新成功", () -> {
            });
        } else {
            DialogFactory.getInstance().showFaildMsg("刷新错误", "请选择数据库", () -> {
            });
        }
    }

    class TableNameCell extends ListCell<TableNameVO> {
        @Override
        protected void updateItem(TableNameVO vo, boolean empty) {
            super.updateItem(vo, empty);
            if (!empty) {
                HBox hbox = new HBox(2.0D);
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().bindBidirectional(vo.checkProperty());
                Label lbl = new Label(vo.getTableName() + "(" + vo.getRemark() + ")");
                hbox.getChildren().add(checkBox);
                hbox.getChildren().add(lbl);
                this.setGraphic(hbox);
            } else {
                this.setGraphic(null);
            }
        }
    }

    class TableNameChangeListener implements ChangeListener<TableNameVO> {
        @Override
        public void changed(ObservableValue<? extends TableNameVO> observable, TableNameVO oldValue, TableNameVO newValue) {
            if (newValue != null) {
                tableFields.setItems(dbPropertiesService.listTableCloumn(newValue.getTableName()));
            }
        }
    }

    class DBChangeListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue != null) {
                dbPropertiesService = DBPropertiesService.getInstance(newValue);
                listTableName.setItems(dbPropertiesService.listTableName());
                operaPropertiesService.saveLastLinkDB(newValue);
            } else if (tableFields.getColumns() != null) {
                tableFields.getColumns().clear();
            }

        }
    }

    class SearchChangeListener implements ChangeListener<String> {
        @Override
        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
            if (newValue != null) {
                if (newValue.equals("")) {
                    searchItemTableNames.clear();
                    listTableName.setItems(itemTableNames);
                } else {
                    searchItemTableNames.clear();
                    searchItemTableNames.addAll(itemTableNames.filtered(new TableNamePredicate(newValue)));
                    listTableName.setItems(searchItemTableNames);
                }
            } else {
                searchItemTableNames.clear();
                listTableName.setItems(itemTableNames);
            }

        }

        class TableNamePredicate implements Predicate<TableNameVO> {
            private String txtSearch;

            TableNamePredicate(String var2) {
                this.txtSearch = var2;
            }

            @Override
            public boolean test(TableNameVO t) {
                return t.getTableName().contains(txtSearch) || (t.getRemark() != null && t.getRemark().contains(txtSearch));
            }
        }
    }
}

