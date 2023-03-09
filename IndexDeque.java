// CS 0445 Spring 2023
// Luke Simpson
// Assignment 1
// IndexDeque class that extends MyDeque and implements the Indexable Interface
import java.util.*;

public class IndexDeque<T> extends MyDeque<T> implements Indexable<T>
{
	//Subclass Constructor
	public IndexDeque(int size)
	{	super(size);	}
	
	//Gets and returns the value located at logical location i from the front of the collection
	//If the collection has fewer than (i+1) items, or if i < 0, an IndexOutOfBoundsException is thrown
	public T getFront(int i)
	{
		//Checks if the index is valid
		if(size()<i+1||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//checks to see if the data is wrapped
		if(front+i>=capacity())
			return data[front+i-capacity()];
		else
			return data[front+i];
	}

	//Gets and returns the value located at logical location i from the back of the collection
	//If the collection has fewer than (i+1) items, or if i < 0, an IndexOutOfBoundsException is thrown	
	public T getBack(int i)
	{
		//Checks if the index is valid
		if(size()<i+1||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//checks to see if the data is wrapped
		if(back-i<0)
			return data[back-i+capacity()];
		else
			return data[back-i];
	}
	
	//Set the value located at logical location i from the front of the collection
	//If the collection has fewer than (i+1) items, or if i < 0, an IndexOutOfBoundsException is thrown
	public void setFront(int i, T item)
	{
		//Checks if the index is valid
		if(size()<i+1||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//checks to see if the data is wrapped
		if(front+i>=capacity())
			data[front+i-capacity()] = item;
		else
			data[front+i] = item;	
	}

	//Set the value located at logical location i from the back of the collection
	//If the collection has fewer than (i+1) items, or if i < 0, an IndexOutOfBoundsException is thrown	
	public void setBack(int i, T item)
	{
		//Checks if the index is valid
		if(size()<i+1||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//checks to see if the data is wrapped
		if(back-i<0)
			data[back-i+capacity()] = item;
		else
			data[back-i] = item;			
	}
	
	// Return the logical size of this Indexable
	public int size()
	{	return n;	}
}