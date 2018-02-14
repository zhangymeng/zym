package cn.lt.dao;

import java.util.List;

import cn.lt.po.Scholarship;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface ScholarshipDao {
	Scholarship getScholarshipById(Integer id);

	List<Scholarship> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(SocialVo vo);
}
