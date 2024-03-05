package Faster;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class DataBaseTabellaSessioniUtenti 
{
    //01
    private static String nomeDB;
    private static String pswDB;
    //02
    public static void settaParametriDB (String nome, String psw)
    {
        nomeDB = nome;
        pswDB = psw;
    }
    //03
    public static List<SessioniBean> caricaSessioniTuttiUtenti (int numeroRighe)
    {
        int contatore = 0;      //03.1
        List<SessioniBean> listaSessioniTuttiUtenti = new ArrayList<>();
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement("SELECT nickName,durata,km,altitudine,pendenza,provincia,velocita,data FROM sessioni ORDER BY data DESC"))
        {
            ResultSet rs = ps.executeQuery();
            while (rs.next()&&contatore<numeroRighe) //03.2
            {       //03.3
                  listaSessioniTuttiUtenti.add(new SessioniBean (rs.getString("nickName"),rs.getString("durata"),rs.getFloat("km"),rs.getInt("altitudine"),rs.getFloat("pendenza"),rs.getString("provincia"),rs.getFloat("velocita"),rs.getString("data")));
                  contatore++;              //03.4
            }
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return listaSessioniTuttiUtenti;        //03.5
    }
    //04
    public static List<SessioniBean> caricaSessioniUtente (String utente)
    {
        List<SessioniBean> listaSessioniUtente = new ArrayList<>();
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement("SELECT nickName,durata,km,altitudine,pendenza,provincia,velocita,data FROM sessioni WHERE nickName = ? ORDER BY data DESC"))
        {
            ps.setString(1,utente);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) //04.1
                  listaSessioniUtente.add(new SessioniBean(rs.getString("nickName"),rs.getString("durata"),rs.getFloat("km"),rs.getInt("altitudine"),rs.getFloat("pendenza"),rs.getString("provincia"),rs.getFloat("velocita"),rs.getString("data")));  //04.2
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return listaSessioniUtente;  //04.3
    }
}
/* Lista commenti
    01: variabile di istanza statiche per la connessione al DBMS
    02: metodo statico che serve a settare le variabili per la connessione al DBMS
    03: metodo che recupera dal DBMS le utime N sessioni inserite, le mette in una 
        lista che ritorna. N è passato come parametro
    03.1:   variabile contatore per tenere il numero delle sessioni prelevate dal DBMS
    03.2:   while che continua a prelevare sessioni fino a che il result set non 
            è vuoto o non si è arrivati al numero di sessioni massime volute, N.
    03.3:   aggiungo la sessione appena prelevata nella lista
    03.4:   incremento la variabile contatore
    03.5:   ritorno la lista dei risultati
    04: metodo che recupera dal DBMS tutte le sessioni inserite di un determinato
        utente passato come parametro, le sessioni sono ordinate secondo la data
    04.1:   while che continua fino a che il result set non è vuoto
    04.2:   aggiungo la sessione appena prelevata alla lista
    04.3:   ritorno la lista dei risultati
 */