package org.ywk.company.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("company_info")
public class Company {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String companyName;

    private String creditCode;

    private String legalName;

    private String legalPhone;

    private String legalIdCard;

    private String postCode;

    private String address;

    private String contactName;

    private String contactPhone;

    private String contactIdCard;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
