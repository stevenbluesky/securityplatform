package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.po.UserPO;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.UserService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService us;

    @RequestMapping("modifyUserInfo")
    public String modifyUserInfo(HttpServletRequest request, Integer userid, Model model) {
        if (userid == null) {
            return null;
        }
        UserAddVO userAddVO = us.queryUserAddInfo(request, userid);
        model.addAttribute("userVO", userAddVO);
        return "user/modifyUserInfo";
    }

    @RequestMapping("queryUserInfo")
    public String queryUserInfo(HttpServletRequest request, Integer userid, Model model) {
        if (userid == null) {
            return null;
        }
        UserAddVO userAddVO = us.queryUserAddInfo(request, userid);
        model.addAttribute("userVO", userAddVO);
        return "user/userInfo";
    }

    @RequestMapping("modify")
    @ResponseBody
    public JsonResult modify(UserAddVO user) {//TODO
        if (user.getUserid() == null) {
            return new JsonResult(-1, "user not exist");
        }
        try {
            us.modify(user);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        }
        return new JsonResult(1, "1");
    }
    @RequestMapping("fillGateway")
    @ResponseBody
    public String fillGateway(@RequestBody String appaccount,Model model) {
        GatewayPO gatewayPO = us.fillGateway(appaccount);
        if(gatewayPO!=null){
            model.addAttribute("deviceid",gatewayPO.getDeviceid());
            return gatewayPO.getDeviceid();
        }else{
            return "failed";
        }
    }
    @RequestMapping("add")
    @ResponseBody
    public JsonResult addUserInfo(UserAddVO user, HttpServletRequest request) {
        try {
            us.add(user, request);
        } catch (RuntimeException e){
            e.printStackTrace();
            return new JsonResult(-1, e.getMessage());
        } catch(Exception e){
            e.printStackTrace();
            return new JsonResult(-1, "-1");
        }
        return new JsonResult(1, "2");
    }

    @RequestMapping("userInfoJsonList")
    @ResponseBody
    public Map<String, Object> userInfoJsonList(PageResult pr, UserSearchVO usv, HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "userid");
        if (!FormUtils.isEmpty(usv)) {//搜索
            return us.search(pageable, usv, request);
        }
        return us.listUserInfo(pageable, request);
    }

    @RequestMapping("toggleUserStatus")
    @ResponseBody
    public String toggleUserStatus0(@RequestBody TransferVO tf, HttpServletRequest request) {
        String hope = tf.getHope();
        Object[] ids = tf.getIds();
        try {
            us.toggleUserStatus0(hope, ids, request);
        } catch (MyArgumentNullException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
    @RequestMapping("validCode")
    @ResponseBody
    public Map<String, Boolean> validCode(String appaccount) {
        Map<String, Boolean> map = new HashMap<>();
        if(StringUtils.isEmpty(appaccount)){
            map.put("valid", true);
            return map;
        }
        map.put("valid", us.validCode(appaccount));
        return map;
    }
    /**
     * 解绑电话卡
     * @param mydata
     * @param request
     * @return
     */
    @RequestMapping(value = "unbundling",method = RequestMethod.POST)
    @ResponseBody
    public String unbundling(@RequestBody String mydata,HttpServletRequest request) {
        String userid = mydata.split("#")[0];
        String serialnumber = mydata.split("#")[1];
        try {
            us.unbundling(userid,serialnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }

    /**
     * 解绑网关
     * @param mydata
     * @param request
     * @return
     */
    @RequestMapping(value = "unbundlinggateway",method = RequestMethod.POST)
    @ResponseBody
    public String unbundlingGateway(@RequestBody String mydata,HttpServletRequest request) {
        try {
            EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
            //us.findPro(emp.getOrganizationid());
            us.unbundlinggateway(mydata);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "1";
    }
    @RequestMapping("userList")
    public String userList() {
        return "user/userList";
    }

    @RequestMapping("typeUserInfo")
    public String typeUserInfo(HttpServletRequest request,Model model) {
        //只有安装员能打开此页面
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer organizationid = emp.getOrganizationid();
        Integer employeeid = emp.getEmployeeid();
        try {
            us.findTypeUserPro(organizationid,employeeid);
        } catch (Exception e) {
            e.printStackTrace();
            return "../../noInstaller";
        }
        //String userCode = us.createUserCode(employeeid,organizationid,null);
        //String supCode = us.createSupCode(organizationid,null);
        String supname = us.findSupName(organizationid);
        String suporgid= us.findSupOrgId(organizationid);
        String insname = us.findInsName(organizationid);
        String groupid = us.findGroupid(organizationid);
        //model.addAttribute("supCode",supCode);
        //model.addAttribute("userCode",userCode);
        model.addAttribute("supname",supname);
        model.addAttribute("suporgid",suporgid);
        model.addAttribute("orgInfo",us.findOrgInfo(organizationid));
        model.addAttribute("insname",insname);
        model.addAttribute("groupid",groupid);
        model.addAttribute("installername",emp.getLoginname());
        model.addAttribute("installercode",emp.getCode());
        return "user/typeUserInfo";
    }
    @RequestMapping("findCodes")
    @ResponseBody
    public String findCodes(HttpServletRequest request){
        String ss = request.getParameter("suporgid");
        String uu = request.getParameter("userid");
        int suporgid = Integer.parseInt(ss);
        Integer organizationid;
        Integer employeeid;
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        boolean flag = us.checkAmeta(emp);
        if(flag&&!StringUtils.isEmpty(uu)){//ameta在修改用户的服务商
            UserPO user = us.findUserInfo(Integer.parseInt(uu));
            organizationid=user.getInstallerorgid();
            String supCode = us.createSupCode(organizationid,suporgid);
            return supCode+"#";
        }else{
            organizationid = emp.getOrganizationid();
            employeeid = emp.getEmployeeid();
            String userCode = us.createUserCode(employeeid,organizationid,suporgid);
            //String supCode = us.createSupCode(organizationid,suporgid);
            return userCode;
        }

    }
    @RequestMapping("findsuppliercode")
    @ResponseBody
    public String findSupplierCode(Model model,HttpServletRequest request){
        String ss = request.getParameter("suporgid");
        int suporgid = Integer.parseInt(ss);
        //model.addAttribute("suppliercode",us.findSupplierCode(suporgid));
        return us.findSupplierCode(suporgid);
    }
    @RequestMapping("modifypassword")
    public String modifypasswordpage(HttpServletRequest request) {
        return "user/modifyPassword";
    }
    @RequestMapping("updatePassowrd")
    @ResponseBody
    public String updatePassword(Model model,HttpServletRequest request){
        try{
            String oldpassword = request.getParameter("oldpassword");
            String newpassword = request.getParameter("newpassword");
            if(StringUtils.isEmpty(oldpassword)||StringUtils.isEmpty(newpassword)){
                return "failed";
            }
            us.updatePassword(oldpassword,newpassword,request);
            return "success";
        }catch (Exception e){
            return "failed";
        }
    }

}
