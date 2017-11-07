// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)


@R2
M=0

@R0
D=M
@END
D;JLE
@R1
D=M
@END
D;JLE

@R2
M=0

@R0
D=M
(LOOP)
@END
D;JLE

@i
M=D

@R1
D=M
@R2
M=M+D

@i
D=M
D=D-1
@LOOP
0;JMP
(END)