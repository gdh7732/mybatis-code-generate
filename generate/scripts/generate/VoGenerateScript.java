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
 * VO代码生成
 *
 * @author jiangbiao
 * @Date 2017年8月9日下午3:31:07
 */
public class VoGenerateScript extends BaseCodeGenerate {

	/**
	 * 需要过滤的字段
	 */
	private List<String> filterColumns = new ArrayList<>(); 
	
	
	/**
	 * 需要引包的类型
	 */
	private Map<String, String> importColumns = new HashMap<>();
	
	
	public VoGenerateScript() {
		filterColumns.add("user_create");
		filterColumns.add("user_modified");
		filterColumns.add("gmt_create");
		filterColumns.add("gmt_modified");
		filterColumns.add("del_flag");
		filterColumns.add("company_flag");
		filterColumns.add("remark");
		
		importColumns.put("Date", "java.util.Date");
		
	}
	
	
	@Override
	public String getGeneratePath() {
		String path;
		String projectPath = operaPropertiesService.getProjectPath();
		String apiPath = operaPropertiesService.getApiPath();
		projectPath = projectPath.replaceAll("\\\\", "/");
		apiPath = apiPath.replaceAll("\\\\", "/");
		apiPath = apiPath.replaceAll("\\.", "/");
		path = projectPath +  apiPath + "/vo/";
		
		return path;
	}
	
	@Override
	public void generateImpl(DBModel dbModel) {
		//文件名
		String fileName = GenerateNameUtils.getClassFileName(dbModel.getTableName());
		//类名
		String className = fileName + "VO";
		//生成文件路径
		String generateFilePath = getGeneratePath() + className + ".java";
		
		//写入参数
		Map<String, Object> params = new HashMap<>();
		
		params.put("user", operaPropertiesService.getUser());
		params.put("className", className);
		params.put("remark", dbModel.getRemark());
		params.put("date", TimeUtils.formatDate(null, null));
		params.put("package", getPackagePath());
		
		List<DBCloumn> dbCloumns = new ArrayList<>();
		List<String> importPackages = new ArrayList<>();
		
		for (DBCloumn dbCloumn : dbModel.getDbCloumns()) {
			if(!filterColumns.contains(dbCloumn.getCloumnName())) {
				
				//判断是否需要引入包
				if(importColumns.containsKey(dbCloumn.getCloumnJavaType())) {
					String packageName = importColumns.get(dbCloumn.getCloumnJavaType());
					if(!importPackages.contains(packageName)) {
						importPackages.add(packageName);
					}
				}
				
				dbCloumns.add(dbCloumn);
			}
		}
		params.put("dbCloumns", dbCloumns);
		params.put("importPackages", importPackages);
		
		params.put("holdCode", getHoldCode(generateFilePath)); // 保留代码
		
		VelocityUtils.getInstance().parseTemplate(getTemplateName(), generateFilePath, params);
	}

	@Override
	public String getTemplateName() {
		return "vo.vm";
	}

	@Override
	public String getPackagePath() {
		String apiPath = operaPropertiesService.getApiPath();
		apiPath = apiPath.replaceAll("\\\\", "/");
		
		int packageStartIndex = apiPath.lastIndexOf("/");
		
		String packagePath = apiPath.substring(packageStartIndex + 1, apiPath.length());
		
		packagePath = packagePath + ".vo";
		
		return packagePath;
	}
	
}
