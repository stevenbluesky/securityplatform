package cn.com.isurpass.house.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.com.isurpass.house.po.AddressPO;
import cn.com.isurpass.house.po.CityPO;
import cn.com.isurpass.house.po.CountryPO;
import cn.com.isurpass.house.po.ProvincePO;

@Repository
//@SuppressWarnings("unchecked")
public class AddressDAO extends BaseDAO {

	/**
	 * 获取所有数据库中的国家list
	 * @return
	 */
	public List<CountryPO> getCountry() {

		List<CountryPO> list2 = getSession().createQuery("from CountryPO").list();
		// list2.forEach(c -> System.out.println(c.getCountryname()));
		return list2;
	}

	/**
	 * 通过省份id获取城市list
	 * @param provinceid
	 * @return
	 */
	public List<CityPO> getCity(Integer provinceid) {
		// TODO Auto-generated method stub
		List<CityPO> list2 = getSession().createQuery("from CityPO where provinceid = ?").setInteger(0, provinceid)
				.list();
		// list2.forEach(c -> System.out.println(c.getCountryname()));
		return list2;
	}

	/**
	 * 通过国家id获取省份list
	 * @param countryid
	 * @return
	 */
	public List<ProvincePO> getProvince(Integer countryid) {
		// TODO Auto-generated method stub
		List<ProvincePO> list2 = getSession().createQuery("from ProvincePO where countryid = ?")
				.setInteger(0, countryid).list();
		// list2.forEach(c -> System.out.println(c.getCountryname()));
		return list2;
	}
	
	/**
	 * 通过 地址id 获取 城市的名称
	 * @param addressid
	 * @return
	 */
	public String getCityName(Integer officeaddressid) {
		return (String)getSession().createQuery("select city from AddressPO where addressid=?")
			.setInteger(0, officeaddressid).uniqueResult();
		
	}

	public Integer add(AddressPO address) {
		getSession().save(address);
		return address.getAddressid();
	}
}
