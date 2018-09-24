/*
 * @author Benjamin Pollak
 */
abstract public class Piece {
    private Location _location;
    private PieceType _pieceType;
    private Color _color;

    /**
     * Piece constructor
     * Location pieceLocation: Where the piece should be placed on the board
     * Location boardParameters: The size of the board
     * PieceType pieceType: The type of the piece (pawn, rook ,etc)
     * Color color: The "color" or side of the piece
     */
    public Piece(Location pieceLocation, Location boardParameters, PieceType pieceType, Color color) {
        int xCoord = pieceLocation.getKey();
        int yCoord = pieceLocation.getValue();

        int boardWidth = boardParameters.getKey();
        int boardLength = boardParameters.getValue();

        checkCoordinates(xCoord, yCoord, boardWidth, boardLength);
        _location = new Location(xCoord, yCoord);
        _pieceType = pieceType;
        _color = color;
    }

    /**
     *  makes sure coordinates are "in-bounds"
     *  @param xCoord: desired horizontal location of piece
     *  @param yCoord: desired vertical location of piece
     *  @param boardWidth: Horizontal size of board
     *  @param boardlength: Vertical size of board
     */

    public void checkCoordinates(int xCoord, int yCoord, int boardWidth, int boardLength) {
        if((xCoord < 0) || (yCoord < 0)) {
            throw new IllegalArgumentException("OUT OF BOUNDS");
        }

        if((xCoord >= boardWidth) || (yCoord >= boardLength)) {
            throw new IllegalArgumentException("OUT OF BOUNDS");
        }

    }

    public abstract MoveType move(int xCoord, int yCoord, Board board);
    public abstract void findIfKingInCheck(Piece[][] field) throws KingInCheck;

    // look functions

    /**
     * Looks down and right for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookDownAndRightForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            ++xCoord; ++yCoord;
        }
    }

    /**
     * Looks up and left for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookUpAndLeftForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            --xCoord; --yCoord;
        }
    }

    /**
     * Looks down and left for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookDownAndLeftForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            --xCoord; ++yCoord;
        }
    }

    /**
     * Looks up for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookUpForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            --yCoord;
        }            }

    /**
     * Looks down for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookDownForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            ++yCoord;
        }                }

    /**
     * Looks left for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookLeftForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            --xCoord;
        }        }

    /**
     * Looks right for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param int xCoord: x coordinate of piece
     * @param int yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookRightForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            ++xCoord;
        }
    }


    /**
     * Looks diagonally up and right for the king.
     * @param Piece[][] field: 2-D array representing where the pieces are
     * @param xCoord: x coordinate of piece
     * @param yCoord: y coordinate of piece
     * @throws: KingInCheck if king found to be in check
     * @return nothing
     */
    public void lookUpAndRightForKing(Piece[][] field, int xCoord, int yCoord) throws KingInCheck {
        while(true) {
            try {
                Piece piece = field[xCoord][yCoord];
                if(piece != null) {
                    if (piece.getPieceType() == PieceType.KING && (piece.getColor() != this.getColor())) {
                        throw new KingInCheck(new Location(xCoord, yCoord), _location);
                    }
                    else break;
                }
            } catch(ArrayIndexOutOfBoundsException e) {
                break;
            }
            ++xCoord; --yCoord;
        }
    }

    // getters and setter

    /**
     * Gets _color member variable
     */
    public Color getColor() {
        return _color;
    }

    /**
     * Gets _location member variable
     */
    public Location getLocation() {
        return _location;
    }

    /**
     * Gets _pieceType member variable
     */
    public PieceType getPieceType() {
        return _pieceType;
    }


    /**
     * Sets _location member variable with newLocation
     * @param Location newLocation: new value for _location
     */
    public void setLocation(Location newLocation) {
        _location = newLocation;
    }
}
