package cn.com.commonUser.service;

import cn.com.common.AuthenticationResult;
import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;

import cn.com.common.model.UserLogs;

import java.util.List;
import java.util.Map;

public interface UserService {

	UserInfo getUserInfoByUserId(String userId) throws Exception;

	UserInfo getUserInfoByUserName(String userName) throws Exception;

	UserInfo getUserInfoByUserNameAndCertificate(String UserName,String Certificate) throws Exception;


	void putUserInfo(UserInfo userInfo) throws Exception;

	/**
	 * 保存UserInfo
	 * 
	 * @param gaUser
	 * @return
	 * @throws Exception
	 */
	public void saveGaUser(GaUser gaUser) throws Exception;




	/**
	 * 校验用户是否有权限登录系统
	 * 
	 * @param personalCard
	 * @return
	 * @throws Exception
	 */
	public AuthenticationResult checkGaUser(String userName, String personalCard)
			throws Exception;





	/**
	 * 根据参数查询GaUser对象集合
	 * 
	 * @param parameterMap
	 * @return
	 */
	public List<GaUser> getGaUserListByParameterMap(
            Map<String, Object> parameterMap);

	/**
	 * 根据参数查询GaUser对象
	 * 
	 * @param parameterMap
	 * @return
	 */
	public GaUser getGaUserByParameterMap(Map<String, Object> parameterMap);


	/**
	 * 保存gaUserLoginLog
	 *
	 * @param userLogs
	 * @throws Exception
	 */
	public void saveGaUserLoginLog(UserLogs userLogs);



	

}