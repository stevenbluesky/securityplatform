package cn.com.isurpass.house.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.exception.MyArgumentNullException;
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
		//TODO 由于页面选择地区的js代码会默认选择一个国家,所以必须要填完所有地址选择框,而有时候用户不想选择总公司等的地址,这时就无法正常添加.可以在地区列表第一行加一个空的选项
//		System.out.println(as.toString());
		try {
			ss.add(as);
		} catch (MyArgumentNullException e) {
			return new JsonResult(-1,e.getMessage());
		}catch(RuntimeException e) {
			e.printStackTrace();
			return new JsonResult(-1,"出错啦~");
		}
		return new JsonResult(1,"success");
	}

	@RequestMapping("listAllOrg")
	@ResponseBody
	public List<OrganizationPO> listAllOrg() {
		return ss.listAllOrg();
	}

	/**
	 * 以页为单位来返回 json 格式的服务商列表
	 * 
	 * @param pr
	 * @return
	 */
	@RequestMapping("supplierJsonList")
	@ResponseBody
	public Map<String, Object> supplierJsonList(PageResult pr) {
		return ss.listOrgByType(pr, Constants.ORGTYPE_SUPPLIER);
	}

	@RequestMapping("installerJsonList")
	@ResponseBody
	public Map<String, Object> installerJsonList(PageResult pr) {
		return ss.listOrgByType(pr, Constants.ORGTYPE_INSTALLER);
	}

	@RequestMapping("listAllSupplier")
	@ResponseBody
	public List<OrganizationPO> listAllInstaller() {
		return ss.listOrgSelectByType(Constants.ORGTYPE_INSTALLER);
	}

	@RequestMapping("listAllOrgSelect")
	@ResponseBody
	public List<OrganizationPO> listAllOrgSelect() {
		return ss.listAllOrgSelect();
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
