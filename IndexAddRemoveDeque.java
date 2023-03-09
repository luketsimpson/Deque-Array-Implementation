// CS 0445 Spring 2023
// Luke Simpson
// Assignment 1
// IndexAddRemoveDeque class that extends IndexDeque and implements the IndexableAddRemove Interface
import java.util.*;

public class IndexAddRemoveDeque<T> extends IndexDeque<T> implements IndexableAddRemove<T>
{
	//Subclass Constructor
	public IndexAddRemoveDeque(int size)
	{	super(size);	}
	
	//Add the value at logical location i from the front of the collection
	//If the collection has fewer than i items, or if i < 0,  an IndexOutOfBoundsException is thrown
	//@param the location from the front to add the item
	//@param the item to add
	public void addToFront(int i, T item)
	{
		//Checks if the index is valid
		if(capacity()<i||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//Resizes the array if needed
		if(size()==capacity())
			resize(); //resizes the array
		
		//First checks if this is the first item being added
		if(size()==0)
		{
			data[0] = item; //adds to the 0 index
			front = 0; //Sets the front index to 0
			back = 0; //Sets the back index to 0
			n++;
		}
		else
		{
			
			//Checks to see if the data wrapped
			if(front+i>=capacity())
			{
				//checks if the data needs shifted
				if(data[front+i-capacity()]==null)
				{
					data[front+i-capacity()] = item; //inserts the item without shifting 
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
				else
				{
					shiftAdd(front+i-capacity());//shifts to make room at the given space
					data[front+i-capacity()] = item;//inserts the new item into the new space created
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
			}
			else
			{
				//checks if the data needs shifted
				if(data[front+i]==null)
				{
					data[front+i] = item; //inserts the item without shifting
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
				else
				{
					shiftAdd(front+i);//shifts to make room at the given space
					data[front+i] = item;
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
				
			}
		}
			
	}

	//Add the value at logical location i from the back of the collection
	//If the collection has fewer than i items, or if i < 0, an IndexOutOfBoundsException is thrown
	//@param the location from the back to add the item
	//@param the item to add
	public void addToBack(int i, T item)
	{
		//Checks if the index is valid
		if(capacity()<i||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
		
		//Resizes the array if needed
		if(size()==capacity())
			resize(); //resizes the array
		
		//First checks if this is the first item being added
		if(size()==0)
		{
			data[0] = item; //adds to the 0 index
			front = 0; //Sets the front index to 0
			back = 0; //Sets the back index to 0
			n++;
		}
		else
		{
			//Checks to see if the data wrapped
			if(back-i<0)
			{
				//checks if the data needs shifted
				if(data[back-i+capacity()]==null)
				{
					data[back-i+capacity()+1] = item; //inserts the item without shifting 
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
				else
				{
					shiftAdd((back-i+capacity())-front);
					data[back-i+capacity()+1] = item;
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
			}
			else
			{
				//checks if the data needs shifted
				if(data[back-i]==null)
				{
					data[back-i+1] = item; //inserts the item without shifting
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
				else
				{
					shiftAdd((back-i)-front);
					data[back-i+1] = item;
					
					back++;//moves the back index back one
					n++;//increases the size of the deque
				}
			}
		}
	}
	
	//Shifts the data to make room for the inserted data
	//@param the index of the space needed from the front
	private void shiftAdd(int space)
	{
		for(int i=back; i!=space-1; i--)
		{
			//shifts the data back
			data[i+1] = data[i];
			
			//checks if the data wrapped 
			if(i==0&&space!=0)
				i=capacity();			
		}
	}

	//Remove and return the item at logical location i from the front of the collection
	//If the collection has fewer than (i+1) items, or if i < 0, an IndexOutOfBoundsException is thrown
	//@param the index from the front to remove
	//@return the item that was removed
	public T removeFront(int i)
	{
		//Checks if the index is valid
		if(capacity()<i||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);

		//Creates the temporary variable to return 
		T temp;

		//Checks to see if the data wrapped
		if(front+i>=capacity())
		{
			temp = data[front+i-capacity()]; //Sets the temporary variable to the item that needs to be removed
			shiftRemove(front+i-capacity()); //Goes to a method that removes the item 
			
			data[back]=null; //Sets the back value to null
			
			checkEdge(front+i-capacity()); //Checks if the back or front was removed causing a special case for the front and back index
			n--; //Decrements the number of items in the deque
			
			return temp; //returns the removed value
			
		}
		else
		{
			temp = data[front+i]; //Sets the temporary variable to the item that needs to be removed
			shiftRemove(front+i); //Goes to a method that removes the item
			
			data[back] = null; //Sets the back value to null
			
			checkEdge(front+i); //Checks if the back or front was removed causing a special case for the front and back index
			n--; //Decrements the number of items in the deque
			
			return temp; //returns the removed value
		}
	}

	//Remove and return the item at logical location i from the back of the collection
	//If the collection has fewer than (i+1) items, or if i < 0,  an IndexOutOfBoundsException is thrown
	//@return the item that was removed
	//@param the index from the back to remove
	public T removeBack(int i)
	{ 
		//Checks if the index is valid
		if(capacity()<i||i<0)
			throw new IndexOutOfBoundsException("Illegal Index " +i);
 
		//Checks to see if the data wrapped
		T temp;
		if(back-i<0)
		{
			temp = data[back-i+capacity()]; //Sets the temporary variable to the item that needs to be removed
			shiftRemove(back-i+capacity()); //Goes to a method that removes the item
			
			data[back] = null; //Sets the back value to null
			
			checkEdge(back-i+capacity()); //Checks if the back or front was removed causing a special case for the front and back index
			n--; //Decrements the number of items in the deque
			
			return temp; //returns the removed value
			
		}
		else
		{
			temp = data[back-i]; //Sets the temporary variable to the item that needs to be removed
			shiftRemove(back-i); //Goes to a method that removes the item
			
			data[back] = null; //Sets the back value to null
			
			checkEdge(back-i); //Checks if the back or front was removed causing a special case for the front and back index
			n--; //Decrements the number of items in the deque
			
			return temp; //returns the removed value
		}
		
		

	}
	
	//Shifts the data to make room for the inserted data
	//@param the index of the space needed from the front
	private void shiftRemove(int space)
	{
		//Runs a for loop that starts at the position to be removed and finishes at the back
		for(int i=space; i!=back; i++)
		{
			data[i] = data[i+1]; //shifts the data forward
			
			//checks if the data wrapped 
			if(i==capacity()-1&&space!=capacity()-1)
				i=-1;			
		}
	}
	
	//This method checks if an edge was removed to check the fron or back variable
	public void checkEdge(int edge)
	{
		if(edge==back) //Checks if the item to be removed is at the logical back
		{
			if(back==0&&size()==1) //checks if there is just one item in the deque
			{
				//resets both indexes to -1
				front=-1;
				back=-1;
			}
			else if(back==0) //checks if the data wrapped
			{
				back=capacity()-1;
			}
		}
		else
		{
			back--; //decrements the back index
		}
	}
}