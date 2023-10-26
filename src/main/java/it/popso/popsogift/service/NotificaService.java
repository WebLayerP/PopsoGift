package it.popso.popsogift.service;

import it.popso.popsogift.dto.NotificaDTO;
import it.popso.popsogift.entity.Notifica;
import it.popso.popsogift.mapper.NotificaMapper;
import it.popso.popsogift.repository.NotificaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificaService {

    @Autowired
    private NotificaRepository notificaRepository;

    @Autowired
    private NotificaMapper notificaMapper;

    public List<NotificaDTO>getAllNotifiche(String codFiliale, String ruolo){
        List<Notifica> result;
        if(!ruolo.equals("RE"))
            result = notificaRepository.findByFilialeCodiceFilialeAndRuolo(codFiliale, ruolo);
        else result = notificaRepository.findByRuolo(ruolo);
        return notificaMapper.listNotificaToListNotificaDto(result);
    }
}
