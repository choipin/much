package com.much.it.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 模板表（邮件/短信/富文本/文案等）
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class Sysemplate extends Model<Sysemplate> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 模板编码
     */
    private String templateKey;
    /**
     * 模板内容
     */
    private String templateContent;
    /**
     * 模板类型。1-短信；2-文案；3-富文本；4-邮件
     */
    private Integer templateType;
    /**
     * 模板描述
     */
    private String templateDesc;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 最后更新日期
     */
    private Date lastupdateDate;
    /**
     * 最后更新人
     */
    private Long lastupdateBy;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
