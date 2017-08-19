package settings.owasp_configs;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import settings.ConfigXMLFileCreator;
import settings.ConfigXMLFileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;

public class OWASPProactiveConfig {

    String parentTag = "owasp-proactives";
    String proactiveTag = "proactive";
    String idTag = "id";
    String nameTag = "name";
    String descriptionTag = "description";

    String fileName = "OWASP_Proactives.xml";

    public OWASPProactiveConfig() {}

    public void createConfigFile(ArrayList<String[]> OWASPProactives) throws ParserConfigurationException, TransformerException {

        ConfigXMLFileCreator configXMLFileCreator = new ConfigXMLFileCreator();
        configXMLFileCreator.createFile();

        configXMLFileCreator.createParentElement(parentTag);

        /* create proactive tags */
        for (String[] OWASPProactive : OWASPProactives) {

            /* create proactive tag */
            Element proactiveElement = configXMLFileCreator.createChildElement(proactiveTag);

            Element proactive_idElement = configXMLFileCreator.createChildElement(idTag, OWASPProactive[0]);
            Element proactive_nameElement = configXMLFileCreator.createChildElement(nameTag, OWASPProactive[1]);
            Element proactive_descriptionElement = configXMLFileCreator.createChildElement(descriptionTag, OWASPProactive[2]);

            proactiveElement.appendChild(proactive_idElement);
            proactiveElement.appendChild(proactive_nameElement);
            proactiveElement.appendChild(proactive_descriptionElement);
            /* end of proactive tag */

            configXMLFileCreator.addToParent(proactiveElement);
        }
        /* end of proactive tags */

        configXMLFileCreator.transformAndSaveFile(fileName);
    }

    public ArrayList<String[]> loadConfigFile() throws IOException, SAXException, ParserConfigurationException {

        ArrayList<String[]> OWASP_proactives_list = new ArrayList<String[]>();

        ConfigXMLFileReader configXMLFileReader = new ConfigXMLFileReader();
        configXMLFileReader.loadFile(fileName);

        NodeList nodeList = configXMLFileReader.loadNodesByTagName(proactiveTag);

        for (int i = 0; i < nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;
                String[] row = new String[3];

                row[0] = element.getElementsByTagName(idTag).item(0).getTextContent();
                row[1] = element.getElementsByTagName(nameTag).item(0).getTextContent();
                row[2] = element.getElementsByTagName(descriptionTag).item(0).getTextContent();

                OWASP_proactives_list.add(row);
            }
        }
        return OWASP_proactives_list;
    }
}
