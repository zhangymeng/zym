package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Aid;
import cn.lt.po.AidDepartment;
import cn.lt.po.AidStudent;
import cn.lt.vo.AidVo;
import cn.lt.vo.IndexVo;

public interface AidService {
	List<Aid> findAid(IndexVo vo);

	List<AidDepartment> findAidDepartment(IndexVo vo);

	Map<String, Object> delAid(IndexVo vo);

	Map<String, Object> addAid(AidVo vo);

	Map<String, Object> editAid(AidVo vo);

	Map<String, Object> delAD(IndexVo vo);

	Map<String, Object> addAD(AidVo vo);

	Map<String, Object> editAD(AidVo vo);

	Map<String, Object> addAS(AidVo vo);

	List<AidStudent> allAS(IndexVo vo);

	Integer delAS(IndexVo vo);
}
