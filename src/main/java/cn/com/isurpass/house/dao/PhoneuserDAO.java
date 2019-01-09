package cn.com.isurpass.house.dao;

import cn.com.isurpass.house.po.PhoneuserPO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/9/7
 * Time:16:55
 */
@Repository
public class PhoneuserDAO  {

    @PersistenceContext(unitName = "iremote")
    protected EntityManager em;

    public int countTotal(){
        String sqlString = "select count(*) from phoneuser where platform=9 and usertype=4 and status=0";
        Query query = em.createNativeQuery(sqlString);
        List re = query.getResultList();
        Object o = re.get(0);
        String s = o.toString();
        int count = Integer.parseInt(s);
        return count;
    }

    public List<PhoneuserPO> originalUser(Pageable p){
        int pageNumber = p.getPageNumber();
        int pageSize = p.getPageSize();
        String sqlstring = "select * from phoneuser where platform=9 and usertype=4 and status=0  order by phoneuserid desc limit  "+(pageNumber)*pageSize+","+pageSize;
        Query nativeQuery = em.createNativeQuery(sqlstring, PhoneuserPO.class);
        List<PhoneuserPO> resultList = nativeQuery.getResultList();
        return resultList;
    }

    public List<PhoneuserPO> originalUser(){
        String sqlstring = "select * from phoneuser where platform=9 and usertype=4 and status=0  order by phoneuserid desc ";
        Query nativeQuery = em.createNativeQuery(sqlstring, PhoneuserPO.class);
        List<PhoneuserPO> resultList = nativeQuery.getResultList();
        return resultList;
    }

    public List<PhoneuserPO> searchUser(String searchName, String searchPhonenumber, String starttime, String endtime, Pageable p,String phonenumberlist) {
        int pageNumber = p.getPageNumber();
        int pageSize = p.getPageSize();
        String sqlstring = "select * from phoneuser where platform=9 and usertype=4 and status=0 and createtime between '"+starttime +"' and '"+endtime+"' ";
        if(!StringUtils.isEmpty(phonenumberlist)){
            sqlstring += " and phonenumber in "+phonenumberlist;
        }
        if(!StringUtils.isEmpty(searchName)){
            sqlstring += " and name like '%"+searchName+"%'";
        }
        if(!StringUtils.isEmpty(searchPhonenumber)){
            sqlstring += " and phonenumber like '%"+searchPhonenumber+"%'";
        }
        sqlstring += " order by phoneuserid desc limit "+(pageNumber)*pageSize+","+pageSize ;
        Query nativeQuery = em.createNativeQuery(sqlstring, PhoneuserPO.class);
        List<PhoneuserPO> resultList = nativeQuery.getResultList();
        return resultList;
    }


    public List<PhoneuserPO> searchUser(String searchName, String searchPhonenumber, String starttime, String endtime,String phonenumberlist) {
        String sqlstring = "select * from phoneuser where platform=9 and usertype=4 and status=0 and createtime between '"+starttime +"' and '"+endtime+"' ";
        if(!StringUtils.isEmpty(phonenumberlist)){
            sqlstring += " and phonenumber in "+phonenumberlist;
        }
        if(!StringUtils.isEmpty(searchName)){
            sqlstring += " and name like '%"+searchName+"%'";
        }
        if(!StringUtils.isEmpty(searchPhonenumber)){
            sqlstring += " and phonenumber like '%"+searchPhonenumber+"%'";
        }
        sqlstring += " order by phoneuserid desc ";
        Query nativeQuery = em.createNativeQuery(sqlstring, PhoneuserPO.class);
        List<PhoneuserPO> resultList = nativeQuery.getResultList();
        return resultList;
    }

    public int countsearchUser(String searchName, String searchPhonenumber, String starttime, String endtime,String phonenumberlist) {
        String sqlstring = "select count(*) from phoneuser where platform=9 and usertype=4 and status=0 and createtime between '"+starttime +"' and '"+endtime+"'";
        if(!StringUtils.isEmpty(phonenumberlist)){
            sqlstring += " and phonenumber in "+phonenumberlist;
        }
        if(!StringUtils.isEmpty(searchName)){
            sqlstring += " and name like '%"+searchName+"%'";
        }
        if(!StringUtils.isEmpty(searchPhonenumber)){
            sqlstring += " and phonenumber like '%"+searchPhonenumber+"%'";
        }
        Query nativeQuery = em.createNativeQuery(sqlstring);
        List<PhoneuserPO> resultList = nativeQuery.getResultList();
        Object o = resultList.get(0);
        String s = o.toString();
        int count = Integer.parseInt(s);
        return count;
    }

}
