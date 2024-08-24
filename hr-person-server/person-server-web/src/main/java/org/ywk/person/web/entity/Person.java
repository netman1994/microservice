package org.ywk.person.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述
 *
 * @author ywk
 * @version 1.0
 * @date 2024/06/22 12:02:12
 */
@Data
@TableName(value = "person_info")
public class Person {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long sbCompanyId;

    private Long socialCompanyId;

    private Long fundCompanyId;

    private String personName;

    private Byte personSex;

    private String personPhone;

    private String personalIdCard;

    private LocalDateTime createTime;

    private LocalDateTime modifyTime;



}
