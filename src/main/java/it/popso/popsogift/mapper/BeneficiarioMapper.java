package it.popso.popsogift.mapper;

import it.popso.popsogift.dto.BeneficiarioDTO;
import it.popso.popsogift.dto.BeneficiarioDettaglioDTO;
import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.dto.TipologiaOggettoDTO;
import it.popso.popsogift.entity.Beneficiario;
import it.popso.popsogift.entity.Categoria;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.entity.TipologiaOggetto;
import it.popso.popsogift.exceptions.ApplicationFaultMsgException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeneficiarioMapper {

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    BeneficiarioDTO beneficiarioToBeneficiarioDTO(Beneficiario beneficiario);

    @Mapping(target="fornitore.listaOggetti", ignore=true)
    OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    Beneficiario beneficiarioDTOToBeneficiario(BeneficiarioDTO beneficiarioDTO);

    List<BeneficiarioDTO> listaBeneficiariToBeneficiariDTO(List<Beneficiario> beneficiari);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    BeneficiarioDTO beneficiarioDettaglioDTOToBeneficiarioDTO (BeneficiarioDettaglioDTO beneficiarioDettaglioDTO);

    @Mapping(target="statoBeneficiario", ignore = true)
    @Mapping(target="listaGruppi", ignore = true)
    @Mapping(target="filiale.listaBeneficiari", ignore = true)
    Beneficiario beneficiarioDettaglioDTOToBeneficiario(BeneficiarioDettaglioDTO beneficiarioDettaglioDTO);

    List<BeneficiarioDettaglioDTO> listaBeneficiariToBeneficiariDettaglioDTO(List<Beneficiario> beneficiari);
    List<Beneficiario> listaBeneficiariDettaglioDTOToBeneficiari(List<BeneficiarioDettaglioDTO> beneficiariDettaglioDTO);

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
    default BeneficiarioDettaglioDTO beneficiarioDettaglioCompleto(BeneficiarioDettaglioDTO beneficiarioDettaglioDTO) {
        switch (beneficiarioDettaglioDTO.getNdg()) {
            case "1" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregio");
                beneficiarioDettaglioDTO.setNome("Paolo");
                beneficiarioDettaglioDTO.setCognome("Rossi");
                beneficiarioDettaglioDTO.setQualifica("Impiegato");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("paoloR@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Rossi 2");
                beneficiarioDettaglioDTO.setCitta("Padova");
                beneficiarioDettaglioDTO.setCap("654654");
                beneficiarioDettaglioDTO.setProvincia("PD");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("56456");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Grossi 4");
                beneficiarioDettaglioDTO.setCapAz("654654");
                beneficiarioDettaglioDTO.setCittaAz("Padova");
                beneficiarioDettaglioDTO.setProvinciaAz("PD");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "8" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregio");
                beneficiarioDettaglioDTO.setNome("Davide");
                beneficiarioDettaglioDTO.setCognome("Rossi");
                beneficiarioDettaglioDTO.setQualifica("Impiegato");
                beneficiarioDettaglioDTO.setProfessione("Dirigente");
                beneficiarioDettaglioDTO.setEmail("davideR@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Fiori 2");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("20194");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("564625");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Verdi 4");
                beneficiarioDettaglioDTO.setCapAz("20194");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "100" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregia");
                beneficiarioDettaglioDTO.setNome("Filippa");
                beneficiarioDettaglioDTO.setCognome("Grigi");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("filippaG@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Tappeti 2");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("20194");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("5612465");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Cola 4");
                beneficiarioDettaglioDTO.setCapAz("20194");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "80" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregia");
                beneficiarioDettaglioDTO.setNome("Fabiana");
                beneficiarioDettaglioDTO.setCognome("Gigli");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dirigente");
                beneficiarioDettaglioDTO.setEmail("fabianaG@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Nola 2");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("20194");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("564625");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Tre 4");
                beneficiarioDettaglioDTO.setCapAz("20194");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "70" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregio");
                beneficiarioDettaglioDTO.setNome("Ludovico");
                beneficiarioDettaglioDTO.setCognome("Pioli");
                beneficiarioDettaglioDTO.setQualifica("Impiegato");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("ludovicoP@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Viale Alberini 2");
                beneficiarioDettaglioDTO.setCitta("Roma");
                beneficiarioDettaglioDTO.setCap("263634");
                beneficiarioDettaglioDTO.setProvincia("RM");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("853353");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Giorgi 2");
                beneficiarioDettaglioDTO.setCapAz("263634");
                beneficiarioDettaglioDTO.setCittaAz("Roma");
                beneficiarioDettaglioDTO.setProvinciaAz("RM");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "11" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregia");
                beneficiarioDettaglioDTO.setNome("Barbara");
                beneficiarioDettaglioDTO.setCognome("Longhi");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("barbaraL@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Pelli 9");
                beneficiarioDettaglioDTO.setCitta("Roma");
                beneficiarioDettaglioDTO.setCap("263634");
                beneficiarioDettaglioDTO.setProvincia("RM");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("81234");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Giudici 2");
                beneficiarioDettaglioDTO.setCapAz("263634");
                beneficiarioDettaglioDTO.setCittaAz("Roma");
                beneficiarioDettaglioDTO.setProvinciaAz("RM");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "88" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregia");
                beneficiarioDettaglioDTO.setNome("Stella");
                beneficiarioDettaglioDTO.setCognome("Luna");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("stellaL@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Biondi 9");
                beneficiarioDettaglioDTO.setCitta("Roma");
                beneficiarioDettaglioDTO.setCap("263634");
                beneficiarioDettaglioDTO.setProvincia("RM");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("768332");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Pronti 4");
                beneficiarioDettaglioDTO.setCapAz("263634");
                beneficiarioDettaglioDTO.setCittaAz("Roma");
                beneficiarioDettaglioDTO.setProvinciaAz("RM");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "120" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregia");
                beneficiarioDettaglioDTO.setNome("Giorgia");
                beneficiarioDettaglioDTO.setCognome("Luna");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("giorgiaL@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Belli 9");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("934629");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("768332");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Pronti 4");
                beneficiarioDettaglioDTO.setCapAz("934629");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "800" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregio");
                beneficiarioDettaglioDTO.setNome("Filippo");
                beneficiarioDettaglioDTO.setCognome("Lunati");
                beneficiarioDettaglioDTO.setQualifica("Impiegato");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("filippoL@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Lolli 1");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("934629");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("768332");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Pronti 4");
                beneficiarioDettaglioDTO.setCapAz("934629");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            case "889" -> {
                beneficiarioDettaglioDTO.setTitolo("Egregio");
                beneficiarioDettaglioDTO.setNome("Loredana");
                beneficiarioDettaglioDTO.setCognome("Lesi");
                beneficiarioDettaglioDTO.setQualifica("Impiegata");
                beneficiarioDettaglioDTO.setProfessione("Dipendente");
                beneficiarioDettaglioDTO.setEmail("loredanaL@email.it");
                beneficiarioDettaglioDTO.setIndirizzo("Via Pola 1");
                beneficiarioDettaglioDTO.setCitta("Milano");
                beneficiarioDettaglioDTO.setCap("934629");
                beneficiarioDettaglioDTO.setProvincia("MI");
                beneficiarioDettaglioDTO.setStato("Italia");
                beneficiarioDettaglioDTO.setContinente("Eu");
                beneficiarioDettaglioDTO.setRagioneSociale("768332");
                beneficiarioDettaglioDTO.setIndirizzoAz("Via Pronti 4");
                beneficiarioDettaglioDTO.setCapAz("934629");
                beneficiarioDettaglioDTO.setCittaAz("Milano");
                beneficiarioDettaglioDTO.setProvinciaAz("MI");
                beneficiarioDettaglioDTO.setStatoAz("Italia");
                beneficiarioDettaglioDTO.setContinenteAz("Eu");
            }
            default->
                throw new ApplicationFaultMsgException("L'ndg inserito non è stato trovato");

        }
        return beneficiarioDettaglioDTO;
    }
}

