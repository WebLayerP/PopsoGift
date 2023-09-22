package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OmaggioDTO;
import it.popso.popsogift.entity.Omaggio;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OmaggioMapper {
    public List<OmaggioDTO> getListaOmaggiDTO(List<Omaggio> listaOmaggi){
        List<OmaggioDTO> listaOmaggiDTO = new ArrayList<>();
        for(Omaggio omaggio: listaOmaggi){
            OmaggioDTO omaggioDTO= new OmaggioDTO();
            omaggioDTO.setIdOmaggio(omaggio.getIdOmaggio());
            listaOmaggiDTO.add(omaggioDTO);
        }
        return listaOmaggiDTO;
    }

    public List<Omaggio> getListaOmaggi(List<OmaggioDTO> listaOmaggiDTO){
        List<Omaggio> listaOmaggi = new ArrayList<>();
        for(OmaggioDTO omaggioDTO: listaOmaggiDTO){
            Omaggio omaggio= new Omaggio();
            omaggio.setIdOmaggio(omaggioDTO.getIdOmaggio());
            listaOmaggi.add(omaggio);
        }
        return listaOmaggi;
    }
}
