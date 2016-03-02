package strategy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import model.board.ChessBoard;
import model.board.Square;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BoardInterrogatorTest {

    Square d_4 = new Square(Column.D, Row.R4);
    Square d_6 = new Square(Column.D, Row.R6);

    @Mock
    private ChessBoard chessBoard;
    @Mock
    private Square squareThreatenedByWhite;
    @Mock
    private Square squareThreatenedByBlack;
    @Mock
    private Piece w_queen;
    @Mock
    private Piece b_queen;
    @Mock
    private Piece w_pawn;
    @Mock
    private Piece b_pawn;

    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    private List<Square> squaresThreatenedByWhite;
    private List<Square> squaresThreatenedByBlack;

    private BoardInterrogator boardInterrogator = new BoardInterrogator();

    @Before
    public void setUp() {
        boardInterrogator = new BoardInterrogator();

        when(w_queen.points()).thenReturn(10);
        when(b_queen.points()).thenReturn(10);
        when(w_pawn.points()).thenReturn(1);
        when(b_pawn.points()).thenReturn(1);

        whitePieces = Arrays.asList(w_queen);
        blackPieces = Arrays.asList(b_queen);

        squaresThreatenedByWhite = Arrays.asList(squareThreatenedByWhite);
        squaresThreatenedByBlack = Arrays.asList(squareThreatenedByBlack);

        when(chessBoard.piecesFor(Color.WHITE)).thenReturn(whitePieces);
        when(chessBoard.piecesFor(Color.BLACK)).thenReturn(blackPieces);

        when(w_queen.rank()).thenReturn(Rank.Queen);
        when(b_queen.rank()).thenReturn(Rank.Queen);
        when(w_pawn.rank()).thenReturn(Rank.Pawn);
        when(b_pawn.rank()).thenReturn(Rank.Pawn);

        when(w_queen.threatenedSquares(chessBoard)).thenReturn(squaresThreatenedByWhite);
        when(b_queen.threatenedSquares(chessBoard)).thenReturn(squaresThreatenedByBlack);

        when(w_queen.opponentAttackers(chessBoard)).thenReturn(blackPieces);
        when(b_queen.opponentAttackers(chessBoard)).thenReturn(whitePieces);
    }

    @Test
    public void attacking_an_unguarded_piece_adds_the_value_of_the_piece_under_attack() {
        blackPieces = Arrays.asList(b_pawn);
        when(w_queen.opponentAttackers(chessBoard)).thenReturn(Collections.<Piece> emptyList());
        when(w_queen.piecesAttacked(chessBoard)).thenReturn(blackPieces);

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.WHITE);
        assertThat(grade.positionGrade(), equalTo(+Rank.Pawn.value()));
    }

    @Test
    public void an_unguarded_piece_under_attack_takes_away_the_value_of_the_piece() {
        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.BLACK);
        assertThat(grade.positionGrade(), equalTo(-Rank.Queen.value()));
    }

    @Test
    public void attacking_a_guarded_equivalent_or_subordinate_piece_is_a_wash() {
        when(w_queen.piecesAttacked(chessBoard)).thenReturn(blackPieces);
        when(w_queen.opponentAttackers(chessBoard)).thenReturn(Collections.<Piece> emptyList());
        when(b_queen.collaboratorDefenders(chessBoard)).thenReturn(Arrays.asList(b_pawn));

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.WHITE);
        assertThat(grade.positionGrade(), equalTo(0));
    }

    @Test
    public void attacking_a_guarded_subordinate_piece_adds_the_value_of_the_subordinate_piece() {
        whitePieces = Arrays.asList(w_pawn);
        blackPieces = Arrays.asList(b_queen);

        when(chessBoard.piecesFor(Color.WHITE)).thenReturn(whitePieces);
        when(w_pawn.piecesAttacked(chessBoard)).thenReturn(blackPieces);
        when(w_pawn.opponentAttackers(chessBoard)).thenReturn(Collections.<Piece> emptyList());
        when(b_queen.collaboratorDefenders(chessBoard)).thenReturn(Arrays.asList(b_pawn));

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.WHITE);
        assertThat(grade.positionGrade(), equalTo(+Rank.Queen.value()));
    }

    @Test
    public void a_guarded_piece_under_attack_by_an_equivalent_piece_is_a_wash() {
        blackPieces = Arrays.asList(b_pawn);
        when(b_queen.collaboratorDefenders(chessBoard)).thenReturn(blackPieces);

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.BLACK);

        assertThat(grade.positionGrade(), equalTo(0));
    }

    @Test
    public void a_quarded_piece_under_attack_by_a_subordinate_piece_takes_away_the_value_of_the_piece() {
        blackPieces = Arrays.asList(b_queen, b_pawn);
        whitePieces = Arrays.asList(w_pawn);
        when(b_queen.collaboratorDefenders(chessBoard)).thenReturn(blackPieces);
        when(b_queen.opponentAttackers(chessBoard)).thenReturn(whitePieces);

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.BLACK);

        assertThat(grade.positionGrade(), equalTo(-Rank.Queen.value()));
    }

    @Test
    public void collaborator_score_is_one_times_the_number_of_collaborators_defended() {
        when(b_queen.piecesDefended(chessBoard)).thenReturn(Arrays.asList(b_pawn, b_queen));
        blackPieces = Arrays.asList(b_queen, b_pawn);

        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.BLACK);

        when(b_queen.collaboratorDefenders(chessBoard)).thenReturn(blackPieces);
        assertThat(grade.collaborationGrade(), equalTo(2));
    }

    @Test
    public void terrirory_score_is_zero_sum_of_threatened_squares_minus_opponents_threatened_squares() {
        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.WHITE);

        assertThat(grade.territoryGrade(), equalTo(0));

        when(b_queen.threatenedSquares(chessBoard)).thenReturn(Collections.<Square> emptyList());
        grade = boardInterrogator.grade(chessBoard, Color.WHITE);
        assertThat(grade.territoryGrade(), equalTo(1));
    }

    @Test
    public void material_score_is_zero_sum_of_piece_points_minus_opponent_piece_points() {
        BoardGrade grade = boardInterrogator.grade(chessBoard, Color.WHITE);

        assertThat(grade.materialGrade(), equalTo(0));

        when(b_queen.points()).thenReturn(5);
        grade = boardInterrogator.grade(chessBoard, Color.WHITE);
        assertThat(grade.materialGrade(), equalTo(5));
    }

    @Test
    public void it_gets_the_Points_from_each_pieces_for_each_sides_when_analyzing_a_side() {
        boardInterrogator.grade(chessBoard, Color.WHITE);

        verify(chessBoard).piecesFor(Color.WHITE);
        verify(chessBoard).piecesFor(Color.BLACK);

        for (Piece whitePiece : whitePieces) {
            verify(whitePiece).points();
        }
        for (Piece blackPiece : whitePieces) {
            verify(blackPiece).points();
        }
    }

}
