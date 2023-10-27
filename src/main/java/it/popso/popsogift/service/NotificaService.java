package it.popso.popsogift.service;

import it.popso.popsogift.dto.NotificaDTO;
import it.popso.popsogift.entity.MatricoleLettura;
import it.popso.popsogift.entity.Notifica;
import it.popso.popsogift.mapper.NotificaMapper;
import it.popso.popsogift.repository.MatricoleRepository;
import it.popso.popsogift.repository.NotificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificaService {

    @Autowired
    private NotificaRepository notificaRepository;

    @Autowired
    private MatricoleRepository matricoleRepository;

    @Autowired
    private NotificaMapper notificaMapper;

    public List<NotificaDTO>getAllNotifiche(String codFiliale, String ruolo, String matricola){
        List<Notifica> result;
        if(!ruolo.equals("RE"))
            result = notificaRepository.findByFilialeCodiceFilialeAndRuolo(codFiliale, ruolo);
        else result = notificaRepository.findByRuolo(ruolo);
        List<NotificaDTO> notificheDto = notificaMapper.listNotificaToListNotificaDto(result);
        notificheDto
                .forEach(notificaDTO -> notificaDTO.setLetta(checkNotificaLetta(matricola, notificaDTO.getIdNotifica())));
        return notificheDto;
    }

    public void leggiNotifica(String matricola, int id){
        List<MatricoleLettura> matricole = matricoleRepository.findByMatricolaAndNotificaIdNotifica(matricola, id);
        Optional<Notifica> notifica;
        if(matricole.isEmpty()){
            notifica = notificaRepository.findById(id);
            notifica.ifPresent(value -> matricoleRepository.save(new MatricoleLettura(matricola, value)));
        }
    }

    private boolean checkNotificaLetta(String matricola, int idNotifica){
        List<MatricoleLettura> matricole = matricoleRepository.findByMatricolaAndNotificaIdNotifica(matricola, idNotifica);
        return !matricole.isEmpty();
    }
}
