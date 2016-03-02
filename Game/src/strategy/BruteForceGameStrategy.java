package strategy;

import java.util.List;

import model.board.ChessBoard;
import model.board.GameEvent;
import model.enums.Color;

public class BruteForceGameStrategy implements GameStrategy {

    BoardInterrogator boardInterrogator = new BoardInterrogator();

    @Override
    public GameEvent nextMove(Color color, ChessBoard chessBoard) {
        return bestCaseMove(color, chessBoard, 0);
    }

    private GameEvent bestCaseMove(Color color, ChessBoard chessBoard, int futureTurns) {
        MoveGrade bestCase = null;

        for (GameEvent move : chessBoard.potentialGameEvents(color)) {
            ChessBoard thisBoard = chessBoard.playEvent(move);

            if (futureTurns > 0) {
                return bestCaseMove(color, chessBoard, --futureTurns);
            }

            MoveGrade moveGrade = new MoveGrade(move, worstCaseResponse(color, thisBoard).grade);

            if (bestCase == null) {
                bestCase = moveGrade;
            }

            bestCase = bestCase.higherGrade(moveGrade);
        }

        return bestCase.move;
    }

    private MoveGrade worstCaseResponse(Color color, ChessBoard board) {
        MoveGrade worstCaseResponse = null;

        List<GameEvent> potentialResponses = board.potentialGameEvents(color.opponentColor());

        ChessBoard boardAfterResponse;
        for (GameEvent moveResponse : potentialResponses) {
            boardAfterResponse = board.playEvent(moveResponse);

            BoardGrade grade = boardInterrogator.grade(boardAfterResponse, color);
            MoveGrade responseGrade = new MoveGrade(moveResponse, grade);

            if (worstCaseResponse == null) {
                worstCaseResponse = responseGrade;
            }

            worstCaseResponse = worstCaseResponse.lowerGrade(responseGrade);
        }
        return worstCaseResponse;
    }

    private class MoveGrade {
        private GameEvent move;
        private BoardGrade grade;

        public MoveGrade(GameEvent moveResponse, BoardGrade grade) {
            this.move = moveResponse;
            this.grade = grade;
        }

        public MoveGrade higherGrade(MoveGrade otherGrade) {
            return new MoveGrade(move, grade.higherGrade(otherGrade.grade));
        }

        public MoveGrade lowerGrade(MoveGrade otherGrade) {
            return new MoveGrade(move, grade.lowerGrade(otherGrade.grade));
        }

    }

}
