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
 * service 实现 代码生成
 * @author jiangbiao
 * @Date 2017年8月10日下午3:17:36
 */
public class ApiImplGenerateScript extends BaseCodeGenerate {

	@Override
	public String getGeneratePath() {
		String path;
		String projectPath = operaPropertiesService.getProjectPath();
		String provider = operaPropertiesService.getProviderPath();
		projectPath = projectPath.replaceAll("\\\\", "/");
		provider = provider.replaceAll("\\\\", "/");
		provider = provider.replaceAll("\\.", "/");
		
		path = projectPath +  provider + "/api/";
		
		return path;
	}

	@Override
	public void generateImpl(DBModel dbModel) {
		
		//文件名
		String fileName = GenerateNameUtils.getClassFileName(dbModel.getTableName());
		//类名
		String className = fileName + "Impl";
		//生成文件路径
		String generateFilePath = getGeneratePath() + className + ".java";
		//对应的接口名称
		String apiClassName = fileName + "Api";
		//对应的dao名称
		String serviceClassName = "I" + fileName + "Service";
		String serviceJavaName = GenerateNameUtils.getJavaName(dbModel.getTableName()) + "Service";
		
		String queryEntity = fileName + "Query";
		String queryPackage = getQueryPackagePath() + "." + fileName + "Query";
		
		//写入参数
		Map<String, Object> params = new HashMap<>();
		
		
		params.put("user", operaPropertiesService.getUser());
		params.put("className", className);
		params.put("remark", dbModel.getRemark());
		params.put("date", TimeUtils.formatDate(null, null));
		params.put("entityName", fileName);
		
		params.put("entityPackagePath", getEntityPackagePath() + "." + fileName);
		params.put("package", getPackagePath());
		
		params.put("queryEntity", queryEntity);
		params.put("queryPackage", queryPackage);
		
		params.put("apiClassName", apiClassName);
		params.put("serviceClassName", serviceClassName);
		params.put("serviceJavaName", serviceJavaName);
		params.put("servicePackagePath", getServicePackagePath() + "." + serviceClassName);
		params.put("apiPackagePath", getApiPackagePath() + "." + apiClassName);
		
		params.put("holdCode", getHoldCode(generateFilePath)); // 保留代码
		
		
		VelocityUtils.getInstance().parseTemplate(getTemplateName(), generateFilePath, params);
		
	}
	
	/**
	 * 获取dao的包路径
	 * @return
	 */
	private String getApiPackagePath() {
		String apiPath = operaPropertiesService.getApiPath();
		apiPath = apiPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = apiPath.lastIndexOf("/");
		
		String packagePath = apiPath.substring(packageStartIndex + 1, apiPath.length());
		
		packagePath = packagePath + ".api";
		
		return packagePath;
	}
	

	/**
	 * 获取service的包路径
	 * @return
	 */
	private String getServicePackagePath() {
		String bizPath = operaPropertiesService.getBizPath();
		bizPath = bizPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = bizPath.lastIndexOf("/");
		
		String packagePath = bizPath.substring(packageStartIndex + 1, bizPath.length());
		
		packagePath = packagePath + ".manager";
		
		return packagePath;
	}
	
	@Override
	public String getTemplateName() {
		return "apiImpl.vm";
	}

	@Override
	public String getPackagePath() {
		String providerPath = operaPropertiesService.getProviderPath();
		providerPath = providerPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = providerPath.lastIndexOf("/");
		
		String packagePath = providerPath.substring(packageStartIndex + 1, providerPath.length());
		
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
