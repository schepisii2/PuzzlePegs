/* 
 * Isabella Schepisi
 * CSCI 271 001
 * PA04 
 */
import java.util.*;

public class PuzzlePegs implements Cloneable
{
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}

	public static void print(char[] board)
	{
		System.out.println(board[0]);
		System.out.println(board[1]+""+board[2]);
		System.out.println(board[3]+""+board[4]+""+board[5]);
		System.out.println(board[6]+""+board[7]+""+board[8]+""+board[9]);
		System.out.println(board[10]+""+board[11]+""+board[12]+""+board[13]+""+board[14]);
		return;
	}
	
	public static void main(String[] args) throws CloneNotSupportedException
	{
		int[][] move={
		{1,2,4},
		{1,3,6},
		{2,4,7},
		{2,5,9},
		{3,5,8},
		{3,6,10},
		{4,2,1},
		{4,5,6},
		{4,7,11},
		{4,8,13},
		{5,8,12},
		{5,9,14},
		{6,3,1},
		{6,5,4},
		{6,9,13},
		{6,10,15},
		{7,4,2},
		{7,8,9},
		{8,5,3},
		{8,9,10},
		{9,5,2},
		{9,8,7},
		{10,6,3},
		{10,9,8},
		{11,7,4},
		{11,12,13},
		{12,8,5},
		{12,13,14},
		{13,12,11},
		{13,8,4},
		{13,9,6},
		{13,14,15},
		{14,13,12},
		{14,9,5},
		{15,10,6},
		{15,14,13}};
		int first_hole;
		int last_peg=15;//dummy value
		char[] board = new char[15];
		Stack<char[]> boards = new Stack<char[]>();
		Stack<String> moves = new Stack<String>();
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
				last_peg=Integer.parseInt(args[1])-1;
		}
		//create board
		for (int i=1; i<=15; i++)
		{
			if (i==first_hole)
				board[i-1]='h';
			else
				board[i-1]='p';
		}
		print(board);
		play(board, move, boards, moves, last_peg);
	}

	public static boolean play(char[] board, int[][] move, Stack<char[]> boards, Stack<String> moves, int last_peg)

	{
		for (int i=0;i<36;i++)//for all moves
		{
			if ((board[move[i][0]]=='p')&&(board[move[i][1]]=='p')&&(board[move[i][2]]=='h'))//if move
			{
				//make move
				board[move[i][0]]='h';
				board[move[i][1]]='h';
				board[move[i][2]]='p';
				//store
				char[] clone=boards.push(board.clone());
				//check :)
				if (play(board, move, boards, moves, last_peg))
				{
					moves.push(move[i][0]+"-"+move[i][2]);
					return true;//:)
				}//:(
				//undo
				if (boards.contains(clone))
				{
					boards.remove(clone);
				}
				board[move[i][0]]='p';
				board[move[i][1]]='p';
				board[move[i][2]]='h';
			}
		}
				play(board, move, boards, moves, last_peg);
				int peg_count=15;
				int peg_pos=15;
				for (int i=0; i<15; i++)
				{
					if (board[i]=='p')
						peg_count++;
					else
						peg_pos=i;
				}
				if ((peg_count==1)&&((peg_pos==last_peg)||(last_peg==15)))
					return true;
				else
					return false;

	}
}
