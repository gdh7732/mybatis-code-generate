package com.mnt.generate.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {
    private final Properties properties = new Properties();
    private final File file;
    private final Map<String, String> allPropertiesMap = new HashMap();

    private PropertiesUtil(File file) {
        this.file = file;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var4) {
            }
        }

        try {
            this.properties.load(new FileInputStream(file));
        } catch (IOException var3) {
        }

        this.parserCurrFile(file);
    }

    public final String getValueByKey(String key) {
        return this.getValueByKey(key, (String)null);
    }

    public final String getValueByKey(String key, String defaultValue) {
        return this.allPropertiesMap.containsKey(key) ? (String)this.allPropertiesMap.get(key) : defaultValue;
    }

    private void parserCurrFile(File file) {
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            properties.load(fis);
            Enumeration enu = properties.propertyNames();

            while(enu.hasMoreElements()) {
                String key = (String)enu.nextElement();
                this.allPropertiesMap.put(key, properties.getProperty(key));
            }
        } catch (IOException var14) {
            ;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException var13) {
                }
            }

        }

    }

    public static final void createPropertiesFile(String fileName) {
        String filePath = getCurrConfPath() + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException var4) {
            }
        }

    }

    public static final PropertiesUtil getInstance(String fileName) {
        return new PropertiesUtil(new File(getCurrConfPath() + fileName));
    }

    protected static String getCurrConfPath() {
        return System.getProperty("user.dir") + "/conf/";
    }

    private synchronized void save() {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(this.file);
            this.properties.store(outputStream, (String)null);
        } catch (IOException var11) {
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var10) {
                }
            }

        }

    }

    public final String getByKey(String key, String defaultValue) {
        return this.properties.containsKey(key) ? this.properties.getProperty(key) : defaultValue;
    }

    public final void setValue(String key, String value) {
        this.properties.put(key, value);
        this.allPropertiesMap.put(key, value);
        this.save();
    }
}
