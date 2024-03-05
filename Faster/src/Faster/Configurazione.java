package Faster;
import org.xml.sax.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import javax.xml.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import javax.xml.validation.*;
/**
 *
 * @author Alessandro Diana numero matricola 531488
 */
public class Configurazione 
{
    //01
    public static ConfigurazioneFaster caricaConfigurazioneFasterXML()
    {
        return (ConfigurazioneFaster) (new XStream(new DomDriver())).fromXML(new File("./myFiles/configurazioneFaster.xml"));
    }
    //02
    public static void validaClasseConfigurazioneFaster()
    {
        try
        {
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            org.w3c.dom.Document documentoConfigurazione = db.parse(new File("./myFiles/configurazioneFaster.xml"));    //02.1
            Schema schemaConfigurazione = sf.newSchema(new StreamSource(new File("./myFiles/configurazioneFaster.xsd"))); //02.2
            schemaConfigurazione.newValidator().validate(new DOMSource(documentoConfigurazione));     //02.3
        }
        catch (ParserConfigurationException | SAXException | IOException ex)
        {
             if (ex instanceof SAXException)  
                 System.out.println("Errore di validazione: " + ex.getMessage());  //02.4
             else
                 System.out.println(ex.getMessage()); 
        }
    }
}
/* Lista commenti
    01: metodo che crea un oggetto ConfigurazioneFaster usando i valori contenuti nel file 
        di configurazione xml in locale
    02: metodo che valida il file di configurazione in locale xml con un file salvato in 
        locale xsd
    02.1:   creo un documento xml dal file in locale xml
    02.2:   creo uno schema xsd dal file in locale xsd
    02.3:   valido il documento xml con lo schema xsd
    02.4:   in caso di errore dovuto a validazione sbagliata restituisco il messaggio di
            di errore preciso di cosa non è conforme al documento xsd
*/