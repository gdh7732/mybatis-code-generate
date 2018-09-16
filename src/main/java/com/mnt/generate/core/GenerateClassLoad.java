
package com.mnt.generate.core;

import com.mnt.gui.fx.loader.classload.ClassLoad;
import com.mnt.gui.fx.loader.classload.ClassLoadSupport;

public class GenerateClassLoad {
    @ClassLoad(srcPath = "scripts/generate"
    )
    public static ClassLoadSupport<BaseCodeGenerate> BASE_CODE_GENERATE_LOAD;

    public GenerateClassLoad() {
    }
}
