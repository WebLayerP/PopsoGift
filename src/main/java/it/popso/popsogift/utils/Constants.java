package it.popso.popsogift.utils;

public class Constants {

    private Constants() {
        //SONAR
    }
//    ###### VARIABILI #######################

    public static final String DATA_INSERIMENTO = "data_Inserimento";
    public static final String ORDER_TYPE_ASC = "ASC";

//    ###### QUERY ###########################

    public static final String CAMPAGNE_OVERVIEW = "select COUNT(distinct id_c) OVER (PARTITION BY stato) AS stato_count,stato, id_c, titolo_campagna, id, autore, descrizione, id_beneficiario, id_c, data_inserimento, data_aggiornamento \n" +
            "from (select id_campagna as id_c, titolo_campagna,  stato\n" +
            "from CAMPAGNA campagna \n" +
            "       where (EXTRACT(YEAR FROM campagna.data_inizio_modifiche) = EXTRACT(YEAR FROM CURRENT_DATE)\n" +
            "                OR EXTRACT(YEAR FROM campagna.data_fine_modifiche) = EXTRACT(YEAR FROM CURRENT_DATE))) campagnaPerDate\n" +
            "        LEFT OUTER JOIN SEGNALAZIONE\n" +
            "        on id_c = segnalazione.id_campagna";
    public static final String CAMPAGNE_OVERVIEW_FILIALI= "\nJOIN REL_CAMPAGNA_FILIALE campFil\n" +
            "     on id_c=campFil.id_campagna\n" +
            "     WHERE campFil.codice_filiale IN (?1)";

    public static final String CAMPAGNA_OVERVIEW = "SELECT campagna.id_campagna, campagna.tipologia, campagna.tot_omaggi, campagna.prezzo_totale, campagna.data_fine, \n" +
            "                  filiale.filiali_confermate, filiale.filiali_in_attesa \n" +
            "                  FROM (SELECT c.id_campagna,c.data_fine, c.tipologia, COUNT(o.id_oggetto) AS tot_omaggi, sum(o.prezzo) as prezzo_totale  \n" +
            "                  FROM campagna c LEFT OUTER JOIN rel_campagna_filiale cf ON c.id_campagna = cf.id_campagna  \n" +
            "                  left join rel_beneficiario_filiale bf on cf.codice_filiale = bf.codice_filiale \n" +
            "                  left JOIN rel_beneficiario_oggetto_campagna boc ON bf.ndg = boc.ndg AND boc.id_campagna=?1\n" +
            "                  left JOIN oggetto O ON boc.id_oggetto = O.id_oggetto \n" +
            "                  WHERE c.id_campagna = ?1\n" +
            "                  GROUP BY c.data_fine, c.tipologia, C.id_campagna) campagna \n" +
            "                  RIGHT OUTER JOIN (SELECT c.id_campagna, SUM(CASE WHEN sf.confermato = 0 THEN 1 ELSE 0 END) AS filiali_in_attesa,  \n" +
            "                      SUM(CASE WHEN sf.confermato = 1 THEN 1 ELSE 0 END) AS filiali_confermate  \n" +
            "                      FROM campagna c LEFT OUTER JOIN rel_campagna_filiale sf ON c.id_campagna = sf.id_campagna WHERE c.id_campagna = ?1\n" +
            "                      GROUP BY c.id_campagna) filiale \n" +
            "                      ON campagna.id_campagna = filiale.id_campagna";

    public static final String OMAGGI_FILTRATI = "SELECT o.id_oggetto, o.codice as codiceOmaggio, o.descrizione, o.prezzo , tipologia.descrizione as tipologia, t.nome as tag , f.ragione_sociale \n" +
            "FROM Oggetto o \n" +
            "    left join tipologia_oggetto tipologia on o.tipologia= tipologia.id_tipologia\n" +
            "    left join fornitore f on f.id_fornitore = O.FORNITORE\n" +
            "    left Join rel_oggetto_tag r on r.id_Oggetto = o.id_Oggetto \n" +
            "    left join tag t on t.id_tag= r.id_tag\n" +
            "WHERE (?1 IS NULL OR tipologia.descrizione = ?1) AND (?2 IS NULL OR f.ragione_sociale= ?2) AND (?3 IS NULL OR t.nome = ?3)";
}
