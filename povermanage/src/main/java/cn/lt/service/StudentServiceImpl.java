package cn.lt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.lt.dao.DepartmentDao;
import cn.lt.dao.ProfessionalDao;
import cn.lt.dao.StudentDao;
import cn.lt.po.Department;
import cn.lt.po.Professional;
import cn.lt.po.Student;
import cn.lt.util.Tools;
import cn.lt.vo.IndexVo;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Autowired
	private ProfessionalDao professionalDao;
	
	@Override
	public Student getStudentById(Integer id) {
		Student student = studentDao.getStudentById(id);
		return student;
	}

	@Override
	public List<Student> findAll(IndexVo vo) {
		List<Student> list = studentDao.findAll(vo);
		for(Student s:list){
			if(s.getSex()==0){
				s.setSexStr("女");
			}else{
				s.setSexStr("男");
			}
			Department de = departmentDao.getDepartmentById(s.getdId());
			Professional pr = professionalDao.getProfessionalById(s.getpId());
			if(de!=null){
				s.setDepartment(de.getName());
			}else{
				s.setDepartment("/");
			}
			if(de!=null){
				s.setProfessional(pr.getName());
			}else{
				s.setProfessional("/");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = studentDao.del(vo);
		if(count>0){
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public List<Department> findDepartment(IndexVo vo) {
		List<Department> list = departmentDao.findAll(vo);
		return list;
	}

	@Override
	public Map<String, Object> add(IndexVo vo) {
		boolean result = false;
		String reason = "";
		//学号不重�?
		Student su = studentDao.getStudentByNo(vo);
		if(su==null){
			vo.setClassNo(vo.getGradeNo()+4);
			Integer count = 0;
			if(vo.getId()!=null){
				count = studentDao.edit(vo);
			}else{
				count = studentDao.add(vo);
			}
			if(count>0){
				result = true;
			}
		}else{
			reason = "学号已存在";
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public List<Professional> getProfessional(IndexVo vo) {
		List<Professional> list = professionalDao.getProfessional(vo);
		return list;
	}

	@Override
	public Department getDepartmentById(Integer id) {
		Department de = departmentDao.getDepartmentById(id);
		return de;
	}

	@Override
	public Student getStudentByNo(String stuNo) {
		IndexVo vo = new IndexVo();
		vo.setStuNo(stuNo);
		Student stu = studentDao.getStudentByNo(vo);
		if(stu!=null){
			if(stu.getSex()==1){
				stu.setSexStr("男");
			}else{
				stu.setSexStr("女");
			}
		}
		return stu;
	}

}
