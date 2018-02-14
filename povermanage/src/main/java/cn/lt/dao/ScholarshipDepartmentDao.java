package cn.lt.dao;

import java.util.List;

import cn.lt.po.ScholarshipDepartment;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface ScholarshipDepartmentDao {
	ScholarshipDepartment getSDById(Integer id);

	List<ScholarshipDepartment> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(SocialVo vo);
}
