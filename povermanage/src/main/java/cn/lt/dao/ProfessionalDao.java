package cn.lt.dao;

import java.util.List;

import cn.lt.po.Professional;
import cn.lt.vo.IndexVo;

public interface ProfessionalDao {

	List<Professional> getProfessional(IndexVo vo);
	
	Professional getProfessionalById(Integer id);
}
