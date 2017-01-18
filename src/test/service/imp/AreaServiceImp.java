package com.htsc.base.area.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htsc.base.area.dao.mapper.AreaMapper;
import com.htsc.base.area.dao.pojo.Area;
import com.htsc.base.area.service.AreaService;
import com.htsc.base.sign.dao.pojo.Sign;
import com.htsc.bean.PageBean;

@Service
public class AreaServiceImp implements AreaService {

	@Autowired
	private AreaMapper mapper;

	@Override
	public int insert(Area area) {
		mapper.insert(area);
		return 0;
	}

	@Override
	public int delete(Long id) {
		mapper.delete(id);
		return 0;
	}

	@Override
	public Area get(Long id) {
		return mapper.get(id);
	}

	@Override
	public List<Area> findActivedList() {
		return mapper.findActivedList();
	}

	@Override
	public List<Area> findAllList() {
		return mapper.findAllList();
	}

	@Override
	public List<Area> findListByParent(Area area) {
		return mapper.findListByParent(area);
	}

	@Override
	public List<Area> findUnactivedList() {
		return mapper.findUnactivedList();
	}

	@Override
	public int update(Area area) {
		mapper.update(area);
		return 0;
	}

	@Override
	public List<Area> findByConditions(Area area) {
		return mapper.findByConditions(area);
	}

	@Override
	public PageBean findPageSelfAndChildrensById(Long id, int start, int end, String orderColumn) {
		PageBean page = new PageBean();
		int total = mapper.countSelfAndChildrensById(id);
		List<Area> areaList = mapper.findPageSelfAndChildrensById(id, start, end, orderColumn);
		page.setTotal(total);
		page.setModels(areaList);
		return page;
	}

	@Override
	public PageBean findPageByName(String name, int start, int end, String orderColumn) {
		PageBean page = new PageBean();
		int total = mapper.countByName(name);
		List<Area> areaList = mapper.findPageByName(name, start, end, orderColumn);
		page.setTotal(total);
		page.setModels(areaList);
		return page;
	}

	@Override
	public List<Area> findSelfAndChildrensById(Long id) {
		List<Area> areaList = new ArrayList<Area>();
		Area area = mapper.get(id);
		areaList.add(area);
		Area areaChild = new Area();
		areaChild.setParent(area);
		areaChild.setIsActive("1");
		List<Area> childrenAreaList = mapper.findByConditions(areaChild);
		areaList.addAll(childrenAreaList);
		return areaList;
	}
	
	
	
	@Override
	public int changeToActive(Long[] ids) {
		mapper.changeToActive(ids);
		return 0;
	}

	@Override
	public int changeToUnactive(Long[] ids) {
		mapper.changeToUnactive(ids);
		return 0;
	}
	@Override
	public PageBean findPageByConditions(Area area, PageBean pageBean) {
		int start = (pageBean.getPageNum() - 1) * pageBean.getPageSize();
		int end = pageBean.getPageNum() * pageBean.getPageSize();
		int total = mapper.countByConditions(area);
		List<Area> list = mapper.findPageByConditions(area, start, end);
		pageBean.setTotal(total);
		pageBean.setModels(list);
		return pageBean;
	}	
}
