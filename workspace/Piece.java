
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
        
        try {
            if (this.img == null) {
              this.img = ImageIO.read(getClass().getResource(img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Board b, Square start) {
      ArrayList<Square> tile = new ArrayList<Square>(); 
      if (color == true){
      if(start.getCol() != 7){
        tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
        }
      if (start.getCol() != 0){
        tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
      }
    }
      if (color == false){
        if(start.getCol() != 7){
          tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
          }
        if (start.getCol() != 0){
          tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
        }
      }
      return tile;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
      ArrayList<Square> tile = new ArrayList<Square>(); 
      if(color == true){
      //if moving for the first time 
      int potential = start.getRow();
      potential = potential - 1;
      if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
        tile.add(b.getSquareArray()[potential][start.getCol()]);
        }
      if (start.getRow() == 6){
        potential = potential - 1;
        if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
          tile.add(b.getSquareArray()[potential][start.getCol()]);
          }
      }
      if (start.getCol() != 0 && b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].isOccupied() && b.getSquareArray()[start.getRow() - 1][start.getCol() - 1].getOccupyingPiece().getColor() == false) {
          tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() - 1]);
      }
      if (start.getCol() != 7 && b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].isOccupied() && b.getSquareArray()[start.getRow() - 1][start.getCol() + 1].getOccupyingPiece().getColor() == false) {
        tile.add(b.getSquareArray()[start.getRow() - 1][start.getCol() + 1]);
    }
      
    }
    if(color == false){
      //if moving for the first time
      int potential = start.getRow();
      potential = potential + 1;
      if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
        tile.add(b.getSquareArray()[potential][start.getCol()]);
        }
      if (start.getRow() == 1){
        potential = potential + 1;
        if(b.getSquareArray()[potential][start.getCol()].isOccupied() == false){
          tile.add(b.getSquareArray()[potential][start.getCol()]);
          }
      }
      if (start.getCol() != 0 && b.getSquareArray()[start.getRow() + 1][start.getCol() - 1].isOccupied() && b.getSquareArray()[start.getRow() + 1][start.getCol()- 1].getOccupyingPiece().getColor() == true) {
        tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() - 1]);
    }
    if (start.getCol() != 7 && b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].isOccupied() && b.getSquareArray()[start.getRow() + 1][start.getCol() + 1].getOccupyingPiece().getColor() == true) {
      tile.add(b.getSquareArray()[start.getRow() + 1][start.getCol() + 1]);
  }
    }
    
    return tile;
  }
}