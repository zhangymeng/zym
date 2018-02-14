package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.SocialDao;
import cn.lt.dao.SocialDepartmentDao;
import cn.lt.dao.SocialStudentDao;
import cn.lt.dao.ProfessionalDao;
import cn.lt.dao.StudentDao;
import cn.lt.po.Department;
import cn.lt.po.Social;
import cn.lt.po.SocialDepartment;
import cn.lt.po.SocialStudent;
import cn.lt.po.Professional;
import cn.lt.po.Student;
import cn.lt.util.Tools;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

@Service
public class SocialServiceImpl implements SocialService {
	@Autowired
	private SocialDao socialDao;
	
	@Autowired
	private SocialDepartmentDao socialDepartmentDao;
	
	@Autowired
	private SocialStudentDao socialStudentDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ProfessionalDao professionalDao;
	
	@Override
	public List<Social> findSocial(IndexVo vo) {
		List<Social> list = socialDao.findAll(vo);
		return list;
	}

	@Override
	public List<SocialDepartment> findSocialDepartment(IndexVo vo) {
		List<SocialDepartment> list = socialDepartmentDao.findAll(vo);
		for(SocialDepartment ld:list){
			Department d = departmentDao.getDepartmentById(ld.getdId());
			ld.setTitle(ld.getSocial().getTitle());
			ld.setTheYear(ld.getSocial().getTheYear());
			if(d!=null){
				ld.setDepartment(d.getName());
			}else{
				ld.setDepartment("/");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> delSocial(IndexVo vo) {
		int SocialId = vo.getId();
		boolean result = false;
		String reason = "";
		//删除Social前提查看是否已有学生被
		vo.setsId(vo.getId());
		List<SocialStudent> lsList = socialStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款条";
		}else{
			socialDao.del(vo);
			
			vo.setsId(SocialId);
			vo.setId(null);
			socialDepartmentDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addSocial(SocialVo vo) {
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		Integer count = socialDao.add(vo);
		if(count>0){
			result = true;
		}else{
			reason = "插入失败";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editSocial(SocialVo vo) {
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setsId(vo.getId());
		List<SocialDepartment> list = socialDepartmentDao.findAll(indexVo);
		int count=0;
		for(SocialDepartment ld:list){
			count = count+ld.getNum();
		}
		if(vo.getNum()<count){
			reason = "现在已经"+count+"个名额被分配，您提交的名额应不小于这个值";
		}else{
			Social Social = socialDao.getSocialById(vo.getId());
			vo.setRemainingNum(Social.getRemainingNum()+(vo.getNum()-Social.getNum()));
			socialDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> delSD(IndexVo vo) {
		Integer SocialId = vo.getsId();
		boolean result = false;
		String reason = "";
		//删除Sociald前提查看是否已有学生
		vo.setsId(null);
		vo.setLdId(vo.getId());
		List<SocialStudent> lsList = socialStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的贷款项";
		}else{
			Social Social = socialDao.getSocialById(SocialId);
			socialDepartmentDao.del(vo);
			
			SocialVo SocialVo = new SocialVo();
			SocialVo.setId(SocialId);
			SocialVo.setRemainingNum(Social.getRemainingNum()+vo.getEditNum());
			socialDao.edit(SocialVo);
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
		List<SocialDepartment> ldList = socialDepartmentDao.findAll(indexVo);
		if(ldList.size()>0){
			reason = "该院系已被分配该资助";
		}else{
			Social Social = socialDao.getSocialById(vo.getsId());
			if(Social.getRemainingNum()<vo.getNum()){
				reason = "名额不足";
			}else{
				socialDepartmentDao.add(vo);
				
				vo.setNum(null);
				vo.setRemainingNum(Social.getRemainingNum()-num);
				vo.setId(vo.getsId());
				socialDao.edit(vo);
				
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
		List<SocialStudent> lsList = socialStudentDao.findAll(indexVo);
		if(vo.getNum()<lsList.size()){
			reason = "现已"+lsList.size()+"人，您提交的名额应不小于这个";
		}else{
			Social Social = socialDao.getSocialById(vo.getsId());
			if(Social.getRemainingNum()<(vo.getNum()-vo.getEditNum())){
				reason = "名额不足";
			}else{
				vo.setRemainingNum(vo.getNum()-lsList.size());
				socialDepartmentDao.edit(vo);
				
				//修改Social的RemainingNum
				vo.setNum(null);
				vo.setRemainingNum(Social.getRemainingNum()-(num-vo.getEditNum()));
				vo.setId(vo.getsId());
				socialDao.edit(vo);
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
				List<SocialStudent> lsList = socialStudentDao.findAll(indexVo);
				if(lsList.size()>0){
					reason = "该生已参与当前贷款";
				}else{
					vo.setStudentId(stu.getId());
					SocialDepartment ld = socialDepartmentDao.getSDById(vo.getSdId());
					if(ld!=null){
						if(stu.getdId()==ld.getdId()){
							vo.setsId(ld.getsId());
							Integer count = socialStudentDao.add(vo);
							if(count>0){
								vo.setId(vo.getSdId());
								vo.setRemainingNum(ld.getRemainingNum()-1);
								socialDepartmentDao.edit(vo);
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
	public List<SocialStudent> allSS(IndexVo vo) {
		List<SocialStudent> list = socialStudentDao.findAll(vo);
		for(SocialStudent ls:list){
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
				if(ls.getSocial()!=null){
					ls.setSocialTitle(ls.getSocial().getTitle());
					ls.setTheYear(ls.getSocial().getTheYear());
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
		Integer count = socialStudentDao.del(vo);
		if(count>0){
			SocialDepartment ld = socialDepartmentDao.getSDById(vo.getSdId());
			SocialVo SocialVo = new SocialVo();
			SocialVo.setId(vo.getSdId());
			SocialVo.setRemainingNum(ld.getRemainingNum()+1);
			socialDepartmentDao.edit(SocialVo);
		}
		return count;
	}
	
}
