package cn.com.isurpass.house.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author liwenxiang
 * Date:2018/11/12
 * Time:16:34
 */
public class ExportExcelUtil {
    private Log log = LogFactory.getLog(ExportExcelUtil.class);

    public HSSFWorkbook exportNoResponse(String sheetName, String titleName, int columnNumber, int[] columnWidth,
                                 String[] columnName, List<?> dataList) throws Exception {
        if (columnNumber == columnWidth.length&& columnWidth.length == columnName.length) {
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet(sheetName);
            // sheet.setDefaultColumnWidth(15); //统一设置列宽
            for (int i = 0; i < columnNumber; i++) {
                for (int j = 0; j <= i; j++) {
                    if (i == j) {
                        // 单独设置每列的宽
                        sheet.setColumnWidth(i, columnWidth[j] * 256);
                    }
                }
            }
            // 创建第0行 也就是标题
            HSSFRow row1 = sheet.createRow(0);
            // 设备标题的高度
            row1.setHeightInPoints(50);
            // 第三步创建标题的单元格样式style2以及字体样式headerFont1
            HSSFCellStyle style2 = wb.createCellStyle();
            style2.setVerticalAlignment(VerticalAlignment.DISTRIBUTED);
            style2.setAlignment(HorizontalAlignment.CENTER);

            HSSFFont headerFont1 = wb.createFont();
            headerFont1.setBold(true);
            headerFont1.setFontName("微软雅黑");
            headerFont1.setFontHeightInPoints((short) 15);
            style2.setFont(headerFont1);

            // 创建标题第一列、合并
            HSSFCell cell1 = row1.createCell(0);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columnNumber - 1));
            cell1.setCellValue(titleName);
            cell1.setCellStyle(style2);

            // 创建第1行 也就是表头
            HSSFRow row = sheet.createRow(1);
            row.setHeightInPoints(37);

            // 第四步，创建表头单元格样式 以及表头的字体样式
            HSSFCellStyle style = wb.createCellStyle();
            style.setWrapText(true);
            style.setVerticalAlignment(VerticalAlignment.DISTRIBUTED);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setBottomBorderColor(HSSFColor.BLACK.index);

            HSSFFont headerFont = wb.createFont();
            headerFont.setFontName("微软雅黑");
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 10);
            style.setFont(headerFont);

            // 第四.一步，创建表头的列
            HSSFRow row11 = sheet.createRow(1);
            for (int i = 0; i < columnNumber; i++) {
                HSSFCell cell = row11.createCell(i);
                cell.setCellValue(columnName[i]);
                cell.setCellStyle(style);
            }

            // 第五步，创建单元格，并设置值
            // 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
            HSSFCellStyle contentStyle = wb.createCellStyle();
            contentStyle.setWrapText(true);
            contentStyle.setVerticalAlignment(VerticalAlignment.DISTRIBUTED);
            contentStyle.setAlignment(HorizontalAlignment.CENTER);
            // 创建字体样式
            HSSFFont font = wb.createFont();
            font.setFontName("微软雅黑");
            font.setFontHeightInPoints((short) 10);
            contentStyle.setFont(font);

            Iterator<?> it = dataList.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index+1);
                Object t =  it.next();
                // 利用反射，根据javabean属性的*先后顺序*，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(contentStyle);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    // 判断值的类型后进行强制类型转换
                    String textValue = "";
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        textValue = sdf.format(date);
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value == null ? "" : value.toString();
                    }
                    cell.setCellValue(textValue);
                }
            }
            return wb;
        } else {
            log.warn("列数目长度名称三个数组长度要一致");
            return null;
        }

    }
}
