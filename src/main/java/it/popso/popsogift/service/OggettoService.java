package it.popso.popsogift.service;

import it.popso.popsogift.dto.OggettoDTO;
import it.popso.popsogift.dto.OmaggiListDTO;
import it.popso.popsogift.dto.OmaggioOverview;
import it.popso.popsogift.dto.PaginazioneDTO;
import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.exceptions.CannotCreateTransactionException;
import it.popso.popsogift.exceptions.DataIntegrityViolationException;
import it.popso.popsogift.mapper.OggettoMapper;
import it.popso.popsogift.repository.CategoriaRepository;
import it.popso.popsogift.repository.OggettoRepository;
import it.popso.popsogift.utils.CastObjectsUtils;
import it.popso.popsogift.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OggettoService {

    @Autowired
    private OggettoRepository oggettoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private OggettoMapper oggettoMapper;


    public List<OggettoDTO> getAllOggetto() {
        List<Oggetto> oggetti;
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

    public OmaggiListDTO listaOmaggi(int page, int size, String order, String orderBy, String tipologia, String tag, String fornitore){
        OmaggiListDTO result = new OmaggiListDTO();
        PaginazioneDTO paginazioneDTO = new PaginazioneDTO();
        Pageable pageable;
        if(Constants.ORDER_TYPE_ASC.equals(order))
            pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? Constants.DATA_INSERIMENTO_NATIVE : orderBy).ascending());
        else
            pageable = PageRequest.of(page, size, Sort.by(StringUtils.isBlank(orderBy) ? Constants.DATA_INSERIMENTO_NATIVE : orderBy).descending());
        Page<Object[]> risultati = oggettoRepository.findByTipologiaFornitoreTag(tipologia, fornitore, tag, pageable);
        List<OmaggioOverview> listaOmaggi= new ArrayList<>();
        List<String> tags = new ArrayList<>();
        for (Object[] o : risultati.getContent()) {
            OmaggioOverview omaggioOverview = new OmaggioOverview();
            if (isNewOmaggio(o, listaOmaggi)) {
                setOmaggioProperties(o, omaggioOverview, tags);
            } else {
                updateTags(o, tags);
            }
            listaOmaggi.add(omaggioOverview);
        }
        paginazioneDTO.setNumeroPagine(risultati.getTotalPages());
        paginazioneDTO.setNumeroElementiPerPagina(risultati.getSize());
        paginazioneDTO.setNumeroPagina(risultati.getNumber());

        result.setNumeroElementiTotali(risultati.getNumberOfElements());
        result.setPaginazione(paginazioneDTO);
        result.setResults(listaOmaggi);
        return result;
    }

    public OggettoDTO omaggioDetail(Integer id){
        Optional<Oggetto> oggetto = oggettoRepository.findById(id);
        return oggetto.map(value -> oggettoMapper.oggettoToOggettoDTO(value)).orElse(null);
    }

    private boolean isNewOmaggio(Object[] o, List<OmaggioOverview> listaOmaggi) {
        return listaOmaggi.stream().noneMatch(omaggio -> CastObjectsUtils.castObjectIntValue(o[0])== omaggio.getIdOmaggio());
    }

    private void setOmaggioProperties(Object[] o, OmaggioOverview omaggioOverview, List<String> tags) {
        if (null != o[0]) omaggioOverview.setIdOmaggio(CastObjectsUtils.castObjectIntValue(o[0]));
        if (null != o[1]) omaggioOverview.setCodiceOmaggio(o[1].toString());
        if (null != o[2]) omaggioOverview.setDescrizioneBreve(o[2].toString());
        if (null != o[3]) omaggioOverview.setPrezzo(CastObjectsUtils.castObjectDoubleValue(o[3]));
        if (null != o[4]) omaggioOverview.setTipologia(o[4].toString());
        if (null != o[5]) {
            tags.add(o[5].toString());
            omaggioOverview.setTag(tags);
        }
        if (null != o[6]) omaggioOverview.setFornitore(o[6].toString());
    }

    private void updateTags(Object[] o, List<String> tags) {
        tags.add(o[5].toString());
    }

}
