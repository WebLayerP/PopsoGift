package it.popso.popsogift.utils;

public class Constants {

    private Constants() {
        //SONAR
    }

    public static final String CAMPAGNE_OVERVIEW = "select * \n" +
            "from (select count(c.stato) stato_count, c.stato as id_st\n" +
            "from campagna c \n" +
            "       where (EXTRACT(YEAR FROM c.data_inizio_modifiche) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "                OR EXTRACT(YEAR FROM c.data_fine_modifiche) = EXTRACT(YEAR FROM SYSDATE))\n" +
            "        GROUP by c.stato)\n" +
            "        RIGHT OUTER JOIN (select id_campagna as id_c, stato from CAMPAGNA campagna\n" +
            "        where (EXTRACT(YEAR FROM campagna.data_inizio_modifiche) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "                OR EXTRACT(YEAR FROM campagna.data_fine_modifiche) = EXTRACT(YEAR FROM SYSDATE)))\n" +
            "        on stato = id_st\n" +
            "     LEFT OUTER JOIN SEGNALAZIONE\n" +
            "     on id_c = segnalazione.id_campagna";
    public static final String CAMPAGNE_OVERVIEW_FILIALI= "\nJOIN REL_CAMPAGNA_FILIALE campFil\n" +
            "     on id_c=campFil.id_campagna\n" +
            "     WHERE campFil.codice_filiale IN (:codiciFiliale)";
}
