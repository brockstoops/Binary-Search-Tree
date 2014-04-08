//Brock Stoops
//b3068055
// COP 3503H, Fall 2013

// BST.java
// ========
// Basic binary search tree (BST) implementation that supports insert() and
// delete() operations. This framework is provide for you to modify as part of
// Programming Assignment #2.


import java.io.*;
import java.util.*;

//Declare node class and make it extend Comparable so you don't need to type 
//up your own compareTo method.
class Node<AnyType extends Comparable<AnyType>>
{
	//set data and left and right nodes of BST
	AnyType data;
	Node<AnyType> left, right;

	Node(AnyType data)
	{
		this.data = data;
	}
}
//Make the BST be able to take anytype and extend Comparable to use the compareTo method
public class GenericBST<AnyType extends Comparable<AnyType>>
{
	private Node<AnyType> root;

	//Function to insert a node of anytype into the root of the BST
	public void insert(AnyType data)
	{
		root = insert(root, data);
	}
	//Insert node into the BST
	private Node<AnyType> insert(Node<AnyType> root, AnyType data)
	{
		//If root node is null return the data node trying to insert
		if (root == null)
		{
			return new Node<AnyType>(data);
		}
		//Compare the nodes to find if needs to go left or right subtree
		//If data is less than root, put in left subtree
		else if (data.compareTo(root.data) < 0)
		{
			root.left = insert(root.left, data);
		}
		//If data is more than root put node in right subtree
		else if (data.compareTo(root.data) > 0)
		{
			root.right = insert(root.right, data);
		}
		else
		{
			// Stylistically, I have this here to explicitly state that we are
			// disallowing insertion of duplicate values.
			;
		}

		return root;
	}
	//Function to delete the rood node out of the BST
	public void delete(AnyType data)
	{
		root = delete(root, data);
	}
	//Function that will delete any node out of the BST
	private Node<AnyType> delete(Node<AnyType> root, AnyType data)
	{
		//If root is null return null
		if (root == null)
		{
			return null;
		}
		//If data node is less than root data go left in subtree and run delete function again
		else if (data.compareTo(root.data) < 0)
		{
			root.left = delete(root.left, data);
		}
		//If node data is more than root data go to right subtree and run delete function again
		else if (data.compareTo(root.data) > 0)
		{
			root.right = delete(root.right, data);
		}
		//This else will run when you run delete function and find the node you want to delete
		else
		{
			//If it is a leaf node, just return null
			if (root.left == null && root.right == null)
			{
				return null;
			}
			//If right node is null and left isn't, choose left to take place of node you're deleting and return
			else if (root.right == null)
			{
				return root.left;
			}
			//If left node is null and right isn't, choose right to take 
			//place of node you're deleting and return
			else if (root.left == null)
			{
				return root.right;
			}
			//If there are two children in node you want to delete find maximum value in
			//left subtree and use that to replace the node you're deleting
			else
			{
				root.data = findMax(root.left);
				root.left = delete(root.left, root.data);
			}
		}

		return root;
	}

	// This method assumes root is non-null, since this is only called by
	// delete() on the left subtree, and only when that subtree is non-empty.
	private AnyType findMax(Node<AnyType> root)
	{
		//While loop to go to right most node in subtree
		//Guaranteed to be max value based on definition of BST
		while (root.right != null)
		{
			root = root.right;
		}

		return root.data;
	}

	// Returns true if the value is contained in the BST, false otherwise.
	public boolean contains(AnyType data)
	{
		return contains(root, data);
	}

	//Searches through the BST to find if the node is in the tree
	private boolean contains(Node<AnyType> root, AnyType data)
	{
		//Empty tree return false
		if (root == null)
		{
			return false;
		}
		//If data is less than the root go left
		else if (data.compareTo(root.data) < 0)
		{
			return contains(root.left, data);
		}
		//If data is greater than root go right
		else if (data.compareTo(root.data) >0)
		{
			return contains(root.right, data);
		}
		//If value equals the node return true, found the value
		else
		{
			return true;
		}
	}
	//Method to print out the In-Order traversal of the BST
	public void inorder()
	{
		//Calls the recursive inorder method here
		System.out.print("In-order Traversal:");
		inorder(root);
		System.out.println();
	}
	//Method to actually calculate the In-Order traversal of the BST
	private void inorder(Node<AnyType> root)
	{
		if (root == null)
			return;
		
		//Recursive function to calculate the In-Order traversal by going left then node
		//then right
		inorder(root.left);
		System.out.print(" " + root.data);
		inorder(root.right);
	}
	//Method to print out the Pre-order traversal
	public void preorder()
	{
		//Calls recursive preoder method here
		System.out.print("Pre-order Traversal:");
		preorder(root);
		System.out.println();
	}

	//Method that actually calculates the Pre-order traversal
	private void preorder(Node<AnyType> root)
	{
		//node is null return to previous recurion field
		if (root == null)
			return;

		//Recursive relations to get Pre-order traversal by going
		//Node-Left-Right
		System.out.print(" " + root.data);
		preorder(root.left);
		preorder(root.right);
	}
	//Method to print out the Post-order Traversal of the BST
	public void postorder()
	{
		//Calls recursive postoder method here
		System.out.print("Post-order Traversal:");
		postorder(root);
		System.out.println();
	}
	//Method that actually runs calculates Post-order Traversal
	private void postorder(Node<AnyType> root)
	{
		//Found root node return to previous recursion call
		if (root == null)
			return;
		
		//Recursive calls that find thje Post-Order Traversal by calling 
		//Left - Right - Node
		postorder(root.left);
		postorder(root.right);
		System.out.print(" " + root.data);
	}
	//Main method
	public static void main(String [] args)
	{
		//Creates new Integer type tree since the GenericBST can be ANY TYPE
		//Must define what type the tree will hold here
		GenericBST<Integer> myTree = new GenericBST<Integer>();

		//Put 5 random integers into the BST
		for (int i = 0; i < 5; i++)
		{
			int r = (int)(Math.random() * 100) + 1;
			System.out.println("Inserting " + r + "...");
			myTree.insert(r);
		}

		//Call the 3 methods to print out the different Traversals for the BST
		myTree.inorder();
		myTree.preorder();
		myTree.postorder();
	}
	public static double DifficultyRating(){
		return 1.0;
	}
	public static double HoursSpent(){
		return 1.0;
	}
}
