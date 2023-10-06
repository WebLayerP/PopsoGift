package it.popso.popsogift.entity;

import it.popso.popsogift.dto.TipologiaSegnalazioneDTO;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="TIPOLOGIA_SEGNALAZIONE")
@Data
public class TipologiaSegnalazione {
    @Id
    @Column(name="ID_TIPOLOGIA")
    private Integer idTipologia;
    @Enumerated(EnumType.STRING)
    @Column(name="DESCRIZIONE")
    private TipologiaSegnalazioneDTO nomeTipologia;
}
