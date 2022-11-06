import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Brian Song
 * @description Parses VM program and commands into fields
 */
public class Parser {
	private int currLineNum;			//int of current line in file
	private String[] currCmd;			//Current command to parse
	private ArrayList<String> instructions = new ArrayList<String>();
	
	//Instruction numbers
	public static final int C_ARITHMETIC = 1;
	public static final int C_PUSH = 2;
	public static final int C_POP = 3;
	public static final int C_LABEL = 4;
	public static final int C_GOTO = 5;
	public static final int C_IF = 6;
	public static final int C_FUNCTION = 7;
	public static final int C_CALL = 8;
	public static final int C_RETURN = 9;
	
	ArrayList<String> arithmeticCommands;	//Arraylist of arithmetic commands
	
	public Parser(File input) throws FileNotFoundException, Exception {
        try {                                                                   
        	// add all non-whitespace lines to an array list of instructions
            removeWhitespace(input);
            initializeArithmeticCommands();
            currLineNum = 0;
            if(!instructions.isEmpty()) {
                currCmd = instructions.get(currLineNum).split("\\s");
            } else {
                throw new Exception("No valid instructions.");
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
	
	/**
	 * Adds all arthmetic/logic commands into arthmethic arraylist
	 */
	private void initializeArithmeticCommands() {
        arithmeticCommands = new ArrayList<String>();

        arithmeticCommands.add("add");
        arithmeticCommands.add("sub");
        arithmeticCommands.add("neg");
        arithmeticCommands.add("eq");
        arithmeticCommands.add("gt");
        arithmeticCommands.add("lt");
        arithmeticCommands.add("and");
        arithmeticCommands.add("or");
        arithmeticCommands.add("not");
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

                if(!(command.equals("") || command.equals(" "))) {
                    instructions.add(command.trim());
                }
            }

            in.close();

        } catch(FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + e.getMessage());
        }
    }
    
    /**
     * Checks if more lines are in input
     * @return True if lines are left, false otherwise
     */
    public boolean hasMoreCommands() {
        return (currLineNum < instructions.size() - 1);
    }

    /**
     * Reads next command from input and makes it the current command
     * Should only be called if hasMoreLines is true
     * Initially not current command
     */
    public void advance() {
        currLineNum++;
        currCmd = instructions.get(currLineNum).split("\\s");
    }

    /**
     * Checks and returns type of command 
     * @return constant representing type of current command
     */
    public int commandType() {
        String type = currCmd[0];
        if(arithmeticCommands.contains(type)) {
            return C_ARITHMETIC;
        }

        if(type.equalsIgnoreCase("push")) {
            return C_PUSH;
        }

        if(type.equalsIgnoreCase("pop")) {
            return C_POP;
        }

        if(type.equalsIgnoreCase("label")) {
            return C_LABEL;
        }

        if(type.equalsIgnoreCase("goto")) {
            return C_GOTO;
        }

        if(type.equalsIgnoreCase("if-goto")) {
            return C_IF;
        }

        if(type.equalsIgnoreCase("function")) {
            return C_FUNCTION;
        }

        if(type.equalsIgnoreCase("call")) {
            return C_CALL;
        }

        if(type.equalsIgnoreCase("return")) {
            return C_RETURN;
        }

        return 0;
    }

    /**
     * Returns first command argument
     * Should not be called if C_RETURN
     * @return first arg of current command
     */
    public String arg1() {
        if(this.commandType() == C_ARITHMETIC) {
            return currCmd[0];
        }
        else {
            return currCmd[1];
        }
    }

    /**
     * Returns second command argument
     * Should only be called if current command is C_PUSH, C_POP, C_FUNCTION, C_CALL
     * @return second argument of current command
     * @throws NumberFormatException if command argument is invalid
     */
    public int arg2() throws NumberFormatException {
        try {
            return Integer.parseInt(currCmd[2]);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid argument: " + e.getMessage());
        }
    }
}
