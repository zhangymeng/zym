package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Department;
import cn.lt.po.Professional;
import cn.lt.po.Student;
import cn.lt.po.UserInfo;
import cn.lt.vo.IndexVo;

public interface StudentService {

	Student getStudentById(Integer id);

	List<Student> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	List<Department> findDepartment(IndexVo vo);

	Map<String, Object> add(IndexVo vo);

	List<Professional> getProfessional(IndexVo vo);

}
