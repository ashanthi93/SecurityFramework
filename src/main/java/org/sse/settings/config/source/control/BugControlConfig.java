package org.sse.settings.config.source.control;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.source.model.BugControl;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;
import org.sse.settings.DescriptionProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BugControlConfig {

    private static final String parentTag = "bug-controls";
    private static final String modelNameTag = "model-name";
    private static final String versionTag = "version";
    private static final String proactiveTag = "control";
    private static final String idTag = "id";
    private static final String nameTag = "name";
    private static final String descriptionTag = "description";
    private static final String pointTag = "point";

    private static final String fileName = "BugControl.xml";

    private BugControlConfig() {}

    /**
     *
     * @param bugControls
     * @param modelNameValue
     * @param versionValue
     * @throws IOException
     */
    public static void createConfigFile(ArrayList<String[]> bugControls, String modelNameValue, String versionValue) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        Element modelNameElement = configFileCreator.createChildElement(modelNameTag, modelNameValue);
        Element versionNameElement = configFileCreator.createChildElement(versionTag, versionValue);

        configFileCreator.addToRoot(modelNameElement);
        configFileCreator.addToRoot(versionNameElement);

        /* create proactive tags */
        for (String[] OWASPProactive : bugControls) {

            /* create proactive tag */
            Element proactiveElement = configFileCreator.createChildElement(proactiveTag);

            Element proactive_idElement = configFileCreator.createChildElement(idTag, OWASPProactive[0]);
            Element proactive_nameElement = configFileCreator.createChildElement(nameTag, OWASPProactive[1]);

            Element proactive_descriptionElement = configFileCreator.createChildElement(descriptionTag);

            List<String> sentences = DescriptionProcessor.getSentences(OWASPProactive[2]);

            for (String sentence : sentences){

                Element pointElement = configFileCreator.createChildElement(pointTag, sentence);
                proactive_descriptionElement.add(pointElement);
            }

            proactiveElement.add(proactive_idElement);
            proactiveElement.add(proactive_nameElement);
            proactiveElement.add(proactive_descriptionElement);
            /* end of proactive tag */

            configFileCreator.addToRoot(proactiveElement);
        }
        /* end of proactive tags */

        configFileCreator.writeFile(fileName);
    }

    /**
     *
     * @return
     * @throws DocumentException
     */
    /*public static ArrayList<String[]> loadConfigFile() throws DocumentException {

        ArrayList<String[]> OWASP_proactives_list = new ArrayList<String[]>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + proactiveTag);

        for (Node node : nodeList){

            String[] row = new String[3];

            row[0] = node.valueOf(idTag);
            row[1] = node.valueOf(nameTag);

            List<Node> pointNodes = node.selectNodes(descriptionTag);

            for (Node pointNode : pointNodes){

            }

            OWASP_proactives_list.add(row);
        }
        return OWASP_proactives_list;
    }*/

    public static List<BugControl> loadConfigFile() throws DocumentException {

        List<BugControl> bugControls = new ArrayList<BugControl>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + proactiveTag);

        for (Node node : nodeList){

            BugControl bugControl = new BugControl();

            bugControl.setId(node.valueOf(idTag));
            bugControl.setName(node.valueOf(nameTag));

            List<Node> pointNodes = node.selectSingleNode(descriptionTag).selectNodes(pointTag);

            List<String> description = new ArrayList<String>();

            for (Node pointNode : pointNodes){
                description.add(pointNode.getStringValue());
            }

            bugControls.add(bugControl);
        }
        return bugControls;
    }

    public static HashMap<String,String> loadControlIdsAndNames() throws DocumentException {

        HashMap<String,String> controlIdsAndNames = new HashMap<String, String>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + proactiveTag);

        for (Node node : nodeList){

            controlIdsAndNames.put(node.valueOf(idTag),node.valueOf(nameTag));
        }

        return controlIdsAndNames;
    }
}
