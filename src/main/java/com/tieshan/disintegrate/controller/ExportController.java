package com.tieshan.disintegrate.controller;

import com.tieshan.disintegrate.pojo.User;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @description:导出excle测试控制类
 * @author: huxuanhua
 * @date: Created in 2019/8/29 9:55
 * @version: 1.0
 * @modified By:
 */
@RestController
public class ExportController {
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export2007(HttpServletRequest request, HttpServletResponse response) {
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(100000000L);
        user1.setUsername("张三");
        user1.setHead("http://qzapp.qlogo.cn/qzapp/101357640/3C94155CAB4E28517D8435BF404B52F1/100");
        user1.setSex(0);
        user1.setPhone("18800000000");
        User user2 = new User();
        user2.setId(100000001L);
        user2.setUsername("李四");
        user2.setHead("http://q.qlogo.cn/qqapp/1105676675/9DA6D356F4FE1DF0E63BD07334680BF2/100");
        user2.setSex(0);
        user2.setPhone("18800000001");
        list.add(user1);
        list.add(user2);

        XSSFWorkbook wb = null;
        try {
            // excel模板路径
            File fi = ResourceUtils.getFile("classpath:excel/user_model.xlsx");
            // 读取excel模板
            wb = new XSSFWorkbook(new FileInputStream(fi));
            // 读取了模板内所有sheet内容
            wb.setSheetName(0,"学生信息");
            XSSFSheet sheet = wb.getSheetAt(0);
            // 在相应的单元格进行赋值
            int rowIndex = 1;
            int j = 1;
            for (User user : list) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (null == row) {
                    row = sheet.createRow(rowIndex);
                }
                XSSFCell cell0 = row.getCell(0);
                if (null == cell0) {
                    cell0 = row.createCell(0);
                }
                cell0.setCellValue(user.getId());// 标识

                XSSFCell cell1 = row.getCell(1);
                if (null == cell1) {
                    cell1 = row.createCell(1);
                }
                cell1.setCellValue(user.getUsername());// 用户名

                XSSFCell cell2 = row.getCell(2);
                if (null == cell2) {
                    cell2 = row.createCell(2);
                }
                cell2.setCellValue(user.getHead());// 头像

                XSSFCell cell3 = row.getCell(3);
                if (null == cell3) {
                    cell3 = row.createCell(3);
                }
                cell3.setCellValue(user.getSex() == 0 ? "女" : "男");// 性别

                XSSFCell cell4 = row.getCell(4);
                if (null == cell4) {
                    cell4 = row.createCell(4);
                }
                cell4.setCellValue(user.getPhone());// 手机
                rowIndex++;
            }

            String fileName = "用户信息";
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            wb.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setContentType("application/octet-stream");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
            ServletOutputStream sout = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(sout);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }

        } catch (Exception e) {
        }

    }

}
