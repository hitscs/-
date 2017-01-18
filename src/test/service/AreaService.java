package com.htsc.base.area.service;

import java.util.List;

import com.htsc.base.area.dao.pojo.Area;
import com.htsc.base.sign.dao.pojo.Sign;
import com.htsc.bean.PageBean;

public interface AreaService {
	/**
	 * 插入单条数据
	 * 
	 * @param area
	 * @return
	 */
	public int insert(Area area);

	/**
	 * 逻辑删除，设置为未激活
	 * 
	 * @param area
	 * @return
	 */
	public int delete(Long id);

	/**
	 * 更新地区
	 * 
	 * @param area
	 * @return
	 */
	public int update(Area area);

	/**
	 * 获取单条地区数据
	 * 
	 * @param id
	 * @return
	 */
	public Area get(Long id);

	/**
	 * 获取所有激活状态地区列表
	 * 
	 * @return
	 */
	public List<Area> findActivedList();

	/**
	 * 获取所有未激活状态地区列表
	 * 
	 * @return
	 */
	public List<Area> findUnactivedList();

	/**
	 * 获取所有地区列表
	 * 
	 * @return
	 */
	public List<Area> findAllList();

	/**
	 * 根据父节点获取所有激活状态地区列表
	 * 
	 * @return
	 */
	public List<Area> findListByParent(Area area);
	/**
	 * 按条件查询，对象中加入需要查询的字段数据即可；
	 * 
	 * @return
	 */		
	public List<Area> findByConditions(Area area);
	/**
	 * 通过id查询对象及其儿子
	 * 
	 * @param id
	 * @param start
	 *            开始（0做为第一条）
	 * @param end
	 *            结束
	 * @param orderColumn
	 *            排序（a desc,b asc,c desc）
	 * @return PageBean
	 */
	public PageBean findPageSelfAndChildrensById(Long id, int start, int end, String orderColumn);

	/**
	 * 通过name模糊查询
	 * 
	 * @param name
	 *            名称
	 * @param start
	 *            开始（0做为第一条）
	 * @param end
	 *            结束
	 * @param orderColumn
	 *            排序（a desc,b asc,c desc）
	 * @return PageBean
	 */
	public PageBean findPageByName(String name, int start, int end, String orderColumn);
	/**
	 * 通过id查询对象及其儿子
	 * 
	 * @param id
	 * 
	 * @param orderColumn
	 *            排序（a desc,b asc,c desc）
	 * @return PageBean
	 */
	public List<Area> findSelfAndChildrensById(Long id);
	
	
	
	public int changeToActive(Long[] ids);
	
	public int changeToUnactive(Long[] ids);
	/**
	 * 根据Area查询
	 * 
	 * @param area,pageBean
	 * @return List<Area>
	 */
	public PageBean findPageByConditions(Area area,PageBean pageBean);	
}
