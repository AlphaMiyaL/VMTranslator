import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Brian Song
 * @description Generates sequence of Hack assembly instructions based on parsed VM instructions
 */
public class CodeWriter {
	private String filename; // Name of file to write instruction in
	private PrintWriter writer; // writer to write assembly instructions in file
	private int labelNum; // location label point to

	public CodeWriter(File file) {
		try {
			writer = new PrintWriter(file);
			labelNum = 0;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Changes filename to filename specified
	 * 
	 * @param filename String to change filename
	 */
	public void setFileName(String filename) {
		this.filename = filename;
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
	 * Writes to output file the assembly code that implements the given arithmethic/logic command
	 * 
	 * @param operation String line containing the arithmetic operation
	 */
	public void writeArithmetic(String cmd) {
		writer.printf("// %s\n",cmd);
		// cases for each arithmetic/logic function
		// add, sub, neg, eq, gt, lt, and, or, not
		switch(cmd.toLowerCase()) {
			case "add":
				popStackToD();
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=D+M");
				incStackPointer();
				break;
			case "sub":
				popStackToD();
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=M-D");
				incStackPointer();
				break;
			case "neg":
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=-M");
				incStackPointer();
				break;
			case "eq":
				writeCompareLogic("JEQ");
				break;
			case "gt":
				writeCompareLogic("JGT");
				break;
			case "lt":
				writeCompareLogic("JLT");
				break;
			case "and":
				popStackToD();
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=D&M");
				incStackPointer();
				break;
			case "or":
				popStackToD();
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=D|M");
				incStackPointer();
				break;
			case "not":
				decStackPointer();
				loadStackPointerToA();
				writer.println("M=!M");
				incStackPointer();
				break;
		}
	}

	/**
	 * Writes to output file the assembly code that implements the given push/pop command
	 * 
	 * @param command Push/Pop command to translate
	 * @param segment String segment
	 * @param index   int index within segment
	 */
	public void writePushPop(int cmd, String segment, int index) {
		switch(cmd) {
			case Parser.C_PUSH:
				writer.printf("// push %s %d\n", segment, index);
				switch(segment) {
					case "constant":
						// store value in D
						writer.println("@" + index);
						writer.println("D=A");
						break;
					case "local":
						loadSegment("LCL", index);
						writer.println("D=M");
						break;
					case "argument":
						loadSegment("ARG", index);
						writer.println("D=M");
						break;
					case "this":
						loadSegment("THIS", index);
						writer.println("D=M");
						break;
					case "that":
						loadSegment("THAT", index);
						writer.println("D=M");
						break;
					case "pointer":
						writer.println("@R" + String.valueOf(3+index));
						writer.println("D=M");
						break;
					case "temp":
						writer.println("@R" + String.valueOf(5+index));
						writer.println("D=M");
						break;
					case "static":
						writer.println("@" + filename.split("\\.")[0] + String.valueOf(index));
						writer.println("D=M");
				}
				pushDToStack();
				break;
			case Parser.C_POP:
				writer.printf("// pop %s %d\n", segment, index);
				switch(segment) {
					case "constant":
						writer.println("@" + index);
						break;
					case "local":
						loadSegment("LCL", index);
						break;
					case "argument":
						loadSegment("ARG", index);
						break;
					case "this":
						loadSegment("THIS", index);
						break;
					case "that":
						loadSegment("THAT", index);
						break;
					case "pointer":
						writer.println("@R" + String.valueOf(3+index));
						break;
					case "temp":
						writer.println("@R" + String.valueOf(5+index));
						break;
					case "static":
						writer.println("@" + filename.split("\\.")[0] + String.valueOf(index));
						break;
				}
				writer.println("D=A");
				writer.println("@R13");
				writer.println("M=D");
				popStackToD();
				writer.println("@R13");
				writer.println("A=M");
				writer.println("M=D");
				break;
		}
	}

	/**
	 * Closes writer
	 */
	public void close() {
		writer.close();
	}

	private void incStackPointer() {
		writer.println("@SP");
		writer.println("M=M+1");
	}

	private void decStackPointer() {
		writer.println("@SP");
		writer.println("M=M-1");
	}

	private void popStackToD() {
		decStackPointer();
		writer.println("A=M");
		writer.println("D=M");
	}

	private void pushDToStack() {
		loadStackPointerToA();
		writer.println("M=D");
		incStackPointer();
	}

	private void loadStackPointerToA() {
		writer.println("@SP");
		writer.println("A=M");
	}

	private void writeCompareLogic(String jumpCmd) {
		popStackToD();
		decStackPointer();
		loadStackPointerToA();
		writer.println("D=M-D");
		writer.println("@LABEL" + labelNum);
		writer.println("D;" + jumpCmd);
		loadStackPointerToA();
		writer.println("M=0");
		writer.println("@ENDLABEL" + labelNum);
		writer.println("0;JMP");
		writer.println("(LABEL" + labelNum + ")");
		loadStackPointerToA();
		writer.println("M=-1");
		writer.println("(ENDLABEL" + labelNum + ")");
		incStackPointer();
		labelNum++;
	}

	private void loadSegment(String seg, int idx) {
		writer.println("@" + seg);
		writer.println("D=M");
		writer.println("@" + String.valueOf(idx));
		writer.println("A=D+A");
	}
	
	/**
	 * Writes line in file open in FileWriter
	 * @param line	Line to write in file
	 */
	private void writeLine(String line) {
        writer.println(line);
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
}
