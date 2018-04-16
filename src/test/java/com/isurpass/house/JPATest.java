package com.isurpass.house;

import cn.com.isurpass.house.dao.GatewayDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.UserDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.util.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:application.xml")
public class JPATest {

	@Autowired
	OrganizationDAO org;
	@Autowired
	private GatewayDAO gatewayDAO;
	@Autowired
	UserDAO ud;
	
	@Test
	public void testOrg() {
		List<OrganizationPO> list = org.findAllOrgSelect();
		list.forEach(o ->{
			System.out.println(o.toString());
		});
	}
	
	@Test
	public void testPage() {
		//@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
		Pageable pageable = PageRequest.of(0,3, Sort.Direction.ASC,"organizationid");
		Page<OrganizationPO> list = org.findByOrgtype(pageable,Constants.ORGTYPE_INSTALLER);
		list.forEach(o ->{
			System.out.println(o.toString());
		});
	}
	@Test
	public void test(){
		OrganizationPO org0 = org.findByOrganizationid(1);
		System.out.println(org0.getOrganizationid());
	}
	@Test
	public void mytest(){
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(3);
		List<Integer> list2 = new ArrayList<>();
		list1.retainAll(list2);
		System.out.println(list1.size());
	}


    @Test
    public void testsp() {
    /*    ZwaveDevicePO z = new ZwaveDevicePO();
        z.setDeviceid("");
        Pageable pageable = new PageRequest(0,10, new Sort(Sort.Direction.DESC, "zwavedeviceid"));
        Page<ZwaveDevicePO> all = zd.findAll(new Specification<ZwaveDevicePO>() {
            @Override
            public Predicate toPredicate(Root<ZwaveDevicePO> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate stuNameLike = null;
//                if (null != student && !StringUtils.isEmpty(student.getName())) {
                stuNameLike = cb.like(root.<String>get("name"), "%" + "hw" + "%");
//                }

                Predicate clazzNameLike = null;
//                if (null != student && null != student.getClazz() && !StringUtils.isEmpty(student.getClazz().getName())) {
                clazzNameLike = cb.like(root.<String>get("gatewayPO").<String>get("deviceid"), "%" + "iRe" + "%");
//                }
                Predicate gu = cb.equal(root.<String>get("gatewayPO").<String>get("gatewayUserPO").<String>get("userid"), 1);
                if (null != stuNameLike) query.where(stuNameLike);
                if (null != clazzNameLike) query.where(clazzNameLike);
                query.where(gu);
                return null;
            }
        }, pageable);
        System.out.println(all);
        all.forEach(System.out::println);*/
    }

    @Test
    public void testSelect(){
//        ZwaveDevicePO z = zd.findByZwavedeviceid(11541);
//        System.out.println(z.getZwavedeviceid());
//        System.out.println(z.getGatewayPO().getGatewayUserPO());
    }

    @Test
    public void testNativeSQL(){
        Integer id = ud.getUserid("fdsafasd");
        String id1 = ud.getUsernameByDeviceid("iRemote8005000000002");
        System.out.println(id);
        System.out.println(id1);
    }
}
