package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.OrgAddVO;
import cn.com.isurpass.house.vo.OrgListVO;
import cn.com.isurpass.house.vo.OrgSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static java.util.stream.Collectors.toList;

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
    @Autowired
    EmployeeroleDAO erd;

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
    @Transactional(rollbackFor = Exception.class)
    public void add(OrgAddVO as, HttpServletRequest request) throws MyArgumentNullException {
        addByOrgtype(as, Constants.ORGTYPE_SUPPLIER, request);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addByOrgtype(OrgAddVO as, Integer orgtype, HttpServletRequest request) throws MyArgumentNullException {
        EmployeePO emp0 = (EmployeePO) request.getSession().getAttribute("emp");
        OrgAddVO orgInfo = (OrgAddVO) request.getSession().getAttribute("orgInfo");//修改时才会存在的机构id
        if (!FormUtils.checkOrgNull(as)) {
            throw new MyArgumentNullException("-100");
        }
        if (orgInfo == null && !FormUtils.checkLoginNull(as)) {
            throw new MyArgumentNullException("-100");
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

        org.setCreatetime(new Date());
        if (orgInfo != null) {//不为空说明在进行修改操作
            org.setCreatetime(od.findByOrganizationid(orgInfo.getOrganizationid()).getCreatetime());
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
            emp.setCreatetime(new Date());
//            if (!FormUtils.isEmpty(emp)) {// 管理员不为空..由于此方法一开始就对管理员账号不存在的情况进行了处理,所以此处管理员对象是肯定存在的.
            // TODO 现在数据库里面 Loginname
            // 是unique类型的,文档上面意思是不同的机构可以有相同的loginname,所以这里要判断一下同机构中的loginname,并去掉数据库里面的unique索引
            EmployeePO save = ed.save(emp);
//            }
            EmployeeRolePO erpo = new EmployeeRolePO();
            erpo.setCreatetime(new Date());
            erpo.setEmployeeid(save.getEmployeeid());
            if (orgtype == Constants.ORGTYPE_AMETA) {
                erpo.setRoleid(1);
            }
            if (orgtype == Constants.ORGTYPE_SUPPLIER) {
                erpo.setRoleid(2);
            }
            if (orgtype == Constants.ORGTYPE_INSTALLER) {
                erpo.setRoleid(3);
            }
            erd.save(erpo);
        }
    }

    /**
     * 通过分页形式返回指定类型的机构列表
     *
     * @param pageable
     * @param orgType
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String, Object> listOrgByType(Pageable pageable, Integer orgType) {
        Map<String, Object> map = new HashMap<>();
        Page<OrganizationPO> orgList = od.findByOrgtype(pageable, orgType);
        map.put("total", od.countByOrgtype(orgType));
//        map.put("total", orgList.getTotalElements());
        List<OrgListVO> list = new ArrayList<>();
        setProperties(orgList, list);
        map.put("rows", list);
        return map;
    }

    //    @RequiresPermissions("admin")
    @Transactional(readOnly = true)
    public Map<String, Object> listInstallerOrg(Pageable pageable, HttpServletRequest request) {
        // 角色为服务商的员工才可以访问
        // 先取员工的机构id,再通过机构id查询其机构下所有的安装商,
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Page<OrganizationPO> orgList = null;
        Map<String, Object> map = new HashMap<>();
        if (isAdmin(emp.getOrganizationid())) {
            orgList = od.findByOrgtype(pageable, Constants.ORGTYPE_INSTALLER);
            map.put("total", od.countByOrgtype(Constants.ORGTYPE_INSTALLER));
        } else {
            orgList = od.findByOrgtypeAndParentorgid(pageable, Constants.ORGTYPE_INSTALLER, emp.getOrganizationid());
            map.put("total", od.countByOrgtypeAndParentorgid(Constants.ORGTYPE_INSTALLER, emp.getOrganizationid()));
        }
//        Map<String, Object> map = new HashMap<>();

//        map.put("total", orgList.getTotalElements());
        List<OrgListVO> list = new ArrayList<>();
        setProperties(orgList, list);
        if (list == null || list.size() == 0) {
            map.put("total", 0);
            map.put("rows", Collections.EMPTY_LIST);
            return map;
        }
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
            } else {
                orgVO.setCity("-");
            }
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
    @Transactional(readOnly = true)
    public List<OrganizationPO> listOrgByType(Integer orgType) {
        return od.findByOrgtype(orgType);
    }

    /**
     * 与 listOrgByTyep 不同的是此方法只返回 organizationid 和 name.
     *
     * @param orgType
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrganizationPO> listOrgSelectByType(Integer orgType) {
        return od.findAllOrgSelect(orgType);
    }

    @Transactional(readOnly = true)
    public List<OrganizationPO> listAllOrg() {
        return od.findAll();
    }

    @Transactional(readOnly = true)
    public List<OrganizationPO> listAllOrgSelect() {
        return od.findAllOrgSelect();
    }

    @Transactional(readOnly = true)
    public boolean validCode(String code) {
        OrganizationPO org = od.findByCode(code);
        if (org == null)
            return true;
        return false;
    }

    @Transactional(readOnly = true)
    public List<Integer> findParentOrgid(Integer id, List<Integer> list) {
        OrganizationPO org = od.findByOrganizationid(id);//
        od.findByParentorgid(org.getOrganizationid());
        if (org.getParentorgid() != null) {
            list.add(org.getParentorgid());
            findParentOrgid(org.getParentorgid(), list);
        }
        return list;
    }

    /**
     * 使用此方法时,返回的list集合里面只有其children机构的id,不包括其本身.<br>
     * 所以在通过此方法返回服务商的安装商集合来显示数据时,一般还要加上服务商本身的id.
     *
     * @param id
     * @param list
     * @return
     */
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public boolean isAdmin(Integer id) {
        OrganizationPO org = od.findByOrganizationid(id);
        if (org != null) {
            return org.getOrgtype() == Constants.ORGTYPE_AMETA;
        }
        return false;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, OrgSearchVO search, HttpServletRequest request, Integer orgtype1) {
//        Integer orgtype = getOrgtypeByReqeust(request);

        String name = "";
        String city1 = "";
        String citycode = "";

        Page<OrganizationPO> orgList = null;
        if (search.getSearchname() != null) {
            name = "%" + search.getSearchname() + "%";
        }
        if (search.getSearchcity() != null) {
            city1 = "%" + search.getSearchcity() + "%";
        }
        if (search.getSearchcitycode() != null) {
            citycode = "%" + search.getSearchcitycode() + "%";
        }

        Map<String, Object> map = new HashMap<>();
        if (search.getSearchcity() == null || search.getSearchcity() == "") {
            map.put("total", od.countByNameLikeAndCitycodeLikeAndOrgtype(name, citycode, orgtype1));
            orgList = od.findByNameLikeAndCitycodeLikeAndOrgtype(pageable, name, citycode, orgtype1);
        } else {
            //先通过citycode和city查找相似的,再通过返回的List集合中的citycode在organization表中查找
            List<CityPO> list = city.findByCitycodeLikeAndCitynameLike(citycode, city1);
            if (list.isEmpty()) {
                map.put("total", 0);
                map.put("rows", Collections.EMPTY_LIST);
                return map;
            }
            //TODO不同的机构取不同的数据
            List<String> list0 = list.stream().map(CityPO::getCitycode).collect(toList());
            if (Constants.ORGTYPE_INSTALLER.equals(orgtype1)) {
                List<Integer> list1 = new ArrayList<>();
                Integer orgid = getOrganizationidByReqeust(request);
                findChildrenOrgid(orgid, list1);
                list1.add(orgid);
                map.put("total", od.countByNameLikeAndCitycodeInAndOrgtypeAndOrganizationidIn(name, list0, orgtype1, list1));
                orgList = od.findByNameLikeAndCitycodeInAndOrgtypeAndOrganizationidIn(name, list0, orgtype1, list1, pageable);
            } else {
                map.put("total", od.countByNameLikeAndCitycodeInAndOrgtype(name, list0, orgtype1));
                orgList = od.findByNameLikeAndCitycodeInAndOrgtype(name, list0, orgtype1, pageable);
            }
        }

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
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        if (!hasPermissionOperateOrg(emp.getEmployeeid(), id)) {
            throw new RuntimeException("-99");
        }
        if (toStatus == null) {
            throw new RuntimeException("-101");
        }
//        if (!hasProvilege(id, request)) {
//            throw new RuntimeException("-99");
//        }
        OrganizationPO org = od.findByOrganizationid(id);
        org.setStatus(toStatus);
        od.save(org);
    }

    /**
     * 通过要操作的员工id和request判断当前登录的员工是否有权限操作此机构
     * @Deprecated 此方法是不安全的,请使用hasPermissionOperateOrg(Integer empid,Integer orgid)
     * @param organizationid
     * @param request
     * @return
     */
    @Deprecated
    @Transactional(readOnly = true)
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

    public void toggleOrganizationStatus0(String hope, Object[] ids, HttpServletRequest request) {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString()), Constants.STATUS_NORMAL, request);
            }

        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString()), Constants.STATUS_SUSPENCED, request);
            }
        }
    }

    /**
     * 通过request中当前登录的用户对象取得其所在机构的类型
     *
     * @param request
     * @return
     */
    @Deprecated
    public Integer getOrgtypeByReqeust(HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        if (emp == null) {
            return null;
        }
        return od.findByOrganizationid(emp.getOrganizationid()).getOrgtype();
    }

    @Deprecated
    public Integer getOrganizationidByReqeust(HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        if (emp == null) {
            return null;
        }
        return emp.getOrganizationid();
    }

    //判断用户是否有权限操作此机构
    @Transactional(readOnly = true)
    public boolean hasPermissionOperateOrg(Integer empid, Integer orgid) {
        List<Integer> emprolelist = erd.findByEmployeeid(empid).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        Integer orgtype = od.findByOrganizationid(orgid).getOrgtype();
        if (emprolelist.contains(Constants.ROLE_AMETA_ADMIN)) {
            return true;
        } else if (orgtype == Constants.ORGTYPE_SUPPLIER && emprolelist.contains(Constants.ROLE_SUPPLIER_ADMIN)) {
            EmployeePO emp = ed.findByEmployeeidAndOrganizationid(empid, orgid);
            return emp != null;
        }else if(orgtype == Constants.ORGTYPE_INSTALLER && emprolelist.contains(Constants.ROLE_SUPPLIER_ADMIN)){
            List<OrganizationPO> org = od.findByParentorgid(orgid);
            return org.contains(orgid);
        }else if(orgtype == Constants.ORGTYPE_INSTALLER && emprolelist.contains(Constants.ROLE_INSTALLER_ADMILN)){
            EmployeePO emp = ed.findByEmployeeidAndOrganizationid(empid, orgid);
            return emp != null;
        }
        return false;
    }

}
