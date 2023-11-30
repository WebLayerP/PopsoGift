package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.dto.TipologiaOggettoDTO;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.entity.TipologiaOggetto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OggettoMapper {

    @Mapping(target="fornitore.listaOggetti", ignore=true)
    OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);
    Oggetto oggettoDTOToOggetto(OggettoDTO oggettoDTO);

    List<Oggetto> listaOggettiDTOToEntity(List<OggettoDTO> oggettiDTO);

    List<OggettoDTO> listaOggettiToDTO(List<Oggetto> oggetti);

    @Named("noOggetti")
    List<OggettoDTO> listaOggettiToDTOC(List<Oggetto> oggetti);

    default TipologiaOggetto toTipologiaOggetto(TipologiaOggettoDTO tipologiaOggettoDTO){
        TipologiaOggetto tipologiaOggetto = new TipologiaOggetto();
        tipologiaOggetto.setIdTipologia(tipologiaOggettoDTO.getIdTipologiaOggetto());
        tipologiaOggetto.setNomeTipologia(tipologiaOggettoDTO);
        return tipologiaOggetto;
    }

    default TipologiaOggettoDTO mapTipologiaOggettoDTOtoTipologia(TipologiaOggetto tipologiaOggetto){
        return tipologiaOggetto.getNomeTipologia();
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
