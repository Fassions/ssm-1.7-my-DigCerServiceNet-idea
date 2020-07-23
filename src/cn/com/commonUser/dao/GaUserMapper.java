package cn.com.commonUser.dao;

import cn.com.common.model.UserInfo;
import cn.com.common.model.dto.GaUser;

import java.util.List;
import java.util.Map;

public interface GaUserMapper {

	void putUserInfo(UserInfo userInfo);

	/**
	 * 保存用户信息
	 * 
	 * @param gaUser
	 */
	public void postGaUser(GaUser gaUser);


	/**
	 * 根据参数查询GaUser对象集合
	 *
	 * @param parameterMap
	 * @return
	 */
	public List<GaUser> getGaUserByParameterMap(Map<String, Object> parameterMap);

	UserInfo getUserInfo(Map map);



}