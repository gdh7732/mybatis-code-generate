package com.mnt.generate.init;

import com.mnt.generate.core.GenerateClassLoad;
import com.mnt.gui.fx.init.InitContext;
import com.mnt.gui.fx.loader.classload.ClassLoadUtil;
import org.apache.log4j.Logger;

import java.net.URLClassLoader;
import java.util.List;

public class StartInit extends InitContext {
    private static final Logger log = Logger.getLogger(StartInit.class);

    public void afterInitView(URLClassLoader classLoad) {
        try {
            ClassLoadUtil.loadClass(GenerateClassLoad.class, classLoad);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("启动失败", e);
        }
    }

    public void init(List<Class<?>> classes, URLClassLoader classLoad) {
    }

    public void shutdown() {
    }
}
