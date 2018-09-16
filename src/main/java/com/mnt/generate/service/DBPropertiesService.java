package com.mnt.generate.service;

import com.mnt.generate.utils.properties.DBPropertiesFileUtils;
import com.mnt.generate.utils.sql.PostgresUtils;
import com.mnt.generate.utils.sql.SqlExecuteUtils;
import com.mnt.generate.vo.TableColumnVO;
import com.mnt.generate.vo.TableNameVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class DBPropertiesService {
    private DBPropertiesFileUtils propertiesService;

    private DBPropertiesService(String jdbcFileName) {
        this.propertiesService = DBPropertiesFileUtils.getInstance(jdbcFileName);
    }

    public static DBPropertiesService getInstance(String jdbcFileName) {
        return new DBPropertiesService(jdbcFileName);
    }

    private Connection getConnection() {
        return PostgresUtils.getConnection(this.propertiesService.getUrl(), this.propertiesService.getUserName(), this.propertiesService.getPassword(), this.propertiesService.getDriver());
    }

    public ObservableList<TableNameVO> listTableName() {
        ObservableList<TableNameVO> result = FXCollections.observableArrayList();
        String url = this.propertiesService.getUrl();
        int index = url.lastIndexOf("/");
        String schema = url.substring(index + 1);
        String sql = "select table_name,table_comment as remark from information_schema.tables where table_schema='" + schema + "' and table_type = 'base table'";
        SqlExecuteUtils.query(this.getConnection(), sql, (rs) -> {
            while (true) {
                try {
                    if (rs.next()) {
                        TableNameVO vo = new TableNameVO();
                        vo.setTableName(rs.getString("table_name"));
                        vo.setRemark(rs.getString("remark"));
                        result.add(vo);
                        continue;
                    }
                } catch (SQLException var3) {
                    var3.printStackTrace();
                }
                return;
            }
        });
        return result;
    }

    public ObservableList<TableColumnVO> listTableCloumn(String tableName) {
        ObservableList<TableColumnVO> result = FXCollections.observableArrayList();
        String sql = "select column_name,data_type,column_comment as remark from information_schema.columns where table_name = '" + tableName + "'";
        SqlExecuteUtils.query(this.getConnection(), sql, (rs) -> {
            try {
                while (rs.next()) {
                    TableColumnVO vo = new TableColumnVO();
                    vo.setCloumnName(rs.getString("column_name"));
                    vo.setCloumnType(rs.getString("data_type"));
                    vo.setRemark(rs.getString("remark"));
                    result.add(vo);
                }
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        });
        return result;
    }

    public static void main(String[] args) {
        getInstance("jdbc.properties").listTableName();
        getInstance("jdbc.properties").listTableCloumn("parking_space");
    }
}
