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
// function Sys.init0
// C_LABEL Sys.init
(Sys.init)
// 2constant4000
@4000
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer0
@SP
AM=M-1
D=M
@THIS
M=D
// 2constant5000
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer1
@SP
AM=M-1
D=M
@THAT
M=D
// call Sys.main 0
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
// C_GOTO Sys.main
@Sys.main
0;JMP
// declare return address label
// C_LABEL return-address1
(return-address1)
// 3temp1
@1
D=A
@R5
D=D+A
@R13
M=D
@SP
AM=M-1
D=M
@R13
M=D
// C_LABEL LOOP
(LOOP)
// C_GOTO LOOP
@LOOP
0;JMP
// function Sys.main5
// C_LABEL Sys.main
(Sys.main)
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
// 2constant4001
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer0
@SP
AM=M-1
D=M
@THIS
M=D
// 2constant5001
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer1
@SP
AM=M-1
D=M
@THAT
M=D
// 2constant200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3local1
@1
D=A
@LCL
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3local2
@2
D=A
@LCL
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3local3
@3
D=A
@LCL
D=D+M
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
// 2constant123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1
// call Sys.add12 1
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
@1
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
// C_GOTO Sys.add12
@Sys.add12
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
// 2local0
@LCL
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2local1
@1
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2local2
@2
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2local3
@3
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2local4
@4
D=A
@LCL
A=D+M
D=M
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
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
// function Sys.add120
// C_LABEL Sys.add12
(Sys.add12)
// 2constant4002
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer0
@SP
AM=M-1
D=M
@THIS
M=D
// 2constant5002
@5002
D=A
@SP
A=M
M=D
@SP
M=M+1
// 3pointer1
@SP
AM=M-1
D=M
@THAT
M=D
// 2argument0
@ARG
A=M
D=M
@SP
A=M
M=D
@SP
M=M+1
// 2constant12
@12
D=A
@SP
A=M
M=D
@SP
M=M+1
// add
@SP
AM=M-1
D=M
A=A-1
M=D+M
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
