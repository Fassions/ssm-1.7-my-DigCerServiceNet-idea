package cn.com.filter;

import java.io.IOException;
import java.security.cert.X509Certificate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;
import cn.com.commonUser.service.UserService;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.common.CommonProperty;
import cn.com.common.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import cn.com.gauser.model.GaUser;

public class URLFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(URLFilter.class);

	@Autowired
	private UserService userService;
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		try {
			if ((servletRequest instanceof HttpServletRequest) && (servletResponse instanceof HttpServletResponse)) {
				request = (HttpServletRequest) servletRequest;
				response = (HttpServletResponse) servletResponse;
				String requestFullUrl = getRequestFullUrl(request.getServerName(), request.getLocalPort(), request.getRequestURI(), request.getQueryString());
				String authenticationUrl = getRequestFullUrl(request.getServerName(), request.getLocalPort(), request.getRequestURI(), null);
				GaUser ga = (GaUser) request.getSession().getAttribute("gaUser");
				if (isNeedAuthentication(authenticationUrl)) {
					if (request.getSession().getAttribute("gaUser") != null) {
						logger.debug("需登录才能正常访问的URL为:" + requestFullUrl);
						//TODO:绕过登陆
						if(!IsLogin(request)){
						//if(!IsLogin(servletRequest)){
							logger.debug("该证书未进行过login登录:" + requestFullUrl);
							response.sendRedirect("/showLogin.do");
						}
						if(StringUtils.isBlank(ga.getUserId())){  //userId 不能为空
							logger.debug("需登录才能正常访问的URL,且该用户UserId 为null:" + requestFullUrl);
							response.sendRedirect("/showLogin.do");
						}
						filterChain.doFilter(request, response);
					} else {
						logger.debug("非法访问的URL为:" + requestFullUrl);
						response.sendRedirect("/showLogin.do");
					}
				} else {
//					logger.info("无需登录即可访问的URL为:" + requestFullUrl);
					filterChain.doFilter(request, response);
				}
				//logger.info("当前连接的sessionID为:" + request.getSession().getId());
			} else {
				throw new Exception("请求类型异常,不是HttpServletRequest和HttpServletResponse");
			}
		} catch (Exception e) {
			doError(request, response, e);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	/**
	 * 获取HTTP请求全路径
	 * 
	 * @param serverName
	 * @param port
	 * @param uri
	 * @param queryStr
	 * @return
	 */
	public String getRequestFullUrl(String serverName, int port, String uri, String queryStr) {
		StringBuffer result = new StringBuffer(60);
		result.append("http://");
		result.append(serverName);
		result.append(port == 80 ? "" : ":" + port);
		result.append(uri);
		result.append(queryStr == null ? "" : "?" + queryStr);
		return result.toString();
	}
	
	/**
	 * URL访问是否需要登录,true需要验证,false不需要验证
	 * 
	 * @param requestUrl
	 * @return
	 */
	public boolean isNeedAuthentication(String requestUrl) {
		String notNeedAuthenticateUrl = CommonProperty.applicationProperties.getProperty("notNeedAuthenticateUrl");
		if (StringUtils.isBlank(notNeedAuthenticateUrl)) {
			return true;
		} else {
			String[] notNeedAuthenticateUrlArray = notNeedAuthenticateUrl.split(",");
			for (int i = 0; i < notNeedAuthenticateUrlArray.length; i++) {
				if (requestUrl.endsWith(notNeedAuthenticateUrlArray[i]) || requestUrl.contains(notNeedAuthenticateUrlArray[i])) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * 异常处理,跳转到错误页面
	 * 
	 * @param request
	 * @param response
	 * @param e
	 */
	public void doError(HttpServletRequest request, HttpServletResponse response, Exception e) {
		logger.error(CommonUtils.geDetailException(e));
		try {
			response.sendRedirect("/DigCerServiceNet/error.do");
		} catch (Exception e1) {
			logger.error(CommonUtils.geDetailException(e1));
		}
	}

	//绕过登陆
	public boolean IsLogin(HttpServletRequest request) throws Exception{
		GaUser gauser = (GaUser) request.getSession().getAttribute("gaUser");
		if(gauser==null){
			String userName = "研发004";
			UserInfo userInfo = userService.getUserInfoByUserName(userName);
			gauser = new GaUser();
			BeanUtilsEx.copyProperties(userInfo,gauser);
			request.getSession().setAttribute("gaUser", gauser);
			request.getSession().setAttribute("cerSerno", gauser.getCerSerno());
		}
		return true;
	}
	public boolean IsLogin(ServletRequest servletRequest){
		HttpServletRequest request = null;
		try {
			if ((servletRequest instanceof HttpServletRequest)) {
				request = (HttpServletRequest) servletRequest;
				GaUser gaUser = (GaUser)request.getSession().getAttribute("gaUser");

				X509Certificate[] chain = (X509Certificate[]) servletRequest
						.getAttribute("javax.servlet.request.X509Certificate");
				if (chain == null || chain.length == 0) {
					request.setAttribute("message", "未读取到证书信息,请插入证书!");
                    return false;
				}
				X509Certificate cert = chain[0];
				
				String subjectDN = cert.getSubjectDN().getName();
				String[] subjectDNArray = subjectDN.split(", ");
				String userName = "";
				String personalCard = "";
				String unit = "";
				String cerSerno = Hex.encodeHexString(cert.getSerialNumber()
						.toByteArray());


				for (String obj : subjectDNArray) {
					if (obj.startsWith("CN=") || obj.startsWith("OU=")
							|| obj.startsWith("O=") || obj.startsWith("L=")
							|| obj.startsWith("ST=")) {
						if (StringUtils.isBlank(userName)) {
							String userInfo = obj.split("=")[1];
							userName = userInfo.split(" ")[0];
							personalCard = userInfo.split(" ")[1];
						} else {
							unit = obj.split("=")[1] + unit;
						}
					}
				}

				/*logger.info("访问URL的证书信息为: cerSerno：" + cerSerno + " userName:"
						+ userName + " personalCard:" + personalCard);
				*/
				if(  (0 == gaUser.getUserName().compareTo(userName))
						&& (0 == gaUser.getCertificateCode().compareTo(personalCard))) {
					//浏览器传入的证书为已登录过的证书，即进行过login操作的证书
					return true;
				}
			} else {
				throw new Exception(
						"请求类型异常,不是HttpServletRequest和HttpServletResponse");
			}
		} catch (Exception e) {
			//doError(request, response, e);
		}
		return false;
	}

}