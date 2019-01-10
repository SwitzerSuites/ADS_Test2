/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;
import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 *
 * @author Lucas
 * 
 * Bean for handling data gathered from original XML file
 */


public class Contact {
    private String custId;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String city;
    private String email;
    private String postalCode;
    private String country;
    private String phone;
    private String fax;
    
    public String getCustId(){
        return custId;
    }
    public void setCustId(String custId){
        this.custId = custId;
    }
    public String getCompanyName(){
        
        return companyName;
    }
    public void setCompanyName(String companyName){
        companyName = formatString(companyName);
        this.companyName = companyName;
    }
    public String getFax(){
        return fax;
    }
    public String getContactName(){
        return contactName;
    }
    public void setContactName(String contactName){
        contactName = formatString(contactName);
        this.contactName = contactName;
    }
    public String getContactTitle(){
        return contactTitle;
    }
    public void setContactTitle(String contactTitle){
        contactTitle = formatString(contactTitle);
        this.contactTitle = contactTitle;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        address = formatString(address);
        this.address = address;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        city = formatString(city);
        this.city = city;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPostalCode(){
        return postalCode;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){
        country = formatString(country);
        this.country = country;
    }
    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public void setFax(String fax){
        this.fax = fax;
    }
    public String formatString(String formatString){
        formatString = Normalizer.normalize(formatString, Form.NFD);
        if(formatString.contains(",")){
            formatString = formatString.replace(",", "");
      
        }
        if(formatString.contains("\n")|| formatString.contains("\r")){
            formatString = formatString = formatString.replace("\n", "").replace("\r", "");
        }
        return formatString;
    }
}
