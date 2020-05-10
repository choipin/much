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
 * C端用户问卷调查表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@Data
@Accessors(chain = true)
@TableName("cus_questionnaire")
public class CusQuestionnaire extends Model<CusQuestionnaire> {

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
     * 问卷领域。1-期待变美的部位；2-需要的项目；3-投资预算；4-认识医美咖的渠道；
     */
    private Integer domain;
    /**
     * 问卷领域对应的结果ID。【期待变美的部位来自项目类目表ID，其他领域来自LOOKUP_ITEM的ID】
     */
    private Integer domainId;
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
