package cn.com.isurpass.house.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/1/9
 * Time:19:56
 */
public class SimCardSearchVO {
    private String serialnumber;
    private String rateplan ;
    private Integer status;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date starttime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endtime;

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public String getRateplan() {
        return rateplan;
    }

    public void setRateplan(String rateplan) {
        this.rateplan = rateplan;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
