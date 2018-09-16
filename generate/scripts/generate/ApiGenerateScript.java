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
 * service 代码生成
 * @author jiangbiao
 * @Date 2017年8月10日下午3:17:36
 */
public class ApiGenerateScript extends BaseCodeGenerate {

	@Override
	public String getGeneratePath() {
		String path;
		String projectPath = operaPropertiesService.getProjectPath();
		String apiPath = operaPropertiesService.getApiPath();
		projectPath = projectPath.replaceAll("\\\\", "/");
		apiPath = apiPath.replaceAll("\\\\", "/");
		apiPath = apiPath.replaceAll("\\.", "/");
		path = projectPath +  apiPath + "/api/";
		
		return path;
	}

	@Override
	public void generateImpl(DBModel dbModel) {
		
		//文件名
		String fileName = GenerateNameUtils.getClassFileName(dbModel.getTableName());
		//类名
		String className = fileName + "Api";
		//生成文件路径
		String generateFilePath = getGeneratePath() + className + ".java";
		
		String queryEntity = fileName + "Query";
		String queryPackage = getQueryPackagePath() + "." + fileName + "Query";
		
		//写入参数
		Map<String, Object> params = new HashMap<>();
		
		
		params.put("user", operaPropertiesService.getUser());
		params.put("className", className);
		params.put("remark", dbModel.getRemark());
		params.put("date", TimeUtils.formatDate(null, null));
		params.put("entityName", fileName);
		
		params.put("queryEntity", queryEntity);
		params.put("queryPackage", queryPackage);
		
		params.put("entityPackagePath", getEntityPackagePath() + "." + fileName);
		params.put("package", getPackagePath());
		
		params.put("holdCode", getHoldCode(generateFilePath)); // 保留代码
		
		VelocityUtils.getInstance().parseTemplate(getTemplateName(), generateFilePath, params);
		
	}
	
	@Override
	public String getTemplateName() {
		return "api.vm";
	}

	@Override
	public String getPackagePath() {
		String apiPath = operaPropertiesService.getApiPath();
		apiPath = apiPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = apiPath.lastIndexOf("/");
		
		String packagePath = apiPath.substring(packageStartIndex + 1, apiPath.length());
		
		packagePath = packagePath + ".api";
		
		return packagePath;
	}
	
	/**
	 * 获取查询实体的包名
	 * @return
	 */
	public String getQueryPackagePath() {
		String apiPath = operaPropertiesService.getApiPath();
		apiPath = apiPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = apiPath.lastIndexOf("/");
		
		String packagePath = apiPath.substring(packageStartIndex + 1, apiPath.length());
		
		packagePath = packagePath + ".query";
		
		return packagePath;
	}
	
}
