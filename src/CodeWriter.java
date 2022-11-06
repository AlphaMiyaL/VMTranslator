import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Brian Song
 * @description Generates sequence of Hack assembly instructions based on parsed VM instructions
 */
public class CodeWriter {
	private String filename;		//Name of file to write instruction in
	private PrintWriter writer;		//writer to write assembly instructions in file
	private int currLine;			//current line in file
	private int labelNum;			//location label point to
	
	public CodeWriter(File output) throws FileNotFoundException{
		try {
			writer = new PrintWriter(output);
			currLine = 0;
			labelNum = 0;
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException("File not found: " + e.getMessage());
		}
	}
	
	/**
	 * Changes filename to filename specified
	 * @param filename String to change filename
	 */
	public void setFileName(String filename) {
        this.filename = filename;
    }
	
	/**
	 * Writes line in file open in FileWriter
	 * @param line	Line to write in file
	 */
	private void writeLine(String line) {
        writer.println(line);
        currLine++;
    }
	
	/**
	 * Initalizing file with default Hack instructions
	 */
	public void writeInit() {
        writer.println("// init");
        writeLine("@256");
        writeLine("D=A");
        writeLine("@SP");
        writeLine("M=D");
        writeCall("Sys.init", 0);
    }
	
	/**
	 * Writes a user-defined label into file
	 * @param label String value of label
	 */
	public void writeLabel(String label) {
        writer.println("// C_LABEL " + label);
        writer.println("(" + label + ")");
    }

	/**
	 * Writes JMP instructions into file equivalent to GoTO
	 * @param label String value of jump/goto label
	 */
    public void writeGoto(String label) {
        writer.println("// C_GOTO " + label);
        writeLine("@" + label);
        writeLine("0;JMP");
    }

    /**
     * Writes Branch instructions into file equivalent to If 
     * @param label String value of IF label
     */
    public void writeIf(String label) {
        writer.println("// C_IF " + label);
        writeLine("@SP");
        writeLine("AM=M-1");
        writeLine("D=M");
        writeLine("@" + label);
        writeLine("D;JNE");
    }
    
    /**
     * Writes to output file the assembly code that implements the given call
     * @param functionName Name of call function
     * @param numArgs Number of arguments in call function
     */
    public void writeCall(String functionName, int numArgs) {
        writer.println("// call " + functionName + " " + numArgs);

        //save calling function
        writer.println("// save calling function");
        writeLine("@return-address" + labelNum);
        writeLine("D=A");
        finishPush();

        writeLine("@LCL");
        writeLine("D=M");
        finishPush();

        writeLine("@ARG");
        writeLine("D=M");
        finishPush();

        writeLine("@THIS");
        writeLine("D=M");
        finishPush();

        writeLine("@THAT");
        writeLine("D=M");
        finishPush();

        // reposition ARG
        writer.println("// reposition ARG");
        writeLine("@SP");
        writeLine("D=M");
        writeLine("@" + numArgs);
        writeLine("D=D-A");
        writeLine("@5");
        writeLine("D=D-A");
        writeLine("@ARG");
        writeLine("M=D");

        // reposition LCL
        writer.println("// reposition LCL");
        writeLine("@SP");
        writeLine("D=M");
        writeLine("@LCL");
        writeLine("M=D");

        // transfer control
        writer.println("// transfer control");
        writeGoto(functionName);

        // declare return address label
        writer.println("// declare return address label");
        writeLabel("return-address" + labelNum);

        labelNum++;
    }

    /**
     * Abstraction of lines in Hack assembly to finish a push
     */
    private void finishPush() {
        writeLine("@SP");
        writeLine("A=M");
        writeLine("M=D");
        writeLine("@SP");
        writeLine("M=M+1");
    }

    /**
     * Returns to return value and restores all pointers back to pointers before call
     */
    public void writeReturn() {
        writer.println("// return");

        //FRAME = LCL
        writer.println("// set FRAME = LCL");
        writeLine("@LCL");
        writeLine("D=M");
        writeLine("@FRAME");
        writeLine("M=D");

        //RET = FRAME - 5
        writer.println("// set RET = FRAME - 5");
        writeLine("@5");
        writeLine("A=D-A");
        writeLine("D=M");
        writeLine("@RET");
        writeLine("M=D");

        //ARG = pop()
        writer.println("// set ARG = pop()");
        writeLine("@SP");
        writeLine("AM=M-1");
        writeLine("D=M");
        writeLine("@ARG");
        writeLine("A=M");
        writeLine("M=D");

        // restore SP of the caller
        writer.println("// restore SP of the caller");
        writeLine("@ARG");
        writeLine("D=M+1");
        writeLine("@SP");
        writeLine("M=D");

        // restore THAT of the caller
        writer.println("// restore THAT of the caller");
        writeLine("@FRAME");
        writeLine("A=M-1");
        writeLine("D=M");
        writeLine("@THAT");
        writeLine("M=D");

        // restore THIS of the caller
        writer.println("// restore THIS of the caller");
        writeLine("@FRAME");
        writeLine("D=M");
        writeLine("@2");
        writeLine("A=D-A");
        writeLine("D=M");
        writeLine("@THIS");
        writeLine("M=D");

        // restore ARG of the caller
        writer.println("// restore ARG of the caller");
        writeLine("@FRAME");
        writeLine("D=M");
        writeLine("@3");
        writeLine("A=D-A");
        writeLine("D=M");
        writeLine("@ARG");
        writeLine("M=D");

        // restore LCL of the caller
        writer.println("// restore LCL of the caller");
        writeLine("@FRAME");
        writeLine("D=M");
        writeLine("@4");
        writeLine("A=D-A");
        writeLine("D=M");
        writeLine("@LCL");
        writeLine("M=D");

        // goto RET
        writer.println("// goto RET");
        writeLine("@RET");
        writeLine("A=M");
        writeLine("0;JMP");
    }
    
    /**
     * Writes to output file the assembly code that implements the given function
     * @param functionName
     * @param numLocals
     */
    public void writeFunction(String functionName, int numLocals) {
        writer.println("// function " + functionName + numLocals);

        // declare label for function entry
        writeLabel(functionName);

        // initialize local variables to 0
        for(int i = 0; i < numLocals; i++) {
            writePushPop(Parser.C_PUSH, "constant", 0);
        }
    }
    
    /**
     * Returns segment label equivalent in Hack Assembly
     * @param segment VM segment
     * @return Hack Assembly equivalent of VM segment
     */
    private String getLabel(String segment) {
        if(segment.equalsIgnoreCase("local")) {
            return "@LCL";
        } else if(segment.equalsIgnoreCase("argument")) {
            return "@ARG";
        } else if(segment.equalsIgnoreCase("this")) {
            return "@THIS";
        } else if(segment.equalsIgnoreCase("that")) {
            return "@THAT";
        } else if(segment.equalsIgnoreCase("temp")) {
            return "@R5";
        } else return null;
    }

    /**
     * Closes writer
     */
    public void close() {
        writer.close();
    }
	
	/**
	 * Writes to output file the assembly code that implements the given arithmethic/logic command
	 * @param operation String line containing the arithmetic operation
	 */
	public void writeArithmetic(String operation) {
        writer.println("// " + operation);
        //Add, Sub, And, Or
        if(operation.equalsIgnoreCase("add") || operation.equalsIgnoreCase("sub") 
        		|| operation.equalsIgnoreCase("and") || operation.equalsIgnoreCase("or")) {
            writeLine("@SP");
            writeLine("AM=M-1");
            writeLine("D=M");
            writeLine("A=A-1");

            if(operation.equalsIgnoreCase("add")) {
                writeLine("M=D+M");
            } 
            else if(operation.equalsIgnoreCase("sub")) {
                writeLine("M=M-D");
            } 
            else if(operation.equalsIgnoreCase("and")) {
                writeLine("M=D&M");
            } 
            else if(operation.equalsIgnoreCase("or")) {
                writeLine("M=D|M");
            }
        } 
        //Equals, Less Than, Greater Than
        else if(operation.equalsIgnoreCase("eq") || operation.equalsIgnoreCase("lt") || operation.equalsIgnoreCase("gt")) {
            writeLine("@SP");
            writeLine("AM=M-1");
            writeLine("D=M");
            writeLine("A=A-1");
            writeLine("D=D-M");
            writeLine("@" + (currLine + 7));

            if(operation.equalsIgnoreCase("eq")) {
                writeLine("D;JEQ");
            } 
            else if(operation.equalsIgnoreCase("lt")) {
                writeLine("D;JGT");
            } 
            else if(operation.equalsIgnoreCase("gt")) {
                writeLine("D;JLT");
            }

            writeLine("@SP");
            writeLine("A=M-1");
            writeLine("M=0");
            writeLine("@" + (currLine + 5));
            writeLine("0;JMP");
            writeLine("@SP");
            writeLine("A=M-1");
            writeLine("M=-1");

        } 
        //Not
        else if(operation.equalsIgnoreCase("not")) {
            writeLine("@SP");
            writeLine("A=M-1");
            writeLine("M=!M");

        }
        //Negative
        else if(operation.equalsIgnoreCase("neg")) {
            writeLine("D=0");
            writeLine("@SP");
            writeLine("A=M-1");
            writeLine("M=D-M");
        }
    }
	
	/**
	 * Writes to output file the assembly code that implements the given push/pop command
	 * @param command Push/Pop command to translate
	 * @param segment String segment
	 * @param index   int index within segment
	 */
	public void writePushPop(int command, String segment, int index) {
        writer.println("// " + command + segment + index);
        //Push command
        if(command == Parser.C_PUSH) {
        	//Pointer
            if(segment.equalsIgnoreCase("pointer")) {
                if(index == 0) {
                    writeLine("@THIS");
                } 
                else if(index == 1) {
                    writeLine("@THAT");
                }
                writeLine("D=M");
            } 
            //Static
            else if(segment.equalsIgnoreCase("static")) {
                writeLine("@" + filename + "." + index);
                writeLine("D=M");
            } 
            else if(index == 0 && !segment.equalsIgnoreCase("constant")) {
                writeLine(getLabel(segment));
                writeLine("A=M");
                writeLine("D=M");
            } 
            //Constant
            else if(index > 0 || segment.equalsIgnoreCase("constant")) {
                writeLine("@" + index);
                writeLine("D=A");
            }

            //Write Label equivalent in Hack if not constant, pointer, or static
            if(index > 0 && !segment.equalsIgnoreCase("constant") 
            		&& !segment.equalsIgnoreCase("pointer")
                    && !segment.equalsIgnoreCase("static")) {
                writeLine(getLabel(segment));

                if(segment.equalsIgnoreCase("temp")) {
                    writeLine("A=D+A");
                } 
                else {
                    writeLine("A=D+M");
                }
                writeLine("D=M");
            }

            writeLine("@SP");
            writeLine("A=M");
            writeLine("M=D");
            writeLine("@SP");
            writeLine("M=M+1");

        } 
        //Pop command
        else if(command == Parser.C_POP) {
        	//Pointer, Static
            if(index == 0 || segment.equalsIgnoreCase("pointer") || segment.equalsIgnoreCase("static")) {
                writeLine("@SP");
                writeLine("AM=M-1");
                writeLine("D=M");
                if(segment.equalsIgnoreCase("pointer") && index == 0) {
                    writeLine("@THIS");
                } 
                else if(segment.equalsIgnoreCase("pointer") && index == 1) {
                    writeLine("@THAT");
                } 
                else if(segment.equalsIgnoreCase("static")) {
                    writeLine("@" + filename + "." + index);
                }
                else {
                    writeLine(getLabel(segment));
                }

            } 
            else if(index > 0) {
                writeLine("@" + index);
                writeLine("D=A");
                writeLine(getLabel(segment));

                if(segment.equalsIgnoreCase("temp")) {
                    writeLine("D=D+A");
                } else {
                    writeLine("D=D+M");
                }

                writeLine("@R13");
                writeLine("M=D");
                writeLine("@SP");
                writeLine("AM=M-1");
                writeLine("D=M");
                writeLine("@R13");
            }

            if(!segment.equalsIgnoreCase("pointer") && !segment.equalsIgnoreCase("static")
                    && !segment.equalsIgnoreCase("temp")) {
                writeLine("A=M");
            }
            writeLine("M=D");
        }
    }
	
}
