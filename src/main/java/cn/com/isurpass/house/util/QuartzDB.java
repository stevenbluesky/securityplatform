package cn.com.isurpass.house.util;

import cn.com.isurpass.house.dao.OrganizationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzDB {
    @Autowired
    private OrganizationDAO organizationDAO;
    public void connectDB(){
        long count = organizationDAO.count();
    }

}
