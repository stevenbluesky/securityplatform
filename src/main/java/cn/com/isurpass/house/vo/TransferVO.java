package cn.com.isurpass.house.vo;

import java.util.Arrays;

public class TransferVO {
	private String hope;
	private Object[] ids;
	private String confirmdelete;
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

	public String getConfirmdelete() {
		return confirmdelete;
	}

	public void setConfirmdelete(String confirmdelete) {
		this.confirmdelete = confirmdelete;
	}

	@Override
	public String toString() {
		return "TransferVO{" +
				"hope='" + hope + '\'' +
				", ids=" + Arrays.toString(ids) +
				", confirmdelete='" + confirmdelete + '\'' +
				'}';
	}
}
