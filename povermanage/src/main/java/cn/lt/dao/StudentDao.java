package cn.lt.dao;

import java.util.List;

import cn.lt.po.Student;
import cn.lt.vo.IndexVo;

public interface StudentDao {

	Student getStudentById(Integer id);

	List<Student> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(IndexVo vo);

	Student getStudentByNo(IndexVo vo);

	Integer edit(IndexVo vo);

}
