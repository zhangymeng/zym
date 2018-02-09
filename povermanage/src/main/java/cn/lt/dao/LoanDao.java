package cn.lt.dao;

import java.util.List;

import cn.lt.po.Loan;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

public interface LoanDao {
	Loan getLoanById(Integer id);

	List<Loan> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(LoanVo vo);

	Integer edit(LoanVo vo);
}
