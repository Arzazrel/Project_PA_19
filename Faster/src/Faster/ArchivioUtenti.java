package Faster;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class ArchivioUtenti 
{
    //01
    private static String nomeDB;
    private static String pswDB;
    //02
    ArchivioUtenti (String nome, String psw)
    {
        nomeDB = nome;
        pswDB = psw;
    }
    //03
    public void aggiungi(Utente u)
    {
        //03.1
        if (utenteRegistrato(u))
        {
            modifica(u);
        }
        else
        {
            //03.2
            try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
                PreparedStatement ps = co.prepareStatement ("INSERT INTO utenti VALUES (?,?)");)
            {
                //03.3
                ps.setString(1,u.nickName);     ps.setFloat(2,u.peso);
                System.out.println("righe aggiunte: " + ps.executeUpdate());
            } catch (SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }
    }
    //04
    public void modifica(Utente u)
    {
        //04.1
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement ("Update utenti SET nickName = ?, peso = ? WHERE nickName = ?");)
        {
            //04.2
            ps.setString(1,u.nickName);     ps.setFloat(2,u.peso);      ps.setString(3,u.nickName);
            System.out.println("righe update: " + ps.executeUpdate());
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
    }
    //05
    public void togli(Utente u)
    {
        //05.1
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement ("DELETE FROM utenti WHERE nickName = ?");)
        {
            //05.2
            ps.setString(1,u.nickName);      ps.setFloat(2,u.peso);
            System.out.println("righe tolte: " + ps.executeUpdate());
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        
    }
    //06
    public boolean utenteRegistrato (Utente u)
    {
        //06.1
        Vector<String> v = new Vector<String>();
        //06.2
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement("SELECT nickName FROM utenti WHERE nickName = ?"))
        {
            //06.3
            ps.setString(1,u.nickName);
            ResultSet rs = ps.executeQuery();
            //06.4
            while (rs.next())
                v.add(rs.getString("nickName"));   
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        //06.5
        if (v.size()==0)
                return false;
            else
                return true;
    }
}
/* Lista commenti
    01: variabili di istanza statiche per la connessione al DB
    02: costrutore con parametri il nome e la psw del DB
    03: metodo che aggiunge l'utente passato come parametro o che richiama il 
        metodo modifica, richiama il metodo utenteRegistrato per sapere se aggiungere
        un nuovo utente o fare update di uno già esistente
    03.1:   if che serve per richiamare il metodo modifica o per aggiungere un 
            utente al DB
    03.2:   connessione al DB per inserimento del nuovo utente 
    03.3:   inserimento valori dell'utente da inserire
    04: metodo che fa l'update nel DB dell'utente passato come parametro
    04.1:   connessione al DB per update dell'utente
    04.2:   inserimento valori nela query
    05: metodo che toglie l'utente passato come parametro dal DB   
    05.1:   connessione al DB per delete dell'utente
    05.2:   inserimento valori nella query
    06: metodo che controlla se l'utente passato per parametro è già presente nel
        DB, in tal caso restituisce TRUE, se non è già presente restituisce FAlSE
    06.1:   creo un Vector di String che dovrà contenere il nickname del risultato 
            della query
    06.2:   connessione al DB per la query di prelievo degli utenti uguali a quello
            passato con parametro
    06.3:   inserimento valori nella query
    06.4:   while ch eserve ad inserire i risultati della query nel vector
    06.5:   if che controlla il contenuto del vector e restituisce TRUE o FALSE
            a seconda se il vector è pieno o vuoto
*/
