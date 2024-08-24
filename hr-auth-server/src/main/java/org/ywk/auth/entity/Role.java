package org.ywk.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName(value = "tab_role")
@Data
public class Role implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String nameZh;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
