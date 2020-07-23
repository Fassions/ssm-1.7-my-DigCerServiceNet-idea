package cn.com.commonUser.controller;

import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.TblHome;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;
import cn.com.commonUser.service.UserService;
import cn.com.companyUser.dao.SystemMapper;
import cn.com.companyUser.service.SystemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/commonUser")
public class CommonUserController {

	@Autowired
	SystemService systemService;

	@Autowired
	private UserService userService;

	@Value("#{aboutConfiguration.SYSTEM_VERSION_INFO}")
	private String system_version_info = null;

	@Value("#{aboutConfiguration.SYSTEM_ID}")
	private String system_id = null;

	private String system_name = "数字证书服务网";

	@Value("#{aboutConfiguration.SYSTEM_PUBLIC_DATE}")
	private String system_public_date = null;


	/**
	 * 显示公安管理员主页头部信息
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/top")
	public ModelAndView top(HttpServletRequest request) throws Exception {
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		request.setAttribute("userName", gauser.getUserName());
		request.setAttribute("roleId", gauser.getRoleId());
		request.setAttribute("userType", gauser.getUserType());
		return new ModelAndView("inc/top");
	}
	/**
	 * 显示主页信息
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public ModelAndView home(HttpServletRequest request) throws Exception {
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		request.setAttribute("userName", gauser.getUserName());
		request.setAttribute("roleId", gauser.getRoleId());
		List<TblHome> tblHomes = systemService.getAllTblHome();
		request.setAttribute("tblHomes", tblHomes);
		return new ModelAndView("inc/home");
	}

	/**
	 * 关于
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/abt")
	public ModelAndView abt(HttpServletRequest request) throws Exception {
		request.setAttribute("systemName", system_name);
		request.setAttribute("systemPublicDate", system_public_date);
		request.setAttribute("systemId", system_id);
		request.setAttribute("systemVersionInfo", system_version_info);
		return new ModelAndView("inc/about");
	}

	/**
	 * 显示公安管理员菜单信息
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/left")
	public ModelAndView left(HttpServletRequest request) throws Exception {
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		request.setAttribute("userName", gauser.getUserName());
		request.setAttribute("userType", gauser.getUserType());
		request.setAttribute("roleId", gauser.getRoleId());
		return new ModelAndView("inc/left");
	}

	/**
	 * 显示公安管理员导航
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/navbar")
	public ModelAndView navbar(HttpServletRequest request) throws Exception {
//		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
//		request.setAttribute("userName", gauser.getUserName());
//		request.setAttribute("roleId", gauser.getRoleId());
		return new ModelAndView("inc/daohang");
	}

	/**
	 * 显示公安管理员页脚信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/bottom")
	public ModelAndView bottom(HttpServletRequest request) throws Exception {
//		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
//		request.setAttribute("userName", gauser.getUserName());
		return new ModelAndView("inc/bottom");
	}

	/**
	 * 公安管理员首页由HTTPS转为HTTP
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/commonMain")
	public ModelAndView commonMain(HttpServletRequest request) throws Exception {
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		if(gauser==null){
			String userName = "研发004";
			UserInfo userInfo = userService.getUserInfoByUserName(userName);
			gauser = new GaUser();
			BeanUtilsEx.copyProperties(userInfo,gauser);
			request.getSession().setAttribute("gaUser", gauser);
			request.getSession().setAttribute("cerSerno", gauser.getCerSerno());
		}

//		request.setAttribute("roleId", gauser.getRoleId());
//		if (gauser.getRoleId() != null)
//			return new ModelAndView("CompanyUser/CompanyMain");
//		else
		String url = request.getParameter("url");
		if(StringUtils.isBlank(url)){
			if(gauser.getUserType()!=0){	//卖家
				url = "/commonUserOrder/showOrderProcess.do";
			}else {		//买家
				url = "home.do";
			}
		}
		request.setAttribute("url",url);
		return new ModelAndView("CommonUser/CommonMain");
	}

}