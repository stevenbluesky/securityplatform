package cn.com.isurpass.house.web.controller;

import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.result.PageResult;
import cn.com.isurpass.house.service.PhoneuserService;
import cn.com.isurpass.house.util.ExportExcelUtil;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.RegistUserSearchVO;
import cn.com.isurpass.house.vo.RegisteredEndUsersVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
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

    @RequestMapping(value = "/exportregisteredendusersdata")
    @ResponseBody
    public String exportRegisteredEndusersData(RegistUserSearchVO usv, HttpServletRequest request, HttpServletResponse response) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        List<RegisteredEndUsersVO> dataset = null;
        if(FormUtils.isEmpty(usv)){
            dataset = pus.listRegistUser();
        }else{
            dataset = pus.search(usv);
        }

        if(dataset==null||dataset.size()==0){
            return "No Data !";
        }
        String sheetName = "Registered End Users Data";
        String titleName = "Registered End Users Data";
        String fileName = "Registered End Users Data";
        int columnNumber = 4;
        int[] columnWidth = { 30, 40,25,60};
        String[] columnName = {"User Name" ,"APP Login Email","Register Time","Gateway ID"};
        try {
            HSSFWorkbook wb = new ExportExcelUtil().exportNoResponse(sheetName, titleName, columnNumber, columnWidth, columnName, dataset);
            if(wb==null){
                return "System Error !";
            }
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Export Successfully !";
    }
    /**
     * 发送响应流方法
     */
    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName+".xls");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
