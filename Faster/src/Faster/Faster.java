package Faster;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *
 * @author Alessandro Diana num. matricola 531488
 */
public class Faster extends Application 
{   //01
    //01.1
    private FasterNoGUI fng = new FasterNoGUI();
    private Grafico graficoUtente;
    private CacheRipristinoSessione cache = new CacheRipristinoSessione();
    private TabellaVisualeSessioniUtenti tabellaSessioni = new TabellaVisualeSessioniUtenti();
    //01.2
    private Button visualizza = new Button("Visualizza");
    private Button calcola = new Button("Calcola");
    private Button elimina = new Button("Elimina");
    private Button modifica = new Button("Modifica");
    private Button importa = new Button("Importa");
    private Button annulla = new Button("Annulla");
    private Button salva = new Button("Salva");
    private TextField[] campiInserimentoSessione;   //01.3    
    private TextField[] campiInserimento;   //01.4    
    private Label[] etichette;  //01.5
    //01.6
    final VBox vbox0 = new VBox();
    final VBox vbox1 = new VBox();
    final VBox vbox2 = new VBox();
    final VBox vbox3 = new VBox();
    final VBox vbox4 = new VBox();
    final HBox hbox0 = new HBox();
    final HBox hbox1 = new HBox();
    final HBox hbox2 = new HBox();
    final HBox hbox3 = new HBox();
    final HBox hbox4 = new HBox();
    //02
    public void settaCampiInserimento()
    {
        //02.1  
        campiInserimentoSessione = new TextField[8];
        campiInserimentoSessione[0] = new TextField("NickName");
        campiInserimentoSessione[1] = new TextField("Durata");
        campiInserimentoSessione[2] = new TextField("KM");
        campiInserimentoSessione[3] = new TextField("Altitudine");
        campiInserimentoSessione[4] = new TextField("Pendenza");
        campiInserimentoSessione[5] = new TextField("Provincia");
        campiInserimentoSessione[6] = new TextField("Velocità");
        campiInserimentoSessione[7] = new TextField("Data 'yyyy-mm-dd'");
        //02.2   
        campiInserimento = new TextField[5];
        campiInserimento[0] = new TextField("NickName");
        campiInserimento[1] = new TextField("Peso(kg)");
        campiInserimento[2] = new TextField("Calorie");
        campiInserimento[3] = new TextField("Grassi");
        campiInserimento[4] = new TextField("Nicname Corridore");
        //02.3
        for (int i=0;i<5;i++)
            campiInserimento[i].setPrefSize(80, 40);
        for (int j=0;j<8;j++)
            campiInserimentoSessione[j].setPrefSize(80, 40);
    }
    //03
    public void settaStileEtichette (String font, int dimFont)
    {   //03.1      
        etichette = new Label[4];
        etichette[0]=new Label("Dati Corridore");
        etichette[0].setFont(new Font(font,dimFont));
        etichette[0].setStyle("-fx-font-weight: bold");
        etichette[1]=new Label("Calorie e grassi consumati");
        etichette[1].setFont(new Font(font,dimFont));
        etichette[1].setStyle("-fx-font-weight: bold");
        etichette[2]=new Label("Ultime sessioni inserite");
        etichette[2].setFont(new Font(font,dimFont));
        etichette[2].setStyle("-fx-font-weight: bold");
        etichette[3]=new Label("Inserimento sessione");
        etichette[3].setStyle("-fx-font-weight: bold");
        etichette[3].setFont(new Font(font,dimFont));
        etichette[3].setTranslateY(-45);    //03.2  
    }
    //04
    public void resettaEtichette()
    {
        campiInserimento[0].setText("NickName");
        campiInserimento[1].setText("Peso(kg)");
        campiInserimento[2].setText("Calorie");
        campiInserimento[3].setText("Grassi");
        campiInserimentoSessione[0].setText("NickName");
        campiInserimentoSessione[1].setText("Durata");
        campiInserimentoSessione[2].setText("KM");
        campiInserimentoSessione[3].setText("Altitudine");
        campiInserimentoSessione[4].setText("Pendenza");
        campiInserimentoSessione[5].setText("Provincia");
        campiInserimentoSessione[6].setText("Velocità");
        campiInserimentoSessione[7].setText("Data 'yyyy-mm-dd'");
    }
    //05
    public void settaEtichette(Utente u, Sessione s)
    {
        if (u!=null)    //05.1    
        {
            campiInserimento[0].setText(u.nickName);
            campiInserimento[1].setText(Float.toString(u.peso));
        }
        if (s!=null)    //05.2  
        {
            campiInserimentoSessione[0].setText(s.nickName);
            campiInserimentoSessione[1].setText(s.durata);
            campiInserimentoSessione[2].setText(Float.toString(s.km));
            campiInserimentoSessione[3].setText(Integer.toString(s.altitudine));
            campiInserimentoSessione[4].setText(Float.toString(s.pendenza));
            campiInserimentoSessione[5].setText(s.provincia);
            campiInserimentoSessione[6].setText(Float.toString(s.velocità));
            campiInserimentoSessione[7].setText(s.data);
        }
    }
    //06
    public void settaBottoni ()
    {
        calcola.setPrefSize(220, 40);
        visualizza.setPrefSize(120, 40);
        importa.setPrefSize(80, 40);
        annulla.setPrefSize(80, 40);
        elimina.setPrefSize(80, 40);
        modifica.setPrefSize(80, 40);
        salva.setPrefSize(80, 40);
        campiInserimento[4].setPrefSize(270, 40);
    }
    //07
    @Override
    public void start(Stage primaryStage) 
    {
        Configurazione.validaClasseConfigurazioneFaster();      //07.1  
        ConfigurazioneFaster parametriDiConfigurazione = Configurazione.caricaConfigurazioneFasterXML();    //07.2
        fng.settaParametriDiConfigurazione(parametriDiConfigurazione);      //07.3
        fng.inviaEventoLog("Avvio");      //07.4
        settaCampiInserimento();        
        settaStileEtichette(parametriDiConfigurazione.getTipoFont(),parametriDiConfigurazione.getGrandezzaFonte());     //07.5
        inizializzaGrafico(parametriDiConfigurazione.getNomeDefaultGrafico(),parametriDiConfigurazione.getValoreColonneGrafico());      //07.6
        tabellaSessioni.aggiornaListaSessioniUtenti(DataBaseTabellaSessioniUtenti.caricaSessioniTuttiUtenti(parametriDiConfigurazione.getRigheTabella()));  //07.7
        //07.8  
        hbox3.getChildren().addAll(importa,annulla,salva);
        vbox4.getChildren().addAll(elimina,modifica);
        hbox2.getChildren().addAll(campiInserimentoSessione);
        hbox4.getChildren().addAll(campiInserimento[4],visualizza);
        vbox2.getChildren().addAll(etichette[0],campiInserimento[0],campiInserimento[1],etichette[1],campiInserimento[2],campiInserimento[3],calcola);
        vbox3.getChildren().addAll(etichette[2],tabellaSessioni,hbox2,hbox3);
        vbox1.getChildren().addAll(hbox4,graficoUtente);
        hbox0.getChildren().addAll(vbox2, vbox1);
        hbox1.getChildren().addAll(etichette[3], vbox3, vbox4);
        vbox0.getChildren().addAll(hbox0,hbox1);
        //07.9  
        vbox0.setAlignment(Pos.CENTER);
        hbox0.setAlignment(Pos.CENTER_RIGHT);
        hbox0.setTranslateX(-120);
        hbox1.setAlignment(Pos.BOTTOM_LEFT);
        vbox4.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.CENTER_LEFT);
        vbox2.setTranslateX(-60);
        vbox3.setMaxSize(640, 400);
        hbox1.setMaxSize(900, 300);
        vbox1.setMaxSize(450, 400);
        vbox2.setMaxSize(450, 300);
        vbox4.setMaxWidth(80);
        hbox3.setAlignment(Pos.CENTER_RIGHT);
        settaBottoni();
        //07.10  
        salva.setOnAction((ActionEvent ev)->{   salva();
                                                fng.inviaEventoLog("Salva");});
        visualizza.setOnAction((ActionEvent ev)->{  aggiornaGrafico();
                                                    fng.inviaEventoLog("Visualizza");});
        calcola.setOnAction((ActionEvent ev)->{ calcolaCalorieGrassi();
                                                fng.inviaEventoLog("Calcola");});
        annulla.setOnAction((ActionEvent ev) ->{    resettaEtichette();
                                                    fng.inviaEventoLog("Annulla");});
        modifica.setOnAction((ActionEvent ev) ->{   modificaEliminaRigaTabella(true);
                                                    fng.inviaEventoLog("Modifica");});
        elimina.setOnAction((ActionEvent ev) ->{    modificaEliminaRigaTabella(false);
                                                    fng.inviaEventoLog("Elimina");});
        importa.setOnAction((ActionEvent ev) ->{    leggiDispositivoMobile();
                                                    fng.inviaEventoLog("Importa");});
        Scene scene = new Scene(vbox0, 1000, 600);      //07.11 
        vbox0.setBackground(new Background(new BackgroundFill(Paint.valueOf(parametriDiConfigurazione.getColoreDefaultBackground()),CornerRadii.EMPTY, Insets.EMPTY))); //07.12
        primaryStage.setOnCloseRequest((WindowEvent) -> {   fng.inviaEventoLog("Termine");      //07.13 
                                                            salvataggioInCache(verificaBisognoSalvataggioInCache()); });//07.14
        primaryStage.setTitle("Faster");
        primaryStage.setScene(scene);
        primaryStage.show();
        recuperoDaCache();      //07.15     
    }
    //08
    public void salva ()
    {
        Sessione s = new Sessione();
        Utente u = new Utente(campiInserimento[0].getText(),Float.parseFloat(campiInserimento[1].getText()));   //08.1
        //08.2
        s.nickName = campiInserimentoSessione[0].getText();
        s.durata = campiInserimentoSessione[1].getText();
        s.km = Float.parseFloat(campiInserimentoSessione[2].getText());
        s.altitudine = Integer.parseInt(campiInserimentoSessione[3].getText());
        s.pendenza = Float.parseFloat(campiInserimentoSessione[4].getText());
        s.provincia = campiInserimentoSessione[5].getText();
        s.velocità = s.calcolaVelocità(s.durata ,s.km );
        campiInserimentoSessione[6].setText(Float.toString(s.velocità));
        s.data = campiInserimentoSessione[7].getText();
        fng.salvaSessione(s);   //08.3
        fng.salvaUtente(u);     //08.4
        resettaEtichette();     //08.5
        tabellaSessioni.aggiornaListaSessioniUtenti(DataBaseTabellaSessioniUtenti.caricaSessioniUtente(s.nickName));    //08.6
    }
    //09    
    public void calcolaCalorieGrassi()
    {
        SessioniBean sb = tabellaSessioni.getSelectionModel().getSelectedItem();    //09.1
        if (sb == null)     //09.2
        {
            Sessione s = new Sessione (campiInserimentoSessione[0].getText(),campiInserimentoSessione[1].getText(),Float.parseFloat(campiInserimentoSessione[2].getText()),
                                       Integer.parseInt(campiInserimentoSessione[3].getText()),Float.parseFloat(campiInserimentoSessione[4].getText()),campiInserimentoSessione[5].getText(),
                                       0,campiInserimentoSessione[7].getText());    
            s.velocità = s.calcolaVelocità(s.durata ,s.km );
            Utente u = new Utente (campiInserimento[0].getText(),Float.parseFloat(campiInserimento[1].getText()));      
            fng.setUtente(u);       //9.3
            fng.setSessione(s);     //9.4
            campiInserimento[2].setText(String.valueOf(fng.calcolaCalorieBruciate()));  //09.5
            campiInserimento[3].setText(String.valueOf(fng.calcolaGrassiBruciati()));   //09.6
        }
        else
        {
            if (campiInserimento[0].getText().equals(sb.getNickName()))     //09.7
            {
                Sessione s = new Sessione (sb.getNickName(),sb.getDurata(),sb.getKm(),sb.getAltitudine(),sb.getPendenza(),sb.getProvincia(),sb.getVelocità(),sb.getData()); //09.8
                Utente u = new Utente (campiInserimento[0].getText(),Float.parseFloat(campiInserimento[1].getText()));  //09.9
                fng.setSessione(s);     //9.3
                fng.setUtente(u);       //9.4
                campiInserimento[2].setText(String.valueOf(fng.calcolaCalorieBruciate()));  //09.5
                campiInserimento[3].setText(String.valueOf(fng.calcolaGrassiBruciati()));   //09.6
            }
            else
            {
                System.err.println("l'utente inserito è diverso dall'utente della sessione selezionata.");
            }
        }
    }
    //10
    public void inizializzaGrafico (String nome, int valore)
    {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        graficoUtente = new Grafico(xAxis,yAxis,nome,valore);
    }
    //11
    public void aggiornaGrafico ()
    {
        graficoUtente.estrapolaDati(campiInserimento[4].getText());     //11.1
        tabellaSessioni.aggiornaListaSessioniUtenti(DataBaseTabellaSessioniUtenti.caricaSessioniUtente(campiInserimento[4].getText())); //11.2
    }
    //12
    public boolean verificaBisognoSalvataggioInCache()
    {
        if ( "NickName".equals(campiInserimento[0].getText()) && "Peso(kg)".equals(campiInserimento[1].getText()) &&
             "NickName".equals(campiInserimentoSessione[0].getText()) && "Durata".equals(campiInserimentoSessione[1].getText()) &&
             "KM".equals(campiInserimentoSessione[2].getText()) && "Altitudine".equals(campiInserimentoSessione[3].getText()) &&
             "Pendenza".equals(campiInserimentoSessione[4].getText()) && "Provincia".equals(campiInserimentoSessione[5].getText()) &&
             "Velocità".equals(campiInserimentoSessione[6].getText()) && "Data 'yyyy-mm-dd'".equals(campiInserimentoSessione[7].getText()))
        {
            return false;       //12.1
        }
        else
        {
            return true;        //12.2
        }
    }
    //13
    public void salvataggioInCache (boolean salvataggio)
    {
        if (salvataggio) //13.1 
        {
            Sessione s = new Sessione();
            Utente u = new Utente();
            u.nickName = campiInserimento[0].getText();
            if (!"Peso(kg)".equals(campiInserimento[1].getText()))
                u.peso = (Float.parseFloat(campiInserimento[1].getText()));
            s.nickName = campiInserimentoSessione[0].getText();
            s.durata = campiInserimentoSessione[1].getText();
            if(!"KM".equals(campiInserimentoSessione[2].getText()))
                s.km = Float.parseFloat(campiInserimentoSessione[2].getText());
            else
                s.km = 0;       //13.2
            if(!"Altitudine".equals(campiInserimentoSessione[3].getText()))
                s.altitudine = Integer.parseInt(campiInserimentoSessione[3].getText());
            else
                s.altitudine = -1000;       //13.2
            if(!"Pendenza".equals(campiInserimentoSessione[4].getText()))
                s.pendenza = Float.parseFloat(campiInserimentoSessione[4].getText());
            else
                s.pendenza = -1000;     //13.2
            s.provincia = campiInserimentoSessione[5].getText();
            s.velocità = 0;     //13.2
            s.data = campiInserimentoSessione[7].getText();
            cache.salvaSessioneParzialeBin(s);      //13.3
            cache.salvaUtenteParzialeBin(u);        //13.4
        }else //13.5    
        {
            cache.salvaSessioneParzialeBin(null);   //13.3
            cache.salvaUtenteParzialeBin(null);     //13.4
        }
    }
    //14
    public void recuperoDaCache()
    {
        Sessione s = cache.recuperaSessioneParzialeBin();
        Utente u = cache.recuperaUtenteParzialeBin();
        if (s == null && u == null)     //14.1
            System.out.println("non ho recuperato nulla dalla cache di ripristino");
        else
        {   //14.2
            System.out.println("ho recuperato dati dalla cache di ripristino");
            campiInserimento[0].setText(u.nickName);
            if (u.peso != 0)
                campiInserimento[1].setText(Float.toString(u.peso));
            else
                campiInserimento[1].setText("Peso(kg)");
            campiInserimentoSessione[0].setText(s.nickName);
            campiInserimentoSessione[1].setText(s.durata);
            if (s.km != 0)
                campiInserimentoSessione[2].setText(Float.toString(s.km));
            else
                campiInserimentoSessione[2].setText("KM");
            if (s.altitudine != -1000)
                campiInserimentoSessione[3].setText(Integer.toString(s.altitudine));
            else
                campiInserimentoSessione[3].setText("Altitudine");
            if (s.pendenza != -1000)
                campiInserimentoSessione[4].setText(Float.toString(s.pendenza));
            else
                campiInserimentoSessione[4].setText("Pendenza");
            campiInserimentoSessione[5].setText(s.provincia);
            if (s.velocità != 0)
                campiInserimentoSessione[6].setText(Float.toString(s.velocità));
            else
                campiInserimentoSessione[6].setText("Velocità");
            campiInserimentoSessione[7].setText(s.data);
        }
    }
    //15
    public void modificaEliminaRigaTabella(boolean modifica)
    {
        SessioniBean sb = tabellaSessioni.getSelectionModel().getSelectedItem();    //15.1  
        if (sb == null)     //15.2
        {
            System.err.println("Non è selezionata nessuna riga della tabella non posso eseguire l'operazione richiesta.");
        }
        else
        {   
            if (campiInserimento[0].getText().equals(sb.getNickName())) //15.3  
            {
                Sessione s = new Sessione (sb.getNickName(),sb.getDurata(),sb.getKm(),sb.getAltitudine(),sb.getPendenza(),sb.getProvincia(),sb.getVelocità(),sb.getData()); //15.4
                if (modifica)       //15.5  
                {
                    settaEtichette(null, s);        //15.6
                }
                fng.eliminaSessione(s);     //15.7
                tabellaSessioni.aggiornaListaSessioniUtenti(DataBaseTabellaSessioniUtenti.caricaSessioniUtente(s.nickName));    //15.8
            }
        } 
    }
    //16
    public void leggiDispositivoMobile()
    {
        DispositivoMobile db = new DispositivoMobile();
        db = fng.leggiDispositivoMobile();      //16.1
        settaEtichette(db.personaMonitorata,db.sessioneMonitorata);     //16.2
        aggiornaGrafico();
    }
    //17
    public static void main(String[] args) 
    {
        launch(args);
    }
}
/* Lista commenti
    01: variabili di istanza
    01.1:   variabile classe FasterNoGUI,dichiarazione del grafico,classe per cache salvataggi
    01.2:   dichiarazione e inizializzazione dei bottoni dell'applicazione
    01.3:   dichiarazione campi di inserimento per la nuova sessione situati sotto la tabella
    01.4:   dichiarazione dei restanti campi di inserimento dell'applicazione
    01.5:   dichiarazione di un array contenente l'etichette dell'applicazione
    01.6:   dichiarazione e inizializzazione delle hbox e vbox per il posizionamento del layout 
            grafico
    02: metodo che setta i valori che dovranno apparire all'inizio nei vari campi di inserimento
        e anche le loro dimensioni.
    02.1:   inizializza i campi di inserimento per l'inserimento della nuova sessione
    02.2:   inizializza gli altri campi di inserimento dell'applicazione
    02.3:   setta le dimensioni predifinite dei campi di inserimento
    03: metodo che setta la dimensione del font e il tipo di font delle etichette
    03.1:   inizializzazione array etichette,settaggio messaggi, dimensione del font e font 
            dell'etichette
    03.2:   sposto l'etichetta in alto di 45px per allineamento
    04: metodo che risetta i valori di default dei camoi di inserimento
    05: metodo che serve a settare i campi di inserimento con valori passati come parametro, 
        sia relativi all'utente che relativi alla sessione.  
    05.1:   controllo se utente passato è null,se lo è non voglio settare i textField relativi
            all'utente
    05.2:   controllo se sessione passata è null, se lo è non voglio settare i textField relative 
            alla sessione
    06: metodo che setta le dimensioni dei bottoni
    07: metodo start
    07.1:   valido file xml di configurazione in locale con file xsd
    07.2:   creo un istanza di ConfigurazioneFaster a partire dal file di configurazione in locale
    07.3:   richiamo il metodo per configurare dell'istanza di FasterNoGUI
    07.4:   invio l'eventoLog di apertura dell'applicazione
    07.5:   richiamo il metodo per settare le etichette e gli passo i valori di configurazione
    07.6:   setto il grafico con i valori di default passati come parametro
    07.7:   popolo la tabella con sessioni di tutti utenti, passandogli come parametro il numero
            di righe da visualizzare, valore preso dall'istanza di configurazioneFaster
    07.8:   incapsulamento e assegnazione degli oggetti dentro vbox e hbox
    07.9:   settaggio layout come dimensioni vbox e hbox
    07.10:  azioni dei bottoni
    07.11:  setta scena
    07.12:  setto il colore di background della vbox xhe contiene tutti gli altri elementi grafici
            con il colore preso dall'istanza di configurazioneFaster
    07.13:  alla chiusura invia l'evento di log
    07.14:  salva su file binario il valore dei campi d'inserimento se deiversi dai valori di default
    07.15:  richiamo il metodo che opera il recupero dei valori dei campi di inserimento da file binario
    08: metodo che preleva il contenuto dei campi di inserimento, crea delle istanze di Sessione e 
        Utente settandole con questi valori presi e poi attraverso fng li salva nel DBMS. dopo resetta
        i campi di inserimento con i valori di default e aggiorna il contenuto della tabella
    08.1:   preleva i valori relativi all'utente e li usa per creare una nuova istanza
    08.2:   preleva i valori relativi alla sessione e li usa per creare una nuova istanza
    08.3:   richiama il metodo per salvare la sessione
    08.4:   richiama il metodo per salvare l'utente
    08.5:   resetta i campi di inserimento con i valori di default
    08.6:   aggiorna il contenuto della tabella, passa il nickName dell'utente che ha appena salvato
            la sua sessione
    09: metodo calcola i valori delle calorie bruciate e dei grassi
    09.1:   preleva l'item del1a riga selezionata nella tabella
    09.2:   controllo, se l'item restituito dal metodo è null vuol dire che non c'era nessuna linea
            selezionata
    09.3:   passa la sessione appena creata con i valori presi dai campi di inserimento a fng 
    09.4:   passa l'utente appena creato con i valori presi dai campi di inserimento a fng
    09.5:   richiama il metodo per calcolare le calorie bruciate
    09.6:   richiama il metodo per calcolare i grassi consumati
    09.7:   controllo per vedere se il nickName della riga della tabella selezionata sia uguale al 
            nickName dell'utente
    09.8:   crea un istanza di Sessione con i parametri della riga selezionata
    09.9:   crea un istanza di Utente con i parametri dei campi di inserimento
    10: metodo che inizializza il grafico con i valori di configurazione di default
    11: metodo che richiama l'aggiornamento del grafico, il prelievo dei nuovi valori da DBMS usando
        il nickName dell'utente. dopo richiama anchel'aggiornamento del contenuto della tabella
    11.1:   richiama metodo aggiorna grafico, il parametro viene preso dal campo di inserimento del 
            nickName
    11.2:   richiama il metodo per cambiare il contenuto della tabella, solo sessioni di uno specifico
            utente, il suo nickName è passato come parametro e viene preso dal campo di inserimento
    12: metodo che dice se c'è bisogno di salvare in cache il contenuto dei campi di inserimento,
        controlla che il contenuto di tutti i campi sia diverso da quello di default. se almeno uno
        è diverso restituisce TRUE, se sono tutti uguali resitutisce FALSE.
    12.1:   restituisce FALSE non c'è bisogno di salvare in cache, verranno passati null, che indica
            che bisogna salvare i valori di default
    12.2:   restituisce TRUE, bisogna salvare in cache il contenuto dei campi di inserimento
    13: metodo per salvare il contenuto dei campi di inserimento in cache in caso di chiusura non 
        desiderata. Interessa salvare solo quello che è stato cambiato, il resto no, quindi metto un 
        controllo, se valore del campo diveso da quello iniziale(di default) lo salvo altrimenti no.
        I valori che non sono stringa posso prenderli direttamente senza controlli, per gli altri devo
        farli, perchè nel caso non fossero stati modificati e li salvassi andrei ad assegnare valori 
        stringa in altri tipi come interi o float generando errori. I controlli servono ad evitare 
        questo errore, se campi il cui tipo è diverso da stringa non sono stati modificati gli verranno
        applicati valori di defalut.
    13.1:   controllo che si avvale del metodo precedente, se vero vuol dire che c'è bisogno della cache
    13.2:   applicazione dei valori di default nel caso in cui nei campi di inserimento non sia stato 
            messo niente e non possa essere salvato un tipo stringa nella variabile
    13.3:   richiamo il metodo per salvare la sessione nel file locale, gli passo come parametro un'istanza
            con vaolori o di default o messi da utente
    13.4:   richiamo il metodo per salvare l'utente nel file locale, gli passo come parametro un'istanza
            con valori o di default o messi da utente
    13.5:   falso non c'è bisogno del salvataggio in cache, passerò istanze null che faranno salvare in
            locale i valori di default " "
    14: metodo che richiama i metodi per recuperare il contenuto da cache e in base a quello ricevuto 
        decide se settare i valori dei campi di inserimento o lasciarli inalterati
    14.1:   controllo che i valori recuperati da cache non siano quelli di default che indicano che     
            l'ultima volta che è stata chiusa applicazione non c'era bisogno di salvare niente in cache
            quindi non dovrò risettare nessun campo di inserimento
    14.2:   in cache avevo qualcosa, devo risettare i campi di inserimento, prima di farlo eseguo gli
            opportuni controlli per le variabili che non sono tipo string, se esse hanno il valore di 
            default messo durante il salvataggio metterò nel campo di inserimento la stringa di default,
            se invece hanno un valore diverso da quello di default metterò nel campo di inserimento tale
            valore
    15:  metodo per modificare o eliminare una riga della tabella. la scelta fra le due opzioni è data da
         una variabile boolean passata come parametro, se true: modifica riga,se false: elimina riga
    15.1:   preleva l'item del1a riga selezionata nella tabella
    15.2:   controllo che sia stata selezionata o meno una riga della tabella e in esito negativo stampo
            una riga di errore
    15.3:   controllo nickName in dati corridore con quello della riga selezionata, devono essere uguali 
            per poter fare le operazioni di modifica ed eliminazione
    15.4:   creo una nuova istanza di Sessione passandogli come parametro del costruttore tutti i dati 
            dell'item della rig della tabella selezionata
    15.5:   controllo se devo modificare o solo cancellare la riga
    15.6:   richiamo il metodo per settare i campi di inserimento passandogli l'istanza di sessione appena 
            fatta
    15.7:   richiamo il metodo per eliminare dal DBMS la sessione passatagli come parametro
    15.8:   aggiorno la tabella, visualizzerò solo le sessioni dello stesso utente della sessione che ho 
            eliminato o che sto modificando
    16: metodo che legge i dati relativi all'utente e alla sessione presenti nel dispositivo mobile
    16.1:   recupera i dati presenti in locale del dispositivo mobile, si aper utente che per la sessione
    16.2:   richiama il metodo per settare i campi di inserimento relativi all'utente e alla sessione
            passandogli come parametri quelli appena presi dal dispositivo mobile
    17: metodo main che lancia il metodo launch
*/