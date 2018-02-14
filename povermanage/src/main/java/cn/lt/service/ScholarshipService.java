package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Scholarship;
import cn.lt.po.ScholarshipDepartment;
import cn.lt.po.ScholarshipStudent;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface ScholarshipService {
	List<Scholarship> findScholarship(IndexVo vo);

	List<ScholarshipDepartment> findScholarshipDepartment(IndexVo vo);

	Map<String, Object> delScholarship(IndexVo vo);

	Map<String, Object> addScholarship(SocialVo vo);

	Map<String, Object> editScholarship(SocialVo vo);

	Map<String, Object> delSD(IndexVo vo);

	Map<String, Object> addSD(SocialVo vo);

	Map<String, Object> editSD(SocialVo vo);

	Map<String, Object> addSS(SocialVo vo);

	List<ScholarshipStudent> allSS(IndexVo vo);

	Integer delSS(IndexVo vo);
}
