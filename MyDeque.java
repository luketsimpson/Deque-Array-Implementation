// CS 0445 Spring 2023
// Luke Simpson
// Assignment 1
// MyDeque Class that implement the Deque Interface
import java.util.*;

public class MyDeque<T> implements DequeInterface<T>
{
	protected T [] data;
	protected int front, back, n; //n is the number of items

	//Constructor
	//@param the size to set the array	
	public MyDeque(int size)
	{
		//Creates a new array of the given size and casts it to type T
		data = (T []) new Object[size];
		
		//Initializes variables
		front = -1;
		back = -1;
		n = 0;
	}
	
	//Copy Constructor 
	//@param the old deque
	public MyDeque(MyDeque<T> old)
	{
		//Creates the new array with a size of the old array capacity 
		data = (T []) new Object[old.data.length];
		
		//Transfers the contents of the array
		for(int i=0; i<old.data.length; i++)
			data[i] = old.data[i];
	
		//Initializes variables
		front = old.front;
		back = old.back;
		n = old.size();
	}	
	
	//Returns a string with the objects in their logical order seperated by a space
	//@return the string of object values if there are items in the array
	public String toString()
	{
		// Make and return single String from data stored in Deque
		String s = new String("Contents:");
		for(int i=0;i<size();i++)
		{
			//Checks to make sure the information did not wrap
			if(front+i<data.length)
				s = s+ " " +data[front+i]; //Adds to the string
			else	
				s = s+ " " +data[front+i-data.length]; //Adds to the string
		}				
		return s; //returns the string
	}
	
	//Checks if the class object is equal to parameter object
	//@param the object to check
	//@return True if the objects are equal, False otherwise
	public boolean equals(MyDeque<T> rhs)
	{	
		//first checks to see if both deques are the same length
		if(rhs.size() != size())
			return false;
		
		//creates temprary variables to hold the front index
		int temp1 = front;
		int temp2 = rhs.front;
		
		//Checks each item
		for(int i=0; i<size(); i++)
		{
			//check both arrays 
			if(!(data[temp1].equals(rhs.data[temp2])))
				return false;
			
			//increments the temporary front of the class object
			if(temp1 == capacity()-1)
				temp1=0;
			else
				temp1++;
			
			//increments the temporary front of the parameter object
			if(temp2 == rhs.capacity()-1)
				temp2=0;
			else
				temp2++;
		}			
		
		return true; //returns true if everything is the same
	}
	
	//Adds a new entry to the front of the deque
	//@param the entry to add
	public void addToFront(T newEntry)
	{
		//Resizes the array if needed
		if(size()==capacity())
			resize(); //resizes the array

		//checks to see where to add to the front
		if(isEmpty()) //Check if there is anything in the array
		{
			data[0] = newEntry; //adds to the 0 index
			front = 0; //Sets the front index to 0
			back = 0; //Sets the back index to 0
		}
		else if(front==0) //Checks if the front is at 0 causing the data to need to be wrapped
		{
			data[capacity()-1] = newEntry; //sets the front to the back of the physical deque
			front = capacity()-1;
		}
		else
		{
			front--; //Decrements the front for the new value to be added to the front
			data[front] = newEntry;
		}
		
		n++; //increases the size of the deque		
	}
	
	//Adds a new entry to the back of the deque
	//@param the entry to add
	public void addToBack(T newEntry)
	{
		//Resizes the array if needed
		if(size()==capacity())
			resize(); //resizes the array
			
		//checks to make sure the data does not need to be wrapped
		if(isEmpty()) //Check if there is anything in the array
		{
			data[0] = newEntry; //adds to the 0 index
			front = 0; //Sets teh front index to 0
			back = 0; //Sets the back index to 0
		}
		else if(back==capacity()-1) //Checks if the back is at the phsycial back of the deque causing the data to need to be wrapped
		{
			data[0] = newEntry; // adds the new entry to the front of the array which becomes the logical back
			back=0; //sets the back to 0
		}
		else
		{
			back++; //increases the index of the back
			data[back] = newEntry; //adds the new entry to the back
		}
		
		n++; //increases the size of the deque
	}
	
	//Removes the entry at the logical front of the deque 
	//@return the object at the front that was removed
	//@return null if the deque is empty before the operation
	public T removeFront()
	{	
		//checks to see if the deque is empty
		if(isEmpty())
			return null; //returns null if the deque is empty
		else
		{
			//gets the object to be returned in a temporary variable
			T temp = data[front];
			
			//sets the removed value to null
			data[front] = null;
			n--; //Decrements the logical number of items in the array
			
			//checks if the logical front is at the physical back
			//resets the back variable
			if(isEmpty())
				back = -1; //sets the front to -1 if data is now empty
			else if(front == capacity()-1)
				front = 0; //resets the front to 0
			else	
				front++; //increases the front by 1
			
			//returns the removed data
			return temp;			
		}
	}
	
	//Removes the entry at the logical back of the deque 
	//@return the object at the back that was removed
	//@return null if the deque is empty before the operation
	public T removeBack()
	{	
		//checks to see if the deque is empty
		if(isEmpty())
			return null; //returns null if the deque is empty
		else
		{
			//gets the object to be returned in a temporary variable
			T temp = data[back];
			
			//sets the removed value to null
			data[back] = null;
			n--; //Decrements the logical number of items in the array
			
			//resets the back variable
			if(isEmpty())
				back = -1; //sets the back to -1 if data is now empty
			else if(back==0 && data[capacity()-1] != null)
				back = capacity()-1; //resets the back to the physical end
			else
				back--; //decreases the back by 1
			
			//returns the removed data
			return temp;			
		}
	}

	//Retrieves the logical front of the deque
	//@return the object at the front of the deque
	//@return null if the deque is empty
	public T getFront()
	{	
		if(isEmpty())
			return null;
		else
			return data[front];
	}
	
	//Retrieves the logical back of the deque
	//@return the object at the back of the deque
	//@return null if the deque is empty
	public T getBack()
	{	
		if(isEmpty())
			return null;
		else
			return data[back];
	}
	
	//Detects whether the deque is empty
	//return True if the deque is empty, false otherwise
	public boolean isEmpty()
	{	
		//checks to see if there is any logical data in the array
		if(size()==0)
			return true;
		else	
			return false;
	}
	
	//Removes all entries from the deque
	public void clear()
	{
		// reset deque to be empty 
		data = (T []) new Object[data.length];

		//resets the variables
		front = 0;
		back = -1;
		n = 0;
	}
	
	//Returns the number of items currently stored in the deque
	//@return an int equal to the logical size of the deque
	public int size()
	{	return n;	}
	
	//returns the capacity of the deque, counting the used and unused locations
	//@return an int equal to the capacity of the Deque
	public int capacity()
	{	return data.length;	}
	
	//Resizes the data array in the class
	public void resize()
	{
		//Creates a temporary array that is double the size of the original
		T [] temp = (T []) new Object[data.length*2];
		
		//Initializes the new temporary array
		for(int i=0; i<data.length; i++)
		{
			if(front+i<=data.length) //condition for if the data wraps
				temp[i] = data[front+i];
			else	
				temp[i] = data[front+i-data.length];
		}
		
		//Sets the temp to the data
		data = temp;
		
		front = 0;
		back = n-1;
	}

} // end DequeInterface