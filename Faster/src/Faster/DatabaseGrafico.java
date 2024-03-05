package Faster;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class DatabaseGrafico 
{
    //01
    private static String nomeDB;
    private static String pswDB;
    //02
    public static void settaParamteriDB(String nome, String psw)
    {
        nomeDB = nome;
        pswDB = psw;
    }
    //03
    public static float calcolaVelocitaMediaSettimana(String nickName,Calendar data1, Calendar data2)
    {
        float mediaVelocita=0;
        Vector<Float> v=new Vector<Float>();
        try (Connection co = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+nomeDB,"root",pswDB);
            PreparedStatement ps = co.prepareStatement("SELECT velocita FROM sessioni WHERE nickName = ? AND data BETWEEN ? AND ?"))
        {
            //03.1
            java.util.Date appoggio1 = data1.getTime();
            java.util.Date appoggio2 = data2.getTime();
            ps.setString(1,nickName);     ps.setDate(2,new java.sql.Date(appoggio2.getTime()));   //03.2
            ps.setDate(3,new java.sql.Date(appoggio1.getTime()));       //03.2
            ResultSet rs = ps.executeQuery();
            int somma = 0;
            while (rs.next()) //03.3
                v.add(rs.getFloat("velocita"));
            for (int i=0;i<v.size();i++)        //03.4
                somma += v.get(i);
            if (v.size()==0) //03.5
            {
                mediaVelocita=0;    //03.6
            }
            else
            {
                mediaVelocita = somma/v.size();     //03.7
            }
                
        } catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return mediaVelocita;           //03.8
    }
}
/* Lista commenti
    01: variabile di istanza statiche per la connessione al DBMS
    02: metodo statico che serve a settare le variabili per la connessione al DBMS
    03: metodo che restituisce la media della velocità media fra tutte le sessioni
        di un determinato utente fra due date. le date e l'utente sono passati come
        parametro
    03.1:   conversione delle date per darle il giusto formato, creo date di appoggio
    03.2:   creazione di oggetti sql.date attraverso le date appoggio
    03.3:   while che aggiunge al vettore le velocità restituite dalla query
    03.4:   faccio la somma di tutte le velocità
    03.5:   controllo che la dimensione del vettore contenente i risultati della 
            query sia zero
    03.6:   dim vector è 0, quindi non ci sono risultati dalla query quindi la media
            è per forza zero, imposto il valore evitando di farlo aritmeticamente 
            per non causare errore dovuto a 0/0
    03.7:   calcolo la media aritmeticamente, somma diviso dimensione vector   
    03.8:   ritorno il valore della media
*/
