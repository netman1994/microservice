package org.ywk.company.entity.dtos;

import lombok.Data;
import org.ywk.person.api.facade.entity.PersonSaveDTO;

import java.util.List;

@Data
public class CompanySaveDTO {

    private String companyName;

    private String creditCode;

    private String address;

    private String legalPerson;

    private String contactPerson;


    private List<PersonSaveDTO> persons;

}
