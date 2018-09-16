package com.mnt.generate.utils.sql;

import com.mnt.gui.fx.controls.dialog.DialogFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class SqlExecuteUtils {
    private static Logger log = Logger.getLogger(SqlExecuteUtils.class);


    public static void query(Connection conn, String sql, QueryResult queryResult) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            queryResult.calback(rs);
            rs.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            int index = sql.lastIndexOf("where");
            DialogFactory.getInstance().showFaildMsg("查询失败", sql.substring(index), () -> {
            });
            log.error("查询出错", e);
            e.printStackTrace();
        }
    }
}
