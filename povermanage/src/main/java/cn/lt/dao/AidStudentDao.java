package cn.lt.dao;

import java.util.List;

import cn.lt.po.AidStudent;
import cn.lt.vo.AidVo;
import cn.lt.vo.IndexVo;

public interface AidStudentDao {
	AidStudent getASById(Integer id);

	List<AidStudent> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(AidVo vo);

	Integer edit(AidVo vo);
}
