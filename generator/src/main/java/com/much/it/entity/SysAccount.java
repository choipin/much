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
 * 账号表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Data
@Accessors(chain = true)
@TableName("sys_account")
public class SysAccount extends Model<SysAccount> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 最后一次登录时间
     */
    private Date loginTime;
    /**
     * 最后一次登录IP地址
     */
    private String loginIp;
    /**
     * 是否测试账号。1-是；0-否
     */
    private Integer tester;
    /**
     * 创建时间/注册时间
     */
    private Date createDate;
    /**
     * 最后更新时间
     */
    private Date lastupdateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
