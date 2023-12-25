# Concurrent and Parallel Programming
#### Topics
<ol>
  <li>Hardware 
    <ul>
      <li> Sequential vs parallel computing
      <li> Parallel computing architecture. Flynn's taxonomy.  
      <li> Shared vs distributed memory
    </ul>
  <li>Threads and Processes 
    <ul>
      <li> What are processes and threads?
      <li> Concurrent vs parallel execution
      <li> OS Scheduler
      <li> Thread Lifecycle
      <li> Thread attributes
      <li>Inheritance (extends) vs Interface (implements)
      <li> revision on SOLID principle
      <li> Daemon thread. Garbage collection.
    </ul>
  <li>Mutual Exclusion 
    <ul>
      <li> Data race
      <li> Mutual exclusion
      <li> Atomic variable
      <li> Synchronized method
      <li> Synchronized statement
    </ul>
  <li>Locks 
    <ul>
      <li> Reentrant lock
      <li> Try lock
      <li> Read-write lock
    </ul>
  <li>Liveness
    <ul>
      <li> Deadlock
      <li> Abandoned lock
      <li> Starvation
      <li> Livelock
    </ul>  
</ol>

<p> Codes are in the "Parallel and Concurrent Processing" folder.<br>
You can run the .java files in the Parallel and Concurrent Processing/src folder using Visual Studio Code.<br>
You can run the .class files in the Parallel and Concurrent Processing/production folder using a "cmd" window.  Navigate to the folder with the desired .class file. Type "cmd" in the search bar to open cmd window for that specific folder. To run, type "java file_name.class".

Good references:<br>
MIT software construction course<br>
https://web.mit.edu/6.005/www/fa15/classes/23-locks/#introduction

The Art of Multiprocessor Programming.<br>
https://books.google.com.sg/books?id=qGURkdAr42cC&lpg=PA157&dq=abandoned%20locks%20java&pg=PR12#v=onepage&q=abandoned%20locks%20java&f=false

Oracle Berkeley DB - Java Locks, Blocks and Deadlocks<br>
https://docs.oracle.com/cd/E17276_01/html/gsg_xml_txn/java/blocking_deadlocks.html

Project Loom <br>
https://wiki.openjdk.org/display/loom/Main <br>
Java runtime implementation of concurrency

Garbage Collection intro video <br>
https://www.infoq.com/presentations/Understanding-Java-Garbage-Collection/
