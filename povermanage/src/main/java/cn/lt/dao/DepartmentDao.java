package cn.lt.dao;

import java.util.List;

import cn.lt.po.Department;
import cn.lt.vo.IndexVo;

public interface DepartmentDao {

	List<Department> findAll(IndexVo vo);
	
	Department getDepartmentById(Integer id);

}
