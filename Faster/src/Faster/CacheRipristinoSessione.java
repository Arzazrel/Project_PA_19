package Faster;
import java.io.*; 
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class CacheRipristinoSessione 
{
    //01
    private Utente utenteParziale;
    private Sessione sessioneParziale;
    
    //02
    CacheRipristinoSessione ()
    {
        utenteParziale = null;
        sessioneParziale = null;
    }
    //03
    public void salvaUtenteParzialeBin (Utente u)
    {
        utenteParziale = u;
        try ( FileOutputStream fout = new FileOutputStream ("./myFiles/cacheUtente.bin",false);
              ObjectOutputStream oout = new ObjectOutputStream(fout);)
        {
            if (utenteParziale==null)  //03.1
            {
                oout.writeUTF(" ");     //03.2
            }
            else
            {
                oout.writeUTF(utenteParziale.serializzaUtente());       //03.3
            }
        }
        catch (IOException ie)
        {
            System.err.println("Si è verificato un errore, impossibile salvare i dati dell'utente in locale");
        }
    }
    //04
    public void salvaSessioneParzialeBin (Sessione s)
    {
        sessioneParziale = s;
        try ( FileOutputStream fout = new FileOutputStream ("./myFiles/cacheSessione.bin",false);
              ObjectOutputStream oout = new ObjectOutputStream(fout);)
        {
            if (sessioneParziale == null)   //04.1
            {
                oout.writeUTF(" ");         //04.2
            }
            else
            {
                oout.writeUTF(sessioneParziale.serializzaSessione());       //04.3
            }
        }
        catch (IOException ie)
        {
            System.err.println("Si è verificato un errore, impossibile salvare i dati della sessione in locale");
        }
    }
    //05
    public Utente recuperaUtenteParzialeBin ()
    {
        try ( FileInputStream fin = new FileInputStream ("./myFiles/cacheUtente.bin");
              ObjectInputStream oin = new ObjectInputStream(fin);  )
        {
            String xml = oin.readUTF();         //05.1
            if (xml.equals(" "))                //05.2
            {
                utenteParziale=null;            
            }
            else
            {
                utenteParziale = new Utente(xml);       //05.3
            }
        }
        catch (IOException ie)
        {
            System.err.println("Si è verificato un errore, impossibile recuperare i dati dell'utente salvati in locale");
        }
        return utenteParziale;          //05.4
    }
    //06
    public Sessione recuperaSessioneParzialeBin ()
    {
        try ( FileInputStream fin = new FileInputStream ("./myFiles/cacheSessione.bin");
              ObjectInputStream oin = new ObjectInputStream(fin);  )
        {
            String xml = oin.readUTF();     //06.1
            if (xml.equals(" "))            //06.2
            {
                sessioneParziale=null;
            }
            else
            {
                sessioneParziale = new Sessione(xml);   //06.3
            }
        }
        catch (IOException ie)
        {
            System.err.println("Si è verificato un errore, impossibile recuperare i dati della sessione salvati in locale");
        }
        return sessioneParziale;        //06.4
    }
}
/* Lista commenti
    01: variabili di istanza
    02: costruttore senza parametri
    03: metodo che salva in locale su file binario un oggetto utente passatogli. se l'oggetto è null
        verrà salvato un valore di default che è " ", se oggetto non è null viene serializzato e salvato
    03.1:   controllo per vedere se l'oggetto passato è = a null o no
    03.2:   scrittura valore di defalut
    03.3:   serializzazione e scrittura dell'oggetto
    04: metodo che salva in locale su file binario un oggetto Sessione passatogli. se l'oggetto è null
        verrà salvato un valore di default che è " ", se l'oggetto non è null biene serializzato e salvato.
    04.1:   controllo per vedere se l'oggetto passato è uguale a null o no
    04.2:   scrittura valore di defalut
    04.3:   serializzazione e scrittura dell'oggetto
    05: metodo che recupera dal file binario locale l'utente salvato dalla cache e lo restituisce
    05.1:   lettura del contenuto del file binario
    05.2:   controllo se il contenuto della cache era il valore di default o meno
    05.3:   uso costruttore che crea un nuovo oggetto con gli stessi valori dell'oggetto serializzato 
            passato come parametro
    05.4:   ritorno l'oggetto deserializzato che era contenuto nel file binario
    06: metodo che recupera dal file binario locale la sessione salvata dalla cache e la restituisce
    06.1:   lettura del contenuto del file binario
    06.2:   controllo se il contenuto della cache era il valore di default o meno
    06.3:   uso costruttore che crea un nuovo oggetto con gli stessi valori dell'oggetto serializzato 
            passato come parametro 
    06.4:   ritorno l'oggetto deserializzato che era contenuto nel file binario
*/
