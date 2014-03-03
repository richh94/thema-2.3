package ttt;

import java.util.Random;
class TicTacToe
{
	private static final int HUMAN        = 0; 
	private static final int COMPUTER     = 1; 
	public  static final int EMPTY        = 2;

	public  static final int HUMAN_WIN    = 0;
	public  static final int DRAW         = 1;
	public  static final int UNCLEAR      = 2;
	public  static final int COMPUTER_WIN = 3;

	private int [ ] [ ] board = new int[ 3 ][ 3 ];
    private Random random=new Random();  
	private int side=random.nextInt(2);  
	private int position=UNCLEAR;
	private char computerChar,humanChar, emptyChar;

	// Constructor
	public TicTacToe( )
	{
		clearBoard( );
		initSide();
	}
	
	private void initSide()
	{
		emptyChar = '.';
	    if (this.side==COMPUTER) { computerChar='X'; humanChar='O'; }
		else                     { computerChar='O'; humanChar='X'; }
    }
    
    public void setComputerPlays()
    {
        this.side=COMPUTER;
        initSide();
    }
    
    public void setHumanPlays()
    {
        this.side=HUMAN;
        initSide();
    }

	public boolean computerPlays()
	{
	    return side==COMPUTER;
	}

	public int chooseMove()
	{
	    //Best best=chooseMove(COMPUTER);
	    //return best.row*3+best.column;
	    return 0;
    }
    
    // Find optimal move
	private Best chooseMove( int side )
	{
		int opp;              // The other side
		Best reply;           // Opponent's best reply
		int simpleEval;       // Result of an immediate evaluation
		int bestRow = 0;
		int bestColumn = 0;
		int value;

		if( ( simpleEval = positionValue( ) ) != UNCLEAR )
			return new Best( simpleEval );

		// TODO: implementeren m.b.v. recursie/backtracking
	    return null;
    }

   
    //check if move = ok
    public boolean moveOk(int move)
    {
	 	return ( move>=0 && move <=8 && board[move/3 ][ move%3 ] == EMPTY );
	 	//return true;
    }
    
    // play move
    public void playMove(int move)
    {
		board[move/3][ move%3] = this.side;
		if (side==COMPUTER) this.side=HUMAN;  else this.side=COMPUTER;
	}


	// Simple supporting routines
	private void clearBoard( )
	{
		//TODO: 
		//alle board values op empty setten
		
		for(int x = 0; x<this.board.length; x++){
			for(int y=0; y<this.board[x].length; y++){
				place(x, y, EMPTY);
			}
		}
	}


	private boolean boardIsFull( )
	{
		//TODO:
		//kijken of alle posities geset zijn
		
		for(int x = 0; x<board.length; x++){
			for(int y=0; y<board[x].length; y++){
				if(squareIsEmpty(x,y)){
					return false;
				}
			}
		}
		//we hebben alles doorlopen, maar we zijn nog geen 
		//positie tegengekomen die leeg is... dus het board
		//is vol, dus return true.
		return true;
	}

	// Returns whether 'side' has won in this position
	public boolean isAWin( int side )
	{
	    //TODO:
		//check of er 3 op 1 rij zijn van de side
		int[][][] winning = 			//met 1 van de volgende combo's win je
			{
				{{0,0}, {0,1}, {0,2}},	//horizontal
				{{1,0}, {1,1}, {1,2}},	//h
				{{2,0}, {2,1}, {2,2}},	//h
				
				{{0,0}, {1,0}, {2,0}},	//vertical
				{{0,1}, {1,1}, {2,1}},	//v
				{{0,2}, {1,2}, {2,2}},	//v
				
				{{0,0}, {1,1}, {2,2}},	//diagonal
				{{0,2}, {1,1}, {2,0}}	//d
			};
		
		int x = 9;   //0 (null) is used on the board, 9 isn't
		int y = 9;
		int win = 0; //we need a row of 3, stored in here.
		
		for( int[][] id : winning ){
			for( int[] foo : id){
				for( int value : foo){
					if(x == 9){	x = value;}
					else	  { y = value;}
				}
				if(this.board[x][y] == side){
					win++;
				}
				x=9; //clear x coordinate
			}
			if(win == 3) return true;
			win = 0;
		}
		return false;		
    }

	// Play a move, possibly clearing a square
	private void place( int row, int column, int piece )
	{
		board[ row ][ column ] = piece;
	}

	private boolean squareIsEmpty( int row, int column )
	{
		return board[ row ][ column ] == EMPTY;
	}

	// Compute static value of current position (win, draw, etc.)
	private int positionValue( )
	{
		// TODO:
		if(isAWin(side)){
			return side;
		}
		return UNCLEAR;
	}
	
	
	public String toString()
	{
	    //TODO:
		//output naar string genereren
	
		String rtrn = "";
		
		for(int x = 0; x<board.length; x++){
			for(int y=0; y<board[x].length; y++){
				if(board[x][y] == HUMAN){
					rtrn += humanChar;
				}else if(board[x][y] == COMPUTER){
					rtrn += computerChar;
				}else{
					rtrn += emptyChar;
				}
			}
			rtrn += "\n";
		}
		return rtrn;   
	}  
	
	public boolean gameOver()
	{
	    this.position=positionValue();
	    return this.position!=UNCLEAR;
    }
    
    public String winner()
    {
        if      (this.position==COMPUTER_WIN) return "computer";
        else if (this.position==HUMAN_WIN   ) return "human";
        else                                  return "nobody";
    }
    
	
	private class Best
    {
       int row;
       int column;
       int val;

       public Best( int v )
         { this( v, 0, 0 ); }
      
       public Best( int v, int r, int c )
        { val = v; row = r; column = c; }
    } 
	
	
}

