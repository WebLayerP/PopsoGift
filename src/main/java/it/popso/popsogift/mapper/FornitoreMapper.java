package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.FornitoreDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Fornitore;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.entity.TipologiaOggetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornitoreMapper {

    Fornitore fornitoreDTOToFornitore(FornitoreDTO fornitoreDTO);

    FornitoreDTO fornitoreToDTO(Fornitore fornitore);

    List<FornitoreDTO> toListFornitoreDTO(List<Fornitore> fornitore);

    @Mapping(target="fornitore", ignore = true)
    OggettoDTO mapToOggettoDTO(Oggetto oggetto);

    default TipologiaOggetto mapTipologiaOggettotoTipologia(Integer idTipologiaOggetto){
        TipologiaOggetto tipologiaOggetto = new TipologiaOggetto();
        tipologiaOggetto.setIdTipologia(idTipologiaOggetto);
        return tipologiaOggetto;
    }
    default Integer mapTipologiaOggettoDTOtoInteger(TipologiaOggetto tipologiaOggetto) {
        return tipologiaOggetto.getIdTipologia();
    }

    default Categoria toCategoria(String categoriaString){
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaString.toUpperCase());
        return categoria;
    }
    default String mapCategoriaDTOtoCategoria(Categoria categoria){
        return categoria.getNomeCategoria();
    }
}
