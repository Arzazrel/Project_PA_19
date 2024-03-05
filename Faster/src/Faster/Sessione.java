package Faster;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class Sessione
{
    //01
    String nickName;
    String durata; //01.1
    float km;
    int altitudine;
    float pendenza;
    String provincia;
    float velocità;
    String data;
    //01.2
    Sessione ()
    {
        
    }
    //02
    Sessione (String nickName, String durata, float km, int altitudine, float pendenza, String provincia, float velocità, String data)
    {
        this.nickName = nickName;
        this.durata=durata;
        this.km=km;
        this.altitudine=altitudine;
        this.pendenza=pendenza;
        this.provincia=provincia;
        this.velocità=velocità;
        this.data=data;
    }
    //03
    Sessione (String xml)
    {
        Sessione s = (Sessione) (new XStream(new DomDriver())).fromXML(xml);
        nickName = s.nickName;
        durata = s.durata;
        km = s.km;
        altitudine = s.altitudine;
        pendenza = s.pendenza;
        provincia = s.provincia;
        velocità = s.velocità;
        data = s.data;
    }
    //04
    public float calcolaVelocità (String d, float l)
    {
        float vel=0;
        String[] tempo = d.split(":"); //04.1
        float minuti = Float.parseFloat(tempo[0]); //04.2
        float secondi = Float.parseFloat(tempo[1]); //04.2
        float durata = minuti*60 + secondi; 
        vel = l/(durata/3600);
        return vel;
    }
    //05
    public void modifica (String nk, String d, float kmm, int a, float pen, String prov, float vel, String dat)
    {
        nickName = nk;
        durata = d;
        km = kmm;
        altitudine = a;
        pendenza = pen;
        provincia = prov;
        velocità = vel;
        data = dat;
    }
    //06
    public static boolean equals (Sessione s1, Sessione s2)
    {
        if ((s1.altitudine == s2.altitudine)&&(s1.data == s2.data)&&(s1.durata == s2.durata)
             &&(s1.km == s2.km)&&(s1.nickName == s2.nickName)&&(s1.pendenza == s2.pendenza)
             &&(s1.provincia == s2.provincia)&&(s1.velocità == s2.velocità))
            return true;
        else
            return false;
    }
    //07
    public String serializzaSessione ()
    {
        return (new XStream(new DomDriver())).toXML(this);
    }
}

/* Lista commenti
    01: variabili di istanza 
    01.1:   la durata della sessione è espressa come stringa in questo formato 
            'minuti:secondi'
    01.2:   costruttore senza parametri
    02: costruttore con tutti i parametri
    03: costruttore che crea un nuova istanza sessione da una sessione serializzata
        passatagli come parametro come stringa.
    04: metodo che passata la durata della sessione 'd' in formato stringa e la lunghezza del
        tragitto 'l' calcola la velocità media in km/h e la restituisce
    04.1:   divido la stringa in due sottostringhe una che sarà il tempo in minuti e l'altra
            il tempo in secondi
    04.2:   converto le durate da String a Float, converto i valori in secondi e li sommo.
    05: modifica tutte le variabili di istanza con i nuovi valori passati come parametro
    06: metodo che confronta tutte le variabili di istanza di 2 oggetti Sessioni, se sono
        uguali ritorna true altrimenti false
    07: metodo che serializza l'oggetto implicito e lo restituisce come stringa
*/