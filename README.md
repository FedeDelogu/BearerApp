# BearerApp
Il progetto BearerApp è un'applicazione sviluppata utilizzando il framework Spring e Hibernate, progettata per gestire un sistema di RESTful API. Questo sistema consente la creazione, modifica, eliminazione e recupero di entità nel database, con l'obiettivo di semplificare le operazioni CRUD. Utilizzando Hibernate, le tabelle del database vengono generate automaticamente dalle entità Java, garantendo una gestione efficiente della persistenza dei dati. 
1) tramite Hibernate, una volta creato il db Bearer su MySql, sono state create cinque tabelle:
Activity: Contiene informazioni sulle attività, inclusi nome, luogo, date di inizio e fine, posti disponibili e occupati.

User: Gestisce i dati degli utenti, consentendo l'autenticazione e la registrazione per le attività.

UserActivity: Registra le iscrizioni degli utenti alle attività, gestendo le relazioni tra utenti e attività.

Category: Permette di categorizzare le attività, così che possano essere raggruppate in base a temi o interessi.

ActivityCategory: Tabella di associazione che collega le attività alle categorie, permettendo che un'attività possa appartenere a più categorie.

2) Filtri attività:
Nel progetto, ho implementato i filtri delle attività utilizzando un DTO (Data Transfer Object) e una classe Filterservice dedicata.

Motivazione per l'Uso del DTO
Il DTO  consente di separare la logica di filtraggio dalla logica di accesso ai dati, mantenendo la repository pulita e organizzata. Inoltre, facilita la gestione dei dati in entrata e in uscita, rendendo più chiara l'interfaccia tra il client e il server.

La classe ActivityFiltersServiceImpl implementa l'interfaccia FiltersService e gestisce la logica di filtraggio delle attività.
La classe  centralizza la logica di business, consentendo una gestione più semplice dei filtri. Se non vengono forniti filtri, il servizio restituisce tutte le attività, garantendo un comportamento intuitivo. In questo modo, abbiamo mantenuto il codice più modulare e testabile, migliorando la manutenibilità dell'applicazione.

3) Utente-attività:

alcuni punti aggiuntivi di riflessione sulle scelte prese:
- pattern usato MVC
- approccio allo svolgimento: attività divisa in sprint, con esecuzione codice e test su postman.
- response entity mi ha permesso di gestire le richieste http in modo più flessibile.
- Predicate verifica se il nome dell'attività è null o se corrisponde al nome fornito
- ho lavorato con Spring Boot in  ambiente locale, utilizzando come strumento di sviluppo  IntelliJ IDEA, e ho gestito il database in locale con  MySQL. Questo approccio ti ha permesso di sviluppare e testare facilmente le  RESTful API senza la complessità di un container
Punti di miglioramento:
- utilizzare un container per garantire un ambiente di sviluppo coerente e ridurre le problematiche legate alle dipendenze e alla configurazione.
- non ho avuto modo di gestire le eccezioni come avrei voluto per una carenza a livello formativo che voglio colmare il prima possibile.
- la parte dei test purtroppo non è stata gestita perchè ho potuto iniziare il progetto solo venerdi mattina. 
