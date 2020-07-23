package cn.com.commonUser.service.impl;

import cn.com.common.AuthenticationResult;
import cn.com.common.agent.BeanUtilsEx;
import cn.com.common.model.UserInfo;
import cn.com.common.utils.PutDateSource;
import cn.com.commonUser.dao.GaUserMapper;
import cn.com.commonUser.dao.UserLogsMapper;
import cn.com.common.model.dto.GaUser;
import cn.com.common.model.UserLogs;
import cn.com.commonUser.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("gaUserService")
public class UserServiceImpl implements UserService {

	@Autowired
	private GaUserMapper gaUserMapper;

	@Autowired
	private UserLogsMapper userLogsMapper;


	@Override
	public UserInfo getUserInfoByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Map map = new HashMap<>();
		map.put("userId",userId);
		UserInfo userInfo = gaUserMapper.getUserInfo(map);
		return userInfo;
	}

	@Override
	public 	UserInfo getUserInfoByUserName(String userName) throws Exception {
		if(StringUtils.isBlank(userName)){
			return null;
		}
		Map map = new HashMap<>();
		map.put("userName",userName);
		UserInfo userInfo = gaUserMapper.getUserInfo(map);
		return userInfo;
	}

	@Override
	public UserInfo getUserInfoByUserNameAndCertificate(String userName, String certificate) throws Exception {
		if(StringUtils.isAnyBlank(userName,certificate)){
			return null;
		}
		Map map = new HashMap<>();
		map.put("userName",userName);
		map.put("certificateCode",certificate);
		UserInfo userInfo = gaUserMapper.getUserInfo(map);
		return userInfo;
	}

	@Override
	public void putUserInfo(UserInfo userInfo) throws Exception {
		BeanUtilsEx.copyProperties(PutDateSource.updateDate(userInfo.getId(),userInfo.getUserId()),userInfo);
		gaUserMapper.putUserInfo(userInfo);
	}

	@Override
	public void saveGaUser(GaUser gaUser) throws Exception {
		gaUser.setUserType(0);
		gaUser.setUserIdUUID();
		BeanUtilsEx.copyProperties(PutDateSource.createDate(gaUser.getId(),gaUser.getUserId()),gaUser);
		gaUserMapper.postGaUser(gaUser);
	}



	@Override
	public AuthenticationResult checkGaUser(String userName, String certificateCode) throws Exception {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("certificateCode", certificateCode);
		// 校验用户是否存在
		GaUser gaUser = getGaUserByParameterMap(parameterMap);
		//新增用户
		if (gaUser.getCertificateCode() == null) {
			gaUser.setUserName(userName);
			gaUser.setCertificateCode(certificateCode);
			saveGaUser(gaUser);
		}
//		// 检查是否有权限登录
//		if (gaUser.getRoleId() == null) {
//			return AuthenticationResult.AUTHENTICATION_RESULT_NOT_LOGIN;
//		}
		return new AuthenticationResult(0, "登录成功", gaUser);
	}
	
	@Override
	public List<GaUser> getGaUserListByParameterMap(Map<String, Object> parameterMap) {
//		parameterMap.put("recStatus", BaseDomain.REC_STATUS_VALID);
		return gaUserMapper.getGaUserByParameterMap(parameterMap);
	}


	@Override
	public void saveGaUserLoginLog(UserLogs userLogs) {
		userLogsMapper.postUserLogs(userLogs);
	}




	@Override
	public GaUser getGaUserByParameterMap(Map<String, Object> parameterMap) {
		List<GaUser> gaUserList = getGaUserListByParameterMap(parameterMap);
		if (gaUserList.size() > 0) {
			return gaUserList.get(0);
		} else {
			return new GaUser();
		}
	}

	
}