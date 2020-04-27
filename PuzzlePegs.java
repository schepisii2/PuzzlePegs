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
	
	public static boolean north(char[][] arr, int r, int c, String d)
	{
		if (d=="north")
			return false;
		if (r<2)
			return false;
		return ((arr[r][c]=='p')&&(arr[r-1][c]=='p')&&(arr[r-2][c]=='h'));
	}
	
	public static boolean south(char[][] arr, int r, int c, String d)
	{
		if (d=="south")
			return false;
		if (r>2)
			return false;
		return ((arr[r][c]=='p')&&(arr[r+1][c]=='p')&&(arr[r+2][c]=='h'));		
	}

	public static boolean east(char[][] arr, int r, int c, String d)
	{
		if (d=="east")
			return false;
		if (c>2)
			return false;
		return ((arr[r][c]=='p')&&(arr[r][c+1]=='p')&&(arr[r][c+2]=='h'));

	}
	
	public static boolean west(char[][] arr, int r, int c, String d)
	{
		if (d=="west")
			return false;
		if (c<2)
			return false;
		return ((arr[r][c]=='p')&&(arr[r][c-1]=='p')&&(arr[r][c-2]=='h'));
	}

	public static boolean northwest(char[][] arr, int r, int c, String d)
	{
		if (d=="northwest")
			return false;
		if ((r<2)||(c<2))
			return false;
		return ((arr[r][c]=='p')&&(arr[r-1][c-1]=='p')&&(arr[r-2][c-2]=='h'));
	}

	public static boolean southeast(char[][] arr, int r, int c, String d)
	{
		if (d=="southeast")
			return false;
		if ((r>2)||(c>2))
			return false;
		return ((arr[r][c]=='p')&&(arr[r+1][c+1]=='p')&&(arr[r+2][c+2]=='h'));
	}

	public static void undo(char[][] arr, int r, int c, String d)
	{
		if (d=="north")
		{
			arr[r][c]='p';
			arr[r-1][c]='p';
			arr[r-2][c]='h';
		}
		else if (d=="south")
		{
			arr[r][c]='p';
			arr[r+1][c]='p';
			arr[r+2][c]='h';
		}
		else if (d=="east")
		{
			arr[r][c]='p';
			arr[r][c+1]='p';
			arr[r][c+2]='h';
		}
		else if (d=="west")
		{
			arr[r][c]='p';
			arr[r][c-1]='p';
			arr[r][c-2]='h';
		}
		else if (d=="northwest")
		{
			arr[r][c]='p';
			arr[r-1][c-1]='p';
			arr[r-2][c-2]='h';
		}
		else if (d=="southeast")
		{
			arr[r][c]='p';
			arr[r+1][c+1]='p';
			arr[r+2][c+2]='h';
		}
		return;
	}

	public static int move(char[][] arr, int peg_count, int r, int c, String d, Stack<Integer> r_stack, Stack<Integer> c_stack, Stack<String> d_stack)
	{
		if (north(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("north");
			arr[r][c]='h';
			arr[r-1][c]='h';
			arr[r-2][c]='p';
			return --peg_count;
		}
		if (south(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("south");
			arr[r][c]='h';
			arr[r+1][c]='h';
			arr[r+2][c]='p';
			return --peg_count;
		}
		if (east(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("east");
			arr[r][c]='h';
			arr[r][c+1]='h';
			arr[r][c+2]='p';
			return --peg_count;
		}
		if (west(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("west");
			arr[r][c]='h';
			arr[r][c-1]='h';
			arr[r][c-2]='p';
			return --peg_count;
		}
		if (northwest(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("northwest");
			arr[r][c]='h';
			arr[r-1][c-1]='h';
			arr[r-2][c-2]='p';
			return --peg_count;
		}
		if (southeast(arr, r, c, d))
		{
			r_stack.push(r);
			c_stack.push(c);
			d_stack.push("southeast");
			arr[r][c]='h';
			arr[r+1][c+1]='h';
			arr[r+2][c+2]='p';
			return --peg_count;
		}
		if ((r==4)&&(c==4))//check if move exhausted
		{
			return peg_count;
		}
		if (r==c)
		{
			c=0;
			r++;
		}
		else
			c++;
		return move(arr, peg_count, r, c, d, r_stack, c_stack, d_stack);
	}

	public static void play(char[][] board, int peg_count, int r, int c, String d, Stack<Integer> r_stack, Stack<Integer> c_stack, Stack<String> d_stack)
	{
		int old_count=peg_count;
		peg_count=move(board, peg_count, r, c, d, r_stack, c_stack, d_stack);
		if (old_count==peg_count)
		{
			r=r_stack.pop();
			c=c_stack.pop();
			d=d_stack.pop();
			undo(board, r, c, d);
			peg_count++;
		}
		else
		{
			r=0;
			c=0;
			d="any";
		}
		if (peg_count==1)
			return;
		play(board, peg_count, r, c, d, r_stack, c_stack, d_stack);
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
	int temp=1;
	r=0;
	c=0;
	while (first_hole>temp)
	{
		temp++;
		if(r==c)
		{
			r++;
			c=0;
		}
		else
			c++;
	}
	pegBoard[r][c]='h';//place hole
	//initial conditions
	int peg_count = 14;
	r=0;
	c=0;
	String d="any";
	Stack<Integer> r_stack = new Stack<>();
	Stack<Integer> c_stack = new Stack<>();
	Stack<String> d_stack = new Stack<>();
	play(pegBoard, peg_count, r, c, d, r_stack, c_stack, d_stack);
	printArray(pegBoard, rows, columns);
}
}
