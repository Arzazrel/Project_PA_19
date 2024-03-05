package Faster;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class Utente
{
    //01
    String nickName;
    float peso;
    //02
    Utente ()
    {
        nickName = "default";
        peso = 0;
    }
    //03
    Utente (String n, float p)
    {
        nickName = n;
        peso = p;
    }
    //04
    Utente (String xml)
    {
        Utente u = (Utente) (new XStream(new DomDriver())).fromXML(xml);
        nickName = u.nickName;
        peso = u.peso;
    }
    //05
    public static boolean equals (Utente u1, Utente u2)
    {
        if ((u1.nickName == u2.nickName)&&(u1.peso == u2.peso))
            return true;
        else
            return false;
    }
    //06
    public String serializzaUtente ()
    {
        return (new XStream(new DomDriver())).toXML(this);
    }
}
/* Lista commenti
    01: variabili di istanza 
    02: costruttore senza parametri
    03: costruttore con parametri
    04: costruttore che ha come parametro un oggetto Utente serializzato
    05: metodo che confronta tutte le variabili di istanza di 2 oggetti Utenti, se sono
        uguali ritorna true altrimenti false
    06: metodo che serializza l'oggetto implicito e lo restituisce come string
*/
