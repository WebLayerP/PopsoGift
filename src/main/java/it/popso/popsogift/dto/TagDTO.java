package it.popso.popsogift.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagDTO implements Serializable{

    private String nomeTag;
    private int idTag;
    private String descrizione;

    public TagDTO() {
    }

    public TagDTO(String nomeTag, int idTag, String descrizione) {
        this.nomeTag = nomeTag;
        this.idTag = idTag;
        this.descrizione = descrizione;
    }
}
