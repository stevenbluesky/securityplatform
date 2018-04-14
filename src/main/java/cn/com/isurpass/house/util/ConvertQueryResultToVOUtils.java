package cn.com.isurpass.house.util;

import cn.com.isurpass.house.vo.ZwaveDeviceListVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 将通过 @Query原生语句查询出来的对象转换成为VO对象
 */
public class ConvertQueryResultToVOUtils {
    public static List<ZwaveDeviceListVO> convertZwaveDevice(List<Object[]> objectlist){
        List<ZwaveDeviceListVO> list = new ArrayList<>();
        for (Object[] obj : objectlist) {
            //此下元素index和sql语句的返回值顺序相关联
            ZwaveDeviceListVO z = new ZwaveDeviceListVO();
            z.setZwavedeviceid((Integer) obj[0]);
            z.setName((String) obj[1]);
            z.setDevicetype((String) obj[2]);
            z.setWarningstatuses((String) obj[3]);
            z.setStatus((Integer) obj[4]);
            z.setBattery((Integer) obj[5]);
            z.setCity((String) obj[6]);
            z.setOrganizationname((String) obj[7]);
            z.setInstallerorgname((String) obj[8]);
            z.setInstallername((String) obj[9]);
            z.setUsername((String) obj[10]);
            list.add(z);
        }
        return list;
    }
}
