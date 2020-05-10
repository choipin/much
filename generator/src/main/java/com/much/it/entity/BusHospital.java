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
 * 医院信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_hospital")
public class BusHospital extends Model<BusHospital> {

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
     * 头像地址
     */
    private String icon;
    /**
     * 医院名称
     */
    private String name;
    /**
     * 医院简介
     */
    private String summary;
    /**
     * 医院资质。三甲医院/?
     */
    private String aptitude;
    /**
     * 认证状态。1-已认证；0-未认证
     */
    private Integer authStatus;
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 省份编码
     */
    private String provinceCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 区县名称
     */
    private String areaName;
    /**
     * 区县编码
     */
    private String areaCode;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 运营案例数。可后台配置。实际案例数通过统计获取
     */
    private Integer yyCaseCount;
    /**
     * 运营上次预定到现在的小时数。定时调度每半个小时刷新此字段每行记录的值，随机1-10产生。
     */
    private Integer yyBespeakHours;
    /**
     * 导入批次号
     */
    private String batchId;
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
