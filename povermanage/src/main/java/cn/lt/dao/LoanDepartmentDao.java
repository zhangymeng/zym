package cn.lt.dao;

import java.util.List;

import cn.lt.po.LoanDepartment;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

public interface LoanDepartmentDao {
	LoanDepartment getLDById(Integer id);

	List<LoanDepartment> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(LoanVo vo);

	Integer edit(LoanVo vo);
}
