package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FilialeDTO;
import it.popso.popsogift.entity.Filiale;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilialeMapper {

    public List<FilialeDTO> getListaFilialiDTO(List<Filiale> listaFiliali){
        List<FilialeDTO> listaFilialiDTO = new ArrayList<>();
        for(Filiale filiale: listaFiliali){
            FilialeDTO filialeDTO= new FilialeDTO();
            filialeDTO.setCodiceFiliale(filiale.getCodiceFiliale());
            listaFilialiDTO.add(filialeDTO);
        }
        return listaFilialiDTO;
    }

    public List<Filiale> getListaFiliali(List<FilialeDTO> listaFilialiDTO){
        List<Filiale> listaFiliali = new ArrayList<>();
        for(FilialeDTO filialeDTO: listaFilialiDTO){
            Filiale filiale= new Filiale();
            filiale.setCodiceFiliale(filialeDTO.getCodiceFiliale());
            listaFiliali.add(filiale);
        }
        return listaFiliali;
    }
}
