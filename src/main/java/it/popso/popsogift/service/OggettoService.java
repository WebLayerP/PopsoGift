package it.popso.popsogift.service;

import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.OggettoMapper;
import it.popso.popsogift.repository.CategoriaRepository;
import it.popso.popsogift.repository.FornitoreRepository;
import it.popso.popsogift.repository.OggettoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OggettoService {

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornitoreRepository fornitoreRepository;

    @Autowired
    private OggettoMapper oggettoMapper;


    public List<OggettoDTO> getAllOggetto() {
        List<Oggetto> oggetti = null;
        try {
            oggetti = oggettoRepository.findAll();

        }catch(org.springframework.transaction.CannotCreateTransactionException e){
            throw new CannotCreateTransactionException(e.getMessage());
        }
        return oggettoMapper.listaOggettiToDTO(oggetti);
    }
    public OggettoDTO saveOggetto(OggettoDTO oggettoDTO){
        oggettoDTO.setDataInserimento(new Date());
        Oggetto oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);
        oggetto.setIdOggetto(null);
            try {
                if(!categoriaRepository.existsByNomeCategoria(oggettoDTO.getCategoria().toUpperCase())) {
                    oggetto.getCategoria().setNomeCategoria(oggetto.getCategoria().getNomeCategoria().toUpperCase());
                    categoriaRepository.save(oggetto.getCategoria());
                }
                oggettoRepository.save(oggetto);
            } catch (org.springframework.dao.DataIntegrityViolationException e) {
                throw new DataIntegrityViolationException(e.getMessage());
            }
        return oggettoDTO;
    }
}
