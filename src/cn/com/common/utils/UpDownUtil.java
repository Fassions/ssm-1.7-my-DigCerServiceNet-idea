package cn.com.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 上传下载文件工具类
 * 
 * @author Horace.zhang
 * 
 */
public class UpDownUtil {
	
	/**
	 * 服务器提供文件下载
	 * 
	 * @param response
	 * @param file
	 * @throws Exception
	 */
	public static void downloadServer(HttpServletResponse response, File file, String fileName) throws Exception {
		InputStream fis = null;
		ServletOutputStream sos = null;
		try {
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/*");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("GBK"), "ISO8859-1"));
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setDateHeader("Expires", System.currentTimeMillis());
			fis = new FileInputStream(file);
			sos = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int n;
			while (-1 != (n = fis.read(buffer))) {
				sos.write(buffer, 0, n);
			}
			sos.flush();
		} finally {
			if (sos != null) {
				sos.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	} 

	/**
	 * 上传文件,支持多个
	 * 
	 * @param request
	 * @param path
	 * @return 返回一个Map<String, String>,key为上传的文件名,value为服务器保存的文件名
	 * @throws Exception
	 */
	public static Map<String, String> getUploadFilesReturnMap(HttpServletRequest request, String path) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, String> mapPar = new HashMap<String, String>();
		Iterator<String> its = multipartRequest.getFileNames();
		for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = it.next();
			List<MultipartFile> multipartFiles = multipartRequest.getFiles(key);
			for(MultipartFile multipartFile : multipartFiles){
				if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
					String fileName = multipartFile.getOriginalFilename();
//					String fileServerName = UUID.randomUUID().toString().substring(0,8)+"_"+DateUtils.getDateYYYYMMDDSS();
//					String fileRealPath = path + fileServerName;
					String fileRealPath =path;
					File file = new File(fileRealPath);
//				file = new File(fileRealPath);
					if(!file.exists()){
						file.mkdirs();
					}
					file = new File(fileRealPath,fileName);
//				file.delete();
					multipartFile.transferTo(file);
					mapPar.put(fileName, fileRealPath);
				}
			}
		}
		return mapPar;
	}

	/**
	 * 上传文件,支持多个
	 * 会改写文件名称
	 *
	 * @param request
	 * @param path
	 * @param fileName (文件名称获取传入值)
	 * @return 返回一个Map<String, String>,key为上传的文件名,value为服务器保存的文件名
	 * @throws Exception
	 */
	public static Map<String, String> getUploadFilesReturnMap(HttpServletRequest request,String fileName, String path) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, String> mapPar = new HashMap<String, String>();
		Iterator<String> its = multipartRequest.getFileNames();
		for (Iterator<String> it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = it.next();
			List<MultipartFile> multipartFiles = multipartRequest.getFiles(key);
			int i = 1;
			for(MultipartFile multipartFile : multipartFiles){
				if (multipartFile != null && StringUtils.isNotBlank(multipartFile.getOriginalFilename())) {
					//String fileServerName = UUID.randomUUID().toString().substring(0,8)+"_"+DateUtils.getDateYYYYMMDDSS();
					//String fileRealPath = path + fileServerName;
					String fileRealPath = path;
					File file = new File(fileRealPath);
					String suffix = "." + StringUtils.substringAfterLast(multipartFile.getOriginalFilename(),".");
//				file = new File(fileRealPath);
					if(!file.exists()){
						file.mkdirs();
					}
                    String saveFileName = fileName+ "_" + i + suffix;
                    //System.out.println(saveFileName);
                    file = new File(fileRealPath,saveFileName);
//				file.delete();
					multipartFile.transferTo(file);

					mapPar.put(saveFileName, fileRealPath);
				}
				i++;
			}
		}
		return mapPar;
	}
	
}