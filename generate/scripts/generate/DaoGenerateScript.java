package generate;

import java.util.HashMap;
import java.util.Map;

import com.mnt.generate.core.BaseCodeGenerate;
import com.mnt.generate.model.DBModel;
import com.mnt.generate.utils.TimeUtils;
import com.mnt.generate.utils.generate.GenerateNameUtils;
import com.mnt.generate.utils.template.VelocityUtils;


/**
 * 
 * dao 代码生成
 * @author jiangbiao
 * @Date 2017年8月8日下午3:17:36
 */
public class DaoGenerateScript extends BaseCodeGenerate {

	@Override
	public String getGeneratePath() {
		String path;
		String projectPath = operaPropertiesService.getProjectPath();
		String bizPath = operaPropertiesService.getBizPath();
		projectPath = projectPath.replaceAll("\\\\", "/");
		bizPath = bizPath.replaceAll("\\\\", "/");
		bizPath = bizPath.replaceAll("\\.", "/");
		
		path = projectPath +  bizPath + "/dao/";
		
		return path;
	}

	@Override
	public void generateImpl(DBModel dbModel) {
		
		//文件名
		String fileName = GenerateNameUtils.getClassFileName(dbModel.getTableName());
		//类名
		String className = fileName + "Mapper";
		//生成文件路径
		String generateFilePath = getGeneratePath() + className + ".java";
		
		
		
		//写入参数
		Map<String, Object> params = new HashMap<>();
		
		
		params.put("user", operaPropertiesService.getUser());
		params.put("className", className);
		params.put("remark", dbModel.getRemark());
		params.put("date", TimeUtils.formatDate(null, null));
		params.put("entityName", fileName);
		
		params.put("entityPackagePath", getEntityPackagePath() + "." + fileName);
		params.put("package", getPackagePath());
		
		params.put("holdCode", getHoldCode(generateFilePath)); // 保留代码
		
		VelocityUtils.getInstance().parseTemplate(getTemplateName(), generateFilePath, params);
		
	}
	
	@Override
	public String getTemplateName() {
		return "dao.vm";
	}

	@Override
	public String getPackagePath() {
		String bizPath = operaPropertiesService.getBizPath();
		bizPath = bizPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = bizPath.lastIndexOf("/");
		
		String packagePath = bizPath.substring(packageStartIndex + 1, bizPath.length());
		
		packagePath = packagePath + ".dao";
		
		return packagePath;
	}
	
}
