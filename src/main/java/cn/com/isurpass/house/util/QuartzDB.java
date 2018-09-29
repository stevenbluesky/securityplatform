package cn.com.isurpass.house.util;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PhoneuserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzDB {
    @Autowired
    private OrganizationDAO organizationDAO;
    @Autowired
    private PhoneuserDAO phoneuserDAO;
    public void connectDB(){
        organizationDAO.count();
        phoneuserDAO.countTotal();
    }

}
