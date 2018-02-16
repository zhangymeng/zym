package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.AidDao;
import cn.lt.dao.AidDepartmentDao;
import cn.lt.dao.AidStudentDao;
import cn.lt.dao.ProfessionalDao;
import cn.lt.dao.StudentDao;
import cn.lt.po.Department;
import cn.lt.po.Aid;
import cn.lt.po.AidDepartment;
import cn.lt.po.AidStudent;
import cn.lt.po.Professional;
import cn.lt.po.Student;
import cn.lt.util.Tools;
import cn.lt.vo.IndexVo;
import cn.lt.vo.AidVo;

@Service
public class AidServiceImpl implements AidService {
	@Autowired
	private AidDao aidDao;
	
	@Autowired
	private AidDepartmentDao aidDepartmentDao;
	
	@Autowired
	private AidStudentDao aidStudentDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private ProfessionalDao professionalDao;
	
	@Override
	public List<Aid> findAid(IndexVo vo) {
		List<Aid> list = aidDao.findAll(vo);
		return list;
	}

	@Override
	public List<AidDepartment> findAidDepartment(IndexVo vo) {
		List<AidDepartment> list = aidDepartmentDao.findAll(vo);
		for(AidDepartment ld:list){
			Department d = departmentDao.getDepartmentById(ld.getdId());
			ld.setTitle(ld.getAid().getTitle());
			ld.setTheYear(ld.getAid().getTheYear());
			if(d!=null){
				ld.setDepartment(d.getName());
			}else{
				ld.setDepartment("/");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> delAid(IndexVo vo) {
		int aidId = vo.getId();
		boolean result = false;
		String reason = "";
		//删除aid前提查看是否已有学生被
		vo.setAidId(vo.getId());
		List<AidStudent> lsList = aidStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的项";
		}else{
			aidDao.del(vo);
			
			vo.setAidId(aidId);
			vo.setId(null);
			aidDepartmentDao.del(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addAid(AidVo vo) {
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		Integer count = aidDao.add(vo);
		if(count>0){
			result = true;
		}else{
			reason = "插入失败";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editAid(AidVo vo) {
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setAidId(vo.getId());
		List<AidDepartment> list = aidDepartmentDao.findAll(indexVo);
		int count=0;
		for(AidDepartment ld:list){
			count = count+ld.getNum();
		}
		if(vo.getNum()<count){
			reason = "现在已经"+count+"个名额被分配，您提交的名额应不小于这个值";
		}else{
			Aid aid = aidDao.getAidById(vo.getId());
			vo.setRemainingNum(aid.getRemainingNum()+(vo.getNum()-aid.getNum()));
			aidDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> delAD(IndexVo vo) {
		Integer aidId = vo.getAidId();
		boolean result = false;
		String reason = "";
		//删除aidd前提查看是否已有学生
		vo.setAidId(null);
		vo.setAdId(vo.getId());
		List<AidStudent> lsList = aidStudentDao.findAll(vo);
		if(lsList.size()>0){
			reason = "不能删除正在进行的项";
		}else{
			Aid aid = aidDao.getAidById(aidId);
			aidDepartmentDao.del(vo);
			
			AidVo aidVo = new AidVo();
			aidVo.setId(aidId);
			aidVo.setRemainingNum(aid.getRemainingNum()+vo.getEditNum());
			aidDao.edit(aidVo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addAD(AidVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		vo.setRemainingNum(vo.getNum());
		IndexVo indexVo = new IndexVo();
		indexVo.setdId(vo.getdId());
		indexVo.setAidId(vo.getAidId());
		List<AidDepartment> ldList = aidDepartmentDao.findAll(indexVo);
		if(ldList.size()>0){
			reason = "该院系已被分配该项";
		}else{
			Aid aid = aidDao.getAidById(vo.getAidId());
			if(aid.getRemainingNum()<vo.getNum()){
				reason = "名额不足";
			}else{
				aidDepartmentDao.add(vo);
				
				vo.setNum(null);
				vo.setRemainingNum(aid.getRemainingNum()-num);
				vo.setId(vo.getAidId());
				aidDao.edit(vo);
				
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> editAD(AidVo vo) {
		Integer num = vo.getNum();
		boolean result = false;
		String reason = "";
		IndexVo indexVo = new IndexVo();
		indexVo.setAdId(vo.getId());
		List<AidStudent> lsList = aidStudentDao.findAll(indexVo);
		if(vo.getNum()<lsList.size()){
			reason = "现已"+lsList.size()+"人，您提交的名额应不小于这个";
		}else{
			Aid aid = aidDao.getAidById(vo.getAidId());
			if(aid.getRemainingNum()<(vo.getNum()-vo.getEditNum())){
				reason = "名额不足";
			}else{
				vo.setRemainingNum(vo.getNum()-lsList.size());
				aidDepartmentDao.edit(vo);
				
				//修改aid的RemainingNum
				vo.setNum(null);
				vo.setRemainingNum(aid.getRemainingNum()-(num-vo.getEditNum()));
				vo.setId(vo.getAidId());
				aidDao.edit(vo);
				result = true;
			}
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> addAS(AidVo vo) {
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
				indexVo.setAdId(vo.getAdId());
				List<AidStudent> lsList = aidStudentDao.findAll(indexVo);
				if(lsList.size()>0){
					reason = "该生已参与当前项";
				}else{
					vo.setStudentId(stu.getId());
					AidDepartment ld = aidDepartmentDao.getADById(vo.getAdId());
					if(ld!=null){
						if(stu.getdId()==ld.getdId()){
							vo.setAidId(ld.getAidId());
							Integer count = aidStudentDao.add(vo);
							if(count>0){
								vo.setId(vo.getAdId());
								vo.setRemainingNum(ld.getRemainingNum()-1);
								aidDepartmentDao.edit(vo);
								result = true;
							}
						}else{
							reason = "当前项与学号所在院系不匹配";
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
	public List<AidStudent> allAS(IndexVo vo) {
		List<AidStudent> list = aidStudentDao.findAll(vo);
		for(AidStudent ls:list){
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
				if(ls.getAid()!=null){
					ls.setAidTitle(ls.getAid().getTitle());
					ls.setTheYear(ls.getAid().getTheYear());
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
	public Integer delAS(IndexVo vo) {
		Integer count = aidStudentDao.del(vo);
		if(count>0){
			AidDepartment ld = aidDepartmentDao.getADById(vo.getAdId());
			AidVo aidVo = new AidVo();
			aidVo.setId(vo.getAdId());
			aidVo.setRemainingNum(ld.getRemainingNum()+1);
			aidDepartmentDao.edit(aidVo);
		}
		return count;
	}
	
}
