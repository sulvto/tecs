@256
D=A
@SP
M=D
@returnAddress0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.init
0;JMP
(returnAddress0)
(Sys.init)
@4000
D=A
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@returnAddress1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@5
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.main
0;JMP
(returnAddress1)
@6
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
(LOOP)
@LOOP
0;JMP
(Sys.main)
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@0
D=A
@SP
A=M
M=D
@SP
M=M+1
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@200
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@1
D=A+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@40
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@2
D=A+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@6
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@3
D=A+D
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@123
D=A
@SP
A=M
M=D
@SP
M=M+1
@returnAddress2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
A=D
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
D=M
@6
D=D-A
@ARG
M=D
@SP
D=M
@LCL
M=D
@Sys.add12
0;JMP
(returnAddress2)
@5
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@LCL
D=M
@0
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@1
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@2
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@3
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@4
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@SP
AM=M-1
D=M
A=A-1
M=M+D
@SP
AM=M-1
D=M
A=A-1
M=M+D
@SP
AM=M-1
D=M
A=A-1
M=M+D
@SP
AM=M-1
D=M
A=A-1
M=M+D
@LCL
D=M
@R5
M=D
@5
D=D-A
@R6
M=D
@SP
AM=M-1
D=M
@ARG
A=M
M=D
@1
D=A
@ARG
D=M+D
@SP
M=D
@R5
D=M
@1
A=D-A
D=M
@THAT
M=D
@R5
D=M
@2
A=D-A
D=M
@THIS
M=D
@R5
D=M
@3
A=D-A
D=M
@ARG
M=D
@R5
D=M
@4
A=D-A
D=M
@LCL
M=D
@R6
A=M
A=M
0;JMP
(Sys.add12)
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@5002
D=A
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=A
@R13
M=D
@SP
AM=M-1
D=M
@R13
A=M
M=D
@ARG
D=M
@0
A=A+D
D=M
@SP
A=M
M=D
@SP
M=M+1
@12
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
AM=M-1
D=M
A=A-1
M=M+D
@LCL
D=M
@R5
M=D
@5
D=D-A
@R6
M=D
@SP
AM=M-1
D=M
@ARG
A=M
M=D
@1
D=A
@ARG
D=M+D
@SP
M=D
@R5
D=M
@1
A=D-A
D=M
@THAT
M=D
@R5
D=M
@2
A=D-A
D=M
@THIS
M=D
@R5
D=M
@3
A=D-A
D=M
@ARG
M=D
@R5
D=M
@4
A=D-A
D=M
@LCL
M=D
@R6
A=M
A=M
0;JMP