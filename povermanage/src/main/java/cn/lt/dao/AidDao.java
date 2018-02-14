package cn.lt.dao;

import java.util.List;

import cn.lt.po.Aid;
import cn.lt.vo.AidVo;
import cn.lt.vo.IndexVo;

public interface AidDao {
	Aid getAidById(Integer id);

	List<Aid> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(AidVo vo);

	Integer edit(AidVo vo);
}
