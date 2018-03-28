package cn.com.isurpass.house.vo;

import java.util.Arrays;

public class TransferVO {
	private String hope;
	private Object[] ids;
	public String getHope() {
		return hope;
	}
	public void setHope(String hope) {
		this.hope = hope;
	}
	
	public Object[] getIds() {
		return ids;
	}
	public void setIds(Object[] ids) {
		this.ids = ids;
	}
	@Override
	public String toString() {
		return "TransferVO [hope=" + hope + ", ids=" + Arrays.toString(ids) + "]";
	}
	
}
