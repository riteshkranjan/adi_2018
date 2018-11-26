DS in java.

To test one java implementations run:  
$ javac com/uca/ds/queue/TestQueue.java  
$ java -ea com.uca.ds.queue.TestQueue  

To compile and run complete test suite:  
$ rm $(find * -name \*.class)  
$ javac $(find * -name \*.java)  
$ java -cp "test;src" -ea com.uca.test.suite.TestRunner  
