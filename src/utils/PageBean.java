package utils;


 
import java.util.ArrayList;
import java.util.List;

public class PageBean
{
	private List<?> models = new ArrayList();
	private int total = 0;
	private int pageNum = 1;
	private int pageSize;
	private int totalPageNum;
	
	public PageBean(){
		pageNum = 1;
		pageSize = 10;  //初始化每页显示记录数，如需更改可通过setPageSize设置
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List getModels()
	{
		return models;
	}
	
	public void setModels(List models)
	{
		this.models = models;
	}
	
	public int getTotal()
	{
		return total;
	}
	
	public void setTotal(int total)
	{
		this.total = total;
	}
	
	public int getNextIndex(){
		return pageSize * (pageNum - 1);
	}
	
	public int getTotalPageNum() {
		totalPageNum = total % pageSize == 0?total/pageSize:total/pageSize+1;
		if(totalPageNum == 0)
			totalPageNum = 1;
		return totalPageNum;
	}

	
}
