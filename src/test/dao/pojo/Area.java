package com.htsc.base.area.dao.pojo;

public class Area {
    private Long id;

    private String sysCode;

    private String activeDate;

    private String showCode;

    private String createTime;

    private Integer dataSource;

    private String isProvincileCaptial;

    private String isDirectControlled;

    private String shortName;

    private Integer dataCreateType;

    private Area parent;

    private String isActive;

    private String isClassify;

    private Long innerOrder;
    
    private String name;
    
    private Integer isProvincileCenter;
    
    private Integer belongDzx;
    
    private Integer belongArea;
    
    private Integer isSingleCity;
    
    private Integer is35City;
    
    private Integer haveSub;//数据库不存在，仅用于页面树结构，根据此判断有无下级子节点     1:有, 0:没有


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getShowCode() {
        return showCode;
    }

    public void setShowCode(String showCode) {
        this.showCode = showCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDataSource() {
        return dataSource;
    }

    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    public String getIsProvincileCaptial() {
        return isProvincileCaptial;
    }

    public void setIsProvincileCaptial(String isProvincileCaptial) {
        this.isProvincileCaptial = isProvincileCaptial;
    }

    public String getIsDirectControlled() {
        return isDirectControlled;
    }

    public void setIsDirectControlled(String isDirectControlled) {
        this.isDirectControlled = isDirectControlled;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getDataCreateType() {
        return dataCreateType;
    }

    public void setDataCreateType(Integer dataCreateType) {
        this.dataCreateType = dataCreateType;
    }

    /**
	 * @return the parent
	 */
	public Area getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Area parent) {
		this.parent = parent;
	}

	public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsClassify() {
        return isClassify;
    }

    public void setIsClassify(String isClassify) {
        this.isClassify = isClassify;
    }

    public Long getInnerOrder() {
        return innerOrder;
    }

    public void setInnerOrder(Long innerOrder) {
        this.innerOrder = innerOrder;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsProvincileCenter() {
		return isProvincileCenter;
	}

	public void setIsProvincileCenter(Integer isProvincileCenter) {
		this.isProvincileCenter = isProvincileCenter;
	}

	public Integer getBelongDzx() {
		return belongDzx;
	}

	public void setBelongDzx(Integer belongDzx) {
		this.belongDzx = belongDzx;
	}

	public Integer getBelongArea() {
		return belongArea;
	}

	public void setBelongArea(Integer belongArea) {
		this.belongArea = belongArea;
	}

	public Integer getIsSingleCity() {
		return isSingleCity;
	}

	public void setIsSingleCity(Integer isSingleCity) {
		this.isSingleCity = isSingleCity;
	}

	public Integer getIs35City() {
		return is35City;
	}

	public void setIs35City(Integer is35City) {
		this.is35City = is35City;
	}

	public Integer getHaveSub() {
		return haveSub;
	}

	public void setHaveSub(Integer haveSub) {
		this.haveSub = haveSub;
	}
    
    
}