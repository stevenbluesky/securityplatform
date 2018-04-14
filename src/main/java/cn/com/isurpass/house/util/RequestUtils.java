package cn.com.isurpass.house.util;

import cn.com.isurpass.house.dao.EmployeeroleDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.EmployeePO;
import cn.com.isurpass.house.po.EmployeeRolePO;
import cn.com.isurpass.house.vo.RequestExpendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static java.util.stream.Collectors.toList;

@Component
public class RequestUtils {
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    OrganizationDAO od;

    public RequestExpendVO getEmployeeInfo(HttpServletRequest request) {
        RequestExpendVO req = new RequestExpendVO();
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        req.setEmployeerole(erd.findByEmployeeid(emp.getEmployeeid()).stream().map(EmployeeRolePO::getRoleid).collect(toList()));
        req.setEmployeeid(emp.getEmployeeid());
        req.setOrgid(emp.getOrganizationid());
        req.setOrgtype(od.findByOrganizationid(emp.getOrganizationid()).getOrgtype());
        return req;
    }
}
