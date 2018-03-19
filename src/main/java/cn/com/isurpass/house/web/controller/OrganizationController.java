package cn.com.isurpass.house.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.PageResult;
import cn.com.isurpass.house.vo.OrgAddVO;

/**
 * 服务商相关 接口 和 访问路径
 * @author jwzh
 *
 */
/**
 * @author jwzh
 *
 */
@Controller
@RequestMapping("org")
public class OrganizationController {
	@Autowired
	OrganizationService ss;
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
	public JsonResult add(OrgAddVO as) {
		System.out.println(as.toString());
		try {
			ss.add(as);
		} catch (RuntimeException e) {
			return new JsonResult(-1,"出错啦");
		}
		return new JsonResult(1,"success");
	}
	
	/**
	 * 以页为单位来返回 json 格式的服务商列表
	 * @param pr
	 * @return
	 */
	@RequestMapping("supplierJsonList")
	@ResponseBody
	public Map<String,Object> supplierJsonList(PageResult pr) {
		return ss.listOrgByType(pr,Constants.ORGTYPE_SUPPLIER);
	}
	
	@RequestMapping("installerJsonList")
	@ResponseBody
	public Map<String,Object> installerJsonList(PageResult pr) {
		return ss.listOrgByType(pr,Constants.ORGTYPE_INSTALLER);
	}
	
	@RequestMapping("listAllInstaller")
	@ResponseBody
	public List<OrganizationPO> listAllInstaller(){
		return ss.listAllInstaller(Constants.ORGTYPE_INSTALLER);
	}
	
	
	@RequestMapping("addSupplierPage")
	public String addSupplier() {
		return "supplier/addSupplier";
	}
	
	@RequestMapping("supplierList")
	public String supplierList() {
		return "supplier/supplierList";
	}

	@RequestMapping("addInstallerPage")
	public String addInstallerPage() {
		return "installer/addInstaller";
	}
	
	@RequestMapping("installerList")
	public String installerList() {
		return "installer/installerList";
	}
}
