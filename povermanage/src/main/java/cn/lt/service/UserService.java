package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Menu;
import cn.lt.po.UserInfo;
import cn.lt.vo.IndexVo;
import cn.lt.vo.UserVo;

public interface UserService {

	Map<String, Object> login(UserVo vo);

	UserInfo getUserById(Integer id);

	List<Menu> getMenu(Integer roleId);

	Integer editUser(UserVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(IndexVo vo);

}
