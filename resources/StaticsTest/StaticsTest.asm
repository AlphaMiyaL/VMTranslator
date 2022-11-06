// init
@256
D=A
@SP
M=D
// call Sys.init 0
// save calling function
@return-address0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Sys.init
@Sys.init
0;JMP
// declare return address label
// C_LABEL return-address0
(return-address0)
// function Class1.set0
// C_LABEL Class1.set
(Class1.set)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 3static0
@SP
AM=M-1
D=M
@Class1.0
M=D
// 2argument1
@1
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 3static1
@SP
AM=M-1
D=M
@Class1.1
M=D
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
// set FRAME = LCL
@LCL
D=M
@FRAME
M=D
// set RET = FRAME - 5
@5
A=D-A
D=M
@RET
M=D
// set ARG = pop()
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// restore SP of the caller
@ARG
D=M+1
@SP
M=D
// restore THAT of the caller
@FRAME
A=M-1
D=M
@THAT
M=D
// restore THIS of the caller
@FRAME
D=M
@2
A=D-A
D=M
@THIS
M=D
// restore ARG of the caller
@FRAME
D=M
@3
A=D-A
D=M
@ARG
M=D
// restore LCL of the caller
@FRAME
D=M
@4
A=D-A
D=M
@LCL
M=D
// goto RET
@RET
A=M
0;JMP
// function Class1.get0
// C_LABEL Class1.get
(Class1.get)
// 2static0
@Class1.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2static1
@Class1.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// return
// set FRAME = LCL
@LCL
D=M
@FRAME
M=D
// set RET = FRAME - 5
@5
A=D-A
D=M
@RET
M=D
// set ARG = pop()
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// restore SP of the caller
@ARG
D=M+1
@SP
M=D
// restore THAT of the caller
@FRAME
A=M-1
D=M
@THAT
M=D
// restore THIS of the caller
@FRAME
D=M
@2
A=D-A
D=M
@THIS
M=D
// restore ARG of the caller
@FRAME
D=M
@3
A=D-A
D=M
@ARG
M=D
// restore LCL of the caller
@FRAME
D=M
@4
A=D-A
D=M
@LCL
M=D
// goto RET
@RET
A=M
0;JMP
// function Class2.set0
// C_LABEL Class2.set
(Class2.set)
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 3static0
@SP
AM=M-1
D=M
@Class2.0
M=D
// 2argument1
@1
D=A
@ARG
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 3static1
@SP
AM=M-1
D=M
@Class2.1
M=D
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// return
// set FRAME = LCL
@LCL
D=M
@FRAME
M=D
// set RET = FRAME - 5
@5
A=D-A
D=M
@RET
M=D
// set ARG = pop()
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// restore SP of the caller
@ARG
D=M+1
@SP
M=D
// restore THAT of the caller
@FRAME
A=M-1
D=M
@THAT
M=D
// restore THIS of the caller
@FRAME
D=M
@2
A=D-A
D=M
@THIS
M=D
// restore ARG of the caller
@FRAME
D=M
@3
A=D-A
D=M
@ARG
M=D
// restore LCL of the caller
@FRAME
D=M
@4
A=D-A
D=M
@LCL
M=D
// goto RET
@RET
A=M
0;JMP
// function Class2.get0
// C_LABEL Class2.get
(Class2.get)
// 2static0
@Class2.0
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2static1
@Class2.1
D=M
@SP
A=M
M=D
@SP
M=M+1
// sub
@SP
AM=M-1
D=M
A=A-1
M=M-D
// return
// set FRAME = LCL
@LCL
D=M
@FRAME
M=D
// set RET = FRAME - 5
@5
A=D-A
D=M
@RET
M=D
// set ARG = pop()
@SP
AM=M-1
D=M
@ARG
A=M
M=D
// restore SP of the caller
@ARG
D=M+1
@SP
M=D
// restore THAT of the caller
@FRAME
A=M-1
D=M
@THAT
M=D
// restore THIS of the caller
@FRAME
D=M
@2
A=D-A
D=M
@THIS
M=D
// restore ARG of the caller
@FRAME
D=M
@3
A=D-A
D=M
@ARG
M=D
// restore LCL of the caller
@FRAME
D=M
@4
A=D-A
D=M
@LCL
M=D
// goto RET
@RET
A=M
0;JMP
// function Sys.init0
// C_LABEL Sys.init
(Sys.init)
// 2constant6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Class1.set 2
// save calling function
@return-address1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@2
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Class1.set
@Class1.set
0;JMP
// declare return address label
// C_LABEL return-address1
(return-address1)
// 3temp0
@SP
AM=M-1
D=M
@R5
M=D
// 2constant23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Class2.set 2
// save calling function
@return-address2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@2
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Class2.set
@Class2.set
0;JMP
// declare return address label
// C_LABEL return-address2
(return-address2)
// 3temp0
@SP
AM=M-1
D=M
@R5
M=D
// call Class1.get 0
// save calling function
@return-address3
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Class1.get
@Class1.get
0;JMP
// declare return address label
// C_LABEL return-address3
(return-address3)
// call Class2.get 0
// save calling function
@return-address4
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
// reposition ARG
@SP
D=M
@0
D=D-A
@5
D=D-A
@ARG
M=D
// reposition LCL
@SP
D=M
@LCL
M=D
// transfer control
// C_GOTO Class2.get
@Class2.get
0;JMP
// declare return address label
// C_LABEL return-address4
(return-address4)
// C_LABEL WHILE
(WHILE)
// C_GOTO WHILE
@WHILE
0;JMP
