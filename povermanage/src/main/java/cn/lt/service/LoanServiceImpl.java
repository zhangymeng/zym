package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.LoanDao;
import cn.lt.dao.LoanDepartmentDao;
import cn.lt.dao.LoanStudentDao;
import cn.lt.po.Department;
import cn.lt.po.Loan;
import cn.lt.po.LoanDepartment;
import cn.lt.po.LoanStudent;
import cn.lt.util.Tools;
import cn.lt.vo.IndexVo;
import cn.lt.vo.LoanVo;

@Service
public class LoanServiceImpl implements LoanService {

	@Autowired
	private LoanDao loanDao;
	
	@Autowired
	private LoanDepartmentDao loanDepartmentDao;
	
	@Autowired
	private LoanStudentDao loanStudentDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Loan> findLoan(IndexVo vo) {
		List<Loan> list = loanDao.findAll(vo);
		return list;
	}

	@Override
	public List<LoanDepartment> findLoanDepartment(IndexVo vo) {
		List<LoanDepartment> list = loanDepartmentDao.findAll(vo);
		for(LoanDepartment ld:list){
			Department d = departmentDao.getDepartmentById(ld.getdId());
			ld.setTitle(ld.getLoan().getTitle());
			ld.setTheYear(ld.getLoan().getTheYear());
			if(d!=null){
				ld.setDepartment(d.getName());
			}else{
				ld.setDepartment("/");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> delLoan(IndexVo vo) {
		int loanId = vo.getId();
		boolean result = false;
		String reason = "";
		//删除loan前提查看是否已有学生被
		vo.setLoanId(vo.getId());
		List<LoanStudent> lsList = loanStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款条";
		}else{
			loanDao.del(vo);
			
			vo.setLoanId(loanId);
			vo.setId(null);
			loanDepartmentDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addLoan(LoanVo vo) {
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		Integer count = loanDao.add(vo);
		if(count>0){
			result = true;
		}else{
			reason = "插入失败";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editLoan(LoanVo vo) {
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setLoanId(vo.getId());
		List<LoanDepartment> list = loanDepartmentDao.findAll(indexVo);
		int count=0;
		for(LoanDepartment ld:list){
			count = count+ld.getNum();
		}
		if(vo.getNum()<count){
			reason = "现在已经"+count+"个名额被分配，您提交的名额应不小于这个值";
		}else{
			Loan loan = loanDao.getLoanById(vo.getId());
			vo.setRemainingNum(loan.getRemainingNum()+(vo.getNum()-loan.getNum()));
			loanDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> delLD(IndexVo vo) {
		Integer loanId = vo.getLoanId();
		boolean result = false;
		String reason = "";
		//删除loand前提查看是否已有学生
		vo.setLoanId(null);
		vo.setLdId(vo.getId());
		List<LoanStudent> lsList = loanStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款项";
		}else{
			Loan loan = loanDao.getLoanById(loanId);
			loanDepartmentDao.del(vo);
			
			LoanVo loanVo = new LoanVo();
			loanVo.setId(loanId);
			loanVo.setRemainingNum(loan.getRemainingNum()+vo.getEditNum());
			loanDao.edit(loanVo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addLD(LoanVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		IndexVo indexVo = new IndexVo();
		indexVo.setdId(vo.getdId());
		indexVo.setLoanId(vo.getLoanId());
		List<LoanDepartment> ldList = loanDepartmentDao.findAll(indexVo);
		if(ldList.size()>0){
			reason = "该院系已被分配该资助";
		}else{
			Loan loan = loanDao.getLoanById(vo.getLoanId());
			if(loan.getRemainingNum()<vo.getNum()){
				reason = "名额不足";
			}else{
				loanDepartmentDao.add(vo);
				
				vo.setNum(null);
				vo.setRemainingNum(loan.getRemainingNum()-num);
				vo.setId(vo.getLoanId());
				loanDao.edit(vo);
				
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editLD(LoanVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setLdId(vo.getId());
		List<LoanStudent> lsList = loanStudentDao.findAll(indexVo);
		if(vo.getNum()<lsList.size()){
			reason = "现已"+lsList.size()+"人，您提交的名额应不小于这个";
		}else{
			Loan loan = loanDao.getLoanById(vo.getLoanId());
			if(loan.getRemainingNum()<(vo.getNum()-vo.getEditNum())){
				reason = "名额不足";
			}else{
				vo.setRemainingNum(vo.getNum()-lsList.size());
				loanDepartmentDao.edit(vo);
				
				//修改loan的RemainingNum
				vo.setNum(null);
				vo.setRemainingNum(loan.getRemainingNum()-(num-vo.getEditNum()));
				vo.setId(vo.getLoanId());
				loanDao.edit(vo);
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}
	
}
