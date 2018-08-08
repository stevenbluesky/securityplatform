package cn.com.isurpass.house.po;

import javax.persistence.*;

/**
 * @author liwenxiang
 * Date:2018/8/2
 * Time:9:46
 */
@Entity
@Table(name = "gatewayphonecard")
public class GatewayPhonecardPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String deviceid;
    private String serialnumber;

    public GatewayPhonecardPO() {
    }

    public GatewayPhonecardPO(String deviceid, String serialnumber) {
        this.deviceid = deviceid;
        this.serialnumber = serialnumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }
}
