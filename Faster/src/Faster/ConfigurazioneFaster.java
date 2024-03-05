package Faster;
/**
 *
 * @author Alessandro Diana
 */
public class ConfigurazioneFaster 
{
    //01
    private String nomeApplicazione;
    private int righeTabella;
    private String nomeDefaultGrafico;
    private int valoreColonneGrafico;
    private String tipoFont;
    private int grandezzaFont;
    private String coloreDefaultBackground;
    private String indirizzoIPClient;
    private String indirizzoIPServerLog;
    private int portaServerLog;
    private String nomeDB;
    private String pswDB;
    //02
    ConfigurazioneFaster ()
    {
        nomeApplicazione = "Faster";
        righeTabella = 10;
        nomeDefaultGrafico = "NickName";
        valoreColonneGrafico = 0;
        tipoFont = "sans-serif";
        grandezzaFont = 18;
        coloreDefaultBackground = "#FFFFFF";
        indirizzoIPClient = "localhost";
        indirizzoIPServerLog = "localhost";
        portaServerLog = 9697;
        nomeDB = "db";
        pswDB = "root";
    }
    //03
    public String getNomeApplicazione()
    {
        return nomeApplicazione;
    }
    public int getRigheTabella()
    {
        return righeTabella;
    }
    public String getNomeDefaultGrafico()
    {
        return nomeDefaultGrafico;
    }
    public int getValoreColonneGrafico()
    {
        return valoreColonneGrafico;
    }
    public String getColoreDefaultBackground()
    {
        return coloreDefaultBackground;
    }
    public String getIndirizzoIPClient()
    {
        return indirizzoIPClient;
    }
    public String getIndirizzoIPServerLog()
    {
        return indirizzoIPServerLog;
    }
    public int getPortaServerLog()
    {
        return portaServerLog;
    }
    public String getNomeDB()
    {
        return nomeDB;
    }
    public String getPswDB()
    {
        return pswDB;
    }
    public String getTipoFont()
    {
        return tipoFont;
    }
    public int getGrandezzaFonte()
    {
        return grandezzaFont;
    }
}
/* Lista commenti
    01: variabili di istanza
    02: costruttore senza parametri
    03: metodi get per le variabili di istanza
*/
