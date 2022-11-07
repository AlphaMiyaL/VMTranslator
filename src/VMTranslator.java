import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Brian Song
 */
public class VMTranslator {
	/**
	 * adds .vm file to list of files to translate, or if directory, recursively adds all .vm files into list
	 * @param input filename or directory of files to translate
	 * @param list of .vm files gotten from input
	 * @throws FileNotFoundException if file is not found
	 */
	private static void getFiles(File input, ArrayList<File> files) throws FileNotFoundException {
        if(input.isFile()) {
            // check for .vm extension before adding to list of files
            String filename = input.getName();
            int extension = filename.indexOf('.');
            if(extension > 0) {
                String fileExtension = filename.substring(extension + 1);
                if(fileExtension.equalsIgnoreCase("vm")) {
                    files.add(input);
                }
            }
        } 
        else if(input.isDirectory()) {
            File[] innerFiles = input.listFiles();
            for(File f : innerFiles) {
                getFiles(f, files);
            }
        } 
        else {
            throw new FileNotFoundException("Could not find file or directory.");
        }
    }
	
	/**
	 * Removes whitespace in given File
	 * @param input File to remove whitespace in
	 * @throws FileNotFoundException If input file does not exist
	 */
    public void removeWhitespace(File input) throws FileNotFoundException {
        try {
            Scanner in = new Scanner(input);

            while(in.hasNext()) {
            	// reads each line and splits into an array of string tokens
                String next = in.nextLine();                                
                String[] line = next.split("\\s");                          
                String command = "";

                for(int i = 0; i < line.length; i++) {
                	// ignores empty lines
                    if(line.length == 0) {                                  
                        break;
                    } 
                    // ignores inline comments
                    else if(line[i].length() > 1 && line[i].substring(0, 2).equals("//") && i != 0) {
                        break;                                              
                    }
                    // ignores whole-line comments
                    else if(line[i].length() > 1 && line[i].substring(0, 2).equals("//") && i == 0) {
                        break;                                             
                    }
                    // preserves space between command words
                    else {
                        command += line[i];
                        command += " ";                                     
                    }
                }
            }
            in.close();
        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + e.getMessage());
        }
    }
	
	private static void translate(File file, CodeWriter cw) {
		File outputFile = new File(file.getName().split(".vm")[0]+ ".asm");
		Scanner reader = null;
		try{
			reader = new Scanner(file);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Parser parser = new Parser(reader);
		cw.setFileName(outputFile.getName());
		
		while(parser.hasMoreCommands()) {
			parser.advance();
			switch (parser.commandType()) {
                case 2:
                case 3:
                    cw.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
                    break;
                case 1:
                    cw.writeArithmetic(parser.arg1());
                    break;
            }
		}
		
		
	}
	
	/**
	 * !!!IMPORTANT!!!
	 * Make sure you set console to false if not using console to find files
	 * ie: resources/BasicTest
	 * Generates assembly files from .vm files
	 * @param args
	 */
    public static void main(String[] args) {
    	// Hard coded value input is from cmd or from running program in console
    	boolean console = true;
        if(args.length > 0 || console) {
            try {
                ArrayList<File> files = new ArrayList<File>();
                File input = null;
                if(console) {
                	Scanner kb = new Scanner(System.in);
                	System.out.print("Please type to filename/directory: ");
                	input = new File(kb.nextLine());
                	kb.close();
                }
                else {
                	input = new File(args[0]);
                }
                getFiles(input, files);
                if(!files.isEmpty()) {
                    String outputName = input.getName();
                    if(outputName.indexOf('.') > 0) {
                        outputName = outputName.substring(0, outputName.indexOf('.'));
                    } else if(outputName.indexOf('/') > 0) {
                        outputName = outputName.substring(0, outputName.indexOf('/'));
                    }

                    File output;
                    if(input.isFile()) {
                        output = new File(input.getParent(), outputName + ".asm");
                    } else {
                        output = new File(input, outputName + ".asm");
                    }

                    CodeWriter cw = new CodeWriter(output);

                    for(File f : files) {
                        String name = f.getName();
                        name = name.substring(0, name.indexOf('.'));
                        cw.setFileName(name);
                        translate(f, cw);
                    }
                    System.out.println(".asm file created. You can find it in the same directory as the file input");
                    cw.close();
                } 
                else {
                    System.out.println("No .vm files found.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        else {
            System.out.println("No source entered.");
        }
    }
}
