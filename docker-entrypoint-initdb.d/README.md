I file presenti in questa directory vengono eseguiti in ordine alfabetico, quindi lo script di creazione dello schema deve sempre essere il primo.

Non è prevista una navigazione ricorsiva per cui gli script devono essere messi in questa cartella.

La query seguente messa nell'ultimo file permette di scrivere nel log la stringa "END_SETUP_DATABASE" che sblocca la fase successiva del processo Maven

select count(*) as END_SETUP_DATABASE from DUAL;
