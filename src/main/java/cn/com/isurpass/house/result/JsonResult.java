package cn.com.isurpass.house.result;

/**
 * 用于构造返回 Json 字符串的对象
 * @author jwzh
 *
 */
public class JsonResult {

	private Integer status;
	private String msg;
	
	public JsonResult() {}
	public JsonResult(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
