package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/1/4
 * Time:17:09
 */
public class AllUserInfoVO {
    private String code;
    private String name;
    private String parentOrgName;
    private String status;
    private Date createtime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
