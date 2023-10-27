package it.popso.popsogift.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="MATRICOLE_LETTURA")
@Data
@AllArgsConstructor
public class MatricoleLettura {

    @Id
    @Column(name="MATRICOLA")
    private String matricola;

    @ManyToOne
    @JoinColumn(name="ID_NOTIFICA")
    private Notifica notifica;

    public MatricoleLettura() {
        // Costruttore vuoto
    }
}
