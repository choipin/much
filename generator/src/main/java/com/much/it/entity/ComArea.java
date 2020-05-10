package com.much.it.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 区/县行政单位信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("com_area")
public class ComArea extends Model<ComArea> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 市级行政单位ID。COM_CITY_T.ID。
     */
    private Integer cityId;
    /**
     * 区编码
     */
    private String code;
    /**
     * 区名称
     */
    private String name;
    /**
     * 名称全拼。小写存储，如：成都存储为chengdu
     */
    private String namePy;
    /**
     * 是否上线。1-是；0-否
     */
    private Integer onLine;
    /**
     * 热门度。值越大，热门度越高
     */
    private Integer hotExtent;
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
