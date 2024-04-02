import java.util.Scanner;
import java.io.File;

public class CL2_Amezquita{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the filename: ");
		String file = input.nextLine();
		String[][] maze = loadMaze(file);
		System.out.println(maze.length*maze.length);
		playGame(maze);

	}

	public static void playGame(String[][]maze){
		while(toWin(maze)==false){
			displayMaze(maze);
			movePlayer(maze);
		}
		displayMaze(maze);
		System.out.println("Congratulations! You've escaped the maze!");
	}

	public static String[][] createSquareMaze(String filename){
		int lines =0;
		try{
			File file = new File(filename);
			Scanner fileReader = new Scanner(file);
			while(fileReader.hasNextLine()){
				lines++;
				String temp=fileReader.nextLine();
			}
		}catch(Exception e){
			System.out.println("Error: "+e);
		}
		String[][] maze = new String[lines][lines];
		return maze;
	}

	public static String[][] loadMaze(String filename){
		Scanner in = new Scanner(System.in);
		String[][] maze = createSquareMaze(filename);

		try{
			File file = new File(filename);
			Scanner fileReader = new Scanner(file);

			int rowCounter=0;
			while(fileReader.hasNextLine()){
				String temp=fileReader.nextLine();
				for(int i=0;i<temp.length();i++){
					String token =""+temp.charAt(i);
					maze[rowCounter][i]=token;
				}
				rowCounter++;
			}
					

		}catch(Exception e){
			System.out.println("Error: "+e);
		}
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze.length;j++){
		
			}
		}
		maze=findStartPosition(maze);
		return maze;
	}

	public static String[][] findStartPosition(String[][] maze){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze.length;j++){
				if(maze[i][j].equals("S")){
					maze[i][j]="P";
				}
			}
		}
		return maze;
	}

	public static void displayMaze(String[][] maze){
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze.length;j++){
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	public static String[][] movePlayer(String[][] maze){
		Scanner input = new Scanner(System.in);
		boolean valid=true;
		String[][] ogMaze = maze;
		System.out.println("MOVE (WASD):"); 
		String move=input.nextLine();
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze.length;j++){
				if(maze[i][j].equals("P")){
					if(move.equals("w")){
						if(maze[i-1][j].equals(".")){
							maze[i-1][j]="P";
							maze[i][j]=".";
							break;
						}else if(maze[i-1][j].equals("F")){
							maze[i-1][j]="P";
							maze[i][j]=".";
							break;
						}else{
							System.out.println("You cant move through walls!");
							break;
						}
					}else if(move.equals("s")){
						while(valid==true){
							if(maze[i+1][j].equals(".")){
								maze[i+1][j]="P";
								maze[i][j]=".";
								valid=false;
								break;
							}else if(maze[i+1][j].equals("F")){
								maze[i+1][j]="P";
								maze[i][j]=".";
								break;
							}else{
								System.out.println("You cant move through walls!");
								break;
							}
						}
					}else if(move.equals("d")){
						if(maze[i][j+1].equals(".")){
							maze[i][j+1]="P";
							maze[i][j]=".";
							break;
						}else if(maze[i][j+1].equals("F")){
							maze[i][j+1]="P";
							maze[i][j]=".";
							break;
						}else{
							System.out.println("You cant move through walls!");
							break;
						}
					}else if(move.equals("a")){
						if(maze[i][j-1].equals(".")){
							maze[i][j-1]="P";
							maze[i][j]=".";
							break;
						}else if(maze[i][j-1].equals("F")){
							maze[i][j-1]="P";
							maze[i][j]=".";
							break;
						}else{
							System.out.println("You cant move through walls!");
							break;
						}
					}
				}
			}
		}
		return maze;
	}

	public static boolean toWin(String maze[][]){
		boolean exit=false;
		int count=0;
		for(int i=0;i<maze.length;i++){
			for(int j=0;j<maze.length;j++){
				if(!(maze[i][j].equals("F"))){
					count++;
				}

			}
		}
		if(count==maze.length*maze.length){
			exit=true;
		}
		return exit;
	}
}
	
