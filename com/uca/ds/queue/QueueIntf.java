package com.uca.ds.queue;
public interface QueueIntf{

  public void enqueue(int e);

  public int dequeue();

  public boolean isEmpty();

  public int size();
  
  public int peek();

}
