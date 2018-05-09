package cn.com.isurpass.house.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.OrgSearchVO;
import cn.com.isurpass.house.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.OrganizationService;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.vo.OrgAddVO;

/**
 * 服务商相关 接口 和 访问路径
 *
 * @author jwzh
 */

/**
 * @author jwzh
 */
@Controller
@RequestMapping("org")
public class OrganizationController {
    @Autowired
    OrganizationService ss;


    @RequestMapping("queryInstallerInfo")
    public String queryInstallerInfo(HttpServletRequest request, Integer installerid, Model model){
        model.addAttribute("orgVO",ss.queryInstallerInfo(request, installerid));
        return "installer/installerInfo";
    }

    @RequestMapping("querySupplierInfo")
    public String querySupplierInfo(HttpServletRequest request, Integer supplierid, Model model){
        model.addAttribute("orgVO",ss.querySupplierInfo(request, supplierid));
        return "supplier/supplierInfo";
    }
    @RequestMapping("toggleOrganizationStatus")
    @ResponseBody
    public JsonResult toggleOrganizationStatus(@RequestBody TransferVO tf, HttpServletRequest request) {
        String hope = tf.getHope();
        Object[] ids = tf.getIds();
        try {
            ss.toggleOrganizationStatus0(hope, ids, request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        }
        return new JsonResult(1, "1");
    }

//    @RequestMapping("toggleOrganizationStatus0")
    @ResponseBody
    public JsonResult toggleOrganizationStatus0(Integer id, Integer toStatus, HttpServletRequest request) {
        try {
            ss.toggleOrganizationStatus(id, toStatus, request);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        }
        return new JsonResult(1, "1");
    }

    /**
     * 添加服务商
     *
     * @param as
     * @param request
     * @return
     */
    //TODO 这个方法到时候可以改成直接跳转或者是返回Json数据,如果返回json数据的话,前端就要通过Ajax提交,而不是通过 form 的 action 属性提交
    @RequestMapping("add")
    @ResponseBody
    public JsonResult add(OrgAddVO as, HttpServletRequest request) {
        //TODO 由于页面选择地区的js代码会默认选择一个国家,所以必须要填完所有地址选择框,而有时候用户不想选择总公司等的地址,这时就无法正常添加.可以在地区列表第一行加一个空的选项
//		System.out.println(as.toString());
        //noinspection Duplicates
        try {
            ss.add(as, request);
        } catch (MyArgumentNullException e) {
            return new JsonResult(-1, e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
        return new JsonResult(1, "1");
    }

    @RequestMapping("addInstallerorg")
    @ResponseBody
    public JsonResult addInstallerorg(OrgAddVO as, HttpServletRequest request) {
        //TODO 由于页面选择地区的js代码会默认选择一个国家,所以必须要填完所有地址选择框,而有时候用户不想选择总公司等的地址,这时就无法正常添加.可以在地区列表第一行加一个空的选项
        try {
            ss.addByOrgtype(as, Constants.ORGTYPE_INSTALLER, request);
        } catch (MyArgumentNullException e) {
            return new JsonResult(-1, e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
        return new JsonResult(1, "1");
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
    //只有ameta可以访问
    @RequestMapping("supplierJsonList")
    @ResponseBody
    public Map<String, Object> supplierJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "organizationid");
        if (!FormUtils.isEmpty(osv)) {//搜索
            return ss.search(pageable, osv, request, Constants.ORGTYPE_SUPPLIER);
        }
        return ss.listOrgByType(pageable, Constants.ORGTYPE_SUPPLIER);
    }

    @RequestMapping("installerJsonList")
    @ResponseBody
    public Map<String, Object> installerJsonList(PageResult pr, OrgSearchVO osv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "organizationid");
        if (!FormUtils.isEmpty(osv)) {//搜索
            return ss.search(pageable, osv, request, Constants.ORGTYPE_INSTALLER);
        }
        return ss.listInstallerOrg(pageable, request);
    }

    @RequestMapping("validCode")
    @ResponseBody
    public Map<String, Boolean> validCode(String code) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("valid", ss.validCode(code));
        return map;
    }

    @RequestMapping("listAllSupplier")
    @ResponseBody
    public List<OrganizationPO> listAllInstaller() {
        return ss.listOrgSelectByType(Constants.ORGTYPE_SUPPLIER);
    }

    @RequestMapping("listAllOrgSelect")
    @ResponseBody
    public List<OrganizationPO> listAllOrgSelect() {
        return ss.listAllOrgSelect();
    }

    @RequestMapping("addSupplierPage")
    public String addSupplier(@RequestParam(required = false) Integer organizationid, HttpServletRequest request) {
        if (organizationid != null) {
            request.getSession().setAttribute("orgInfo", ss.getOrganizationVOInfo(organizationid));
        } else {
            request.getSession().setAttribute("orgInfo", null);
        }
        return "supplier/addSupplier";
    }

    @RequestMapping("supplierList")
    public String supplierList() {
        return "supplier/supplierList";
    }

    @RequestMapping("addInstallerPage")
    public String addInstallerPage(@RequestParam(required = false) Integer organizationid, HttpServletRequest request) {
        if (organizationid != null) {
//		    request.setAttribute("orgInfo",ss.getOrganizationVOInfo(organizationid));
            request.getSession().setAttribute("orgInfo", ss.getOrganizationVOInfo(organizationid));
        } else {
            //清除empInfo session
            request.getSession().setAttribute("orgInfo", null);
        }
        return "installer/addInstaller";
    }

    @RequestMapping("installerList")
    public String installerList() {
        return "installer/installerList";
    }

}
