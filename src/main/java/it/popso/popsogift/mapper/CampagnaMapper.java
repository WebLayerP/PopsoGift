package it.popso.popsogift.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.popso.popsogift.dto.CampagnaDTO;
import it.popso.popsogift.entity.Campagna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CampagnaMapper {
    private final OmaggioMapper omaggioMapper;
    private final FilialeMapper filialeMapper;

    private final StatoMapper statoMapper;

    private final TipologiaMapper tipologiaMapper;

    @Autowired
    public CampagnaMapper(OmaggioMapper omaggioMapper, FilialeMapper filialeMapper,
                          StatoMapper statoMapper, TipologiaMapper tipologiaMapper) {
        this.omaggioMapper = omaggioMapper;
        this.filialeMapper = filialeMapper;
        this.statoMapper = statoMapper;
        this.tipologiaMapper= tipologiaMapper;
    }

    public CampagnaDTO getCampagnaDTO(Campagna campagna) {
        CampagnaDTO campagnaDTO = new CampagnaDTO();
        campagnaDTO.setIdCampagna(campagna.getIdCampagna());
        campagnaDTO.setTitoloCampagna(campagna.getTitoloCampagna());
        campagnaDTO.setTipologia(tipologiaMapper.getTipologiaDTO(campagna.getTipologia()));
        campagnaDTO.setDataInizioModifiche(campagna.getDataInizioModifiche());
        campagnaDTO.setDataFineModifiche(campagna.getDataFineModifiche());
        campagnaDTO.setStato(statoMapper.getStatoDTO(campagna.getStato()));
        campagnaDTO.setListaOmaggi(omaggioMapper.getListaOmaggiDTO(campagna.getListaOmaggi()));
        campagnaDTO.setListaFiliali(filialeMapper.getListaFilialiDTO(campagna.getListaFiliali()));
        return campagnaDTO;
    }
    public Campagna campagnaDTOToEntity(CampagnaDTO campagnaDTO) throws JsonProcessingException {
        Campagna campagna = new Campagna();
        campagna.setIdCampagna(campagnaDTO.getIdCampagna());
        campagna.setTitoloCampagna(campagnaDTO.getTitoloCampagna());
        campagna.setTipologia(tipologiaMapper.getTipologia(campagnaDTO));
        campagna.setDataInizioModifiche(campagnaDTO.getDataInizioModifiche());
        campagna.setDataFineModifiche(campagnaDTO.getDataFineModifiche());
        campagna.setStato(statoMapper.getStato(campagnaDTO));
        campagna.setListaOmaggi(omaggioMapper.getListaOmaggi(campagnaDTO.getListaOmaggi()));
        campagna.setListaFiliali(filialeMapper.getListaFiliali(campagnaDTO.getListaFiliali()));
        return campagna;
    }
}

