package com.isurpass.house.service;

import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class SearchTest {
    @Autowired
    OrganizationDAO od;
    @Test
    public void testSearch(){
        List<OrganizationPO> resultList = null;
        Specification s = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(root.get("status"),1));
                predicates.add(criteriaBuilder.like(root.get("name"),"%a%"));
//                predicates.add(criteriaBuilder.equal(root.join("employee", JoinType.INNER).get("organizationid"),"1"));
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  od.findAll(s);
        System.out.println(resultList.size());
    }

    @Test
    public void testSearch0() {
//        List<OrganizationPO> list = od.findByCodeLikeAndOrganizationidLike("%"+""+"%",5+"%");
//        System.out.println(list.toString());
    }

}
