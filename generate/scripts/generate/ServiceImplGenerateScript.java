package generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mnt.generate.core.BaseCodeGenerate;
import com.mnt.generate.model.DBCloumn;
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
public class ServiceImplGenerateScript extends BaseCodeGenerate {

	/**
	 * 需要过滤的字段
	 */
	private List<String> filterColumns = new ArrayList<>(); 
	
	public ServiceImplGenerateScript() {
		
		filterColumns.add("user_create");
		filterColumns.add("user_modified");
		filterColumns.add("gmt_create");
		filterColumns.add("gmt_modified");
		filterColumns.add("del_flag");
		filterColumns.add("company_flag");
		filterColumns.add("remark");
		
	}
	
	@Override
	public String getGeneratePath() {
		String path;
		String projectPath = operaPropertiesService.getProjectPath();
		String bizPath = operaPropertiesService.getBizPath();
		projectPath = projectPath.replaceAll("\\\\", "/");
		bizPath = bizPath.replaceAll("\\\\", "/");
		bizPath = bizPath.replaceAll("\\.", "/");
		
		path = projectPath +  bizPath + "/manager/impl/";
		
		return path;
	}

	@Override
	public void generateImpl(DBModel dbModel) {
		
		//文件名
		String fileName = GenerateNameUtils.getClassFileName(dbModel.getTableName());
		//类名
		String className = fileName + "ServiceImpl";
		//生成文件路径
		String generateFilePath = getGeneratePath() + className + ".java";
		//对应的接口名称
		String serviceClassName = "I" + fileName + "Service";
		//对应的dao名称
		String daoClassName = fileName + "Mapper";
		String daoJavaName = GenerateNameUtils.getJavaName(dbModel.getTableName()) + "Mapper";
		
		
		String queryEntity = fileName + "Query";
		String queryPackage = getQueryPackagePath() + "." + fileName + "Query";
		
		List<DBCloumn> dbCloumns = new ArrayList<>();
		for (DBCloumn dbCloumn : dbModel.getDbCloumns()) {
			if(!filterColumns.contains(dbCloumn.getCloumnName())) {
				dbCloumns.add(dbCloumn);
			}
		}
		
		//写入参数
		Map<String, Object> params = new HashMap<>();
		
		
		params.put("dbCloumns", dbCloumns);
		params.put("user", operaPropertiesService.getUser());
		params.put("className", className);
		params.put("remark", dbModel.getRemark());
		params.put("date", TimeUtils.formatDate(null, null));
		params.put("entityName", fileName);
		
		params.put("entityPackagePath", getEntityPackagePath() + "." + fileName);
		params.put("package", getPackagePath());
		
		params.put("queryEntity", queryEntity);
		params.put("queryPackage", queryPackage);
		
		params.put("serviceClassName", serviceClassName);
		params.put("daoClassName", daoClassName);
		params.put("daoJavaName", daoJavaName);
		params.put("daoPackagePath", getDaoPackagePath() + "." + daoClassName);
		params.put("servicePackagePath", getServicePackagePath() + "." + serviceClassName);
		
		params.put("holdCode", getHoldCode(generateFilePath)); // 保留代码
		
		
		VelocityUtils.getInstance().parseTemplate(getTemplateName(), generateFilePath, params);
		
	}
	
	/**
	 * 获取dao的包路径
	 * @return
	 */
	private String getDaoPackagePath() {
		String bizPath = operaPropertiesService.getBizPath();
		bizPath = bizPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = bizPath.lastIndexOf("/");
		
		String packagePath = bizPath.substring(packageStartIndex + 1, bizPath.length());
		
		packagePath = packagePath + ".dao";
		
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
		return "serviceImpl.vm";
	}

	@Override
	public String getPackagePath() {
		String bizPath = operaPropertiesService.getBizPath();
		bizPath = bizPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = bizPath.lastIndexOf("/");
		
		String packagePath = bizPath.substring(packageStartIndex + 1, bizPath.length());
		
		packagePath = packagePath + ".manager.impl";
		
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
