Welcome to MiniOS, a simple operating system simulator for learning and demonstration purposes.
This manual explains how to use the command-line interface (CLI) and, if included, the GUI.
Steps for CLI:
1.	Compile the code
a.	Open a terminal in the project directoery and run: “javac *.java”
2.	Start the MiniOS
a.	For CLI version: “java Main”
b.	For GUI version: “java MiniOSGUI”
3.	Commands: 
a.	For process management:
Command	Description	Example
create [name]	Create a new process with a chosen name	create alpha
ps	List all processes and their states	Ps
schedule	Run the scheduler (simulates process execution)	schedule

b.	For memory management 
Command	Description	Example
alloc [pid] [size]	Allocate memory of given size to the process (pid)	alloc 1 10
free [pid]	Free all memory allocated to the process (pid)	free 1
mem	Show the current memory layout	mem

Command	Description	Example
exit 	Exit MiniOS	exit
c.	For the system

4.	Error handling
a.	If you type an invalid command or use the wrong arguments, miniOS will print a helpful message
b.	If you try to allocate more memory than available, you will see:
i.	“allocation failed: Not enough memory”
c.	If you try to free memory for a PID not in use, you will see:
i.	“No memory found for PID x
5.	Tips
a.	Always check the PID using ps before allocating or freeing memory
b.	Use mem often to visualize memory usage 
c.	You can create as many processes as you like, limited by system and memory constraints
For GUI version:
•	Enter process names and press enter or click ‘create process’ to add
•	Enter PID and size then press enter or click ‘allocate memory’ to allocate
•	Clikc ‘schedule’ to run the scheduler
•	Click ‘show memory’ to refresh the momory display
•	Process and memory lists update automatically after actions
