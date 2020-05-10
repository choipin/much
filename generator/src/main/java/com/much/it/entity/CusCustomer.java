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
 * C端用户信息表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("cus_customer")
public class CusCustomer extends Model<CusCustomer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 账号ID
     */
    private Long accountId;
    /**
     * 用户头像
     */
    private String icon;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 是否填写问卷。1-是；0-否
     */
    private Integer questionFilled;
    /**
     * qq号
     */
    private String qq;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 性别。1-男；2-女
     */
    private Integer gender;
    /**
     * 出生日期。
     */
    private Date bornDate;
    /**
     * 民族
     */
    private String nation;
    /**
     * 出生地地址
     */
    private String bornAddress;
    /**
     * 车品牌及型号
     */
    private String carInfo;
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
