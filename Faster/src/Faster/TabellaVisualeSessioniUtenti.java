package Faster;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class TabellaVisualeSessioniUtenti extends TableView<SessioniBean> 
{
    private final ObservableList<SessioniBean> listaSessioniUtenti;     //01
    //02
    TabellaVisualeSessioniUtenti()
    {   
        //02.1
        TableColumn nickCol = new TableColumn("NickName");
        nickCol.setCellValueFactory(new PropertyValueFactory<>("nickName"));
        TableColumn durataCol = new TableColumn("Durata");
        durataCol.setCellValueFactory(new PropertyValueFactory<>("durata"));
        TableColumn kmCol = new TableColumn("KM");
        kmCol.setCellValueFactory(new PropertyValueFactory<>("km"));
        TableColumn altitudineCol = new TableColumn("Altitudine");
        altitudineCol.setCellValueFactory(new PropertyValueFactory<>("altitudine"));
        TableColumn pendenzaCol = new TableColumn("Pendenza");
        pendenzaCol.setCellValueFactory(new PropertyValueFactory<>("pendenza"));
        TableColumn provinciaCol = new TableColumn("Provincia");
        provinciaCol.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        TableColumn velocitaCol = new TableColumn("Velocità");
        velocitaCol.setCellValueFactory(new PropertyValueFactory<>("velocità"));
        TableColumn dataCol = new TableColumn("Data");
        dataCol.setCellValueFactory(new PropertyValueFactory<>("data"));
        this.setMaxSize(640, 200);      //02.2
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);    //02.3
        listaSessioniUtenti = FXCollections.observableArrayList();
        setItems(listaSessioniUtenti); //02.4
        getColumns().addAll(nickCol, durataCol, kmCol, altitudineCol, pendenzaCol, provinciaCol, velocitaCol, dataCol); //02.5
    }
    //03
    public void aggiornaListaSessioniUtenti(List<SessioniBean> sessioni)
    {
        listaSessioniUtenti.clear();        //03.1
        listaSessioniUtenti.addAll(sessioni);       //03.2
    }
}
/* Lista commenti
    01: variabile di istanza, ObservableList di sessioniBean che conterrà i dati
        delle righe della tabella
    02: costruttore senza parametri che serve a settare la tabella come struttura 
        e graficamente
    02.1:   creo una colonna per ogni variabile di istanza della classe SessioniBean 
    02.2:   setto massima dimensione della tabella
    02.3:   ridimensiona tutte le colonne automaticamente sino a quando la larghezza 
            massima della tabella non è raggiunta
    02.4:   setto nella tabella i valori dell'osservable list 
    02.5:   aggiungo tutte le colonne create prima alla tabella 
    03: metodo che aggiorna il contenuto della tabella
    03.1:   svuoto l'observableList dai valori attuali
    03.2:   aggiungo all'observableList tutti i valori della lisa passata come parametro
*/