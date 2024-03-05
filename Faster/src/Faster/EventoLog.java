package Faster;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class EventoLog
{
    //01
    private static String nomeApplicazioneStatico;      //01.1
    private static String indirizzoIPClientStatico;     //01.1
    private String indirizzoIPClient;
    private String nomeApplicazione;
    private String tipo;
    private java.sql.Time ora;
    private java.sql.Date data;
    //02
    EventoLog ()
    {
        tipo = "Default";
        ora = new java.sql.Time(System.currentTimeMillis());    //02.1 
        data = new java.sql.Date(System.currentTimeMillis());   //02.2 
        indirizzoIPClient = indirizzoIPClientStatico;
        nomeApplicazione = nomeApplicazioneStatico; 
    }
    //03
    EventoLog (String t)
    {
        tipo = t;
        ora = new java.sql.Time(System.currentTimeMillis());    //02.1
        data = new java.sql.Date(System.currentTimeMillis());   //02.2
        indirizzoIPClient = indirizzoIPClientStatico;
        nomeApplicazione = nomeApplicazioneStatico;
    }
    //04
    public static void settaNomeAppEdIP (String nome, String ip)
    {
        nomeApplicazioneStatico = nome;
        indirizzoIPClientStatico = ip;
    }
    //05
    public String serializzaLogXML ()
    {
        return (new XStream(new DomDriver())).toXML(this);
    }
    //06
    public void deserializzaLogXML (String e)
    {
        EventoLog ev = (EventoLog)(new XStream(new DomDriver())).fromXML(e);
        this.tipo = ev.tipo;
        this.data = ev.data;
        this.ora = ev.ora;
    }
}
/* Lista commenti
    01: variabile di istanza
    01.1:   variabili statiche per le informazioni date dal file di configurazione
    02: costruttore di default
    02.1:   inizializzo con l'ora corrente la variabile ora
    02.2:   inizializzo con la data corrente la variabile data
    03: costruttore con un parametro, una stringa che dice il messaggio dell'eventoLog
    04: metodo che setta le variabili di istanza statiche con valori passati come parametro
    05: metodo che serializza l'oggetto implicito e lo restituisce come stringa
    06: metodo che deserializza un oggetto passato come parametro e lo usa per 
        inizializzaare l'istanza EventoLog su cui viene richiamato il metodo.
*/