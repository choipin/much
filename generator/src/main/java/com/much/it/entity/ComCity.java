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
 * 市级行政单位信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("com_city")
public class ComCity extends Model<ComCity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 省级行政单位ID。COM_PROVINCE_T.ID。
     */
    private Integer provinceId;
    /**
     * 市编码
     */
    private String code;
    /**
     * 市名称
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
     * 当前城市认证大咖数量（非真实数据，随机产生【待确定随机范围】）
     */
    private Integer yyAuthCount;
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
