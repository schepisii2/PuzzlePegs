/* 
 * Isabella Schepisi
 * CSCI 271 001
 * PA04 
 */
import java.util.*;
public class PuzzlePegs
{

	public static void printArray(char[][] arr, int r, int c)
	{
		for (int i=0;i<r;i++)
		{
			for (int j=0;j<c;j++)
				System.out.print(arr[i][j]);
			System.out.println(" ");
		}
	}

	public static void num_to_position(int num, int r, int c)
	{
		int temp=1;//start at position 1
		r=0;
		c=0;
		while(num>temp)
		{
			temp++;
			if (r==c)
			{
				r++;
				c=0;
			}
			else
				c++;
		}
		return;
	}

public static void main(String[] args)
{
	//create pegBoard
	int rows=5;
	int columns=5;
	int r=0;
	int c=0;
	char pegBoard[][] = new char[rows][columns];
	for (r=0; r<rows;r++)//all pegs
	{
		for (c=0; c<columns;c++)
		{
			if (r<c)
				pegBoard[r][c]=' ';
			else
				pegBoard[r][c]='p';
		}
	}
	int first_hole;
	int last_peg;
	if (args.length==0)//stdin for first_hole
	{
		Scanner s1 = new Scanner(System.in);
		System.out.print("Hole position (1-15): ");
		first_hole=s1.nextInt();
		s1.close();
	}
	else//cmd line for first_hole
	{
		first_hole=Integer.parseInt(args[0]);
		if (args.length==2)
			last_peg=Integer.parseInt(args[1]);
		else
			last_peg=16;
	}
	num_to_position(first_hole, r, c);
	pegBoard[r][c]='h';
	printArray(pegBoard,rows, columns);
}
}
