package org.ywk.company.entity.vos;

import lombok.Data;
import org.ywk.person.api.facade.entity.PersonVO;

import java.util.List;

@Data
public class CompanyVO {

    private String companyName;

    private String creditCode;

    private String legalPerson;

    private List<PersonVO> persons;

}
