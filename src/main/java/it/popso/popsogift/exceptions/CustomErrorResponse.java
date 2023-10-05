package it.popso.popsogift.exceptions;

import it.popso.popsogift.utils.ClasseFault;
import org.springframework.http.HttpStatusCode;

/**
 * Classe che definisce il POJO di tutti gli errori che verranno resituiti dall'applicazione
 */
public class CustomErrorResponse {

    private ClasseFault classeFault;
    private String codice;
    private String messaggio;
    private String layer;
    public CustomErrorResponse() {  }

    public CustomErrorResponse(HttpStatusCode httpStatus, Exception ex, ClasseFault classeFault) {
        this.codice=httpStatus.value()+"";
        this.messaggio=ex.getMessage();
        this.layer="Applicazione Dipartimentale POPSOGIFT";
        this.classeFault=classeFault;
    }

    public CustomErrorResponse(HttpStatusCode httpStatus, String message, ClasseFault classeFault)
    {
        this.codice=httpStatus.value()+"";
        this.messaggio=message;
        this.layer="Applicazione Dipartimentale POPSOGIFT";
        this.classeFault=classeFault;
    }
    public void setCodice(String codice)
    {
        this.codice=codice;
    }
    public void setMessaggio(String messaggio)
    {
        this.messaggio=messaggio;
    }
    public void setLayer(String layer)
    {
        this.layer=layer;
    }
    public String getCodice()
    {
        return codice;
    }
    public String getMessaggio()
    {
        return messaggio;
    }
    public String getLayer()
    {
        return layer;
    }
    public void setClasseFault(ClasseFault classeFault)
    {
        this.classeFault=classeFault;
    }
    public ClasseFault getClasseFault()
    {
        return classeFault;
    }
}

