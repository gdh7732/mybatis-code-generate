package com.mnt.generate.utils.sql;

import java.sql.ResultSet;

public abstract interface QueryResult {
    public abstract void calback(ResultSet paramResultSet);
}

