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

                    cw.writeInit();

                    for(File f : files) {
                        String name = f.getName();
                        name = name.substring(0, name.indexOf('.'));
                        cw.setFileName(name);

                        Parser p = new Parser(f);
                        while(true) {
                            if(p.commandType() == 0) {
                                System.out.println(f + " contains an invalid instruction.");
                                return;
                            }

                            if(p.commandType() == Parser.C_ARITHMETIC) {
                                cw.writeArithmetic(p.arg1());
                            } 
                            else if(p.commandType() == Parser.C_PUSH || p.commandType() == Parser.C_POP) {
                                cw.writePushPop(p.commandType(), p.arg1(), p.arg2());
                            } 
                            else if(p.commandType() == Parser.C_LABEL) {
                                cw.writeLabel(p.arg1());
                            } 
                            else if(p.commandType() == Parser.C_GOTO) {
                                cw.writeGoto(p.arg1());
                            } 
                            else if(p.commandType() == Parser.C_IF) {
                                cw.writeIf(p.arg1());
                            } 
                            else if(p.commandType() == Parser.C_FUNCTION) {
                                cw.writeFunction(p.arg1(), p.arg2());
                            } 
                            else if(p.commandType() == Parser.C_CALL) {
                                cw.writeCall(p.arg1(), p.arg2());
                            } 
                            else if(p.commandType() == Parser.C_RETURN) {
                                cw.writeReturn();
                            }

                            if(p.hasMoreCommands()) {
                                p.advance();
                            } else break;
                        }
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
