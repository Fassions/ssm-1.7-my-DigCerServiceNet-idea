package cn.com.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordUtil {

	public static void createDoc(Map<String, Object> dataMap, Object obj, File file, String templateName) throws Exception {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(obj.getClass(), "/model");
			configuration.setNumberFormat("#");
			configuration.setDateFormat("yyyy-MM-dd");
			Template t = configuration.getTemplate(templateName);
			file.mkdirs();
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(osw);
			t.process(dataMap, bw);
		} finally {
			if (bw != null) {
				bw.close();
			}
			if (osw != null) {
				osw.close();			
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	public static File createDoc(Map<String, Object> dataMap, Object obj,String path, String fileName, String templateName) throws Exception {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		File file = null;
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setClassForTemplateLoading(obj.getClass(), "/model");
			configuration.setNumberFormat("#");
			configuration.setDateFormat("yyyy-MM-dd");
			Template t = configuration.getTemplate(templateName);
			file = new File(path);
			if(!file.exists()){
				file.mkdirs();
			}
			file = new File(path,fileName);
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, "utf-8");
			bw = new BufferedWriter(osw);
			t.process(dataMap, bw);
		} finally {
			if (bw != null) {
				bw.close();
			}
			if (osw != null) {
				osw.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		return file;
	}
	
}