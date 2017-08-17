package settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class ConfigXMLFileCreator {

    Document document;
    Element parentElement;
/*
    public ConfigFileCreator(String fileName){
        this.fileName = fileName;
    }*/

    public ConfigXMLFileCreator(){}


    public void createFile() throws ParserConfigurationException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.newDocument();
    }

    public void createParentElement(String parentName) {

        parentElement = document.createElement(parentName);
        document.appendChild(parentElement);
    }

    public void addToParent(Element element){

        parentElement.appendChild(element);
    }

    public Element createChildElement(String childName) {

        Element childElement = document.createElement(childName);
        return childElement;
    }

    public Element createChildElement(String childName, String value) {

        Element childElement = document.createElement(childName);
        childElement.appendChild(document.createTextNode(value));

        return childElement;
    }

    // Transform and write config file
    public void transformAndSaveFile(String fileName) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);

        String pathName = "src/main/resources/configurations/" + fileName;

        StreamResult streamResult = new StreamResult(new File(pathName));
        transformer.transform(source, streamResult);
    }
}
