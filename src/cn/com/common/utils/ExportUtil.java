package cn.com.common.utils;

import cn.com.common.agent.BaseDomain;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ExportUtil {


	/**
	 * @param title
	 *            ：excel文件名称
	 * @param headers
	 *            ：表头
	 * @param dataset
	 *            ：要保存到表格的对象集合
	 * @param attrList
	 *            ：对象要保存的属性
	 * @param response
	 *            ：输出
	 */
	public static void exportExcel(String title, List<String> headers,
								   Collection dataset, List<String> attrList,
								   HttpServletResponse response, String type) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet");

		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth((short) 20);
		// sheet.setColumnWidth((short)1, (short)30);

		// 产生表头
		HSSFRow row = sheet.createRow(0);

		// 插入表头数据
		for (short i = 0; i < headers.size(); i++) {
			HSSFCell cell = row.createCell(i);
			// 设置样式
			cell.setCellStyle(createHeaderStyle(workbook));
			HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(text.toString());
		}

		int index = 1;
		if(dataset!=null){
			for (Object bean : dataset) {
				// 得到一个bean，则生成表格的一行
				row = sheet.createRow(index++);
				for (int i = 0; i < attrList.size(); i++) {
					PropertyDescriptor pd = null;
					try {
//						Class classzz = Class.forName("cn.com.common.model.dto.TemPlateExcelVo");
//						Object obj = classzz.newInstance();
//						Field[] fields = classzz.getDeclaredFields();
//						for(Field f: fields){
//							String ff = f.getName();
//							PropertyDescriptor pds = new PropertyDescriptor(ff,classzz);
//						}

						pd = new PropertyDescriptor(attrList.get(i), bean.getClass());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}

					// 得到bean的属性值
					Object attrValue = null;
					try {
						attrValue = pd.getReadMethod().invoke(bean);
					} catch (Exception e) {
						throw new RuntimeException("无法获取bean的属性值：" + pd.getName());
					}

					// 转成字符串
					String cellValue = "";
					if (attrValue instanceof Date || attrValue instanceof Timestamp) {
						SimpleDateFormat df = new SimpleDateFormat(type);
						cellValue = df.format(attrValue);
					} else {
						if (attrValue != null) {
							cellValue = attrValue.toString();
						}
					}
					HSSFCell cell = row.createCell(new Short(i + ""));
					cell.setCellStyle(createDataStyle(workbook));
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(cellValue);
				}

			}
		}

		try {
			response.setContentType("charset=utf-8;application/msexcel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(title, "utf-8") + ".xls");
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 存入指定路径
	 *	财务开票单同一InvoiceId 需合并单元格
	 * @param excelId
	 *  		  ： 0 生产任务单 1 财务开票单 2 快递单 3.订单管理表
	 * @param title
	 *            ：excel文件名称
	 * @param headers
	 *            ：表头
	 * @param dataset
	 *            ：要保存到表格的对象集合
	 * @param attrList
	 *            ：对象要保存的属性
	 * @param response
	 *            ：输出
	 *            return 可接收file 也可默认void
	 */
	public static File exportExcel(String excelId, String title, List<String> headers,
								   Collection dataset, String filePath,String fileName,
								   List<String> attrList, String type) {

		// 声明一个工作薄
		HSSFWorkbook workbook = exportExcelParam(excelId, title, headers, dataset, attrList, type);
		File file;
		try {
			//创建路径
			file = new File(filePath + fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return file;
	}



	/**
	 *	财务开票单同一InvoiceId 需合并单元格
	 * @param excelId
	 *  		  ： 0 生产任务单 1 财务开票单 2 快递单 3.订单管理表
	 * @param title
	 *            ：excel文件名称
	 * @param headers
	 *            ：表头
	 * @param dataset
	 *            ：要保存到表格的对象集合
	 * @param attrList
	 *            ：对象要保存的属性
	 * @param response
	 *            ：输出
	 */
	public static void exportExcel(String excelId,String title, List<String> headers,
								   Collection dataset, List<String> attrList,
								   HttpServletResponse response, String type) {
		// 声明一个工作薄
		HSSFWorkbook workbook = exportExcelParam(excelId,title,headers,dataset,attrList,type);
		try {
			response.setContentType("charset=utf-8;application/msexcel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(title, "utf-8") + ".xls");
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	//扩展方法
	private static HSSFWorkbook exportExcelParam(String excelId, String title, 
			List<String> headers, Collection dataset, List<String> attrList, String type){
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		//数据源需要的单元格格式
		HSSFCellStyle contextStyle = workbook.createCellStyle();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet");

		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth((short) 15);
		// sheet.setColumnWidth((short)1, (short)30);

		// 产生表头
		HSSFRow row = sheet.createRow(0);

		// 插入表头数据
		for (short i = 0; i < headers.size(); i++) {
			HSSFCell cell = row.createCell(i);
			// 设置样式
			cell.setCellStyle(createHeaderStyle(workbook));
			HSSFRichTextString text = new HSSFRichTextString(headers.get(i));
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			if (Objects.equals(BaseDomain.PRODUCTIONEXCEL, excelId)) {
				if("证书编号".equals(text.toString())) {
					sheet.setColumnWidth(i, 8000);
				}
			}
			if (Objects.equals(BaseDomain.EXPRESSEXCEL, excelId)) {
				if("发货日期".equals(text.toString())) {
					text = new HSSFRichTextString("发货日期（yyyy-MM-dd）");
					sheet.setColumnWidth(i, 8000);
				}
			}
			cell.setCellValue(text.toString());
		}

		int index = 1;
		Integer line = null; //需要合并的列
		String value = "";	//合并比对值
		int minRow = 1;		//合并起始行数
		int maxRow = 1;		//合并结束行数
		if(dataset!=null){
			for (Object bean : dataset) {
				// 得到一个bean，则生成表格的一行
				row = sheet.createRow(index++);
				//设置表格行高
				row.setHeight((short) 600);
				for (int i = 0; i < attrList.size(); i++) {
					PropertyDescriptor pd = null;
					try {
//						Class classzz = Class.forName("cn.com.common.model.dto.TemPlateExcelVo");
//						Object obj = classzz.newInstance();
//						Field[] fields = classzz.getDeclaredFields();
//						for(Field f: fields){
//							String ff = f.getName();
//							PropertyDescriptor pds = new PropertyDescriptor(ff,classzz);
//						}

						pd = new PropertyDescriptor(attrList.get(i), bean.getClass());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}

					// 得到bean的属性值
					Object attrValue = null;
					try {
						attrValue = pd.getReadMethod().invoke(bean);
					} catch (Exception e) {
						throw new RuntimeException("无法获取bean的属性值：" + pd.getName());
					}

					// 转成字符串
					String cellValue = "";
					if (attrValue instanceof Date || attrValue instanceof Timestamp) {
						SimpleDateFormat df = new SimpleDateFormat(type);
						cellValue = df.format(attrValue);
					} else {
						if (attrValue != null) {
							cellValue = attrValue.toString();
						}
					}

					//需要合并的列 ，财务开票表需要合并开票单位，其他需要合并订购单位
					if(Objects.equals(BaseDomain.INVOICEEXCEL.toString(),excelId)){
						if("invoiceUnitName".equals(pd.getName())){
							line  = i;   //TODO:需要合并的列
						}
					}else {
						if("orderUnitName".equals(pd.getName())){
							line  = i;   //TODO:需要合并的列
						}else if("receiveUnitName".equals(pd.getName())){  //发票快递单 需要合并收件单位（同订购单位）
							line  = i;   //TODO:需要合并的列
						}

					}


					HSSFCell cell = row.createCell(new Short(i + ""));
					Boolean isNum= false;	//data是否为数值型
					Boolean isInteger = false; //data是否为整数
					Boolean isPercent= false;	//data是否为百分数
//					if(){
//
//					}

					cell.setCellStyle(createDataStyle(workbook));
//					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellValue(cellValue);
				}
				//财务开票表  合并单元格
				if(Objects.equals(BaseDomain.INVOICEEXCEL.toString(),excelId)){
					try {
						Class cls = bean.getClass();
						Field[] fields = cls.getDeclaredFields();
						for(int k = 0;k<fields.length;k++){
							Field field = fields[k];
							field.setAccessible(true);
							String name = field.getName();
							Object beanValue = field.get(bean);
							if(index==2 && "invoiceId".equals(name)){
								value = beanValue.toString();
								break;
							}

							if("invoiceId".equals(name)){
								if(Objects.equals(value,beanValue)){ //值相同
									//起始编号不累加
									//结束编号累加
									maxRow++;
								}else{				//值不相同
									CellRangeAddress cel =  new CellRangeAddress(minRow,maxRow,line,line);
									sheet.addMergedRegion(cel);

									//起始编号累加 //结束编号累加
									minRow = ++maxRow;

								}
								if(index==dataset.size()+1){ //最后一位判断是否需要合并
									CellRangeAddress cel =  new CellRangeAddress(minRow,maxRow,line,line);
									sheet.addMergedRegion(cel);
								}
								value = beanValue.toString();
								break;	//累加结束跳出循环
							}
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}


				}
				//除财务开票表  合并订购单位单元格
				if(!Objects.equals(BaseDomain.INVOICEEXCEL.toString(),excelId)){
					try {
						Class cls = bean.getClass();
						Field[] fields = cls.getDeclaredFields();
						for(int k = 0;k<fields.length;k++){
							Field field = fields[k];
							field.setAccessible(true);
							String name = field.getName();
							Object beanValue = field.get(bean);
							if(line==null){ //TODO:比对数据不存在无需合并
								value = beanValue.toString();
								break;
							}
							if(index==2 && "orderId".equals(name)){
								value = beanValue.toString();
								break;
							}
							if("orderId".equals(name)){
								if(Objects.equals(value,beanValue)){ //值相同
									//起始编号不累加
									//结束编号累加
									maxRow++;
								}else { 	//值不相同
									CellRangeAddress cel =  new CellRangeAddress(minRow,maxRow,line,line);
									sheet.addMergedRegion(cel);
									//起始编号累加 //结束编号累加
									minRow = ++maxRow;
								}
								if(index==dataset.size()+1){ //最后一位判断是否需要合并
									CellRangeAddress cel =  new CellRangeAddress(minRow,maxRow,line,line);
									sheet.addMergedRegion(cel);
								}

								value = beanValue.toString();
								break;	//累加结束跳出循环
							}
						}
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				
			}
		}
		return workbook;
	}


	/**
	 * @param title
	 *            ：excel文件名称
	 * @param headers
	 *            ：表头
	 * @param dataset
	 *            ：要保存到表格的对象集合
	 * @param attrList
	 *            ：对象要保存的属性
	 * @param response
	 *            ：输出
	 */
	public static void exportExcel(String title, String[] headers,
			Collection dataset, String attrList[],
			HttpServletResponse response, String type) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet");

		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth((short) 20);
		// sheet.setColumnWidth((short)1, (short)30);

		// 产生表头
		HSSFRow row = sheet.createRow(0);

		// 插入表头数据
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			// 设置样式
			cell.setCellStyle(createHeaderStyle(workbook));
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(text.toString());
		}

		int index = 1;
		for (Object bean : dataset) {
			// 得到一个bean，则生成表格的一行
			row = sheet.createRow(index++);
			for (int i = 0; i < attrList.length; i++) {
				PropertyDescriptor pd = null;
				try {
					pd = new PropertyDescriptor(attrList[i], bean.getClass());
				} catch (Exception e) {
					throw new RuntimeException("bean中没有属性：" + attrList[i]);
				}

				// 得到bean的属性值
				Object attrValue = null;
				try {
					attrValue = pd.getReadMethod().invoke(bean);
				} catch (Exception e) {
					throw new RuntimeException("无法获取bean的属性值：" + pd.getName());
				}

				// 转成字符串
				String cellValue = "";
				if (attrValue instanceof Date || attrValue instanceof Timestamp) {
					SimpleDateFormat df = new SimpleDateFormat(type);
					cellValue = df.format(attrValue);
				} else {
					if (attrValue != null) {
						cellValue = attrValue.toString();
					}
				}

				HSSFCell cell = row.createCell(new Short(i + ""));
				cell.setCellStyle(createDataStyle(workbook));
//				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(cellValue);
			}
		}
		try {
			response.setContentType("charset=utf-8;application/msexcel");
			response.setCharacterEncoding("utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ URLEncoder.encode(title, "utf-8") + ".xls");
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	
	public static HSSFWorkbook getExportExcel(String[] headers,
			Collection dataset, String attrList[], String type) {

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet("sheet");

		// 设置表格默认列宽度为15
		sheet.setDefaultColumnWidth((short) 20);
		// sheet.setColumnWidth((short)1, (short)30);

		// 产生表头
		HSSFRow row = sheet.createRow(0);

		// 插入表头数据
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			// 设置样式
			cell.setCellStyle(createHeaderStyle(workbook));
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
//			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue(text.toString());
		}

		int index = 1;
		for (Object bean : dataset) {
			// 得到一个bean，则生成表格的一行
			row = sheet.createRow(index++);
			for (int i = 0; i < attrList.length; i++) {
				PropertyDescriptor pd = null;
				try {
					pd = new PropertyDescriptor(attrList[i], bean.getClass());
				} catch (Exception e) {
					throw new RuntimeException("bean中没有属性：" + attrList[i]);
				}

				// 得到bean的属性值
				Object attrValue = null;
				try {
					attrValue = pd.getReadMethod().invoke(bean);
				} catch (Exception e) {
					throw new RuntimeException("无法获取bean的属性值：" + pd.getName());
				}

				// 转成字符串
				String cellValue = "";
				if (attrValue instanceof Date || attrValue instanceof Timestamp) {
					SimpleDateFormat df = new SimpleDateFormat(type);
					cellValue = df.format(attrValue);
				} else {
					if (attrValue != null) {
						cellValue = attrValue.toString();
					}
				}

				HSSFCell cell = row.createCell(new Short(i + ""));
				cell.setCellStyle(createDataStyle(workbook));
//				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(cellValue);
			}
		}
		
		return workbook;
	}
	

	private static HSSFCellStyle createHeaderStyle(HSSFWorkbook workbook) {
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();

		// 设置表头的样式
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		// 生成表头的字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	private static HSSFCellStyle createDataStyle(HSSFWorkbook workbook) {

		// 生成数据行的样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setWrapText(true);
		// 生成数据行的字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
	

}
