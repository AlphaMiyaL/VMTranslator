import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Brian Song
 * @description Parses VM program and commands into fields
 */
public class Parser {
	private String currCmd;			//Current command to parse
	private Scanner reader;			//Scanner reading the file
	
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
	
	public Parser(Scanner sc) {
        reader = sc;
    }
    
    /**
     * Checks if more lines are in input
     * @return True if lines are left, false otherwise
     */
    public boolean hasMoreCommands() {
        return reader.hasNextLine();
    }

    /**
     * Reads next command from input and makes it the current command
     * Should only be called if hasMoreLines is true
     * Initially not current command
     */
    public void advance() {
    	if(hasMoreCommands()) {
    		currCmd = reader.nextLine();
    		// take out comments and trim for whitespaces
            int commentIndex = currCmd.indexOf("/");
            if (commentIndex >= 0) {
                currCmd = currCmd.substring(0, commentIndex);
            }
            currCmd = currCmd.trim();
            
            //Move to next command if line was comment or whitespace
            if(currCmd.isEmpty()) {
            	advance();
            }
    	}
    }

    /**
     * Checks and returns type of command 
     * @return constant representing type of current command
     */
    public int commandType() {
    	String cmd = currCmd.split(" ")[0];
    	switch(cmd.toLowerCase()) {
    		case "push":
                return C_PUSH;
            case "pop":
                return C_POP;
            default:
                return C_ARITHMETIC;
    	}
    }

    /**
     * Returns first command argument
     * Should not be called if C_RETURN
     * @return first arg of current command
     */
    public String arg1() {
        if (commandType() == C_RETURN) {
            return null;
        }

        if (commandType() == C_ARITHMETIC) {
            return currCmd;
        }

        return currCmd.split(" ")[1];
    }

    /**
     * Returns second command argument
     * Should only be called if current command is C_PUSH, C_POP, C_FUNCTION, C_CALL
     * @return second argument of current command
     * @throws NumberFormatException if command argument is invalid
     */
    public int arg2() {
        if (commandType() == C_PUSH
                || commandType() == C_POP
                || commandType() == C_FUNCTION
                || commandType() == C_CALL) {
            return Integer.valueOf(currCmd.split(" ")[2]);
        }

        return 0;
    }
}
