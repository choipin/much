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
 * 医生/大咖信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_doctor")
public class BusDoctor extends Model<BusDoctor> {

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
     * 医生/大咖名称
     */
    private String name;
    /**
     * 头像地址
     */
    private String icon;
    /**
     * 从业时长
     */
    private Integer jobAge;
    /**
     * 医生简介
     */
    private String summary;
    /**
     * 头衔。主任医师/副主任医师/主治医师/执业医师/教授
     */
    private String rank;
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
     * 运营预约数。可后台配置。实际预约数通过统计获取
     */
    private Integer yyBespeakCount;
    /**
     * 是否运营推荐。1-是；0-否
     */
    private Integer yyRecom;
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
