package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.ProfessionalDao;
import cn.lt.dao.ScholarshipDao;
import cn.lt.dao.ScholarshipDepartmentDao;
import cn.lt.dao.ScholarshipStudentDao;
import cn.lt.dao.StudentDao;
import cn.lt.po.Department;
import cn.lt.po.Professional;
import cn.lt.po.Scholarship;
import cn.lt.po.ScholarshipDepartment;
import cn.lt.po.ScholarshipStudent;
import cn.lt.po.Student;
import cn.lt.util.Tools;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

@Service
public class ScholarshipServiceImpl implements ScholarshipService {
	@Autowired
	private ScholarshipDao scholarshipDao;
	
	@Autowired
	private ScholarshipDepartmentDao scholarshipDepartmentDao;
	
	@Autowired
	private ScholarshipStudentDao scholarshipStudentDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ProfessionalDao professionalDao;
	
	@Override
	public List<Scholarship> findScholarship(IndexVo vo) {
		List<Scholarship> list = scholarshipDao.findAll(vo);
		return list;
	}

	@Override
	public List<ScholarshipDepartment> findScholarshipDepartment(IndexVo vo) {
		List<ScholarshipDepartment> list = scholarshipDepartmentDao.findAll(vo);
		for(ScholarshipDepartment ld:list){
			Department d = departmentDao.getDepartmentById(ld.getdId());
			ld.setTitle(ld.getScholarship().getTitle());
			ld.setTheYear(ld.getScholarship().getTheYear());
			if(d!=null){
				ld.setDepartment(d.getName());
			}else{
				ld.setDepartment("/");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> delScholarship(IndexVo vo) {
		int ScholarshipId = vo.getId();
		boolean result = false;
		String reason = "";
		//删除Scholarship前提查看是否已有学生被
		vo.setsId(vo.getId());
		List<ScholarshipStudent> lsList = scholarshipStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款条";
		}else{
			scholarshipDao.del(vo);
			
			vo.setsId(ScholarshipId);
			vo.setId(null);
			scholarshipDepartmentDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addScholarship(SocialVo vo) {
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		Integer count = scholarshipDao.add(vo);
		if(count>0){
			result = true;
		}else{
			reason = "插入失败";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editScholarship(SocialVo vo) {
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setsId(vo.getId());
		List<ScholarshipDepartment> list = scholarshipDepartmentDao.findAll(indexVo);
		int count=0;
		for(ScholarshipDepartment ld:list){
			count = count+ld.getNum();
		}
		if(vo.getNum()<count){
			reason = "现在已经"+count+"个名额被分配，您提交的名额应不小于这个值";
		}else{
			Scholarship Scholarship = scholarshipDao.getScholarshipById(vo.getId());
			vo.setRemainingNum(Scholarship.getRemainingNum()+(vo.getNum()-Scholarship.getNum()));
			scholarshipDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> delSD(IndexVo vo) {
		Integer ScholarshipId = vo.getsId();
		boolean result = false;
		String reason = "";
		//删除Scholarshipd前提查看是否已有学生
		vo.setsId(null);
		vo.setLdId(vo.getId());
		List<ScholarshipStudent> lsList = scholarshipStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款项";
		}else{
			Scholarship Scholarship = scholarshipDao.getScholarshipById(ScholarshipId);
			scholarshipDepartmentDao.del(vo);
			
			SocialVo socialVo = new SocialVo();
			socialVo.setId(ScholarshipId);
			socialVo.setRemainingNum(Scholarship.getRemainingNum()+vo.getEditNum());
			scholarshipDao.edit(socialVo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addSD(SocialVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		IndexVo indexVo = new IndexVo();
		indexVo.setdId(vo.getdId());
		indexVo.setsId(vo.getsId());
		List<ScholarshipDepartment> ldList = scholarshipDepartmentDao.findAll(indexVo);
		if(ldList.size()>0){
			reason = "该院系已被分配该资助";
		}else{
			Scholarship Scholarship = scholarshipDao.getScholarshipById(vo.getsId());
			if(Scholarship.getRemainingNum()<vo.getNum()){
				reason = "名额不足";
			}else{
				scholarshipDepartmentDao.add(vo);
				
				vo.setNum(null);
				vo.setRemainingNum(Scholarship.getRemainingNum()-num);
				vo.setId(vo.getsId());
				scholarshipDao.edit(vo);
				
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editSD(SocialVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setLdId(vo.getId());
		List<ScholarshipStudent> lsList = scholarshipStudentDao.findAll(indexVo);
		if(vo.getNum()<lsList.size()){
			reason = "现已"+lsList.size()+"人，您提交的名额应不小于这个";
		}else{
			Scholarship Scholarship = scholarshipDao.getScholarshipById(vo.getsId());
			if(Scholarship.getRemainingNum()<(vo.getNum()-vo.getEditNum())){
				reason = "名额不足";
			}else{
				vo.setRemainingNum(vo.getNum()-lsList.size());
				scholarshipDepartmentDao.edit(vo);
				
				//修改Scholarship的RemainingNum
				vo.setNum(null);
				vo.setRemainingNum(Scholarship.getRemainingNum()-(num-vo.getEditNum()));
				vo.setId(vo.getsId());
				scholarshipDao.edit(vo);
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addSS(SocialVo vo) {
		boolean result = false;
		String reason = "";
		//查看学号存不存在
		IndexVo indexVo = new IndexVo();
		indexVo.setStuNo(vo.getStuNo());
		Student stu = studentDao.getStudentByNo(indexVo);
		if(stu!=null){
			indexVo.setStudentId(stu.getId());
			if(vo.getdId()==0 || stu.getdId()==vo.getdId()){
				//查看是否已存在
				indexVo.setLdId(vo.getSdId());
				List<ScholarshipStudent> lsList = scholarshipStudentDao.findAll(indexVo);
				if(lsList.size()>0){
					reason = "该生已参与当前贷款";
				}else{
					vo.setStudentId(stu.getId());
					ScholarshipDepartment ld = scholarshipDepartmentDao.getSDById(vo.getSdId());
					if(ld!=null){
						if(stu.getdId()==ld.getdId()){
							vo.setsId(ld.getsId());
							Integer count = scholarshipStudentDao.add(vo);
							if(count>0){
								vo.setId(vo.getSdId());
								vo.setRemainingNum(ld.getRemainingNum()-1);
								scholarshipDepartmentDao.edit(vo);
								result = true;
							}
						}else{
							reason = "当前贷款与学号所在院系不匹配";
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
	public List<ScholarshipStudent> allSS(IndexVo vo) {
		List<ScholarshipStudent> list = scholarshipStudentDao.findAll(vo);
		for(ScholarshipStudent ls:list){
			Department d = departmentDao.getDepartmentById(ls.getStudent().getdId());
			if(d!=null && ls.getStudent()!=null){
				ls.setStuName(ls.getStudent().getName());
				ls.setStuNo(ls.getStudent().getStuNo());
				ls.setPhone(ls.getStudent().getPhone());
				ls.setGradeNo(ls.getStudent().getGradeNo());
				ls.setDepartment(d.getName());
				Professional p = professionalDao.getProfessionalById(ls.getStudent().getpId());
				if(p!=null){
					ls.setProfessional(p.getName());
				}else{
					ls.setProfessional("/");
				}
				if(ls.getScholarship()!=null){
					ls.setScholarshipTitle(ls.getScholarship().getTitle());
					ls.setTheYear(ls.getScholarship().getTheYear());
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
	public Integer delSS(IndexVo vo) {
		Integer count = scholarshipStudentDao.del(vo);
		if(count>0){
			ScholarshipDepartment ld = scholarshipDepartmentDao.getSDById(vo.getSdId());
			SocialVo socialVo = new SocialVo();
			socialVo.setId(vo.getSdId());
			socialVo.setRemainingNum(ld.getRemainingNum()+1);
			scholarshipDepartmentDao.edit(socialVo);
		}
		return count;
	}
	
}
