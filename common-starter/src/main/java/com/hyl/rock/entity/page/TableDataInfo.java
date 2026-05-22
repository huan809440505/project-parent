package com.hyl.rock.entity.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 */
@Data
@Schema(name = "表格分页数据对象")
public class TableDataInfo implements Serializable {

    /** 总记录数 */
    @Schema(name = "总记录数")
    private long total;

    /** 列表数据 */
    @Schema(name = "列表数据")
    private List<?> rows;

    /** 消息状态码 */
    @Schema(name = "消息状态码")
    private int code;

    /** 消息内容 */
    @Schema(name = "消息内容")
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo() {
    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> list, long total) {
        this.rows = list;
        this.total = total;
    }


}