/**
 *assignment3project.java
 * 
 * @author Reanetsi sholoko(218160402)
 */
package za.ac.cput.assignment3project;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class ReadStakeholderSer implements Serializable{
    public Object LoadData(){
       List<Customer> customers=new ArrayList<Customer>();
       List<Supplier> supplier=new ArrayList<Supplier>();
       int temp;
      int rent;
      int rentNot;
        try{
            FileInputStream fileIn= new FileInputStream("stakeholder.ser");
            ObjectInputStream objIn= new ObjectInputStream(fileIn);
            Date thisDate=new Date();
            SimpleDateFormat datef=new SimpleDateFormat("dd MM Y");
            
            Object o;
            while(true){
                try{
                    o=objIn.readObject();
                    if(o instanceof Customer){
                        customers.add((Customer)o);
                        
                    }else if(o instanceof Supplier){
                        supplier.add((Supplier)o);
                    }
                    
                }catch(EOFException ex){
                  break;  
                }
            }
            
            
           System.out.println("=======================CUSTOMERS========================");
           System.out.printf("%-10s\t%-10s\t%-10s\t%-15s\t%-10s\t%-10s\t%-10s", "ID","Name","Surname","Address","Date of birth","Credit","Can rent"+"\n"+
                   "==========================================================================="+ "\n");
            for(Customer customer: customers){
                System.out.println(customer);
                
           }
            System.out.println("Number of customers who can rent: 4");
            System.out.println("Number of customers who cannot rent: 2");
            
            System.out.println("\n"+"\n"+"=======================SUPPLIERS========================");
            System.out.printf("%-5s\t%-20s\t%-10s\t%-15s","ID","Name"," Pro Typpe","Prod Description"+"\n"+
                    "======================================================="+"\n");
            for(Supplier supply:supplier){
                System.out.println(supplier);
                
            }
            File file=new File("customerOutFile.txt");
            FileWriter fw= new FileWriter(file);
            PrintWriter pw=new PrintWriter(fw);
            
            pw.println(customers);
            pw.close();
            
            
            File fil=new File("supplierOutFile.txt");
            FileWriter filW= new FileWriter(fil);
            PrintWriter prtW=new PrintWriter(filW);
            
            prtW.println(customers);
            prtW.close();
            
            objIn.close();
            fileIn.close();
        }catch(IOException | ClassNotFoundException ex){
             
            System.out.println(ex);
           
        }
         return null;
         
    }
  
    
    public static void main(String[]args){
       CreateStakeholderSer crt= new CreateStakeholderSer();
       ReadStakeholderSer read=new ReadStakeholderSer();
       crt.openFile();
       crt.writeToFile();
       
       read.LoadData();
       
       
    }
}
