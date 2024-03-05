package Faster;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class SessioniBean 
{
    //01
    private final SimpleStringProperty nickName;
    private final SimpleStringProperty durata;
    private final SimpleFloatProperty km;
    private final SimpleIntegerProperty altitudine;
    private final SimpleFloatProperty pendenza;
    private final SimpleStringProperty provincia;
    private final SimpleFloatProperty velocità;
    private final SimpleStringProperty data;
    //02 
    SessioniBean (String nickName, String durata, float km, int altitudine, float pendenza, String provincia, float velocità, String data)
    {
        this.nickName = new SimpleStringProperty(nickName);
        this.durata = new SimpleStringProperty(durata);
        this.km = new SimpleFloatProperty(km);
        this.altitudine = new SimpleIntegerProperty(altitudine);
        this.pendenza = new SimpleFloatProperty(pendenza);
        this.provincia = new SimpleStringProperty(provincia);
        this.velocità = new SimpleFloatProperty(velocità);
        this.data = new SimpleStringProperty(data);
    }
    //03
    public String getNickName ()
    {
        return nickName.get();
    }
    public String getDurata ()
    {
        return durata.get();
    }
    public float getKm ()
    {
        return km.get();
    }
    public int getAltitudine()
    {
        return altitudine.get();
    }
    public float getPendenza ()
    {
        return pendenza.get();
    }
    public String getProvincia ()
    {
        return provincia.get();
    }
    public float getVelocità ()
    {
        return velocità.get();
    }
    public String getData ()
    {
        return data.get();
    }
}
/* Lista commenti
    01: variabili di istanza, i loro nomi e il tipo di SimpleProperty sono congruenti
        con le rispettive variabili di istanza nella classe Sessione
    02: costrutore con parametri che setta tute le variabili di istanza dell'oggetto
    03: serie di metodi get per ciascuna variabili di istanza
*/
