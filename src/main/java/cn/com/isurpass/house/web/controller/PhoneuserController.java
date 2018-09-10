package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.PhoneuserService;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.RegistUserSearchVO;
import cn.com.isurpass.house.vo.UserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author liwenxiang
 * Date:2018/9/7
 * Time:19:18
 */
@Controller
@RequestMapping("phoneuser")
public class PhoneuserController {

    @Autowired
    private PhoneuserService pus;

    @RequestMapping("registuserJsonList")
    @ResponseBody
    public Map<String, Object> registuserJsonList(PageResult pr, RegistUserSearchVO usv) {
        Pageable pageable = PageRequest.of(pr.getPage() - 1, pr.getRows(), Sort.Direction.DESC, "phoneuserid");
        if (!FormUtils.isEmpty(usv)) {//搜索
            return pus.search(pageable, usv);
        }
        return pus.listRegistUser(pageable);
    }

    @RequestMapping("registuser")
    private String registuserpage(){
        return "user/registuser";
    }
}
