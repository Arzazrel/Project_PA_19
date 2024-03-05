package Faster;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import java.util.Calendar;
import javafx.scene.chart.BarChart;
import javafx.scene.layout.Background;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class Grafico extends BarChart<String,Number>
{
    //01
    private XYChart.Series<String, Number> settimana1 = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> settimana2 = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> settimana3 = new XYChart.Series<String, Number>();
    private XYChart.Series<String, Number> settimana4 = new XYChart.Series<String, Number>();  
    //02
    Grafico (CategoryAxis x,NumberAxis y, String nome, int valore)
    {
        super(x,y);     //02.1
        y.setLabel("velocità media");
        this.setMaxHeight(250);     //02.2
        this.setMaxWidth(400);
        this.setTitle(nome);
        this.setLegendVisible(false);
        this.setBackground(Background.EMPTY);
        this.setCategoryGap(10);
        this.setBarGap(0);
        settimana1.getData().add(new XYChart.Data<String, Number>("settimana1",valore));    //02.3
        settimana2.getData().add(new XYChart.Data<String, Number>("settimana2",valore));
        settimana3.getData().add(new XYChart.Data<String, Number>("settimana3",valore));
        settimana4.getData().add(new XYChart.Data<String, Number>("settimana4",valore));
        this.getData().addAll(settimana1,settimana2,settimana3,settimana4);             //02.4
    }
    //03
    public void estrapolaDati (String n)
    {
        settimana1 = new XYChart.Series<String, Number>();      //03.1
        settimana2 = new XYChart.Series<String, Number>();
        settimana3 = new XYChart.Series<String, Number>();
        settimana4 = new XYChart.Series<String, Number>();
        getData().clear();      //03.2
        this.setTitle(n);       //03.3
        java.util.Date d = new java.util.Date();        //03.4
        Calendar dataSet0 = Calendar.getInstance();
        Calendar dataSet1 = Calendar.getInstance();
        dataSet0.setTime(d);
        dataSet1.setTime(d);
        dataSet0.add(Calendar.DATE, 0);     //03.5
        dataSet1.add(Calendar.DATE, -7);
        settimana1.getData().add(new XYChart.Data<String, Number>("settimana1",DatabaseGrafico.calcolaVelocitaMediaSettimana(n, dataSet0, dataSet1)));  //03.6
        Calendar dataSet2 = Calendar.getInstance();     //03.7
        dataSet2.setTime(d);
        dataSet2.add(Calendar.DATE, -14);
        settimana2.getData().add(new XYChart.Data<String, Number>("settimana2",DatabaseGrafico.calcolaVelocitaMediaSettimana(n, dataSet1, dataSet2)));  //03.6
        Calendar dataSet3 = Calendar.getInstance();     //03.8
        dataSet3.setTime(d);
        dataSet3.add(Calendar.DATE, -21);
        settimana3.getData().add(new XYChart.Data<String, Number>("settimana3",DatabaseGrafico.calcolaVelocitaMediaSettimana(n, dataSet2, dataSet3)));  //03.6
        Calendar dataSet4 = Calendar.getInstance();     //03.9
        dataSet4.setTime(d);
        dataSet4.add(Calendar.DATE, -28);
        settimana4.getData().add(new XYChart.Data<String, Number>("settimana4",DatabaseGrafico.calcolaVelocitaMediaSettimana(n, dataSet3, dataSet4)));  //03.6
        this.getData().addAll(settimana1,settimana2,settimana3,settimana4);     //02.4
    }
    
    
}
/* Lista commenti
    01: variabili di istanza che andranno a prendere i valori delle colonne del grafico
    02: costruttore con parametri
    02.1:   richiamo al costruttore della classe padre, a cui passo CategoryAxis e
            NumberAxis
    02.2:   setto valori per la grandezza della finestra del grafico, per il titolo
            (che è passato per parametro), la legenda, il background e distanze delle
            barre(colonne del grafico).
    02.3:   setto le colonne del grafico con parametri di default, valore numerico
            passato come parametro
    02.4:   aggiungo al grafico le colonne 
    03: metodo che calcola la velocità media delle ultime 4 settimane delle sessioni
        presenti nel DBMS di un determinato utente, il cui nickName passato come 
        parametro. estrapola i dati interfacciandosi al DBMS tramite la classe 
        DatabaseGrafico
    03.1:   associo le variabili di istanza (che sarebbero le colonne del grafo) a 
            dei nuovi oggetti XYChart.Series
    03.2:   ripulisco il grafico da vecchi dati
    03.3:   cambio il titolo del grafico e lo metto uguale al nickName dell'utente 
            di cui sto riportando i dati
    03.4:   creo due oggetti Calendar e uno date per impostarlo.  
    03.5:   calcolo il valore degli oggeti Calendar per la prima settimana
    03.6:   ottengo i dati che mi servono tramite la classe DtabaseGrafico, passo
            come parametri nickName che mi interessa e le due date Calendar che    
            delimiteranno il periodo di ricerca per questa colonna. inserisco i 
            dati nella colonna
    03.7:   calcola i valori delle date per la seconda settimanda del grafico
    03.8:   calcola i valori delle date per la terza settimanda del grafico
    03.9;   calcola i valori delle date per la quarta settimanda del grafico
*/