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
 * 案例表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_case")
public class BusCase extends Model<BusCase> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 医生/医院账号ID
     */
    private Long accountId;
    /**
     * 案例角色。1-医生案例；2-医院案例
     */
    private Integer role;
    /**
     * 案例标题
     */
    private String title;
    /**
     * 是否常规医美案例。1-是；0-否
     */
    private Integer medicalCase;
    /**
     * 是否医美修复案例。1-是；0-否
     */
    private Integer repairCase;
    /**
     * 浏览量
     */
    private Integer reviewTotal;
    /**
     * 收藏量
     */
    private Integer collectTotal;
    /**
     * 过去的照片（正面）地址
     */
    private String picFront;
    /**
     * 过去的照片（左面）地址
     */
    private String picLeft;
    /**
     * 过去的照片（右面）地址
     */
    private String picRight;
    /**
     * 案例开始时间
     */
    private Date caseBeginTime;
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
