package cn.com.common.controller;

import cn.com.common.AuthenticationResult;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.UserLogs;
import cn.com.commonUser.service.UserService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.cert.X509Certificate;
import java.util.Date;

@Controller
public class LoginController {

	@Value("#{applicationConfiguration['notNeedAuthenticateUrl']}")
	private String notNeedAuthenticateUrl = null;

	@Autowired
	private UserService userService;






//	@Autowired
//	private RepairStatusInfoService repairStatusInfoService;

	/**
	 * 显示登录界面
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showLogin", method = RequestMethod.GET)
	public ModelAndView showLogin() throws Exception {
		return new ModelAndView("login");
	}

	/**
	 * 登录认证
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) throws Exception {

		//TODO：登陆信息页面：http://localhost:8080/showLogin.do?user=研发004
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		if(gauser==null){
			String userName = request.getParameter("user");
			if(StringUtils.isBlank(userName)){
				X509Certificate[] chain = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
				if (chain == null || chain.length == 0) {
					request.setAttribute("message", "未读取到证书信息,请插入证书!");
					return new ModelAndView(new RedirectView("login.do"));
				}
				X509Certificate cert = chain[0];
				String subjectDN = cert.getSubjectDN().getName();
				String[] subjectDNArray = subjectDN.split(", ");
				String personalCard = "";
				String unit = "";
				String cerSerno = Hex.encodeHexString(cert.getSerialNumber().toByteArray());
				for (String obj : subjectDNArray) {
					if (obj.startsWith("CN=") || obj.startsWith("OU=") || obj.startsWith("O=") || obj.startsWith("L=") || obj.startsWith("ST=")) {
						if (StringUtils.isBlank(userName)) {
							String userInfo = obj.split("=")[1];
							userName = userInfo.split(" ")[0];
							personalCard = userInfo.split(" ")[1];
						} else {
							unit = obj.split("=")[1] + unit;
						}
					}
				}
				AuthenticationResult rs = userService.checkGaUser(userName,personalCard);
				if (AuthenticationResult.AUTHENTICATION_RESULT_OK.getResult() == rs.getResult()) {
					GaUser gaUser = rs.getGaUser();
					request.getSession().setAttribute("gaUser", gaUser);
					request.getSession().setAttribute("cerSerno", cerSerno);
					UserLogs userLogs = new UserLogs();
					userLogs.setIdUUID();
					userLogs.setUserId(gaUser.getUserId());
					userLogs.setIp(request.getRemoteAddr());
					userLogs.setCerSerno(cerSerno);
					userLogs.setBroswerInfo(request.getHeader("user-agent"));
					userLogs.setLoginDate(new Date());
					userLogs.setLoginType(new Integer("1"));
					userLogs.setDateCreated(new Date());
					userLogs.setUserCreated(gaUser.getUserId());
					userLogs.setDel(false);
					userService.saveGaUserLoginLog(userLogs);
					return new ModelAndView(new RedirectView("https://" + request.getServerName() + ":8443" + request.getContextPath() + "/commonUser/commonMain.do"));
				} else {
					return new ModelAndView(new RedirectView("showLogin.do"));
				}
			}
			UserInfo userInfo = userService.getUserInfoByUserName(userName);
			gauser = new GaUser();
			BeanUtilsEx.copyProperties(userInfo,gauser);
		}
		request.getSession().setAttribute("gaUser", gauser);
		request.getSession().setAttribute("cerSerno", gauser.getCerSerno());
		UserLogs userLogs = new UserLogs();
		userLogs.setIdUUID();
		userLogs.setUserId(gauser.getUserId());
		userLogs.setIp(request.getRemoteAddr());
		userLogs.setCerSerno(gauser.getCerSerno());
		userLogs.setBroswerInfo(request.getHeader("user-agent"));
		userLogs.setLoginDate(new Date());
		userLogs.setLoginType(new Integer("1"));
		userLogs.setDateCreated(new Date());
		userLogs.setUserCreated(gauser.getUserId());
		userLogs.setDel(false);
		userService.saveGaUserLoginLog(userLogs);
		return new ModelAndView(new RedirectView("http://" + request.getServerName() + ":8080" + request.getContextPath() + "/commonUser/commonMain.do"));
	}

	/**
	 * 首页展示
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) throws Exception {
		return new ModelAndView("index");
	}

	/**
	 * 统一错误处理页面
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public ModelAndView error(HttpServletRequest request) throws Exception {
		return new ModelAndView("error");
	}

	/**
	 * 用户登出
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserLogs userLogs = new UserLogs();
		//gaUserLoginLog.setLoginId(request.getSession().getAttribute("loginId").toString());
		userLogs.setIdUUID();
		userLogs.setCerSerno(request.getSession().getAttribute("cerSerno")!=null?request.getSession().getAttribute("cerSerno").toString():null);
		GaUser gaUser = (GaUser) request.getSession().getAttribute("gaUser");
		userLogs.setUserId(gaUser.getUserId());
		userLogs.setDateCreated(new Date());
		userLogs.setUserCreated(gaUser.getUserId());
		userLogs.setDel(false);
		userLogs.setIp(request.getRemoteAddr());
		userLogs.setBroswerInfo(request.getHeader("user-agent"));
		userLogs.setLogoutDate(new Date());
		userLogs.setLogoutType(2);
		//gaUserLoginLog.setLoginFrom(3);
		userService.saveGaUserLoginLog(userLogs);
		request.getSession().invalidate();
		return new ModelAndView(new RedirectView("index.do"));
	}
	
	/**
	 * 常见问题、驱动下载、工作流程、联系我们
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showFaq", method = RequestMethod.GET)
	public ModelAndView showFaq(HttpServletRequest request) throws Exception {
		GaUser gaUser = (GaUser) request.getSession().getAttribute("gaUser");
		if(gaUser==null){
			request.setAttribute("backFlag", 1);
		}
		return new ModelAndView("faq");
	}
	
	
	/**
	 * 证书出货日期查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showKeyQuery", method = RequestMethod.GET)
	public ModelAndView showKeyQuery(HttpServletRequest request) throws Exception {
		return new ModelAndView("query");
	}

}