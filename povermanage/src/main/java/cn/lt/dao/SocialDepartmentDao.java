package cn.lt.dao;

import java.util.List;

import cn.lt.po.SocialDepartment;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface SocialDepartmentDao {
	SocialDepartment getSDById(Integer id);

	List<SocialDepartment> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(SocialVo vo);
}
