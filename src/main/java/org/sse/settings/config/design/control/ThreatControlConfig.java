package org.sse.settings.config.design.control;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.design.model.Threat;
import org.sse.design.model.ThreatControl;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;
import org.sse.settings.DescriptionProcessor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ThreatControlConfig {

    private static final String parentTag = "threat-controls";
    private static final String securityControlTag = "security-control";
    private static final String idTag = "id";
    private static final String nameTag = "name";
    private static final String descriptionTag = "description";
    private static final String pointTag = "point";
    
    private static final String fileName = "ThreatControl.xml";

    private ThreatControlConfig(){}

    /**
     *
     *
     * @param threatControls
     * @throws IOException
     */
    public static void createFile(ArrayList<ThreatControl> threatControls) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        /* create threat-type tags */
        for (ThreatControl threatControl : threatControls){

            /* create threat-type tag */
            Element threatTypeElement = configFileCreator.createChildElement(securityControlTag);

            Element idElement = configFileCreator.createChildElement(idTag, threatControl.getId());
            Element nameElement = configFileCreator.createChildElement(nameTag, threatControl.getName());

            /* create description tag */
            Element descriptionElement = configFileCreator.createChildElement(descriptionTag);

            List<String> sentences = threatControl.getDescription();

            for (String sentence : sentences){

                Element pointElement = configFileCreator.createChildElement(pointTag, sentence);
                descriptionElement.add(pointElement);
            }
            /* end of description tag */

            threatTypeElement.add(idElement);
            threatTypeElement.add(nameElement);
            threatTypeElement.add(descriptionElement);
            /* end of  threat-type tag */

            configFileCreator.addToRoot(threatTypeElement);
        }
        /* end of threat-type tags*/

        configFileCreator.writeFile(fileName);
    }

    public static List<ThreatControl> loadConfigFile() throws DocumentException {

        List<ThreatControl> threatControls = new ArrayList<ThreatControl>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + securityControlTag);

        for(Node node : nodeList){
            ThreatControl threatControl = new ThreatControl();

            threatControl.setId(node.valueOf(idTag));
            threatControl.setName(node.valueOf(nameTag));

            List<Node> pointNodes = node.selectSingleNode(descriptionTag).selectNodes(pointTag);

            List<String> description = new ArrayList<String>();

            for (Node pointNode : pointNodes){
                description.add(pointNode.getStringValue());
            }
            threatControl.setDescription(description);

            threatControls.add(threatControl);
        }
        return threatControls;
    }

    /**
     *
     *
     * @return
     * @throws DocumentException
     */
    public static HashMap<String,String> loadThreatControlIdsAndNames() throws DocumentException {

        HashMap<String,String> threatControlIdsAndNames = new HashMap<String, String>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + securityControlTag);

        for (Node node : nodeList){
            threatControlIdsAndNames.put(node.valueOf(idTag), node.valueOf(nameTag));
        }

        return threatControlIdsAndNames;
    }
}
