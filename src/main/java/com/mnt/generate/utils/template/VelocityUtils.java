package com.mnt.generate.utils.template;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class VelocityUtils {
    private static final class SingletonHolder {
        private static final VelocityUtils INSTANCE = new VelocityUtils();
    }

    public static VelocityUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private VelocityUtils() {
        try {
            Properties p = new Properties();
            p.setProperty("file.resource.loader.path", System.getProperty("user.dir") + "/tmp/");
            Velocity.init(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void parseTemplate(String templateName, String toFilePath, Map<String, Object> params) {
        try {
            VelocityContext context = new VelocityContext();

            for (Map.Entry<String, Object> paramKV : params.entrySet()) {
                context.put((String) paramKV.getKey(), paramKV.getValue());
            }

            FileWriter fw = null;
            try {
                File f = new File(toFilePath);
                fw = new FileWriter(f, false);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Velocity.mergeTemplate(templateName, "UTF-8", context, fw);
            try {
                fw.flush();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Map<String, Object> params = new HashMap();
        params.put("test", "姜彪");
        getInstance().parseTemplate("test.vm", "E:/jiangroom/jm03/utils/codeGenerate/tmp/test.java", params);
    }
}

