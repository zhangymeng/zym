package cn.lt.dao;

import java.util.List;

import cn.lt.po.Loan;
import cn.lt.po.LoanDepartment;
import cn.lt.po.LoanStudent;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

public interface LoanStudentDao {
	LoanStudent getLSById(Integer id);

	List<LoanStudent> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(LoanVo vo);

	Integer edit(IndexVo vo);

	List<Loan> getLSByLoanIid(Integer id);
}
