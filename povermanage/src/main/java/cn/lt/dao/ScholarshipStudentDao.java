package cn.lt.dao;

import java.util.List;

import cn.lt.po.ScholarshipStudent;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface ScholarshipStudentDao {
	ScholarshipStudent getSSById(Integer id);

	List<ScholarshipStudent> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(IndexVo vo);
}
