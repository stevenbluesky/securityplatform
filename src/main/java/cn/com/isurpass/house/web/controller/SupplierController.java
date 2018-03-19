package cn.com.isurpass.house.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.SupplierService;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.AddSupplierVO;

/**
 * 服务商相关 接口 和 访问路径
 * @author jwzh
 *
 */
@Controller
@RequestMapping("supplier")
public class SupplierController {

	@Autowired
	SupplierService ss;
	/**
	 * 添加服务商
	 * @param name
	 * @param code
	 * @param country
	 * @return
	 */
	//TODO 这个方法到时候可以改成直接跳转或者是返回Json数据,如果返回json数据的话,前端就要通过Ajax提交,而不是通过 form 的 action 属性提交
	@RequestMapping("add")
	@ResponseBody
	public JsonResult add(AddSupplierVO as) {
		try {
			ss.addSupplier(as);
		} catch (RuntimeException e) {
			return new JsonResult(-1,e.getMessage());
		}
		return new JsonResult(1,"success");
	}
	
	@RequestMapping("supplierJsonList")
	@ResponseBody
	public Map<String,Object> supplierList(PageResult pr) {
		return ss.listSupplier(pr);
	}
	
	@RequestMapping("addSupplierPage")
	public String addSupplier() {
		return "supplier/addSupplier";
	}
	
	@RequestMapping("supplierList")
	public String supplierList() {
		return "supplier/supplierList";
	}
	
	
}
