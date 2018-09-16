package com.mnt.generate.core;

import com.mnt.generate.model.DBModel;
import com.mnt.generate.service.OperaRecordService;
import com.mnt.gui.fx.controls.dialog.DialogFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public abstract class BaseCodeGenerate {
    protected OperaRecordService operaPropertiesService;
    protected static final String APPEND_START = "append__start";
    protected static final String APPEND_END = "append__end";

    public BaseCodeGenerate() {
        this.operaPropertiesService = OperaRecordService.getInstance();
    }

    public final void generate(DBModel dbModel) {
        checkAndCreateDir(getGeneratePath());
        generateImpl(dbModel);
    }

    protected abstract void generateImpl(DBModel paramDBModel);

    public final void generate(List<DBModel> dbModels) {
        for (DBModel dbModel : dbModels) {
            generate(dbModel);
        }
    }

    public abstract String getGeneratePath();

    public abstract String getTemplateName();

    public abstract String getPackagePath();

    public String getEntityPackagePath() {
        String apiPath = this.operaPropertiesService.getApiPath();
        apiPath = apiPath.replaceAll("\\\\", "/");
        int packageStartIndex = apiPath.lastIndexOf("/");
        String packagePath = apiPath.substring(packageStartIndex + 1, apiPath.length());
        packagePath = packagePath + ".entity";
        return packagePath;
    }

    public String getHoldCode(String filePath) {
        File file = new File(filePath);
        StringBuilder sbResult = new StringBuilder("");
        if (file.exists()) {
            try {
                boolean isStart = false;
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                while ((line = br.readLine()) != null) {
                    if (line.contains("append__end")) {
                        break;
                    }
                    if (isStart) {
                        sbResult.append(line + "\n");
                    }
                    if (line.contains("append__start")) {
                        isStart = true;
                    }
                }
                br.close();
                fr.close();
                return sbResult.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public void checkAndCreateDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            DialogFactory.getInstance().showSuccessMsg("file", filePath, () -> {
            });
            file.mkdirs();
        }
    }

}

