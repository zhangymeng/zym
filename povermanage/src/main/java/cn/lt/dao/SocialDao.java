package cn.lt.dao;

import java.util.List;

import cn.lt.po.Social;
import cn.lt.vo.IndexVo;
import cn.lt.vo.SocialVo;

public interface SocialDao {
	Social getSocialById(Integer id);

	List<Social> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(SocialVo vo);

	Integer edit(SocialVo vo);
}
