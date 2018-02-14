package cn.lt.service;

import java.util.List;
import java.util.Map;

import cn.lt.po.Social;
import cn.lt.po.SocialDepartment;
import cn.lt.po.SocialStudent;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface SocialService {
	List<Social> findSocial(IndexVo vo);

	List<SocialDepartment> findSocialDepartment(IndexVo vo);

	Map<String, Object> delSocial(IndexVo vo);

	Map<String, Object> addSocial(SocialVo vo);

	Map<String, Object> editSocial(SocialVo vo);

	Map<String, Object> delSD(IndexVo vo);

	Map<String, Object> addSD(SocialVo vo);

	Map<String, Object> editSD(SocialVo vo);

	Map<String, Object> addSS(SocialVo vo);

	List<SocialStudent> allSS(IndexVo vo);

	Integer delSS(IndexVo vo);
}
