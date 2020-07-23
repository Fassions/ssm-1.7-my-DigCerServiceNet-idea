package cn.com.ukey.controller;

import cn.com.ukey.model.UKeyInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping(value = "/uKey")
public class UKeyController {
	
//	@Autowired
//	private KeyBirthInfoService  keyBirthInfoService;
//
//	@Autowired
//	private KeyAreaService keyAreaService;
	
//	/**
//	 * 查询指定证书的出货日期
//	 *
//	 * @param request
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(method = RequestMethod.POST, value = "/queryKeyInfo")
//	@ResponseBody
//	public String getKeyInfo(HttpServletRequest req,HttpServletResponse res){
//		try {
//			String label = req.getParameter("labelNumber");
//
//			String labelHead = label.substring(0, 2);
//			if(false == keyAreaService.findKeyArea(labelHead)){
//				//输入的标号前两位，在数据库中不存在  判断证书格式是否正确
//				return "LABEL_FORMAT_ERROR";
//			}
//
//			UKeyInfo ukeyInfo = keyBirthInfoService.getUKeyInfo(label);
//			if (null == ukeyInfo) {
//				return "KEY_NOT_EXIST";
//			} else {
//				String strDate = new SimpleDateFormat("yyyy-MM-dd").format(ukeyInfo.getBirthDate());
//				return strDate;
//			}
//		} catch(Exception e){
//			return "EXCETION_OCCUR";
//		}
//
//		/*
//		String label = req.getParameter("labelNumber");
//		String labelHead = label.substring(0, 2);
//
//		String ret = "KEY_NOT_EXIST";
//		return ret;
//		*/
//	}
	

}
