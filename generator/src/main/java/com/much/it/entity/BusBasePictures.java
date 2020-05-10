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
 * 基本信息图片表
 * </p>
 *
 * @author zhangruting
 * @since 2020-05-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@TableName("bus_base_pictures")
public class BusBasePictures extends Model<BusBasePictures> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号ID
     */
    private Integer accountId;
    /**
     * 角色。1-医生证件；2-医院证件
     */
    private Integer role;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 证件类型。TYPE=1:(1-医生资格证；2-医生执业证；3-医疗美容医生主诊医生资格证；4-荣誉证书；)  TYPE=2:(1-营业执照；2-医疗机构执业许可证；3-医疗广告审查证明；4-荣誉证书；5-环境图)
     */
    private Integer type;
    /**
     * 证件图片链接地址
     */
    private String url;
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
