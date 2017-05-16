package model.board;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;
import model.piece.Piece;
import model.piece.PieceFactory;

import org.junit.Test;

public class BoardStateTranslatorTest {

    private BoardStateTranslator underTest = new BoardStateTranslator();

    @Test
    public void translate_set_board_to_simple_string() {
        ChessBoard board = new ChessBoard().setBoardForGame();
        String state = underTest.boardToString(board);
        System.out.println(state);
        assertThat(
            state,
            equalTo("'wR','wN','wB','wQ','wK','wB','wN','wR','wP','wP','wP','wP','wP','wP','wP','wP','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','bP','bP','bP','bP','bP','bP','bP','bP','bR','bN','bB','bQ','bK','bB','bN','bR'"));
    }

    @Test
    public void translator_can_retrieve_the_compressed_id_from_a_piece() {
        Piece queen = PieceFactory.newPiece(Color.BLACK, Rank.Queen, new Square(Column.A, Row.R8));
        String id = underTest.compressedIdOf(queen);
        assertThat(id, equalTo("'bQ'"));
    }

}
