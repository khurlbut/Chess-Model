package onceagain.board.perspectives;

import java.util.ArrayList;
import java.util.List;

import onceagain.board.ChessBoard;
import onceagain.board.Square;
import onceagain.enums.Color;
import onceagain.enums.Direction;

public class RadiantPerspective extends Square implements RankPerspective {

    private Direction[] radii;

    public RadiantPerspective(Square s, Direction[] radii) {
        super(s.col(), s.row());
        this.radii = radii;
    }

    public List<Square> radials() {
        List<Square> radials = new ArrayList<Square>();
        for (Direction radius : radii) {
            radials.addAll(line(radius));
        }
        return radials;
    }

    public List<Square> radialsIncludingOccuppied(ChessBoard chessBoard) {
        List<Square> radials = new ArrayList<Square>();
        for (Direction radius : radii) {
            radials.addAll(lineIncludingNearestOccupied(radius, chessBoard));
        }
        return radials;
    }

    public List<Square> radialsExcludingOccupied(ChessBoard chessBoard) {
        List<Square> radials = new ArrayList<Square>();
        for (Direction radius : radii) {
            radials.addAll(lineExcludingNearestOccupied(radius, chessBoard));
        }
        return radials;
    }

    public List<Square> radialsOccupiedByColor(ChessBoard chessBoard, Color color) {
        List<Square> radials = new ArrayList<Square>();
        for (Direction radius : radii) {
            radials.addAll(nearestColorOccupiedOnLine(radius, chessBoard, color));
        }
        return radials;
    }

    public List<Square> line(Direction dir) {
        List<Square> squares = new ArrayList<Square>();
        Square nextSquare = neighbor(dir);
        while (nextSquare != null) {
            squares.add(nextSquare);
            nextSquare = nextSquare.neighbor(dir);
        }
        return squares;
    }

    public List<Square> lineIncludingNearestOccupied(Direction dir, ChessBoard chessBoard) {
        List<Square> squares = new ArrayList<Square>();
        Square nextSquare = neighbor(dir);
        while (nextSquare != null) {
            squares.add(nextSquare);
            if (squareIsOccupied(chessBoard, nextSquare)) {
                break;
            }
            nextSquare = nextSquare.neighbor(dir);
        }
        return squares;
    }

    public List<Square> lineExcludingNearestOccupied(Direction dir, ChessBoard chessBoard) {
        List<Square> squares = new ArrayList<Square>();
        Square nextSquare = neighbor(dir);
        while (nextSquare != null) {
            if (squareIsOccupied(chessBoard, nextSquare)) {
                break;
            }
            squares.add(nextSquare);
            nextSquare = nextSquare.neighbor(dir);
        }
        return squares;
    }

    public List<Square> nearestColorOccupiedOnLine(Direction dir, ChessBoard chessBoard, Color color) {
        List<Square> squares = new ArrayList<Square>();
        Square nextSquare = neighbor(dir);
        while (nextSquare != null) {
            if (squareIsOccupied(chessBoard, nextSquare) && color.equals(chessBoard.pieceAt(nextSquare).color())) {
                squares.add(nextSquare);
                break;
            }
            nextSquare = nextSquare.neighbor(dir);
        }
        return squares;
    }

    private boolean squareIsOccupied(ChessBoard chessBoard, Square nextSquare) {
        return null != chessBoard.pieceAt(nextSquare);
    }

}
