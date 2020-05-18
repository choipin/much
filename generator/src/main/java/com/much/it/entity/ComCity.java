package com.much.it.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@AllArgsConstructor
@NoArgsConstructor
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
    private String onLine;
    /**
     * 热门度。值越大，热门度越高
     */
    private String hotExtent;
    /**
     * 当前城市认证大咖数量（非真实数据，随机产生【待确定随机范围】）
     */
    private String yyAuthCount;
    /**
     * 最后更新时间
     */
    private String lastupdateDate;
    /**
     * 创建时间
     */
    private String createDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
