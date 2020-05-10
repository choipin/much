package com.much.it.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户管家会话明细表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_dialog")
public class BusDialog extends Model<BusDialog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 服务ID。BUS_CUSTOMER_STEWARD_T.ID
     */
    private Integer csId;
    /**
     * 内容类型。1-文本；2-图片；3-视频；4-链接；4-问诊单；5-订单；6-订单确认；7-推荐；8-推荐确认；9-系统问候；10-资料完善；11-最近浏览；12-服务评分；13-用户评分
     */
    private Integer type;
    /**
     * 聊天内容
     */
    private String content;
    /**
     * 用户是否已读。1-已读；0-未读
     */
    private Integer customerReaded;
    /**
     * 管家是否已读。1-已读；0-未读
     */
    private Integer stewardReaded;
    /**
     * 最后更新时间
     */
    private Date lastupdateDate;
    /**
     * 创建时间
     */
    private Date createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
