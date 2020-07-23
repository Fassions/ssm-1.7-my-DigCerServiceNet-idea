package cn.com.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import cn.com.common.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共静态对象类
 * 
 * @author
 * 
 */
public class CommonProperty {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonProperty.class);

	public static final Properties applicationProperties = getApplicationProperties();
	
	/**
	 * 得到 application.properties 资源文件对象,剔除掉 # 开头的行
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Properties getApplicationProperties() {
		logger.info("------------getApplicationProperties()方法被调用------------");
		Properties p = new Properties();
		FileInputStream fis = null;
		try {
			String se = System.getProperty("file.separator");
			String path = System.getProperty("DigCerServiceNet") + se + "WEB-INF" + se + "properties" + se + "application.properties";
			fis = new FileInputStream(new File(path));
			p.load(fis);
		} catch (Exception e) {
			logger.error(CommonUtils.geDetailException(e));
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
				logger.error(CommonUtils.geDetailException(e));
			}
		}
		Properties temp = new Properties();
		for (Object key : p.keySet()) {
			if (!key.toString().startsWith("#")) {
				temp.setProperty(key.toString(), p.get(key).toString());
			}
		}
		return temp;
	}
	
}