package cn.com.isurpass.house.result;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

public class PageResult {

	private Integer rows;// 每页的数据条数
	private Integer page;// 第几页
	private Direction sortOrder;

	public Direction getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		if (sortOrder.equalsIgnoreCase("asc"))
			this.sortOrder = Direction.ASC;
		if (sortOrder.equalsIgnoreCase("desc"))
			this.sortOrder = Direction.DESC;
		else
			this.sortOrder = null;
	}

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

	public Pageable pageable() {
		return PageRequest.of(this.getPage() - 1, this.getRows(), this.getSortOrder());
	}
}
