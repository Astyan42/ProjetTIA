package ClientLourd.ui.controller;

import ClientLourd.ui.Ressource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class XmlArboController extends Ressource{
    DocumentBuilderFactory factory;
    DocumentBuilder builder;
    Document doc;

    public XmlArboController(String filename) {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            doc = builder.parse(filename);
            factory.setValidating(true);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * renvoie la liste des dossier direct du dossier donner avec url
     * @param folderName
     * @return list des dossier
     */
    public ArrayList<String> getFolders(String folderName){
        NodeList folderList= doc.getElementsByTagName("projet");
        return getArray(folderList,folderName);
    }

    /**
     * Renvoie la listes des fichiers direct du dossier url
     * @param folderName
     * @return list de fichier
     */
    public ArrayList<String> getFiles(String folderName) {
        NodeList filelist= doc.getElementsByTagName("fichier");
        return getArray(filelist,folderName);
    }

    /**
     * Reduction de code pour eviter la duplication de code de getFiles et getFolders
     * @param list
     * @param folderName
     * @return tout les noeud de list qui ont folderName en parent
     */
    private ArrayList<String> getArray(NodeList list,String folderName) {
        int nbFiles = list.getLength();
        ArrayList<String> StringTable = new ArrayList<>();
        for (int i = 0; i < nbFiles; i++) {
            if (list.item(i).getParentNode().getNodeName().equals("projet")) {
                if (list.item(i).getParentNode().getAttributes().getNamedItem("name").getNodeValue().equals(folderName)) {
                    String name = list.item(i).getAttributes().getNamedItem("name").getNodeValue();
                    StringTable.add(name);
                }
            }
        }
        return StringTable;
    }

    /**
     *
     * @param folderName
     * @return String le fil d'arianne du dossier folderName
     */
    public String getArianne(String folderName){
        NodeList list = doc.getElementsByTagName("projet");
        ArrayList<String> arianne = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++){
            if (list.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(folderName)){
                idProjectHere = list.item(i).getAttributes().getNamedItem("idp").getNodeValue();
                ProjectHere=list.item(i).getAttributes().getNamedItem("name").getNodeValue();
                Node current = list.item(i);
                while(current.getParentNode().getNodeName().equals("projet")){
                    arianne.add(current.getAttributes().getNamedItem("name").getNodeValue());
                    current = current.getParentNode();
                }
            }
        }
        String arianneFinal="/";
        Collections.reverse(arianne);
        int as = arianne.size();
        for (int j = 0; j<as; j++){
            arianneFinal+=arianne.get(j)+"/";
        }
        return arianneFinal;
    }

    /**
     * recupere le parent selon le node url utilisé
     * @param url
     * @return String du parent
     */
    public String getParent(String url){
        NodeList list = doc.getElementsByTagName("projet");
        ArrayList<String> arianne = new ArrayList<>();
        for (int i = 0; i < list.getLength(); i++){
            if (list.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(url)){
                Node current = list.item(i);
                if(current.getParentNode().getNodeName().equals("projet")){
                    return current.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
                }
            }
        }
        return "/";
    }
    public String getId(String name,String type){
        NodeList list = doc.getElementsByTagName(type);
        String id="";
        String res="";
        if (type.equals("projet"))id="idp";
        else id = "idf";
        for (int i = 0; i < list.getLength(); i++){
            if (list.item(i).getAttributes().getNamedItem("name").getNodeValue().equals(name))
                res = list.item(i).getAttributes().getNamedItem(id).getNodeValue();
        }
        return res;
    }
}
