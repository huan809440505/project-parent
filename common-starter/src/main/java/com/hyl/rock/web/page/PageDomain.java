package com.hyl.rock.web.page;


import com.hyl.rock.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页数据
 * 
 */
@ApiModel(value = "分页数据")
@Data
public class PageDomain {

    /** 当前记录起始索引 */
    @ApiModelProperty(name = "当前记录起始索引")
    private Integer pageNum;

    /** 每页显示记录数 */
    @ApiModelProperty(name = "每页显示记录数")
    private Integer pageSize;

    /** 排序列 */
    @ApiModelProperty(name = "排序列")
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    @ApiModelProperty(name = "排序的方向desc或者asc")
    private String isAsc = "asc";

    /** 分页参数合理化 */
    @ApiModelProperty(name = "分页参数合理化")
    private Boolean reasonable = true;

    public String getOrderBy() {
        if (StringUtils.isEmpty(orderByColumn)) {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public void setIsAsc(String isAsc) {
        if (StringUtils.isNotEmpty(isAsc)) {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc))
            {
                isAsc = "asc";
            }
            else if ("descending".equals(isAsc))
            {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable() {
        if (StringUtils.isNull(reasonable)) {
            return Boolean.TRUE;
        }
        return reasonable;
    }

}
