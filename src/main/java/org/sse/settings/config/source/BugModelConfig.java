package org.sse.settings.config.source;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.sse.settings.ConfigFileCreator;
import org.sse.settings.ConfigFileReader;;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BugModelConfig {

    private static String parentTag = "bug-categorization-model";
    private static String modelNameTag = "model-name";
    private static String versionTag = "version";
    private static String bugCategoryTag = "bug-category";
    private static String idTag = "id";
    private static String nameTag = "name";
    private static String descriptionTag = "description";

    private static String fileName = "BugModel.xml";

    private BugModelConfig() {}

    /**
     *
     * @param bugCategories
     * @param categoryModelName
     * @param versionValue
     * @throws IOException
     */
    public static void createConfigFile(ArrayList<String[]> bugCategories, String categoryModelName, String versionValue) throws IOException {

        ConfigFileCreator configFileCreator = new ConfigFileCreator();
        configFileCreator.createFile();

        configFileCreator.createRootElement(parentTag);

        Element parentNameElement = configFileCreator.createChildElement(modelNameTag, categoryModelName);
        Element versionElement = configFileCreator.createChildElement(versionTag, versionValue);

        configFileCreator.addToRoot(parentNameElement);
        configFileCreator.addToRoot(versionElement);

        /* create type tags */
        for (String[] OWASPType : bugCategories) {

            /* create type tag */
            Element typeElement = configFileCreator.createChildElement(bugCategoryTag);

            Element type_idElement = configFileCreator.createChildElement(idTag, OWASPType[0]);
            Element type_nameElement = configFileCreator.createChildElement(nameTag, OWASPType[1]);
            Element type_descriptionElement = configFileCreator.createChildElement(descriptionTag, OWASPType[2]);

            typeElement.add(type_idElement);
            typeElement.add(type_nameElement);
            typeElement.add(type_descriptionElement);
            /* end of type tag */

            configFileCreator.addToRoot(typeElement);
        }
        /* end of type tags */

        configFileCreator.writeFile(fileName);
    }

    /**
     *
     *
     * @return
     * @throws DocumentException
     */
    public static ArrayList<String[]> loadConfigFile() throws DocumentException {

        ArrayList<String[]> OWASP_T10_list = new ArrayList<String[]>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + bugCategoryTag);

        for (Node node : nodeList){

            String[] row = new String[3];

            row[0] = node.valueOf(idTag);
            row[1] = node.valueOf(nameTag);
            row[2] = node.valueOf(descriptionTag);

            OWASP_T10_list.add(row);
        }

        return OWASP_T10_list;
    }

    /**
     *
     *
     * @return
     * @throws DocumentException
     */
    public static HashMap<String,String> loadBugCategoryIdsAndNames() throws DocumentException {

        HashMap<String,String> bugIdsAndNames = new HashMap<String, String>();

        ConfigFileReader configFileReader = new ConfigFileReader();
        configFileReader.readFile(fileName);

        List<Node> nodeList = configFileReader.getNodes("//" + parentTag + "/" + bugCategoryTag);

        for (Node node : nodeList){

            String id = node.valueOf(idTag);
            String name = node.valueOf(nameTag);

            bugIdsAndNames.put(id,name);
        }

        return bugIdsAndNames;
    }

}
