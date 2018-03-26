package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.PersonDAO;
import cn.com.isurpass.house.po.OrganizationPO;
import cn.com.isurpass.house.po.PersonPO;
import cn.com.isurpass.house.vo.EmployeeAddVO;
import cn.com.isurpass.house.vo.OrgAddVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class PersonService {

    @Autowired
    PersonDAO pd;

    /**
     * 通过personid将其查询到的所有关于person的信息存入employeeaddvo里面
     *
     * @param personid
     * @param emp
     * @return
     */
    public void findPeronInfo(Integer personid, EmployeeAddVO emp) {
        if (personid != null) {
            PersonPO person = pd.findByPersonid(personid);
            if (person != null) {
                emp.setLastname(person.getLastname());
                emp.setFirstname(person.getFirstname());
                emp.setTitle(person.getFirstname());
                emp.setSsn(person.getSsn());
                emp.setGender(person.getGender());
                emp.setPhonenumber(person.getPhonenumber());
                emp.setEmail(person.getEmail());
                emp.setFax(person.getFax());
            }
        }
    }

    public PersonPO findPeronInfo(Integer personid) {
        PersonPO person0 = new PersonPO();
        if (personid != null) {
            PersonPO person = pd.findByPersonid(personid);
            if (person != null) {
                person0.setLastname(person.getLastname());
                person0.setFirstname(person.getFirstname());
                person0.setTitle(person.getFirstname());
                person0.setSsn(person.getSsn());
                person0.setGender(person.getGender());
                person0.setPhonenumber(person.getPhonenumber());
                person0.setEmail(person.getEmail());
                person0.setFax(person.getFax());
                person0.setName(person.getName());
            }
        }
        return person0;
    }

    public void findPersonInfo(OrganizationPO orgPO, OrgAddVO org) {
        if (orgPO.getContactid() != null) {
            PersonPO person = pd.findByPersonid(orgPO.getContactid());
            if (person != null) {
                org.setSname(person.getName());
                org.setSemail(person.getEmail());
                org.setSfax(person.getFax());
                org.setSphonenumber(person.getPhonenumber());
            }
        }
        if (orgPO.getCscontactid() != null) {
            PersonPO person = pd.findByPersonid(orgPO.getCscontactid());
            if (person != null) {
                org.setCspname(person.getName());
                org.setCspemail(person.getEmail());
                org.setCspfax(person.getFax());
                org.setCspphonenumber(person.getPhonenumber());
            }
        }
        System.out.println(org.toString());
    }
}