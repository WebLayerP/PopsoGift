package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO implements Serializable{

    private String nomeTag;
    private Integer idTag;
    private String descrizione;

}
