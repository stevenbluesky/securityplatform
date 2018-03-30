package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.OrgAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("Duplicates")
@Service
public class AddressService {


    @Autowired
    AddressDAO ad;
    @Autowired
    CountryDAO country;
    @Autowired
    ProvinceDAO province;
    @Autowired
    CityDAO city;

    public List<CountryPO> getCountry() {
        return country.findAll();
    }

    public List<CityPO> getCity(Integer provinceid) {
        return city.findByProvinceid(provinceid);
    }

    public List<ProvincePO> getProvince(Integer countryid) {
        return province.findByCountryid(countryid);
    }

    /**
     * 通过addressid将查询到的信息存入employeeAddVO里面
     *
     * @param addressid
     * @param emp
     */
    @Transactional(readOnly = true)
    public void findAddressInfo(Integer addressid, EmployeeAddVO emp) {
        if (addressid != null) {
            AddressPO addresspo = ad.findByAddressid(addressid);
            if (addresspo != null) {
                emp.setCityname(addresspo.getCity());
                emp.setCountryname(addresspo.getCountry());
                emp.setProvincename(addresspo.getProvince());
                emp.setDetailaddress(addresspo.getDetailaddress());
                emp.setAddressid(addresspo.getAddressid());
            }
        }
    }

    @Transactional(readOnly = true)
    public void findAddressInfo(OrganizationPO orgPO, OrgAddVO org) {
        if (orgPO.getOfficeaddressid() != null) {
            org.setOfficeaddressid(orgPO.getOfficeaddressid());
            org.setAddress(ad.findByAddressid(orgPO.getOfficeaddressid()));
        }
        if (orgPO.getBillingaddressid() != null) {
            org.setBillingaddressid(orgPO.getBillingaddressid());
            org.setBaddress(ad.findByAddressid(orgPO.getBillingaddressid()));
        }
        if (orgPO.getCsaddressid() != null) {
            org.setCsaddressid(orgPO.getCsaddressid());
            org.setCsaddress(ad.findByAddressid(orgPO.getCsaddressid()));
        }
    }
}
