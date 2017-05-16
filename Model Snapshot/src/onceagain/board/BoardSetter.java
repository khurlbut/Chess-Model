package onceagain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import onceagain.ChessPiece;
import onceagain.Piece;
import onceagain.enums.Color;
import onceagain.enums.Column;
import onceagain.enums.Rank;
import onceagain.enums.Row;
import events.PutEvent;

final class BoardSetter {
    private List<PutEvent> putEvents = new ArrayList<PutEvent>();

    BoardSetter() {
        putEvents.addAll(Arrays.asList(w_King_e_1, w_Queen_d_1, b_King_e_8, b_Queen_d_8));

        putEvents.addAll(Arrays.asList(w_Bishop_c_1, w_Bishop_f_1, b_Bishop_c_8, b_Bishop_f_8));
        putEvents.addAll(Arrays.asList(w_Knight_b_1, w_Knight_g_1, b_Knight_b_8, b_Knight_g_8));
        putEvents.addAll(Arrays.asList(w_Rook_a_1, w_Rook_h_1, b_Rook_a_8, b_Rook_h_8));

        putEvents.addAll(Arrays.asList(w_Pawn_a_2, w_Pawn_b_2, w_Pawn_c_2, w_Pawn_d_2));
        putEvents.addAll(Arrays.asList(w_Pawn_e_2, w_Pawn_f_2, w_Pawn_g_2, w_Pawn_h_2));
        putEvents.addAll(Arrays.asList(b_Pawn_a_7, b_Pawn_b_7, b_Pawn_c_7, b_Pawn_d_7));
        putEvents.addAll(Arrays.asList(b_Pawn_e_7, b_Pawn_f_7, b_Pawn_g_7, b_Pawn_h_7));
    }

    ChessBoard setBoard() {
        ChessBoard board = new ChessBoard();

        for (PutEvent put : putEvents) {
            board = board.playEvent(put);
        }

        return board;
    }

    private PutEvent b_King_e_8 = new PutEvent(piece(Rank.King, Color.BLACK), square(Column.E, Row.R8));
    private PutEvent w_King_e_1 = new PutEvent(piece(Rank.King, Color.WHITE), square(Column.E, Row.R1));
    private PutEvent b_Queen_d_8 = new PutEvent(piece(Rank.Queen, Color.BLACK), square(Column.D, Row.R8));
    private PutEvent w_Queen_d_1 = new PutEvent(piece(Rank.Queen, Color.WHITE), square(Column.D, Row.R1));

    private PutEvent b_Bishop_c_8 = new PutEvent(piece(Rank.Bishop, Color.BLACK), square(Column.C, Row.R8));
    private PutEvent b_Bishop_f_8 = new PutEvent(piece(Rank.Bishop, Color.BLACK), square(Column.F, Row.R8));
    private PutEvent w_Bishop_c_1 = new PutEvent(piece(Rank.Bishop, Color.WHITE), square(Column.C, Row.R1));
    private PutEvent w_Bishop_f_1 = new PutEvent(piece(Rank.Bishop, Color.WHITE), square(Column.F, Row.R1));

    private PutEvent b_Knight_b_8 = new PutEvent(piece(Rank.Knight, Color.BLACK), square(Column.B, Row.R8));
    private PutEvent b_Knight_g_8 = new PutEvent(piece(Rank.Knight, Color.BLACK), square(Column.G, Row.R8));
    private PutEvent w_Knight_b_1 = new PutEvent(piece(Rank.Knight, Color.WHITE), square(Column.B, Row.R1));
    private PutEvent w_Knight_g_1 = new PutEvent(piece(Rank.Knight, Color.WHITE), square(Column.G, Row.R1));

    private PutEvent b_Rook_a_8 = new PutEvent(piece(Rank.Rook, Color.BLACK), square(Column.A, Row.R8));
    private PutEvent b_Rook_h_8 = new PutEvent(piece(Rank.Rook, Color.BLACK), square(Column.H, Row.R8));
    private PutEvent w_Rook_a_1 = new PutEvent(piece(Rank.Rook, Color.WHITE), square(Column.A, Row.R1));
    private PutEvent w_Rook_h_1 = new PutEvent(piece(Rank.Rook, Color.WHITE), square(Column.H, Row.R1));

    private PutEvent b_Pawn_a_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.A, Row.R7));
    private PutEvent b_Pawn_b_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.B, Row.R7));
    private PutEvent b_Pawn_c_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.C, Row.R7));
    private PutEvent b_Pawn_d_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.D, Row.R7));
    private PutEvent b_Pawn_e_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.E, Row.R7));
    private PutEvent b_Pawn_f_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.F, Row.R7));
    private PutEvent b_Pawn_g_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.G, Row.R7));
    private PutEvent b_Pawn_h_7 = new PutEvent(piece(Rank.Pawn, Color.BLACK), square(Column.H, Row.R7));

    private PutEvent w_Pawn_a_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.A, Row.R2));
    private PutEvent w_Pawn_b_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.B, Row.R2));
    private PutEvent w_Pawn_c_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.C, Row.R2));
    private PutEvent w_Pawn_d_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.D, Row.R2));
    private PutEvent w_Pawn_e_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.E, Row.R2));
    private PutEvent w_Pawn_f_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.F, Row.R2));
    private PutEvent w_Pawn_g_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.G, Row.R2));
    private PutEvent w_Pawn_h_2 = new PutEvent(piece(Rank.Pawn, Color.WHITE), square(Column.H, Row.R2));

    private Piece piece(Rank rank, Color col) {
        return ChessPiece.newPiece(rank, col);
    }

    private Square square(Column col, Row row) {
        return new Square(col, row);
    }

}