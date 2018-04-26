package com.isurpass.house;


import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.po.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OPDatabase {

    @Autowired
    AddressDAO ad;
    @Autowired
    EmployeeDAO emp;
    @Autowired
    RolePrivilegeDAO rp;
    @Autowired
    EmployeeroleDAO erd;
    @Autowired
    UserDAO ud;
    @Autowired
    ZwaveDeviceDAO zdd;
    @Autowired
    CountryDAO cd;

    @Test
    public void addRP() {
        for (int i = 4; i < 17; i++) {
            RolePrivilegePO rolep = new RolePrivilegePO();
            rolep.setCreatetime(new Date());
            rolep.setPrivilegeid(i);
            rolep.setRoleid(6);
            rp.save(rolep);
        }
    }

    /**
     * 给数据库中没有员工的角色添加角色
     */
    @Test
    public void addEmployeerole() {
        List<EmployeePO> all = emp.findAll();
        List<Integer> employeeid = all.stream().map(EmployeePO::getEmployeeid).collect(toList());
        System.out.println(employeeid);
        employeeid.forEach(e -> {
            if (erd.findByEmployeeid(e).size() == 0) {
                //员工存在,而employeerole里面没有他的角色信息
                EmployeeRolePO erp = new EmployeeRolePO();
                erp.setCreatetime(new Date());
                erp.setEmployeeid(e);
                erp.setRoleid(5);
                erd.save(erp);
            }
        });
    }

    @Test
    public void search() throws InterruptedException {
        Random rand = new Random();
        ZwaveDevicePO z = new ZwaveDevicePO();
        z.setBattery(100);
        z.setDevicetype("4");
        z.setCreatetime(new Date());
        z.setStatus(255);
        z.setStatuses(null);
        z.setWarningstatuses("[255]");

        for (int i = 2000; i < 4000; i++) {
//            int i1 = rand.nextInt(6) + 1;
            z.setZwavedeviceid(i);
            z.setDeviceid("iRemote8005000000001");//[1,7)
            z.setName("testzwavedevice"+i);
            zdd.save(z);
//            Thread.sleep(10);
        }
    }

    @Test
    public void search1() throws InterruptedException {
//        List<ZwaveDeviceListVO> zwaveDeviceListVOS = zdd.lilstZwaveDeviceListVO(0, 10);
//        List<Object[]> lists = zdd.listZwaveDeviceListVO(0, 10);
//        List<ZwaveDeviceListVO> zwaveDeviceListVOS = ConvertQueryResultToVOUtils.convertZwaveDevice(lists);
//        for (ZwaveDeviceListVO zwaveDeviceListVO : zwaveDeviceListVOS) {
//            System.out.println(zwaveDeviceListVO);
//        }
//        String test = zdd.test("SELECT z.name form zwavedevice z where z.zwavedeviceid=1;");
//        System.out.println(test);
//        Object[] objects = zdd.lilstZwaveDeviceListVO0(0, 10);
//        for (Object object : objects) {
//            Object[] cells = (Object[]) object;
//            System.out.println(cells[0]);
//        }
//        System.out.println(o.toString());
    }

    @Test
    public void addCountry(){
        for (int i = 0; i < 15; i++) {
            CountryPO c = new CountryPO();
            c.setCountryabbreviation("testCountry"+i);
            c.setCountryname("countryName"+i);
            cd.save(c);
        }
    }
}
