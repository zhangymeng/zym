package cn.lt.dao;

import java.util.List;

import cn.lt.po.AidDepartment;
import cn.lt.vo.AidVo;
import cn.lt.vo.IndexVo;

public interface AidDepartmentDao {
	AidDepartment getADById(Integer id);

	List<AidDepartment> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(AidVo vo);

	Integer edit(AidVo vo);
}
