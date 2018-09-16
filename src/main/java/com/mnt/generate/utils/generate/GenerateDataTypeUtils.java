package com.mnt.generate.utils.generate;


public class GenerateDataTypeUtils {
    public static String getJavaTypeByPostgresql(String typeName) {
        if (typeName.equals("varchar"))
            return "String";
        if (typeName.equals("int8"))
            return "Long";
        if (typeName.equals("int4"))
            return "Integer";
        if (typeName.equals("bool"))
            return "Boolean";
        if (typeName.equals("float4"))
            return "Float";
        if (typeName.equals("float8"))
            return "Double";
        if (typeName.equals("timestamp"))
            return "Date";
        if (typeName.equals("bpchar"))
            return "String";
        if (typeName.equals("date")) {
            return "Date";
        }

        return "String";
    }


    public static String getJdbcTypeByPostgresql(String typeName) {
        if (typeName.equals("varchar"))
            return "VARCHAR";
        if (typeName.equals("int8"))
            return "BIGINT";
        if (typeName.equals("int4"))
            return "INTEGER";
        if (typeName.equals("bool"))
            return "BIT";
        if (typeName.equals("float4"))
            return "REAL";
        if (typeName.equals("float8"))
            return "DOUBLE";
        if (typeName.equals("timestamp"))
            return "TIMESTAMP";
        if (typeName.equals("bpchar"))
            return "CHAR";
        if (typeName.equals("date")) {
            return "DATE";
        }

        return "VARCHAR";
    }
}
