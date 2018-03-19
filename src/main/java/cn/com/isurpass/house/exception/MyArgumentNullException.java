package cn.com.isurpass.house.exception;

/**
 * 当表单传过来的必填字段为空时,返回此异常
 * @author jwzh
 *
 */
public class MyArgumentNullException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MyArgumentNullException() {};
	public MyArgumentNullException(String msg) {
		super(msg);
	};
}
