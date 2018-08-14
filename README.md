# OperatingSysXML
This program simulates the basic fonctionality of an operating system.

## Overview
A thread called generator, generates process and the system executes them.
Here is the _flow_ of a process, from the creation to the death
* The generator choose a random number from 1 to 5 (each of those numbers refers to a different program installed on the Disk).
* The generator Make a system call to tell the OS that he wants to create this process
* The system call is taken by the OS.
* The OS verify the adress corresponding to the call and execute the subroutine of that call
* _The System locates the Program on the Disk_
* _Calculate its size_
* _The MMU allocates the memory to the process_
* _The process is now loaded to the memory_
* _A PCB is automatically created and placed in the readyqueue._
    (Once in the ready queue, Another thread called executor can pick the PCB)
* The executor pick the PCB, updates the status of the process ( ready to executing )
* After updating the status, the CPU Executes the process.
* **_here will be the interruption section_**
* When the process has finished executing, the CPU make the system call to kill the process.
* The OS execute the subroutine that will kill the process and free the memory allocated to it.


## Functionnality
**The following features are implemented**
* [X] A thread can generate process
* [X] The process are placed in the memory by the MMU
* [X] The process is executed by the CPU
* [ ] Each process can generate interruptions  
* [ ] Interruption are handled  by the interrupt handler
* [ ] Each interruption is referenced in the interruption vector table and/ or the syscall table
* [ ] After the interruption, the program must return at the exact same place where it was executing

