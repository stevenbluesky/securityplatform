package cn.com.isurpass.house.service;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.vo.LoginVO;
import cn.com.isurpass.house.vo.OrgSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.isurpass.house.dao.AddressDAO;
import cn.com.isurpass.house.dao.CityDAO;
import cn.com.isurpass.house.dao.CountryDAO;
import cn.com.isurpass.house.dao.EmployeeDAO;
import cn.com.isurpass.house.dao.OrganizationDAO;
import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.dao.ProvinceDAO;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.OrgAddVO;
import cn.com.isurpass.house.vo.OrgListVO;

@Service
public class OrganizationService {

    @Autowired
    OrganizationDAO od;
    @Autowired
    PersonDAO pd;
    @Autowired
    AddressDAO ad;
    @Autowired
    EmployeeDAO ed;
    @Autowired
    CountryDAO country;
    @Autowired
    ProvinceDAO province;
    @Autowired
    CityDAO city;
    @Autowired
    AddressService as;
    @Autowired
    PersonService ps;

    /**
     * 通过指定员工id和要切换成的方法进行状态的改变
     *
     * @param orgid
     * @param status
     */
    public void changeStatus(Integer orgid, Integer status) {
        OrganizationPO org = od.findByOrganizationid(orgid);
        switch (status) {
            case 0:
                org.setStatus(Constants.STATUS_UNVALID);
                break;
            case 1:
                org.setStatus(Constants.STATUS_NORMAL);
                break;
            case 2:
                org.setStatus(Constants.STATUS_SUSPENCED);
                break;
            case 9:
                org.setStatus(Constants.STATUS_DELETED);
                break;
            default:
                org.setStatus(Constants.STATUS_NORMAL);
                break;
        }
        od.save(org);
    }

    /**
     * 添加一个机构
     *
     * @param as
     * @throws MyArgumentNullException
     */
    public void add(OrgAddVO as, HttpServletRequest request) throws MyArgumentNullException {
        addByOrgtype(as, Constants.ORGTYPE_SUPPLIER, request);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addByOrgtype(OrgAddVO as, Integer orgtype, HttpServletRequest request) throws MyArgumentNullException {
        EmployeePO emp0 = (EmployeePO) request.getSession().getAttribute("emp");
        OrgAddVO orgInfo = (OrgAddVO) request.getSession().getAttribute("orgInfo");
        if (!FormUtils.checkOrgNull(as)) {
            throw new MyArgumentNullException("必填字段不能为空!");
        }
        if (orgInfo== null && !FormUtils.checkLoginNull(as)) {
            throw new MyArgumentNullException("必填字段不能为空!");
        }

        OrganizationPO org = new OrganizationPO();
        org.setName(as.getName());
        org.setCode(as.getCode());
        org.setStatus(1);
        org.setCentralstationname(as.getCsname());
        // FormUtils.copyO2O(org, as);// 将一些机构的属性复制到 org 中

        // if (as.getParentorgid() == null) {// 没有填写上级机构id,则表明这是一个服务商
        // Integer ametaId =
        // od.findByOrgtype(Constants.ORGTYPE_AMETA).get(0).getOrganizationid();// 取
        // Ameta 的机构id
        // as.setParentorgid(ametaId);
        // }
        // 不能这么判断,事实上,服务商新增安装商时也是不填上级机构的id(默认为服务商的id)

        if (orgInfo != null) {//不为空说明在进行修改操作
            org.setParentorgid(orgInfo.getParentorgid());
            org.setOrgtype(orgInfo.getOrgtype());
        } else {
            if (Constants.ORGTYPE_SUPPLIER.equals(orgtype)) {// 如果是添加服务商
                org.setParentorgid(od.findByOrgtype(Constants.ORGTYPE_AMETA).get(0).getOrganizationid());
                org.setOrgtype(Constants.ORGTYPE_SUPPLIER);
            }
            if (Constants.ORGTYPE_INSTALLER.equals(orgtype)) {// 安装商
                // org.setParentorgid(emp0.getOrganizationid());
                org.setOrgtype(Constants.ORGTYPE_INSTALLER);
                if (as.getParentorgid() != null) {// 不为空,ameta在进行添加//要进行当前用户是否是ameta员工的判断
                    org.setParentorgid(as.getParentorgid());

                } else {
                    org.setParentorgid(emp0.getOrganizationid());
                }
            }
        }

        // 以下这些值可能为空,因此需要进行NPE判断
        String countryname = null;
        String provincename = null;
        String cityname = null;
        String bCountryname = null;
        String bProvincename = null;
        String bCityname = null;
        String csCountryname = null;
        String csProvincename = null;
        String csCityname = null;

        if (as.getCountry() != null)
            countryname = country.findByCountryid(as.getCountry()).getCountryname();
        if (as.getProvince() != null)
            provincename = province.findByProvinceid(as.getProvince()).getProvincename();
        if (as.getCity() != null) {
            CityPO c = city.findByCityid(as.getCity());
            org.setCitycode(c.getCitycode());
            cityname = c.getCityname();
        }
        if (as.getBcountry() != null)
            bCountryname = country.findByCountryid(as.getBcountry()).getCountryname();
        if (as.getBprovince() != null)
            bProvincename = province.findByProvinceid(as.getBprovince()).getProvincename();
        if (as.getBcity() != null)
            bCityname = city.findByCityid(as.getBcity()).getCityname();

        if (as.getCscountry() != null)
            csCountryname = country.findByCountryid(as.getCscountry()).getCountryname();
        if (as.getCsprovince() != null)
            csProvincename = province.findByProvinceid(as.getCsprovince()).getProvincename();
        if (as.getCscity() != null)
            csCityname = city.findByCityid(as.getCscity()).getCityname();

        // 公司地址
        AddressPO address = new AddressPO(countryname, provincename, cityname, as.getDetailaddress(), as.getPostal());
        // 公司账务地址
        AddressPO bAddress = new AddressPO(bCountryname, bProvincename, bCityname, as.getBdetailaddress(),
                as.getBpostal());
        // 服务商联系人
        PersonPO sPerson = new PersonPO(as.getSname(), as.getSphonenumber(), as.getSemail(), as.getSfax());
        // 总公司地址
        AddressPO csAddress = new AddressPO(csCountryname, csProvincename, csCityname, "", as.getCspostal());
        // 服务商总公司联系人
        PersonPO csPerson = new PersonPO(as.getCspname(), as.getCspphonenumber(), as.getCspemail(), as.getCspfax());
//        EmployeePO emp = new EmployeePO(as.getLoginname(), FormUtils.encrypt(as.getPassword()), as.getQuestion(),FormUtils.encrypt(as.getAnswer()));
        EmployeePO emp = new EmployeePO(as.getLoginname(), Encrypt.encrypt(as.getLoginname(), as.getPassword(), as.getCode()), as.getQuestion(), Encrypt.encrypt(as.getLoginname(), as.getAnswer(), as.getCode()));


        System.out.println(address.toString());
        System.out.println(bAddress.toString());
        System.out.println(csAddress.toString());

        if (orgInfo != null) {
            address.setAddressid(orgInfo.getOfficeaddressid());
            bAddress.setAddressid(orgInfo.getBillingaddressid());
            csAddress.setAddressid(orgInfo.getCsaddressid());
            sPerson.setPersonid(orgInfo.getContactid());
            csPerson.setPersonid(orgInfo.getCscontactid());
        }

        Integer aId = null;// 公司地址id
        Integer csAId = null;
        Integer bAId = null;
        Integer sPId = null;
        Integer csPId = null;

        if (!FormUtils.isEmpty(address))
            aId = ad.save(address).getAddressid();
        if (!FormUtils.isEmpty(csAddress))
            csAId = ad.save(csAddress).getAddressid();
        if (!FormUtils.isEmpty(bAddress))
            bAId = ad.save(bAddress).getAddressid();
        if (!FormUtils.isEmpty(sPerson)) // 服务商联系人不为空
            sPId = pd.save(sPerson).getPersonid();
        if (!FormUtils.isEmpty(csPerson)) {// 服务商总公司联系人不为空
            csPId = pd.save(csPerson).getPersonid();
        }
//        od.findByName(as.getCsname());

        // 取到各分支方法返回的主键id后将其存入 org 对象中,然后再进行保存
        org.setOfficeaddressid(aId);
        org.setBillingaddressid(csAId);
        org.setCsaddressid(csAId);
        org.setBillingaddressid(bAId);
        org.setContactid(sPId);
        org.setCscontactid(csPId);

        if (orgInfo != null) {
            org.setOrganizationid(orgInfo.getOrganizationid());
        }
        Integer orgId = od.save(org).getOrganizationid();
        if (orgInfo == null) {
            emp.setOrganizationid(orgId);
            emp.setStatus(1);
            if (!FormUtils.isEmpty(emp)) {// 管理员不为空..由于此方法一开始就对管理员账号不存在的情况进行了处理,所以此处管理员对象是肯定存在的.
                // TODO 现在数据库里面 Loginname
                // 是unique类型的,要文档上面意思是不同的机构可以有相同的loginname,所以这里要判断一下同机构中的loginname,并去掉数据库里面的unique索引
                ed.save(emp);
            }
        }
    }

    /**
     * 通过分页形式返回指定类型的机构列表
     *
     * @param pageable
     * @param orgType
     * @return
     */
    public Map<String, Object> listOrgByType(Pageable pageable, Integer orgType) {
        Map<String, Object> map = new HashMap<>();
        Page<OrganizationPO> orgList = od.findByOrgtype(pageable, orgType);
        map.put("total", orgList.getTotalElements());
        List<OrgListVO> list = new ArrayList<>();
        setProperties(orgList, list);
        map.put("rows", list);
        return map;
    }

    public Map<String, Object> listInstallerOrg(Pageable pageable, HttpServletRequest request) {
        // 角色为服务商的员工才可以访问
        // 先取员工的机构id,再通过机构id查询其机构下所有的安装商,
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Page<OrganizationPO> orgList = null;
        if (isAdmin(emp.getOrganizationid())) {
            orgList = od.findByOrgtype(pageable, Constants.ORGTYPE_INSTALLER);
        } else {
            orgList = od.findByOrgtypeAndParentorgidIn(pageable, Constants.ORGTYPE_INSTALLER, emp.getOrganizationid());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", orgList.getTotalElements());
        List<OrgListVO> list = new ArrayList<>();
        setProperties(orgList, list);
        map.put("rows", list);
        return map;
    }

    private void setProperties(Page<OrganizationPO> orgList, List<OrgListVO> list) {
        orgList.forEach(o -> {
            OrgListVO orgVO = new OrgListVO();
            orgVO.setOrganizationid(o.getOrganizationid());
            orgVO.setName(o.getName());
            if (o.getOfficeaddressid() != null) {
                AddressPO addresspo = ad.findByAddressid(o.getOfficeaddressid());
                if (addresspo != null) {
                    orgVO.setCity(addresspo.getCity());
                }
            } else
                orgVO.setCity("-");
            orgVO.setCode(o.getCode());
            orgVO.setStatus(o.getStatus());
            list.add(orgVO);
            // System.out.println(list.toString()+"fff");
        });
    }

    /**
     * 通过机构类型列出所有的机构
     *
     * @return
     */
    public List<OrganizationPO> listOrgByType(Integer orgType) {
        return od.findByOrgtype(orgType);
    }

    /**
     * 与 listOrgByTyep 不同的是此方法只返回 organizationid 和 name.
     *
     * @param orgType
     * @return
     */
    public List<OrganizationPO> listOrgSelectByType(Integer orgType) {
        return od.findAllOrgSelect(orgType);
    }

    public List<OrganizationPO> listAllOrg() {
        return od.findAll();
    }

    public List<OrganizationPO> listAllOrgSelect() {
        return od.findAllOrgSelect();
    }

    public boolean validCode(String code) {
        OrganizationPO org = od.findByCode(code);
        if (org == null)
            return true;
        return false;
    }

    public List<Integer> findParentOrgid(Integer id, List<Integer> list) {
        OrganizationPO org = od.findByOrganizationid(id);//
        od.findByParentorgid(org.getOrganizationid());
        if (org.getParentorgid() != null) {
            list.add(org.getParentorgid());
            findParentOrgid(org.getParentorgid(), list);
        }
        return list;
    }

    public List<Integer> findChildrenOrgid(Integer id, List<Integer> list) {
        List<OrganizationPO> orgList = od.findByParentorgid(id);
        if (orgList == null) {
            list.add(id);
            return list;
        }
        orgList.forEach(o -> {
            list.add(o.getOrganizationid());
            findChildrenOrgid(o.getOrganizationid(), list);
        });
        return list;

    }

    /**
     * 通过机构id判断它是否是ameta
     *
     * @param id
     * @return
     */
    public boolean isAdmin(Integer id) {
        OrganizationPO org = od.findByOrganizationid(id);
        if (org != null) {
            return org.getOrgtype() == Constants.ORGTYPE_AMETA;
        }
        return false;
    }

    public OrgAddVO getOrganizationVOInfo(Integer organizationid) {
        OrgAddVO org = new OrgAddVO();
        OrganizationPO orgPO = od.findByOrganizationid(organizationid);
        org.setName(orgPO.getName());
        org.setCode(orgPO.getCode());
        org.setCsname(orgPO.getCentralstationname());
        org.setOrgtype(orgPO.getOrgtype());
        org.setOrganizationid(orgPO.getOrganizationid());
        org.setParentorgid(orgPO.getParentorgid());
//        AddressPO addressInfo = as.findAddressInfo(orgPO.getOfficeaddressid());
//        AddressPO baddressInfo = as.findAddressInfo(orgPO.getBillingaddressid());
//        AddressPO csaddressInfo = as.findAddressInfo(orgPO.getCsaddressid());
//        PersonPO peronInfo = ps.findPeronInfo(orgPO.getContactid());
//        PersonPO bPersonInfo = ps.findPeronInfo(orgPO.getCscontactid());
        as.findAddressInfo(orgPO, org);
        ps.findPersonInfo(orgPO, org);
        System.out.println(org.toString());
        return org;
    }

    public Map<String, Object> search(Pageable pageable,OrgSearchVO search,Integer orgtype) {
        if (FormUtils.isEmpty(search)) {
            return null;
        }
        String name = "";
        String city1 = "";
        String citycode = "";
        Page<OrganizationPO> orgList= null;
        if (search.getName() != null) {
            name= search.getName();
        }
        if (search.getCity() != null) {
            city1= search.getCity();
        }
        if (search.getCitycode() != null) {
            citycode=search.getCitycode();
        }

        if (search.getCity() == "") {
            orgList = od.findByNameLikeAndCitycodeLike(pageable,name,citycode);
        }
        if (search.getCity()!= ""){
            //先通过citycode和city查找相似的,再通过返回的List集合中的citycode在organization表中查找
            List<CityPO> list = city.findByCitycodeLikeAndCitynameLike(citycode, city1);
            if (list.isEmpty()) {
                return null;
            }
            List<String> list0 = new ArrayList<>();
            list.forEach(l->list0.add(l.getCitycode()));
            orgList = od.findByNameLikeAndCitycodeInAndOrgtype(name, list0,orgtype,pageable);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", orgList.getTotalElements());
        List<OrgListVO> list = new ArrayList<>();
        setProperties(orgList, list);
        map.put("rows", list);
        return map;
    }

    /**
     * @param id
     * @param toStatus
     * @param request
     */
    public void toggleOrganizationStatus(Integer id, Integer toStatus, HttpServletRequest request) {
        if (toStatus == null) {
            throw new RuntimeException("status不能为空.");
        }
        if (!hasProvilege(id, request)) {
            throw new RuntimeException("无权操作");
        }
        OrganizationPO org = od.findByOrganizationid(id);
        org.setStatus(toStatus);
        od.save(org);
    }

    /**
     * 通过要操作的员工id和request判断当前登录的员工是否有权限操作此机构
     *
     * @param organizationid
     * @param request
     * @return
     */
    public boolean hasProvilege(Integer organizationid, HttpServletRequest request) {
        OrganizationPO org = od.findByOrganizationid(organizationid);//要操作的员工的机构
        EmployeePO emp1 = (EmployeePO) request.getSession().getAttribute("emp");
        OrganizationPO org1 = od.findByOrganizationid(emp1.getOrganizationid());
        if (org1.getOrgtype() == Constants.ORGTYPE_AMETA) {//如果登录的员工机构是ameta,直接操作
            return true;
        } else if (org1.getOrgtype() == Constants.ORGTYPE_SUPPLIER) {//员工的机构id必须是他的安装商或者服务商
            if (org1.getOrganizationid() == org.getOrganizationid() || org1.getOrganizationid() == org.getParentorgid()) {
                return true;
            } else if (org1.getOrgtype() == Constants.ORGTYPE_INSTALLER && org.getOrganizationid() == org1.getOrganizationid()) {
                return true;
            }
        }
        return false;
    }

    public void toggleOrganizationStatus0(String hope, Object[] ids,HttpServletRequest request) {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString()), Constants.STATUS_NORMAL, request);
            }

        } else if("suspence".equals(hope)){
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString()),Constants.STATUS_SUSPENCED,request);
            }
        }
    }
}
