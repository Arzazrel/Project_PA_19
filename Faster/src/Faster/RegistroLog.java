package Faster;
import java.net.*;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class RegistroLog 
{
    //01
    private static int portaServerLog;
    private static String indirizzoIPServerLog;
    //02
    RegistroLog (int porta, String indIPSL)
    {
        portaServerLog = porta;
        indirizzoIPServerLog = indIPSL;
    }
    //03
    public static void validaEventoLogXML(String eventoLogSerializzato)
    {
        try
        {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            org.w3c.dom.Document documentoConfigurazione = db.parse(new InputSource(new StringReader (eventoLogSerializzato))); //03.1
            Schema schemaConfigurazione = sf.newSchema(new StreamSource(new File("./myFiles/eventoLog.xsd")));  //03.2
            schemaConfigurazione.newValidator().validate(new DOMSource(documentoConfigurazione));   //03.3
        }
        catch (ParserConfigurationException | SAXException | IOException ex)
        {
             if (ex instanceof SAXException)  
                 System.out.println("Errore di validazione: " + ex.getMessage());  //03.4
             else
                 System.out.println(ex.getMessage()); 
        }
    }
    //04
    public static void inviaLog (EventoLog ev)
    {
        try (   Socket sock = new Socket(indirizzoIPServerLog, portaServerLog); //04.1
                DataOutputStream dos = new DataOutputStream(sock.getOutputStream());)
        {
            dos.writeUTF(ev.serializzaLogXML());    //04.2
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //05
    public static void ricezioneLog ()
    {
        String eventoXML;
        try( ServerSocket servSock = new ServerSocket(portaServerLog);  //05.1
             Socket sock = servSock.accept();
             DataInputStream dis = new DataInputStream (sock.getInputStream());)
        {
            eventoXML = dis.readUTF();  //05.2
            salvaLogTXT(eventoXML);     //05.3
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //06
    public static void salvaLogTXT (String eventoSerializzato)
    {
        try(  FileOutputStream fout = new FileOutputStream ("./myFiles/registroLogFaster.txt",true);
              DataOutputStream dout = new DataOutputStream (fout);)
        {
            validaEventoLogXML(eventoSerializzato);
            dout.writeUTF(eventoSerializzato+"\n");
        }catch (IOException ex) 
        {
            System.err.println("errore: impossibile salvare correttamente il log su file txt");
        }
    }
    //07
    public static void main (String[] arg)
    {
        Configurazione.validaClasseConfigurazioneFaster();  //07.1
        ConfigurazioneFaster parametriDiConfigurazione = Configurazione.caricaConfigurazioneFasterXML();    //07.2
        RegistroLog ServerLog = new RegistroLog(parametriDiConfigurazione.getPortaServerLog(),parametriDiConfigurazione.getIndirizzoIPServerLog());     //07.3
        while(true)     //07.4
            ricezioneLog();
    }
}
/* Lista commenti
    01: variabile di istanza statiche per invio e ricezione al server log
    02: costruttore con parametri che setta le variabili di istanza 
    03: metodo che valida un eventoLog serializzato passato come parametro con un 
        file xsd salvato in locale
    03.1:   creo un documento dall'oggeto serializzato
    03.2:   creo uno schema xsd dal file locale xsd
    03.3:   valido il documento xml con schema xsd
    03.4:   in caso di errore dovuto a validazione sbagliata restituisco il messaggio di
            di errore preciso di cosa non è conforme al documento xsd
    04: metodo che manda un eventoLog serializzato al serverLog
    04.1:   creo un socket usando come parametri di indirizzo e porta le variabili di
            istanza statiche
    04.2:   serializzo l'eventoLog e lo invio
    05: metodo che esegue il serverSocket, riceve un evento serializzato e richiama
        il metodo per scriverlo nel registro di log in locale passandoglielo come
        parametro
    05.1:   creo un ServerSocket usando il numero di porta salvato nella variabile
            di istanza statica, lo stesso dato al socket in invio
    05.2:   prendo l'eventoLog serializzato ricevuto, lo salvo in una string
    05.3:   richiamo il metodo per scrivere l'eventoLog ricevuto in file locale
    06: metodo che riceve un eventoLog serializzato come parametro e lo scrive in
        un file txt in locale (registro dei log)
    07: metodo main che serve da serverLog sarà sempre attivo in attesa di messaggi
        in ricezione.
    07.1:   valido il documento di configurazione xml in locale
    07.2:   creo un oggetto ConfigurazioneFaster che prende i valori dal file di 
            configurazione xml in locale
    07.3:   creo un oggetto RegistroLog e gli passo i parametri di configurazione
    07.4:   while sempre attivo che eseguirà sempre il metodo che aspetta in
            ricezione, implementa il ServerLog
*/