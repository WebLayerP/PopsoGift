package it.popso.popsogift.utils;

public class Constants {

    private Constants() {
        //SONAR
    }

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

    public static final String CAMPAGNA_OVERVIEW ="SELECT * FROM (SELECT c.id_campagna, c.tipologia,COUNT(o.id_oggetto) AS tot_omaggi,sum(o.prezzo) AS tot_costo, c.data_fine\n" +
            "FROM campagna c\n" +
            "LEFT JOIN rel_campagna_oggetto rco ON c.id_campagna = rco.id_campagna\n" +
            "LEFT JOIN oggetto o ON o.id_oggetto = rco.id_oggetto\n" +
            "WHERE c.id_campagna = :idCampagna\n" +
            "GROUP BY c.id_campagna, c.tipologia, c.data_fine) campagnaOverview\n" +
            "LEFT JOIN (SELECT C.ID_CAMPAGNA, SUM(CASE WHEN sf.confermato = 0 THEN 1 ELSE 0 END) AS filiali_in_attesa,\n" +
            "SUM(CASE WHEN sf.confermato = 1 THEN 1 ELSE 0 END) AS filiali_confermate\n" +
            "FROM campagna c LEFT OUTER JOIN rel_campagna_filiale sf ON c.id_campagna = sf.id_campagna WHERE c.id_campagna = :idCampagna\n" +
            "GROUP BY c.id_campagna) filialeOverview\n" +
            "ON campagnaOverview.id_campagna = filialeOverview.id_campagna";
}
