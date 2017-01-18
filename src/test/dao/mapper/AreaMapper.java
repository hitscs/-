package com.htsc.base.area.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.htsc.base.area.dao.pojo.Area;
import com.htsc.base.sign.dao.pojo.Sign;

public interface AreaMapper {
	public int insert(Area area);

	public int delete(Long id);

	public int update(Area area);

	public Area get(Long id);

	public List<Area> findActivedList();

	public List<Area> findAllList();

	public List<Area> findListByParent(Area area);

	public List<Area> findUnactivedList();

	public List<Area> findByConditions(Area area);

	public List<Area> findPageSelfAndChildrensById(@Param("id") Long id, @Param("start") int start, @Param("end") int end, @Param("orderColumn") String orderColumn);

	public int countSelfAndChildrensById(@Param("id") Long id);

	public List<Area> findPageByName(@Param("name") String name, @Param("start") int start, @Param("end") int end, @Param("orderColumn") String orderColumn);

	public int countByName(@Param("name") String name);
	
	public int changeToActive(Long[] ids); 

	public int changeToUnactive(Long[] ids);
	
	List<Area> findPageByConditions(@Param("area") Area area, @Param("start") int start, @Param("end") int end);
	
	public int countByConditions(Area area);
}