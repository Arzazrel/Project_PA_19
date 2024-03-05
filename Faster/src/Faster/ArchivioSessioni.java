package Faster;
import java.sql.*;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class ArchivioSessioni 
{   
    //01
    private static String nomeDB;
    private static String pswDB;
    //02
    ArchivioSessioni (String nome, String psw)
    {
        nomeDB = nome;
        pswDB = psw;
    }
    
    //03
    public void aggiungi(Sessione s)
    {
        //03.1
        java.sql.Date dataSQL= new Date(System.currentTimeMillis());
        //03.2
        dataSQL= Date.valueOf(s.data);
        //03.3
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement ("INSERT INTO sessioni VALUES (?, ?, ?, ?, ?, ?, ?, ?)");)
        {
            //03.4
            ps.setString(1,s.nickName);     ps.setString(2,s.durata);       ps.setFloat(3,s.km);
            ps.setInt(4,s.altitudine);      ps.setFloat(5,s.pendenza);      ps.setString(6,s.provincia);
            ps.setFloat(7,s.velocità);      ps.setDate(8,dataSQL);
            System.out.println("righe aggiunte: " + ps.executeUpdate());
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    //04
    public void togli(Sessione s)
    {
        //03.1
        java.sql.Date dataSQL= new Date(System.currentTimeMillis());
        //03.2
        dataSQL= Date.valueOf(s.data);
        //04.1
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement ("DELETE FROM sessioni WHERE nickName = ? AND data = ? AND durata = ? AND km = ? AND provincia = ?");)
        {
            //04.2
            ps.setString(1,s.nickName);      ps.setDate(2, dataSQL);    ps.setString(3, s.durata);
            ps.setFloat(4, s.km);       ps.setString(5, s.provincia);
            System.out.println("righe tolte: " + ps.executeUpdate());
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
/* Lista commenti
    01: variabili di istanza statiche per la connessione al DB
    02: costrutore con parametri il nome e la psw del DB
    03: aggiunge una nuova sessione alla tabella sessioni del DB
    03.1:   creo una data sql inizializzata col tempo dell'istante in cui la creo
    03.2:   assegno alla data creata il valore della data nell'oggetto sessione
            uso il metodo valueOf che ricava la data da una stringa.
    03.3:   mi connetto al DB ed inserisco la nuova sessione, passata come parametro
    03.4:   inserimento valori della sessione da aggiungere dal DB
    04: toglie una sessione dal DB, toglie la sessione che ha stessi: nickName,
        data, durata, km e provincia dell'oggetto sessione passato come parametro.
    04.1:   mi connetto al DB, ed eseguo la delete
    04.2:   inserimento valori per la ricerca della sessione da eliminare dal DB
*/