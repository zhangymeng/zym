package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.LoanDao;
import cn.lt.dao.LoanDepartmentDao;
import cn.lt.dao.LoanStudentDao;
import cn.lt.dao.ProfessionalDao;
import cn.lt.dao.StudentDao;
import cn.lt.po.Department;
import cn.lt.po.Loan;
import cn.lt.po.LoanDepartment;
import cn.lt.po.LoanStudent;
import cn.lt.po.Professional;
import cn.lt.po.Student;
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
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ProfessionalDao professionalDao;
	
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

	@Override
	public Map<String, Object> addLS(LoanVo vo) {
		boolean result = false;
		String reason = "";
		//查看学号存不存在
		IndexVo indexVo = new IndexVo();
		indexVo.setStuNo(vo.getStuNo());
		Student stu = studentDao.getStudentByNo(indexVo);
		if(stu!=null){
			if(vo.getdId()==0 || stu.getdId()==vo.getdId()){
				//查看是否已存在
				indexVo.setLdId(vo.getLdId());
				List<LoanStudent> lsList = loanStudentDao.findAll(indexVo);
				if(lsList.size()>0){
					reason = "该生已参与当前贷款";
				}else{
					vo.setStudentId(stu.getId());
					LoanDepartment ld = loanDepartmentDao.getLDById(vo.getLdId());
					if(ld!=null){
						vo.setLoanId(ld.getLoanId());
						Integer count = loanStudentDao.add(vo);
						if(count>0){
							vo.setId(vo.getLdId());
							vo.setRemainingNum(ld.getRemainingNum()-1);
							loanDepartmentDao.edit(vo);
							result = true;
						}
					}
				}
			}else{
				reason = "该学号非本院系学生";
			}
		}else{
			reason = "该学号不存在";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public List<LoanStudent> allLS(IndexVo vo) {
		List<LoanStudent> list = loanStudentDao.findAll(vo);
		for(LoanStudent ls:list){
			Department d = departmentDao.getDepartmentById(ls.getStudent().getdId());
			if(d!=null && ls.getStudent()!=null){
				ls.setStuName(ls.getStudent().getName());
				ls.setStuNo(ls.getStudent().getStuNo());
				ls.setPhone(ls.getStudent().getPhone());
				ls.setGradeNo(ls.getStudent().getGradeNo());
				ls.setDepartment(d.getName());
				Professional p = professionalDao.getProfessionalById(ls.getStudent().getId());
				if(p!=null){
					ls.setProfessional(p.getName());
				}else{
					ls.setProfessional("/");
				}
				if(ls.getLoan()!=null){
					ls.setLoanTitle(ls.getLoan().getTitle());
					ls.setTheYear(ls.getLoan().getTheYear());
				}
				if(ls.getUserInfo()!=null){
					ls.setAdminStr(ls.getUserInfo().getUsername());
				}
			}else{
				ls.setDepartment("/");
				ls.setProfessional("/");
			}
		}
		return list;
	}

	@Override
	public Integer delLS(IndexVo vo) {
		Integer count = loanStudentDao.del(vo);
		if(count>0){
			LoanDepartment ld = loanDepartmentDao.getLDById(vo.getLdId());
			LoanVo loanVo = new LoanVo();
			loanVo.setId(vo.getLdId());
			loanVo.setRemainingNum(ld.getRemainingNum()+1);
			loanDepartmentDao.edit(loanVo);
		}
		return count;
	}
	
}
