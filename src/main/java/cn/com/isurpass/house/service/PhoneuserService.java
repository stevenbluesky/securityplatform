package cn.com.isurpass.house.service;

import cn.com.isurpass.house.dao.GatewayDAO;
import cn.com.isurpass.house.dao.PhoneuserDAO;
import cn.com.isurpass.house.po.GatewayPO;
import cn.com.isurpass.house.po.PhoneuserPO;
import cn.com.isurpass.house.vo.RegistUserListVO;
import cn.com.isurpass.house.vo.RegistUserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author liwenxiang
 * Date:2018/9/7
 * Time:17:00
 */
@Service
public class PhoneuserService {
    @Autowired
    private PhoneuserDAO phoneuserDAO;
    @Autowired
    private GatewayDAO gd;
    public Map<String,Object> search(Pageable p, RegistUserSearchVO rus){
        Map<String, Object> map = new HashMap<>();
        String start = rus.getSearchStarttime();
        String end = rus.getSearchEndtime();
        if(StringUtils.isEmpty(start)){
            start = "2012-01-01";
        }
        if(StringUtils.isEmpty(end)) {
            end = "2099-12-30";
        }
        List<RegistUserListVO> transfer = null;
        int count = 0;
        if(!StringUtils.isEmpty(rus.getSearchGatewayid())){
            List<GatewayPO> gatewaylist = gd.findByDeviceidContainingAndAppaccountIsNotNull(rus.getSearchGatewayid());
            List<String> phonenumberlist = new ArrayList<>();
            String phonenumberstringlist = "";
            StringBuffer sb = new StringBuffer();
            for(GatewayPO g:gatewaylist){
                if(!StringUtils.isEmpty(g.getAppaccount()))
                    phonenumberlist.add(g.getAppaccount());
            }
            if(phonenumberlist.size()>0) {
                sb.append("(");
                for(String s:phonenumberlist){
                    sb.append("'"+s+"',");
                }
                phonenumberstringlist = sb.toString();
                phonenumberstringlist = phonenumberstringlist.substring(0,phonenumberstringlist.lastIndexOf(",")) + ")";
                List<PhoneuserPO> templist = phoneuserDAO.searchUser(rus.getSearchName(), rus.getSearchPhonenumber(), start, end, p, phonenumberstringlist);
                count = phoneuserDAO.countsearchUser(rus.getSearchName(), rus.getSearchPhonenumber(), start, end, phonenumberstringlist);
                transfer = transfer(templist);
            }
        }else {
            List<PhoneuserPO> templist = phoneuserDAO.searchUser(rus.getSearchName(), rus.getSearchPhonenumber(), start, end, p,"");
            count = phoneuserDAO.countsearchUser(rus.getSearchName(), rus.getSearchPhonenumber(), start, end,"");
            transfer = transfer(templist);
        }
        map.put("rows",transfer);
        map.put("total",count);
        return map;
    }

    public Map<String,Object> listRegistUser(Pageable p){
        Map<String, Object> map = new HashMap<>();
        int count = phoneuserDAO.countTotal();
        List<PhoneuserPO> templist = phoneuserDAO.originalUser(p);
        List<RegistUserListVO> transfer = transfer(templist);

        map.put("rows",transfer);
        map.put("total",count);
        return map;
    }

    public List<RegistUserListVO> transfer(List<PhoneuserPO> templist){
        List<RegistUserListVO> registUserListVOS = new ArrayList<>();
        for(int i =0;i<templist.size();i++){
            RegistUserListVO registUserListVO = new RegistUserListVO();
            PhoneuserPO phoneuserPO = templist.get(i);
            registUserListVO.setPhoneuserid(phoneuserPO.getPhoneuserid());
            registUserListVO.setPhonenumber(phoneuserPO.getPhonenumber());
            registUserListVO.setName(phoneuserPO.getName());
            registUserListVO.setCreatetime(phoneuserPO.getCreatetime());
            registUserListVOS.add(registUserListVO);
        }
        for(RegistUserListVO r:registUserListVOS){
            if(StringUtils.isEmpty(r.getPhonenumber())){
                continue;
            }
            List<GatewayPO> byAppaccount = gd.findByAppaccount(r.getPhonenumber());
            StringBuffer sb = new StringBuffer();
            if(byAppaccount!=null&&byAppaccount.size()>0){
                for(int i=0;i<byAppaccount.size();i++){
                    if(i!=byAppaccount.size()-1){
                        sb.append(byAppaccount.get(i).getDeviceid()+", ");
                    }else{
                        sb.append(byAppaccount.get(i).getDeviceid());
                    }
                }
            }
            r.setDeviceid(sb.toString());
        }
        return registUserListVOS;
    }
}
