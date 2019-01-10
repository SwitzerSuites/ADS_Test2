/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import com.sun.xml.internal.ws.util.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lucas
 */
public class xmlConvert {
    
    private String xmlPath;
    private NodeList nList;
    private static Contact c;
    List<Contact> contactList;
    
    public xmlConvert(String xmlPath){
        
            this.xmlPath = xmlPath;
            convert();
        }
    //Main method for converting XML file to CSV or JSON
    public void convert(){
        try {
        //Get XML file
        File xmlFile = new File(xmlPath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);
        
        doc.getDocumentElement().normalize();
        nList = doc.getElementsByTagName("Contact");
        //Save list of contacts
        contactList = new ArrayList<Contact>();
        for(int i = 0; i < nList.getLength(); i++){
            Node node = nList.item(i);
            contactList.add(getContact(node));
            
        }
        //Give user options, convert to CSV, JSON, or both
        boolean selection = false;
        while(!selection){
        System.out.println("Enter a number for the corresponding conversion option:");
        System.out.println("[1] CSV [2] JSON [3] Both [4] Exit");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                writeCSV();
                System.out.println("CSV file created");
                selection = true;
                break;
            case 2:
                
                writeJSON();
                System.out.println("JSON file created");
                selection = true;
                break;
            case 3:
                
                writeCSV();
                writeJSON();
                System.out.println("CSV and JSON file created");
                selection = true;
                break;
            case 4:
                selection = true;
                System.exit(0);
                break;
            default:
                System.out.println("Not a valid option");
                break;
        }
        }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    //Set Contact info into bean
    private static Contact getContact(Node node){
        c = new Contact();
        
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element ele = (Element) node;
            c.setCustId(tagVal("CustomerID", ele));
            c.setCompanyName(tagVal("CompanyName", ele));
            c.setContactName(tagVal("ContactName", ele));
            c.setContactTitle(tagVal("ContactTitle", ele));
            c.setAddress(tagVal("Address", ele));
            c.setCity(tagVal("City", ele));
            c.setEmail(tagVal("Email", ele));
            c.setPostalCode(tagVal("PostalCode", ele));
            c.setCountry(tagVal("Country", ele));
            c.setPhone(tagVal("Phone", ele));
            c.setFax(tagVal("Fax", ele));
        }
        return c;
    }
    //Returns string for tag value, if null returns null
    private static String tagVal(String tag, Element ele){
        Node nVal = ele.getElementsByTagName(tag).item(0);
        if(nVal == null){
            return null;
        }
        return nVal.getTextContent();
    }
    //Command Line prompts for naming file
    private String setFileName(String type){
        Scanner sc = new Scanner(System.in);
        boolean name = false;
        String fName = "default";
        while(!name){
        System.out.println("Specify new "+type+" file name (without file type):");
            fName = sc.nextLine();
            if(fName.matches("[a-zA-Z0-9]+")) {
                name = true;
            } 
            else{
                System.out.println("Invalid naming convention please try again");
            }
        }
        return fName;
    }
    //Write CSV file
    private void writeCSV() throws IOException{
        
        PrintWriter pWrite = new PrintWriter(new File(setFileName("CSV")+".csv"));
        StringBuilder sBuild = new StringBuilder();
        
        //Set column names
        sBuild.append("CustomerID"); sBuild.append(",");
        sBuild.append("CompanyName"); sBuild.append(",");
        sBuild.append("ContactName"); sBuild.append(",");
        sBuild.append("ContactTitle"); sBuild.append(",");
        sBuild.append("Address"); sBuild.append(",");
        sBuild.append("City"); sBuild.append(",");
        sBuild.append("Email"); sBuild.append(",");
        sBuild.append("PostalCode"); sBuild.append(",");
        sBuild.append("Country"); sBuild.append(",");
        sBuild.append("Phone"); sBuild.append(",");
        sBuild.append("Fax"); 
        sBuild.append("\n"); 
        
        //Add each element
        for(Contact c: contactList){
            sBuild.append(c.getCustId()); sBuild.append(",");
            sBuild.append(c.getCompanyName()); sBuild.append(",");
            sBuild.append(c.getContactName()); sBuild.append(",");
            sBuild.append(c.getContactTitle()); sBuild.append(",");
            sBuild.append(c.getAddress()); sBuild.append(",");
            sBuild.append(c.getCity()); sBuild.append(",");
            sBuild.append(c.getEmail()); sBuild.append(",");
            sBuild.append(c.getPostalCode()); sBuild.append(",");
            sBuild.append(c.getCountry()); sBuild.append(",");
            sBuild.append(c.getPhone()); sBuild.append(",");
            sBuild.append(c.getFax()); 
            sBuild.append("\n");
                }
        pWrite.write(sBuild.toString());
        pWrite.close();
    }
    
    //Write JSON file
    private void writeJSON() throws IOException{
        JSONArray contacts = new JSONArray();
        for (Contact c : contactList){
        JSONObject contactDetails = new JSONObject();
        contactDetails.put("CustomerID", c.getCustId());
        contactDetails.put("CompanyName", c.getCompanyName());
        contactDetails.put("ContactName", c.getContactName());
        contactDetails.put("ContactTitle", c.getContactTitle());
        contactDetails.put("Address", c.getAddress());
        contactDetails.put("City", c.getCity());
        contactDetails.put("Email", c.getEmail());
        contactDetails.put("PostalCode", c.getPostalCode());
        contactDetails.put("Country", c.getCountry());
        contactDetails.put("Phone", c.getPhone());
        contactDetails.put("Fax", c.getFax());
        JSONObject contactObject = new JSONObject();
        contactObject.put("Contact", contactDetails);
        contacts.add(contactObject);
        }
      
        FileWriter fWrite = new FileWriter(new File(setFileName("JSON")+".json"));
        fWrite.write(contacts.toJSONString());
        fWrite.flush();
    }

}
    

