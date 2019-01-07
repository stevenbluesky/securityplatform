package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.*;
import cn.com.isurpass.house.exception.MyArgumentNullException;
import cn.com.isurpass.house.po.*;
import cn.com.isurpass.house.util.Constants;
import cn.com.isurpass.house.util.Encrypt;
import cn.com.isurpass.house.util.FormUtils;
import cn.com.isurpass.house.vo.*;
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
    @Autowired
    EmployeeService es;

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

    /**
     * 新增服务商与安装商
     * @param as
     * @param orgtype
     * @param request
     * @throws MyArgumentNullException
     */
    @Transactional(rollbackFor = Exception.class)
    public void addByOrgtype(OrgAddVO as, Integer orgtype, HttpServletRequest request) throws MyArgumentNullException {
        EmployeePO emp0 = (EmployeePO) request.getSession().getAttribute("emp");
        //修改时才会存在的机构id
        OrgAddVO orgInfo = (OrgAddVO) request.getSession().getAttribute("orgInfo");
        if (!FormUtils.checkOrgNull(as)) {
            throw new MyArgumentNullException("-100");
        }
        if (orgInfo == null && !FormUtils.checkLoginNull(as)) {
            throw new MyArgumentNullException("-100");
        }
        OrganizationPO org = new OrganizationPO();
        org.setName(as.getName());
        org.setCode(as.getCode());
        org.setGroupid(as.getCode());
        org.setStatus(1);
        org.setCentralstationname(as.getCsname());
        org.setCreatetime(new Date());
        //不为空说明在进行修改操作
        if (orgInfo != null) {
            org.setCreatetime(od.findByOrganizationid(orgInfo.getOrganizationid()).getCreatetime());
//            org.setParentorgid(orgInfo.getParentorgid());
            org.setParentorgid(as.getParentorgid());
            org.setOrgtype(orgInfo.getOrgtype());
/*            if (!validCode(as.getCode(), emp0.getOrganizationid(),orgInfo.getParentorgid())) {
                throw new RuntimeException("-118");
            }*/
        } else {
            // 如果是添加服务商，服务商的父id为ameta
            if (Constants.ORGTYPE_SUPPLIER.equals(orgtype)) {
                org.setParentorgid(od.findByOrgtype(Constants.ORGTYPE_AMETA).get(0).getOrganizationid());
                org.setOrgtype(Constants.ORGTYPE_SUPPLIER);
                if (!validCode(as.getCode(), emp0.getOrganizationid(),od.findByOrgtype(Constants.ORGTYPE_AMETA).get(0).getOrganizationid())) {
                    throw new RuntimeException("-118");
                }
            }
            // 安装商
            if (Constants.ORGTYPE_INSTALLER.equals(orgtype)) {
                // org.setParentorgid(emp0.getOrganizationid());
                org.setOrgtype(Constants.ORGTYPE_INSTALLER);
                // 不为空,ameta在进行添加
                OrganizationPO emporg = od.findByOrganizationid(emp0.getOrganizationid());
                if(Constants.ORGTYPE_SUPPLIER.equals(emporg.getOrgtype())){//录入安装商信息的是服务商,将服务商的id作为安装商的父id
                    if (!validCode(as.getCode(), emp0.getOrganizationid(),emporg.getOrganizationid())) {
                        throw new RuntimeException("-118");
                    }
                    org.setParentorgid(emporg.getOrganizationid());
                }else if(Constants.ORGTYPE_AMETA.equals(emporg.getOrgtype())) {//录入安装商信息的是ameta
                    if (as.getParentorgid() != null) {//选了服务商
                        org.setParentorgid(as.getParentorgid());
                        if (!validCode(as.getCode(), emp0.getOrganizationid(),as.getParentorgid())) {
                            throw new RuntimeException("-118");
                        }
                    } else if (es.isAmetaAdmin(emp0.getEmployeeid())) {//ameta添加安装商时不选则服务商
                        org.setParentorgid(null);
                        if (!validCode(as.getCode(), emp0.getOrganizationid(),null)) {
                            throw new RuntimeException("-118");
                        }
                    }
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
        EmployeePO emp = new EmployeePO(as.getLoginname(), Encrypt.encrypt(as.getLoginname(), as.getPassword(), as.getCode()), as.getQuestion(), Encrypt.encrypt(as.getLoginname(), as.getAnswer(), as.getCode()));

        if (orgInfo != null) {
            address.setAddressid(orgInfo.getOfficeaddressid());
            bAddress.setAddressid(orgInfo.getBillingaddressid());
            csAddress.setAddressid(orgInfo.getCsaddressid());
            sPerson.setPersonid(orgInfo.getContactid());
            csPerson.setPersonid(orgInfo.getCscontactid());
        }

        // 公司地址id
        Integer aId = null;
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
        // 服务商联系人不为空
        if (!FormUtils.isEmpty(sPerson))
            sPId = pd.save(sPerson).getPersonid();
        // 服务商总公司联系人不为空
        if (!FormUtils.isEmpty(csPerson)) {
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
            emp.setType(0);
            emp.setCreatetime(new Date());
//            if (!FormUtils.isEmpty(emp)) {// 管理员不为空..由于此方法一开始就对管理员账号不存在的情况进行了处理,所以此处管理员对象是肯定存在的.
            // TODO 现在数据库里面 Loginname
            // 是unique类型的,文档上面意思是不同的机构可以有相同的loginname,所以这里要判断一下同机构中的loginname,并去掉数据库里面的unique索引
            EmployeePO save = ed.save(emp);
//            }
            EmployeeRolePO erpo = new EmployeeRolePO();
            erpo.setCreatetime(new Date());
            erpo.setEmployeeid(save.getEmployeeid());
            if (orgtype.equals(Constants.ORGTYPE_AMETA)) {
                erpo.setRoleid(1);
            }
            if (orgtype.equals(Constants.ORGTYPE_SUPPLIER)) {
                erpo.setRoleid(2);
            }
            if (orgtype.equals(Constants.ORGTYPE_INSTALLER)) {
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
            OrganizationPO parentorg = od.findByOrganizationid(o.getParentorgid());
            if(parentorg!=null){
                orgVO.setParentname(parentorg.getName());
                orgVO.setParentcode(parentorg.getCode());
            }
            orgVO.setCode(o.getCode());
            orgVO.setStatus(o.getStatus());
            list.add(orgVO);
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
    public List<OrganizationPO> listAllOrgSelect(String organizationid) {
        if("1".equals(organizationid)){
            return od.findAllOrgSelect();
        }
        List<OrganizationPO> byParentorgid = od.findByParentorgid(Integer.parseInt(organizationid));//TODO 机构id为null
        OrganizationPO byOrganizationid = od.findByOrganizationid(Integer.parseInt(organizationid));
        byParentorgid.add(byOrganizationid);
        return byParentorgid;

    }

    /**
     *
     * @param code  传递过来的机构代码
     * @param organizationid    操作员工的机构id
     * @return
     */
    @Transactional(readOnly = true)
    public boolean validCode(String code,Integer organizationid,Integer parentorgid) {
        OrganizationPO loginorg = od.findByOrganizationid(organizationid);
        if(loginorg.getOrgtype()==Constants.ORGTYPE_SUPPLIER){//服务商在操作
            OrganizationPO org = od.findByCodeAndParentorgid(code,loginorg.getOrganizationid());
            //OrganizationPO org = od.findByCode(code);
            if (org == null) {//如果未找到则可以使用
                return true;
            }
        }else if(loginorg.getOrgtype()==Constants.ORGTYPE_AMETA){
            if(parentorgid!=null){
                OrganizationPO org = od.findByCodeAndParentorgid(code,parentorgid);
                if (org == null) {
                    return true;
                }
            }else{
                OrganizationPO org = od.findByCode(code);
                if (org == null) {
                    return true;
                }
            }
        }else{
            OrganizationPO org =  od.findByCodeAndOrganizationid(code,loginorg.getOrganizationid());
            if (org == null) {//如果未找到则可以使用
                return true;
            }
        }
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
        list.add(id);
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
        as.findAddressInfo(orgPO, org);
        ps.findPersonInfo(orgPO, org);
        return org;
    }

    /**
     *
     * @param pageable
     * @param search    搜索条件
     * @param request
     * @param orgtype1  在该机构中搜索
     * @return
     */
    @Transactional(readOnly = true)
    public Map<String, Object> search(Pageable pageable, OrgSearchVO search, HttpServletRequest request, Integer orgtype1) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        String code = "";
        String name = "";
        String city1 = "";
        String parname = "";
        String parcode = "";
        Page<OrganizationPO> orgList = null;
        if (search.getSearchcode() != null) {
            code = "%" + search.getSearchcode() + "%";
        }
        if (search.getSearchname() != null) {
            name = "%" + search.getSearchname() + "%";
        }
        if (search.getSearchcity() != null) {
            city1 = "%" + search.getSearchcity() + "%";
        }
        Map<String, Object> map = new HashMap<>();

        if (search.getSearchcity() == null || search.getSearchcity() == "") {//如果不搜索城市名称
            Integer organizationid = emp.getOrganizationid();
            map.put("total", od.countByCodeContainingAndNameContainingAndOrgtype(code,name,orgtype1));
            orgList = od.findByCodeContainingAndNameContainingAndOrgtype(pageable,code, name, orgtype1);
        } else {
            //先通过citycode和city查找相似的,再通过返回的List集合中的citycode在organization表中查找
            List<CityPO> list = city.findByCitynameLike(city1);
            if (list.isEmpty()) {
                map.put("total", 0);
                map.put("rows", Collections.EMPTY_LIST);
                return map;
            }
            //TODO不同的机构取不同的数据
            List<String> list0 = list.stream().map(CityPO::getCitycode).collect(toList());
            if (Constants.ORGTYPE_INSTALLER.equals(orgtype1)) {//查找安装商
                List<Integer> list1 = new ArrayList<>();
                Integer orgid = getOrganizationidByReqeust(request);
                findChildrenOrgid(orgid, list1);
                list1.add(orgid);
                Integer organizationid = emp.getOrganizationid();
                OrganizationPO byOrganizationid = od.findByOrganizationid(organizationid);
                if(Constants.ORGTYPE_AMETA.equals(byOrganizationid.getOrgtype())){//是ameta在搜索安装商
                    List <OrganizationPO> orglist2= od.findNUllParentorgid();
                    for(OrganizationPO o : orglist2){
                        list1.add(o.getOrganizationid());
                    }
                }
                map.put("total", od.countByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndOrganizationidIn(code,name, list0, orgtype1, list1));
                orgList = od.findByCodeLikeAndNameLikeAndCitycodeInAndOrgtypeAndOrganizationidIn(code,name, list0, orgtype1, list1, pageable);
            } else {//查找服务商
                map.put("total", od.countByCodeLikeAndNameLikeAndCitycodeInAndOrgtype(code,name, list0, orgtype1));
                orgList = od.findByCodeLikeAndNameLikeAndCitycodeInAndOrgtype(code,name, list0, orgtype1, pageable);
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
    public void deleteOrganization(Integer id, HttpServletRequest request){
        OrganizationPO loginorg = (OrganizationPO) request.getSession().getAttribute("loginorg");
        if(Constants.ORGTYPE_AMETA!=loginorg.getOrgtype()){
            return ;
        }
        od.deleteByOrganizationid(id);
    }
    /**
     * 通过要操作的员工id和request判断当前登录的员工是否有权限操作此机构
     *
     * @param organizationid
     * @param request
     * @return
     * @Deprecated 此方法是不安全的, 请使用hasPermissionOperateOrg(Integer empid, Integer orgid)
     */
    @Deprecated
    @Transactional(readOnly = true)
    public boolean hasProvilege(Integer organizationid, HttpServletRequest request) {
        OrganizationPO org = od.findByOrganizationid(organizationid);//要操作的员工的机构
        EmployeePO emp1 = (EmployeePO) request.getSession().getAttribute("emp");
        OrganizationPO org1 = od.findByOrganizationid(emp1.getOrganizationid());
        if (org1.getOrgtype().equals(Constants.ORGTYPE_AMETA)) {//如果登录的员工机构是ameta,直接操作
            return true;
        } else if (org1.getOrgtype().equals(Constants.ORGTYPE_SUPPLIER)) {//员工的机构id必须是他的安装商或者服务商
            if (org1.getOrganizationid().equals(org.getOrganizationid()) || org1.getOrganizationid().equals(org.getParentorgid())) {
                return true;
            } else if (org1.getOrgtype().equals(Constants.ORGTYPE_INSTALLER) && org.getOrganizationid().equals(org1.getOrganizationid())) {
                return true;
            }
        }
        return false;
    }
    @Transactional(rollbackFor = Exception.class)
    public void toggleOrganizationStatus0(String hope, Object[] ids, HttpServletRequest request) {
        if ("unsuspence".equals(hope)) {
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_NORMAL, request);
            }

        } else if ("suspence".equals(hope)) {
            for (Object id : ids) {
                toggleOrganizationStatus(Integer.valueOf(id.toString().replace( ",", "")), Constants.STATUS_SUSPENCED, request);
            }
        }
        else if ("delete".equals(hope)) {
            OrganizationPO loginorg = (OrganizationPO) request.getSession().getAttribute("loginorg");
            if(Constants.ORGTYPE_AMETA!=loginorg.getOrgtype()){
                return ;
            }
            for (Object id : ids) {
                //deleteOrganization(Integer.valueOf(id.toString().replace( ",", "")), request);
//                od.deleteByOrganizationid(Integer.valueOf(id.toString().replace( ",", "")));
                OrganizationPO orgentity = od.findByOrganizationid(Integer.valueOf(id.toString().replace(",", "")));
                od.delete(orgentity);
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
        List< Integer> emprolelist = erd.findByEmployeeid(empid).stream().map(EmployeeRolePO::getRoleid).collect(toList());
        Integer orgtype = od.findByOrganizationid(orgid).getOrgtype();
        EmployeePO loginemp = ed.findByEmployeeid(empid);
        OrganizationPO loginorg = od.findByOrganizationid(loginemp.getOrganizationid());
        if (emprolelist.contains(Constants.ROLE_AMETA_ADMIN)) {
            return true;
        } else if (orgtype.equals(Constants.ORGTYPE_SUPPLIER) && emprolelist.contains(Constants.ROLE_SUPPLIER_ADMIN)) {
            EmployeePO emp = ed.findByEmployeeidAndOrganizationid(empid, orgid);
            return emp != null;
        } else if (orgtype.equals(Constants.ORGTYPE_INSTALLER) && emprolelist.contains(Constants.ROLE_SUPPLIER_ADMIN)) {//被操作的机构是安装商，操作员工是服务商管理员
            /*List<OrganizationPO> org = od.findByParentorgid(orgid);
            return org.contains(orgid);*/
            OrganizationPO sonorg = od.findByOrganizationid(orgid);
            if(sonorg.getParentorgid()==loginorg.getOrganizationid()){
                return true;
            }
        } else if (orgtype.equals(Constants.ORGTYPE_INSTALLER) && emprolelist.contains(Constants.ROLE_INSTALLER_ADMILN)) {
            EmployeePO emp = ed.findByEmployeeidAndOrganizationid(empid, orgid);
            return emp != null;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public OrgAddVO queryInstallerInfo(HttpServletRequest request, Integer installerid) {
        Integer parentorgid = od.findByOrganizationid(installerid).getParentorgid();
        if(parentorgid!=null){
            return queryOrgInfo(request,parentorgid,installerid,  Constants.ORGTYPE_INSTALLER);
        }else {
            return queryOrgInfo(request,installerid, installerid, Constants.ORGTYPE_INSTALLER);
        }
    }

    @Transactional(readOnly = true)
    public OrgAddVO querySupplierInfo(HttpServletRequest request, Integer supplierid) {
        return queryOrgInfo(request,supplierid, supplierid,Constants.ORGTYPE_SUPPLIER);
    }

    private OrgAddVO queryOrgInfo(HttpServletRequest request,Integer orgid, Integer supplierid,Integer orgtype) {
        RequestExpendVO employeeInfo = es.getEmployeeInfo(request);
        /*if (!hasPermissionOperateOrg(employeeInfo.getEmployeeid(), orgid)) {
            throw new RuntimeException("-998");
        }*/
        OrgAddVO orgAddVO = new OrgAddVO();
        OrganizationPO byOrganizationid = od.findByOrganizationid(supplierid);
        if (byOrganizationid == null) {
            return null;
        }
        Integer parentorgid = byOrganizationid.getParentorgid();
        OrganizationPO parentorg = od.findByOrganizationid(parentorgid);
        orgAddVO.setParentorgid(parentorgid);
        if(parentorg!=null){
            orgAddVO.setParentorgcode(parentorg.getCode());
            orgAddVO.setParentorgname(parentorg.getName());
        }
        orgAddVO.setName(byOrganizationid.getName());
        orgAddVO.setCode(byOrganizationid.getCode());
        orgAddVO.setCsname(byOrganizationid.getCentralstationname());
        as.findAddressInfo(byOrganizationid, orgAddVO);
        ps.findPersonInfo(byOrganizationid,orgAddVO);
        EmployeePO admin = null;
        /*if (Constants.ORGTYPE_SUPPLIER.equals(orgtype)) {机构很有可以不止一个管理员，这样拿并展示没有必要
            admin = es.findSuppllierAdmin(supplierid);
            if (admin != null) {
                orgAddVO.setLoginname(admin.getLoginname());
            }
        } else if (Constants.ORGTYPE_INSTALLER.equals(orgtype)) {
            admin = es.findInstallerInfo(supplierid);
            if (admin != null) {
                orgAddVO.setLoginname(admin.getLoginname());
            }
        }*/
        return orgAddVO;
    }

    /**
     * 判断当前登录用户的所属机构是否被冻结，如果服务商被冻结，安装商也不能登录
     * @param organizationid 机构id
     */
    public void findOrgStatus(String organizationid) {
        //TODO 根据机构id判断父机构、本机构是否被冻结
        OrganizationPO byOrganizationid1 = od.findByOrganizationid(Integer.parseInt(organizationid));
        if(byOrganizationid1==null){
            throw new RuntimeException("-98");//该机构不存在
        }
        if(byOrganizationid1.getStatus()!=Constants.STATUS_NORMAL){
            throw new RuntimeException("-98");
        }
        Integer parentorgid = byOrganizationid1.getParentorgid();
        if(parentorgid!=null){
            OrganizationPO byOrganizationid = od.findByOrganizationid(parentorgid);
            if(byOrganizationid!=null&&byOrganizationid.getStatus()!=Constants.STATUS_NORMAL){
                throw new RuntimeException("-98");
            }
        }
    }

    public Boolean validName(String name) {
        OrganizationPO byName = od.findByName(name);
        if(byName==null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据机构id和机构名称判断该名称是否可用
     * @param organizationid
     * @param name
     */
    @Transactional
    public boolean findName(Integer organizationid, String name) {
        OrganizationPO byOrganizationid = od.findByOrganizationid(organizationid);
        if(byOrganizationid!=null&&byOrganizationid.getName().equals(name)){
            //机构名称未修改
            return true;
        }else{
            return false;
        }
    }

    public void testDB() {
        od.count();
    }


    @Transactional(readOnly = true)
    public OrganizationPO findOrgByLoginEmp(HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        OrganizationPO org =  od.findByOrganizationid(emp.getOrganizationid());
        return org;
    }

    /**
     * 搜索安装商
     * @param pageable
     * @param osv
     * @param request
     * @return
     */
    @Transactional
    public Map<String,Object> searchInstallerOrg(Pageable pageable, OrgSearchVO osv, HttpServletRequest request) {
        EmployeePO emp = (EmployeePO) request.getSession().getAttribute("emp");
        Integer organizationid = emp.getOrganizationid();
        OrganizationPO loginorg = od.findByOrganizationid(organizationid);
        String code = "%"+osv.getSearchcode()+"%";
        String name = "%"+osv.getSearchname()+"%";
        String city = "%"+osv.getSearchcity()+"%";
        String parname = "%"+osv.getSearchparentname()+"%";
        String parcode = "%"+osv.getSearchparentcode()+"%";
        long total = 0;
        Map<String, Object> map = new HashMap<>();
        List<Object[]> olist = new ArrayList<>();
        List<OrgListVO> list = new ArrayList<>();
        if(Constants.ORGTYPE_AMETA.equals(loginorg.getOrgtype())){
            olist = od.findInstallerOrg(pageable,code,name,city,parname,parcode);
            total = od.countInstallerOrg(code,name,city,parname,parcode);
        }else{
            List<Integer> list1 = new ArrayList<>();
            List<Integer> childrenOrgid = findChildrenOrgid(organizationid, list1);
            childrenOrgid.add(organizationid);
            olist = od.findOwnInstallerOrg(pageable,name,code,city,childrenOrgid,parname,parcode);
            total = od.countOwnInstallerOrg(name,code,city,childrenOrgid,parname,parcode);
        }
        for(Object[] o:olist){
            OrgListVO orgVO = new OrgListVO();
            orgVO.setOrganizationid((Integer)o[0]);
            orgVO.setCode((String)o[1]);
            orgVO.setName((String)o[2]);
            orgVO.setCity((String)o[3]);
            orgVO.setStatus((Integer) o[4]);
            orgVO.setParentname((String)o[5]);
            orgVO.setParentcode((String)o[6]);
            list.add(orgVO);
        }
        map.put("rows", list);
        map.put("total",total);
        return map;
    }
    @Transactional(readOnly = true)
    public OrganizationPO findCodeByLoginname(String loginname) {
        EmployeePO tryemp = ed.findByLoginname(loginname);
        if(tryemp!=null){
            OrganizationPO byOrganizationid = od.findByOrganizationid(tryemp.getOrganizationid());
            if(byOrganizationid!=null){
                return byOrganizationid;
            }
        }
        return null;
    }

    public Boolean validLoginName(String loginname) {
        EmployeePO byLoginname = ed.findByLoginname(loginname);
        if(byLoginname==null){
            return true;
        }else {
            return false;
        }
    }
    @Transactional(readOnly = true)
    public List<OrganizationPO> listAllSupOrg() {
        List<OrganizationPO> byOrgtype = od.findByOrgtype(Constants.ORGTYPE_SUPPLIER);
        return byOrgtype;
    }
    @Transactional(readOnly = true)
    public List<OrganizationPO> listAllInsOrg() {
        return od.findByOrgtype(Constants.ORGTYPE_INSTALLER);
    }
    @Transactional(readOnly = true)
    public List<OrganizationPO> listMyInsOrg(EmployeePO emp) {
        return od.findByParentorgidAndOrgtype(emp.getOrganizationid(),Constants.ORGTYPE_INSTALLER);
    }
}
