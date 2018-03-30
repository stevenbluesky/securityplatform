package com.isurpass.house;


import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.vo.OrgSearchVO;
import org.hibernate.boot.jaxb.SourceType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class OrganizationDAOTest {

    @Autowired
    OrganizationDAO od;

    @Test
    public void odtest(){
        String name = "%"+""+"%";
        String citycode= "%"+"1"+"%";
        Pageable pageable = PageRequest.of(0,10, Sort.Direction.ASC,"organizationid");
        Page<OrganizationPO> byNameLikeAndCitycodeLike = od.findByNameLikeAndCitycodeLike(pageable,name, citycode);
        System.out.println("====================");
        byNameLikeAndCitycodeLike.forEach(f-> System.out.println(f.getName()));
    }

    @Test
    public void test(){
        OrgSearchVO search = new OrgSearchVO();

        /*search.setName("12313");
        search.setCitycode("citycode");
        search.setCity("city");
        System.out.println("======");*/
//        System.out.println(Optional.of(search).filter(f->f.getName() =="12313").get());
//        System.out.println(Optional.of(search).filter(f -> f.getName() == null).filter(f -> f.getCity() == null).filter(f -> f.getCitycode() == null).get());
//        String s1 = Optional.ofNullable(search).map(s -> s.getName()).orElse("");
    }
}
