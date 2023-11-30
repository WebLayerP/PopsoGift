package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.*;
import it.popso.popsogift.entity.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BeneficiarioMapper {

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    @Mapping(target="listaOggetti",source= "listaOggetti")
    BeneficiarioDTO beneficiarioToBeneficiarioDTO(Beneficiario beneficiario);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    Beneficiario beneficiarioDTOToBeneficiario(BeneficiarioDTO beneficiarioDTO);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    BeneficiarioDTO beneficiarioDettaglioDTOToBeneficiarioDTO (BeneficiarioDettaglioDTO beneficiarioDettaglioDTO);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    Beneficiario beneficiarioDettaglioDTOToBeneficiario(BeneficiarioDettaglioDTO beneficiarioDettaglioDTO);

    @Mapping(target = "fornitore", ignore = true)
    void updateO2FromO1List(List<FornitoreDTO> oggetto1List, @MappingTarget List<Fornitore> oggetto2List);

    default Categoria toCategoria(String categoriaString){
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(categoriaString.toUpperCase());
        return categoria;
    }
    default String mapCategoriaDTOtoCategoria(Categoria categoria) {
        return categoria.getNomeCategoria();
    }

    default TipologiaOggettoDTO mapTipologiaOggettoDTOtoTipologia(TipologiaOggetto tipologiaOggetto){
        return null!=tipologiaOggetto ? tipologiaOggetto.getNomeTipologia(): null;
    }

    BeneficiarioDettaglioDTO beneficiarioDTOToBeneficiarioDettaglioDTO(BeneficiarioDTO beneficiarioDTO);
    default BeneficiarioDettaglioDTO beneficiarioDettaglioCompleto(BeneficiarioDettaglioDTO beneficiarioDettaglioDTO){
        beneficiarioDettaglioDTO.setTitolo("titolo");
        beneficiarioDettaglioDTO.setNomeCognome("nomeECognome");
        beneficiarioDettaglioDTO.setQualifica("qualifica");
        beneficiarioDettaglioDTO.setProfessione("professione");
        beneficiarioDettaglioDTO.setEmail("email");
        beneficiarioDettaglioDTO.setIndirizzo("indirizzo");
        beneficiarioDettaglioDTO.setCitta("citta");
        beneficiarioDettaglioDTO.setCap("cap");
        beneficiarioDettaglioDTO.setProvincia("provincia");
        beneficiarioDettaglioDTO.setStato("stato");
        beneficiarioDettaglioDTO.setContinente("continente");
        beneficiarioDettaglioDTO.setRagioneSociale("ragioneSociale");
        beneficiarioDettaglioDTO.setIndirizzoAz("indirizzoAz");
        beneficiarioDettaglioDTO.setCapAz("capAz");
        beneficiarioDettaglioDTO.setCittaAz("cittaAz");
        beneficiarioDettaglioDTO.setProvinciaAz("provinciaAz");
        beneficiarioDettaglioDTO.setStatoAz("statoAz");
        beneficiarioDettaglioDTO.setContinenteAz("continenteAz");
        return beneficiarioDettaglioDTO;
    }
}

