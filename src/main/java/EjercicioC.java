
import com.google.gson.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;


import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class EjercicioC {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("src\\car_sales.xml");
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            ContentHandler contentHandler = new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(xmlFile.getAbsolutePath());

            List<Map<String, String>> resultList = contentHandler.getResultList();

            try (FileWriter file = new FileWriter("src\\ejercicio_c.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(resultList, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ContentHandler extends DefaultHandler {
    private List<Map<String, String>> resultList = new ArrayList<>();
    private Map<String, String> currentSale = new HashMap<>();
    private StringBuilder characters = new StringBuilder();
    private String currentElement;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        characters.setLength(0);
        currentElement = localName;
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("sale".equals(localName)) {
            resultList.add(new HashMap<>(currentSale));
            currentSale.clear();
        } else if (currentElement != null && !currentElement.isEmpty()) {
            currentSale.put(currentElement, characters.toString().trim());
        }
        currentElement = null;
        characters.setLength(0);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        characters.append(ch, start, length);
    }

    public List<Map<String, String>> getResultList() {
        return resultList;
    }
}