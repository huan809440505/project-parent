package com.hyl.rock.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.AbstractColumnWidthStyleStrategy;
import com.hyl.rock.exception.ServiceException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ExportUtils {

    /**
     * 导出 Excel 文件（简单导出）
     *
     * @param dataList  导出的数据列表
     * @param clazz     数据类型（Excel 实体类）
     * @param fileName  导出的文件名（不带扩展名）
     * @param <T>       数据泛型
     */
    public static <T> void exportExcel(List<T> dataList, Class<T> clazz, String fileName) {
        if (dataList == null || dataList.isEmpty()) {
            log.warn("[ExportUtils] 导出数据为空: {}", fileName);
        }
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            // 使用复用的响应流设置方法
            setupResponse(response, fileName + ".xlsx");
            try (ServletOutputStream out = response.getOutputStream()) {
                // 简单导出，不增加样式或列宽限制
                EasyExcel.write(out, clazz)
                        .autoCloseStream(true)
                        .sheet("数据")
                        .doWrite(dataList);
            }
            log.info("[ExportUtils] 导出成功: {}, 共 {} 条记录", fileName, dataList.size());
        } catch (Exception e) {
            log.error("[ExportUtils] 导出失败: {}", fileName, e);
            throw new ServiceException("导出失败，请稍后重试");
        }
    }

    /**
     * 导出 Excel 文件（带表头样式）
     *
     * @param dataList 数据列表
     * @param clazz    数据类型（Excel 实体类）
     * @param fileName 导出文件名（包含 .xlsx）
     * @param <T>      数据类型
     */
    public static <T> void exportExcelWithStyle(List<T> dataList, Class<T> clazz, String fileName) {
        HttpServletResponse response = ServletUtils.getResponse();
        try {
            // 设置响应头
            setupResponse(response, fileName);
            // 获取输出流并防止 EasyExcel 关闭响应流
            ServletOutputStream out = response.getOutputStream();
            NoCloseOutputStream noCloseOut = new NoCloseOutputStream(out);
            // 创建 ExcelWriter
            ExcelWriter excelWriter = EasyExcel.write(noCloseOut, clazz)
                    // 注册样式策略
                    .registerWriteHandler(new HorizontalCellStyleStrategy(createHeadStyle(), createContentStyle()))
                    // 注册列宽自适应策略
                    .registerWriteHandler(createAutoWidthStrategy())
                    .build();
            // 创建 Sheet
            WriteSheet writeSheet = EasyExcel.writerSheet("导出数据").build();
            // 写入数据
            excelWriter.write(dataList, writeSheet);
            // 完成写入
            excelWriter.finish();
            log.info("[ExportUtils] 导出成功: {}", fileName);
        } catch (Exception e) {
            log.error("[ExportUtils] 导出失败: {}", fileName, e);
            throw new ServiceException("导出 Excel 失败");
        }
    }

    /**
     * 设置 HttpServletResponse 响应头
     */
    private static void setupResponse(HttpServletResponse response, String fileName) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
        response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
    }

    /**
     * 创建表头样式
     */
    private static WriteCellStyle createHeadStyle() {
        WriteCellStyle headStyle = new WriteCellStyle();
        // 设置白底（不填充颜色）
        headStyle.setFillForegroundColor(null);
        headStyle.setFillPatternType(FillPatternType.NO_FILL);
        // 设置字体
        WriteFont headFont = new WriteFont();
        headFont.setFontHeightInPoints((short) 11);
        headFont.setFontName("微软雅黑");
        headFont.setBold(true);
        headFont.setColor(IndexedColors.PALE_BLUE.getIndex());
        headStyle.setWriteFont(headFont);
        // 设置居中与自动换行
        headStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headStyle.setWrapped(true);
        // 设置无边框
        headStyle.setBorderLeft(BorderStyle.NONE);
        headStyle.setBorderRight(BorderStyle.NONE);
        headStyle.setBorderTop(BorderStyle.NONE);
        headStyle.setBorderBottom(BorderStyle.NONE);
        return headStyle;
    }

    /**
     * 创建内容样式
     */
    private static WriteCellStyle createContentStyle() {
        WriteCellStyle contentStyle = new WriteCellStyle();
        // 设置字体
        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 11);
        contentFont.setFontName("等线");
        contentStyle.setWriteFont(contentFont);
        // 内容右对齐，垂直居中，自动换行
        contentStyle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        contentStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        contentStyle.setWrapped(true);
        return contentStyle;
    }

    /**
     * 列宽自适应策略（根据内容长度自动调整列宽，限制最小 15、最大 20 字符）
     */
    private static AbstractColumnWidthStyleStrategy createAutoWidthStrategy() {
        return new AbstractColumnWidthStyleStrategy() {
            private final Map<Integer, Integer> columnWidthMap = new HashMap<>();
            @Override
            protected void setColumnWidth(WriteSheetHolder writeSheetHolder,
                                          List<WriteCellData<?>> cellDataList,
                                          Cell cell,
                                          Head head,
                                          Integer relativeRowIndex,
                                          Boolean isHead) {
                if (cell == null || cell.getCellType() != CellType.STRING) return;
                String value = cell.getStringCellValue();
                if (value == null) return;
                Sheet sheet = writeSheetHolder.getSheet();
                int columnIndex = cell.getColumnIndex();
                // 根据 UTF-8 字节长度计算列宽
                int length = value.getBytes(StandardCharsets.UTF_8).length;
                int minWidth = 15 * 256;
                int maxWidth = 20 * 256;
                int width = Math.max(minWidth, Math.min(length * 256 + 200, maxWidth));
                // 如果当前列宽大于记录的最大宽度，则更新列宽
                Integer maxWidthRecorded = columnWidthMap.getOrDefault(columnIndex, 0);
                if (width > maxWidthRecorded) {
                    columnWidthMap.put(columnIndex, width);
                    sheet.setColumnWidth(columnIndex, width);
                }
                // 设置自动换行样式
                CellStyle style = sheet.getWorkbook().createCellStyle();
                style.cloneStyleFrom(cell.getCellStyle());
                style.setWrapText(true);
                cell.setCellStyle(style);
            }
        };
    }

    /**
     * 包装 Servlet 输出流，不关闭底层流
     */
    public static class NoCloseOutputStream extends FilterOutputStream {
        public NoCloseOutputStream(OutputStream out) {
            super(out);
        }
        @Override
        public void close() throws IOException {
            flush(); // 不关闭底层流，仅刷新
        }
    }
}
