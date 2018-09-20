package com.uca.ds.queue;
public class TestQueue{

   public static void main(String... s){
     testQueueFunctions(new BasicQueue());
     testQueueFunctions(new CircularQueue());
     testQueueFunctions(new LinkedListQueue());
   }
   
   public static void testQueueFunctions(QueueIntf q){
	  assert q.size() == 0;
      assert q.isEmpty() == true;
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);
      q.enqueue(4);
	  assert q.peek() == 1;
	  assert q.size() == 4;
      assert q.dequeue() == 1;
      assert q.size() == 3;
      assert q.dequeue() == 2;
      assert q.size() == 2;
      assert q.dequeue() == 3;
      assert q.size() == 1;
      assert q.dequeue() == 4;
      assert q.size() == 0;
      assert q.isEmpty() == true;
      System.out.println("All test cases passed for " + q.getClass().getName());
   }
}
