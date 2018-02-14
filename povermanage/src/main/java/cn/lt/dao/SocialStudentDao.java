package cn.lt.dao;

import java.util.List;

import cn.lt.po.SocialStudent;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface SocialStudentDao {
	SocialStudent getSSById(Integer id);

	List<SocialStudent> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(IndexVo vo);
}
