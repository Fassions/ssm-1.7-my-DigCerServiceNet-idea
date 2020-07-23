package cn.com.common.utils;

import cn.com.common.agent.BaseDomain;
import org.apache.commons.lang3.StringUtils;

/**
 * 工具类
 * 
 * @author Horace.zhang
 * 
 */
public class CommonUtils {

	/**
	 * 获取详细的异常信息
	 * 
	 * @param e
	 * @return
	 */
	public static String geDetailException(Exception e) {
		StackTraceElement[] stackTraceElements = e.getStackTrace();
		if (stackTraceElements != null) {
			int arrayLength = stackTraceElements.length;
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append(e.toString());
			stringBuffer.append("\n");
			for (int i = 0; i < arrayLength; i++) {
				StackTraceElement stackTraceElement = stackTraceElements[i];
				stringBuffer.append("	at ");
				stringBuffer.append(stackTraceElement.getClassName());
				stringBuffer.append(".");
				stringBuffer.append(stackTraceElement.getMethodName());
				stringBuffer.append("(");
				stringBuffer.append(stackTraceElement.getFileName());
				stringBuffer.append(":");
				stringBuffer.append(stackTraceElement.getLineNumber());
				stringBuffer.append(")");
				if (i + 1 != arrayLength) {
					stringBuffer.append("\n");
				}
			}
			return stringBuffer.toString();
		} else {
			return "";
		}
	}


	/**
	 * TODO:公共通用配件类型 productListId
	 */
	public static String[] getProductListId(){
		return new String[]{"0","1","2","3","4","5","6"};
	}


	/**
	 * 过滤斜杠，防止路径转义
	 * @param name
	 * @return
	 */
	public static String getFilterName(String name) throws Exception{
		if(StringUtils.isNotBlank(name)){
			name = name.replaceAll("/","").replaceAll("\\\\","");
		}
		return name;
	}
	
}