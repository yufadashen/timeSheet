package com.intehel.common.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author：席林达
 * @description：导出Excel
 * @createDate：2019/12/11 21:16
 * @version：V1
 */
public class ExportExcel {

    /**
     * 导出Excel
     * titleMap：表头
     * rowDataList：内容
     * fileName：Excel名字
     * */
    public static void exportExcel(Map<String,String> titleMap, List<Map<String, Object>> rowDataList, String fileName, String sheetTitle, HttpServletRequest req, HttpServletResponse response){
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        if(null != titleMap) {
            //设置表头信息
            for(Map.Entry<String,String> entry : titleMap.entrySet()) {
                entity.add(new ExcelExportEntity(entry.getValue(), entry.getKey()));
            }
        }
        ExportParams exportParams = new ExportParams(sheetTitle,"1");
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entity, rowDataList);
        // 重置响应对象
        response.reset();
        // 指定下载的文件名--设置响应头

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode(fileName+".xls", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 写出数据输出流到页面
        try {
            OutputStream output = response.getOutputStream();
            BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
            workbook.write(bufferedOutPut);
            bufferedOutPut.flush();
            bufferedOutPut.close();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
