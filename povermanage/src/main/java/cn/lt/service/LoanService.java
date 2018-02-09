package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Loan;
import cn.lt.po.LoanDepartment;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

public interface LoanService {

	List<Loan> findLoan(IndexVo vo);

	List<LoanDepartment> findLoanDepartment(IndexVo vo);

	Map<String, Object> delLoan(IndexVo vo);

	Map<String, Object> addLoan(LoanVo vo);

	Map<String, Object> editLoan(LoanVo vo);

	Map<String, Object> delLD(IndexVo vo);

	Map<String, Object> addLD(LoanVo vo);

	Map<String, Object> editLD(LoanVo vo);

}
