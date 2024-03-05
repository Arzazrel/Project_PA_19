package Faster;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class FasterNoGUI 
{
    //01
    private Sessione sessione;
    private Utente utente;
    private ArchivioSessioni archivioSessioni;
    private ArchivioUtenti archivioUtenti;
    private DispositivoMobile dispositivoMobile;
    private RegistroLog client;
    //02
    FasterNoGUI ()
    {
        dispositivoMobile = new DispositivoMobile();
    }
    //03
    public void settaParametriDiConfigurazione (ConfigurazioneFaster parametriDiConfigurazione)
    {
        DataBaseTabellaSessioniUtenti.settaParametriDB(parametriDiConfigurazione.getNomeDB(), parametriDiConfigurazione.getPswDB());    //03.1
        EventoLog.settaNomeAppEdIP(parametriDiConfigurazione.getNomeApplicazione(), parametriDiConfigurazione.getIndirizzoIPClient());  //03.2
        DatabaseGrafico.settaParamteriDB(parametriDiConfigurazione.getNomeDB(), parametriDiConfigurazione.getPswDB());      //03.3
        client = new RegistroLog(parametriDiConfigurazione.getPortaServerLog(),parametriDiConfigurazione.getIndirizzoIPServerLog());    //03.4
        archivioSessioni = new ArchivioSessioni(parametriDiConfigurazione.getNomeDB(), parametriDiConfigurazione.getPswDB());       //03.5
        archivioUtenti = new ArchivioUtenti(parametriDiConfigurazione.getNomeDB(), parametriDiConfigurazione.getPswDB());       //03.6
    }
    //04
    public void salvaSessione(Sessione s)
    {
        this.setSessione(s);
        archivioSessioni.aggiungi(s);
    }
    //05
    public void eliminaSessione (Sessione s)
    {
        this.setSessione(s);
        archivioSessioni.togli(s);
    }
    //06
    public void salvaUtente (Utente u)
    {
        this.setUtente(u);
        archivioUtenti.aggiungi(u);
    }
    //07
    public float calcolaCalorieBruciate ()
    {
        float calorie = (float)(0.9 * sessione.km * utente.peso);
        return calorie;
    }
    //08
    public float calcolaGrassiBruciati ()
    {
        float grassi = (float)((utente.peso*sessione.km)/20);
        return grassi;
    }
    //09
    public void setSessione(Sessione s)
    {
        sessione = new Sessione();
        sessione.altitudine = s.altitudine;
        sessione.data = s.data;
        sessione.durata = s.durata;
        sessione.km = s.km;
        sessione.nickName = s.nickName;
        sessione.pendenza = s.pendenza;
        sessione.provincia = s.provincia;
        sessione.velocità = s.velocità;
    }
    //10
    public void setUtente(Utente u)
    {
        utente= new Utente(u.nickName, u.peso);
    }
    //11
    public void setArchivioSessioni(ArchivioSessioni as)
    {
        archivioSessioni = as;
    }
    //12
    public void setArchivioUtenti (ArchivioUtenti au)
    {
        archivioUtenti = au;
    }
    //13
    public Sessione getSessione ()
    {
        return sessione;
    }
    //14
    public Utente getUtente ()
    {
        return utente;
    }
    //15
    public void scriviDispositivoMobile()
    {
        Utente u = new Utente("Dino",80);
        Sessione s = new Sessione ("Dino","30:00",(float)4.5,0,0,"MI",9,"2019-08-07");
        dispositivoMobile = new DispositivoMobile();
        dispositivoMobile.aggiungiSessione(s, u);
    }
    //16
    public DispositivoMobile leggiDispositivoMobile()
    {
        DispositivoMobile db = new DispositivoMobile();
        return db.importaSessione();
    }
    //17
    public void inviaEventoLog(String messaggio)
    {
        EventoLog ev = new EventoLog (messaggio);
        client.inviaLog(ev);
    }
}
/* Lista commenti
    01: variabili di istanza
    02: costruttore senza parametri
    03: metodo che ha come parametro un oggetto ConfigurazioneFaster da cui prende i dati
        di configurazione per le variabili d'istanza.
    03.1:   setto i parametri di configurazione per il DBMS della classe 
            DataBaseTabellaSessioniUtenti
    03.2:   setto i parametri di configurazione per il DBMS della classe EventoLog
    03.3:   setto i parametri di configurazione per il DBMS della classe DatabaseGrafico
    03.4:   inizializzo client (istanza di RegistroLog che farà dal client che invia gli
            eventiLog) e gli passo i parametri di configurazione per l'invio e la ricezione
            degli eventiLog al ServerLog
    03.5:   inizializzo archivioSessioni e gli passo come parametro i parametri di configurazione
            per la connessione al DBMS
    03.6:   inizializzo archivioUtenti e gli passo come parametro i parametri di configurazione
            per la connessione al DBMS
    04: metodo che prende un oggetto sessione come parametro e lo aggiunge al DBMS tramite
        l'archivioSessioni
    05: metodo che prende un oggetto sessione come parametro e lo toglie dal DBMS tramite
        l'archivioSessioni
    06: metodo che prende un oggetto utente come parametro e lo aggiunge al DBMS tramite
        l'archivioUtente
    07: metodo che calcola e restituisce le calorie bruciate dall'utente nella sessione
        di allenamento, usa utente e sessione che ha come variabile di istanza
    08: metodo che calcola e restituisce i grammi di grassi consumati dall'utente nella
        sessione di allenamento, usa utente e sessione che ha come variabile di istanza
    09: metodo che setta la sessione che ha come variabile di istanza tramite un altro
        oggetto sessione passato come parametro
    10: metodo che setta l'utente che ha come variabile di istanza tramite un altro     
        oggetto utente passato come parametro
    11: metodo che setta l'archiovioSessioni che ha come variabile di istanza tramite
        un altro oggetto ArchivioSessioni passato come parametro
    12: metodo che setta l'archivioUtenti che ha come variabile di istanza tramite
        un altro oggetto ArchivioUtenti passato come parametro
    13: metodo get per la sessione
    14: metodo get per l'utente
    15: metodo che va a scrivere sul file binario in locale che rappresenta il dispositivo
        mobile, usato in fase di testing per popolare il dispositivo mobile
    16: metodo che ritorna i dati dell'utente e della sessione inseriti nel dispositivo
        mobile
    17: metodo che crea un nuovo EventoLog che ha come messaggio la stringa passata come
        parametro e lo invia al ServerLog
*/
