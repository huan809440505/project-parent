package com.hyl.rock.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEmptyEntity implements Serializable {

    /** 搜索值 */
    @JsonIgnore
    @Schema(name = "搜索值")
    @TableField(exist = false)
    private String searchValue;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @Schema(name = "创建人")
    @TableField("create_by")
    private String createBy;

    /**
     * 修改时间
     */
    @Schema(name = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @Schema(name = "修改人")
    @TableField("update_by")
    private String updateBy;

    /**
     * 备注
     */
    @Schema(name = "备注")
    @TableField("remark")
    private String remark;

    /** 请求参数 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Schema(name = "请求参数")
    @TableField(exist = false)
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }
}
