import java.util.*;
import java.util.concurrent.TimeUnit;


public class battleship{
	
	public static int Rows =8;
	
	public static int columns = 8;
	
	public static String[][] board = new String[Rows][columns];
	
	public static String[][] board2 = new String[Rows][columns];
    
    public static boolean winner = false;
    
    public static int player1hits = 3;
    
    public static int player2hits = 3;
    
    public static void main(String[] args) throws InterruptedException{
    	
    	System.out.println(" ----- This is a 2 player battleship game-----");
    	System.out.println("------- Lets setup the game board first-------");
    	
    	//display a game board
    	gameboard();
    	
    	//Ask player 1 to place his ship
    	System.out.println("-------Player 1 place your ship-------");
        placeplayership();
        
       //show the board to player 1
        showboard();
        
        
        //ask player 2 to place his ship
        TimeUnit.SECONDS.sleep(3);
        for(int i=0;i<100 ; i++) {System.out.println();} 
        System.out.println("-------Player 2 place your ship-------");
        placeplayer2ship();
      
        //show player 2 his board
        showboard2();
        TimeUnit.SECONDS.sleep(3);
        
        //clear console screen and start game
        for(int i=0;i<100 ; i++) {System.out.println();} 
        System.out.println("Lets fight");
       
        //attack starts
        fight();
        
        //game finishes
        finishgame();
        
        
    }

	

	private static void finishgame() {
		
		if(player1hits==0) {
			System.out.println("Player 1 WINS!!!!!");
		}
		
		if(player2hits==0) {
			System.out.println("Player 2 WINS!!!!");
		}
		
	}



	private static void fight() {
		
		do{
			player1shot();
			player2shot();
		}
       while(player1hits!=0 && player2hits!=0);
	}



	private static void player2shot() {
		
		System.out.println("\nplayer 2 attack");
        int x,y;
        
            do {
            Scanner input = new Scanner(System.in);
            
            System.out.print("Enter the X coordinate you want to destroy: ");
            
            x = input.nextInt();
            
            System.out.print("Enter the Y coordinate you want to destroy: ");
            
            y = input.nextInt();

            if ((x >= 0 && x < Rows) && (y >= 0 && y < columns))
            {
                if (board[x][y] == "*") //if player 1's ship is present
                {
                	 --player2hits;
                    System.out.println("wow! you put a hole in the enemy ship");
                    board[x][y] = "!"; //damaged portion     
                }
          
                else if (board[x][y] == " ") {
                    System.out.println("Missed your shot!!!!!");//hit the blank spot
                    board[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= Rows) || (y < 0 || y >= columns))
                System.out.println("You can't hit outside the board ");
        }while((x < 0 || x >= Rows) || (y < 0 || y >= columns));//keep going until done
		
	}



	private static void player1shot() {
		
		System.out.println("\n player 1 attack");
        int x, y;
        do {
            Scanner input = new Scanner(System.in);
            
            System.out.print("Enter the X coordinate you want to destroy: ");
            
            x = input.nextInt();
            
            System.out.print("Enter the Y coordinate you want to destroy: ");
            
            y = input.nextInt();

            if ((x >= 0 && x < Rows) && (y >= 0 && y < columns))
            {
                if (board2[x][y] == "@") //if player 2's ship is present
                {
                	--player1hits;
                    System.out.println("wow! you put a hole in the enemy ship");
                    board2[x][y] = "!"; //damaged portion
                   
                   
                }
          
                else if (board2[x][y] == " ") {
                    System.out.println("Missed your shot!!!!!");//hit the blank spot
                    board2[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= Rows) || (y < 0 || y >= columns))
                System.out.println("You can't hit outside the board ");
        }while((x < 0 || x >= Rows) || (y < 0 || y >= columns));//keep going until done
		
	}



	private static void placeplayership() {
		
		
		Scanner input = new Scanner(System.in);

        System.out.println("\nplease place your ship(make sure the other player isnt watching the screen!) :");
        
        System.out.print("please enter the X cordinate for placing ship: ");
        int x = input.nextInt();
        
        System.out.print("please enter the Y cordinate for placing ship: ");
        int y = input.nextInt();
        
        
        
        if((x < 0 || x >= Rows) || (y < 0 || y >= columns)) 
            {
            System.out.println("you need to place your boat within the gameboard. Try again.");
            placeplayership();
            }
        
        if((x >= 0 && x < Rows) && (y >= 0 && y < columns))  {
        	
        	 System.out.println("Place ship horizontally(H) or vertically(V)?(H/V) ");
             String choice = input.next();
             
            if(choice.equals("V")) {
            	
            	if (x<6) 
            	{
            	board[x][y]="*";
            	board[x+1][y]="*";
            	board[x+2][y]="*";
                }
            
            	else {
            		board[x][y]="*";
                	board[x-1][y]="*";
                	board[x-2][y]="*";	
            	}
            }
            else if(choice.equals("H")) {
            	
            	if (y<6) 
            	{
            	board[x][y]="*";
            	board[x][y+1]="*";
            	board[x][y+2]="*";
                }
            
            	else {
            		board[x][y]="*";
                	board[x][y-1]="*";
                	board[x][y-2]="*";	
            	}
            	
            }            
            else 
            {
         	   
         	   System.out.println("Invalid choice. Start again.");
         	   placeplayership();
            }
        }
            
            
	}

	private static void showboard() {
	
		System.out.println();
        System.out.print("   ");
        for(int i = 0; i < columns; i++)
        System.out.print(i);
        System.out.println();

        for(int x = 0; x < board.length; x++) {
        System.out.print(x + "||");
        for (int y = 0; y < board[x].length; y++){
                System.out.print(board[x][y]);
            }

            System.out.println("||" + x);
        }
        System.out.print("   ");
        for(int i = 0; i < columns; i++)
        System.out.print(i);
        System.out.println();
    }

	private static void gameboard() {
		 
		System.out.println("---- This is where the battle will be fought----- ");
		
		System.out.println();
		System.out.print("   ");
	    for(int i = 0; i < columns; i++)
        System.out.print(i); //displaying my column values
        System.out.println();
        for(int  i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
                 board[i][j] = " ";
                 board2[i][j]= " ";
                 if (j == 0)
                 System.out.print(i + "||" + board[i][j]);
                 else if (j == board[i].length - 1)
                 System.out.print(board[i][j] + "||" + (i));
                 else
                 System.out.print(board[i][j]);
            }
                 System.out.println();
        }

        System.out.print("   ");
        for(int i = 0; i < columns; i++)
        System.out.print(i);
        System.out.println();
    }

    private static void placeplayer2ship() {
		
		
		Scanner input = new Scanner(System.in);

        System.out.println("\nplease place your ship(make sure the other player isnt watching the screen!) :");
        
        System.out.print("please enter the X cordinate for placing ship: ");
        int x = input.nextInt();
        
        System.out.print("please enter the Y cordinate for placing ship: ");
        int y = input.nextInt();
        
        
        
        if((x < 0 || x >= Rows) || (y < 0 || y >= columns)) 
            {
            System.out.println("you need to place your boat within the gameboard. Try again.");
            placeplayer2ship();
            }
        
        if((x >= 0 && x < Rows) && (y >= 0 && y < columns))  {
        	
        	 System.out.println("Place ship horizontally(H) or vertically(V)?(H/V) ");
             String choice = input.next();
             
            if(choice.equals("V")) {
            	
            	if (x<6) 
            	{
            	board2[x][y]="@";
            	board2[x+1][y]="@";
            	board2[x+2][y]="@";
                }
            
            	else {
            		board2[x][y]="@";
                	board2[x-1][y]="@";
                	board2[x-2][y]="@";	
            	}
            }
            else if(choice.equals("H")) {
            	
            	if (y<6) 
            	{
            	board2[x][y]="@";
            	board2[x][y+1]="@";
            	board2[x][y+2]="@";
                }
            
            	else {
            		board2[x][y]="@";
                	board2[x][y-1]="@";
                	board2[x][y-2]="@";	
            	}
            	
            }            
            else 
            {
         	   System.out.println("Invalid choice. Start again.");
         	   placeplayer2ship();
            }
        }
        
            
            
	}

    private static void showboard2() {
    	
		System.out.println();
        System.out.print("   ");
        for(int i = 0; i < columns; i++)
        System.out.print(i);
        System.out.println();

        for(int x = 0; x < board2.length; x++) {
        System.out.print(x + "||");
        for (int y = 0; y < board2[x].length; y++){
                System.out.print(board2[x][y]);
            }

            System.out.println("||" + x);
        }
        System.out.print("   ");
        for(int i = 0; i < columns; i++)
        System.out.print(i);
        System.out.println();
    }

        }