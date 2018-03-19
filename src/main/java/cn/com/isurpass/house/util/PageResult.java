package cn.com.isurpass.house.util;

public class PageResult {

	private Integer rows;// 每页的数据条数
	private Integer page;// 第几页
	@SuppressWarnings("unused")
	private Integer start;

	public Integer getRows() {
		return rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getStart() {
		return (page - 1) * rows;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

}
