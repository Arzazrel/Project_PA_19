package Faster;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class DispositivoMobile
{
    //01
    public Sessione sessioneMonitorata;
    public Utente personaMonitorata;
    //02
    DispositivoMobile ()
    {
        sessioneMonitorata = null;
        personaMonitorata = null;
    }
    //03
    DispositivoMobile (Utente u, Sessione s)
    {
        sessioneMonitorata = s;
        personaMonitorata = u;
    }
    //04
    public void aggiungiSessione(Sessione s, Utente u)
    {
        sessioneMonitorata = s;
        personaMonitorata = u;
        try ( FileOutputStream fout = new FileOutputStream("./myFiles/dispositivoMobile.bin",true);
              ObjectOutputStream oout = new ObjectOutputStream(fout);) 
        {
            oout.writeUTF((new XStream(new DomDriver())).toXML(this));  //04.1
        }catch (IOException e) 
        {
            System.err.println("errore: impossibile aggiungere la sessione di corsa sul dispositivo mobile");
        }
    }
    
    //05
    public DispositivoMobile importaSessione ()
    {
        DispositivoMobile dm = null;
        try ( FileInputStream fin = new FileInputStream("./myFiles/dispositivoMobile.bin");
              ObjectInputStream oin = new ObjectInputStream(fin); ) 
        { 
            String xml = oin.readUTF();     //05.1
            dm = (DispositivoMobile)(new XStream(new DomDriver())).fromXML(xml);       //05.2
        } catch (IOException e) 
        {
            System.err.println("errore: impossibile prelevare la sessione di corsa dal dispositivo mobile");
        }
        return dm;      //05.3
    }
}
/* Lista commenti
    01: variabili di istanza
    02: costruttore senza parametri
    03: costruttore con parametri 
    04: metodo che prende come parametri sessione e utente, e poi scrive se stesso serializzato nel file binario
    04.1:   scrittura dell'oggetto serializzato
    05: metodo che recupera dal file binario i dati di una sessione ed un utente, li salva in un oggetto 
        DispositivoMobile e poi ritorna tale oggetto
    05.1:   leggo l'oggetto serializzato e lo assegno ad una String
    05.2:   deserializzo la stringa in un oggetto DispositivoMobile 
    05.3:   ritorno l'oggetto contenente sessione ed utente presi da file binario 
*/
