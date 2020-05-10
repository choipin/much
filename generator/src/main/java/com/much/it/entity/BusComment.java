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
 * 评价信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_comment")
public class BusComment extends Model<BusComment> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号ID
     */
    private Long accountId;
    /**
     * 最后更新时间
     */
    private Date lastupdateDate;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 评价类型。1-用户评价医生/医院
     */
    private Integer type;
    /**
     * 评价人账号ID
     */
    private Long sourceId;
    /**
     * 被评价方账号ID
     */
    private Long targetId;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 评分1-5
     */
    private Integer score;
    /**
     * 评价标签。多个逗号分隔
     */
    private String tag;
    /**
     * 媒体类型。1-图片；2-视频
     */
    private Integer mediaType;
    /**
     * 媒体文件地址
     */
    private String mediaUrl;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
