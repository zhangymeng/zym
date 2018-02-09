package cn.lt.dao;

import java.util.List;

import cn.lt.po.Menu;

public interface MenuDao {

	List<Menu> findAll(List<Integer> list);

}
