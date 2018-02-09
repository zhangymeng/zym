package cn.lt.dao;

import java.util.List;

import cn.lt.po.UserInfo;
import cn.lt.vo.IndexVo;
import cn.lt.vo.UserVo;

public interface UserDao {

	UserInfo getUserByUsername(IndexVo vo);
	
	UserInfo getUserById(Integer id);

	Integer editUser(UserVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer edit(IndexVo vo);

	Integer add(IndexVo vo);

}
