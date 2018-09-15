/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.io.FilePermission;
import java.security.AccessController;
import java.util.Scanner;
import lib.Zipperzapper;

/**
 * Main class to start the application.
 *
 * @author Fredrik Grönqvist <fredrik.gronqvist@gmail.com>
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ZipperZapper!");
        System.out.println("");
        Main main = new Main();
        int compression_option = main.compressionOption(scanner);
        String input_file = main.getInputfile(scanner);
        String output_file = main.getOutputfile(scanner, input_file);

        Zipperzapper zipper = new Zipperzapper();
        zipper.setCompressionOption(compression_option);
        zipper.setInpufile(input_file);
        zipper.setOutputfile(output_file);        
        try{
            zipper.run();
        } catch (Exception e){
            System.out.println("Something went wrong!?!");
            System.out.println(e);
        }        
    }

    /**
     * Ask the user for the compressionalgorithm.
     * @param scanner
     * @return 
     */
    private int compressionOption(Scanner scanner) {
        System.out.println("Please choose compression option below.");
        System.out.println("Any other option terminates the program.");
        System.out.println("");

        System.out.println("1 - the ZipperZapper compression");
        System.out.println("2 - the Java.Util.Zip compression");
        System.out.println("");
        System.out.println("Enter option as 1 or 2:");
        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (Exception e) {
            Runtime.getRuntime().exit(1);
        }
        
        if(option < 1 || option > 2){
            System.out.println("Unknown option. Exiting.");
            Runtime.getRuntime().exit(1);
        }
        
        return option;
    }

    /**
     * Ask the user for an inputfile.
     * @param scanner
     * @return 
     */
    private String getInputfile(Scanner scanner){
        System.out.println("Please provide the inputfilename:");
        String inputfile = scanner.next();
        if(inputfile.trim().isEmpty()){
            System.out.println("No inputfilename provided. Exiting.");
            Runtime.getRuntime().exit(1);
        }
        try{
            FilePermission fp = new FilePermission(inputfile, "read");
            AccessController.checkPermission(fp);
        } catch (Exception e){
            System.out.println("No read permission on inputfile. Exiting.");
            Runtime.getRuntime().exit(1);
        }
        return inputfile;
    }
    
    /**
     * Ask the user for an outputfile
     * @param scanner
     * @param inputfile
     * @return 
     */
    private String getOutputfile(Scanner scanner, String inputfile){
        System.out.println("Please provide the outputfilename.");
        System.out.println("Using ["+inputfile+".zipper] if left empty:");
        String outputfile = scanner.next();
        if(outputfile.trim().isEmpty()){
            outputfile = outputfile + ".zipper";
        }
        try{
            FilePermission fp = new FilePermission(outputfile, "write");
            AccessController.checkPermission(fp);            
        } catch (Exception e){
            System.out.println("No write permissions on outputfile. Exiting.");
            Runtime.getRuntime().exit(1);
        }        
        return outputfile;
    }
}
