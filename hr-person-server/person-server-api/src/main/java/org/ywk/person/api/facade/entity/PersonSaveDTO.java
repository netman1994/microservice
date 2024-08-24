package org.ywk.person.api.facade.entity;

import lombok.Data;

@Data
public class PersonSaveDTO {


    private Long companyId;

    private String name;

    private Byte sex;

    private String phone;

    private String idCard;



}
