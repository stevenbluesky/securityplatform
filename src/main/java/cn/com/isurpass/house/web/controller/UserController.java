package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.result.JsonResult;
import cn.com.isurpass.house.service.UserService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.vo.TransferVO;
import cn.com.isurpass.house.vo.UserAddVO;
import cn.com.isurpass.house.vo.UserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public JsonResult modify(UserAddVO user) {
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
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.ASC, "userid");
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

    @RequestMapping("userList")
    public String userList() {
        return "user/userList";
    }

    @RequestMapping("typeUserInfo")
    public String typeUserInfo() {
        return "user/typeUserInfo";
    }
}
