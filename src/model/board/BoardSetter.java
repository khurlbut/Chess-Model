package model.board;

import static model.Sugar.eventList;
import static model.Sugar.play;
import static model.Sugar.put;

import java.util.ArrayList;
import java.util.List;

import model.enums.Color;
import model.enums.Column;
import model.enums.Rank;
import model.enums.Row;

public final class BoardSetter {
    private List<GameEvent> putEvents = new ArrayList<GameEvent>();

    public BoardSetter() {
        putEvents.addAll(eventList(w_King_e_1, b_King_e_8, w_Queen_d_1, b_Queen_d_8));
        putEvents.addAll(eventList(w_Bishop_c_1, b_Bishop_c_8, w_Bishop_f_1, b_Bishop_f_8));
        putEvents.addAll(eventList(w_Knight_b_1, b_Knight_b_8, w_Knight_g_1, b_Knight_g_8));
        putEvents.addAll(eventList(w_Rook_a_1, b_Rook_a_8, w_Rook_h_1, b_Rook_h_8));
        putEvents.addAll(eventList(w_Pawn_a_2, w_Pawn_b_2, w_Pawn_c_2, w_Pawn_d_2));
        putEvents.addAll(eventList(b_Pawn_a_7, b_Pawn_b_7, b_Pawn_c_7, b_Pawn_d_7));
        putEvents.addAll(eventList(w_Pawn_e_2, w_Pawn_f_2, w_Pawn_g_2, w_Pawn_h_2));
        putEvents.addAll(eventList(b_Pawn_e_7, b_Pawn_f_7, b_Pawn_g_7, b_Pawn_h_7));
    }

    public ChessBoard setBoard() {
        ChessBoard board = new ChessBoard();

        board = play(putEvents, board);

        return board;
    }

    private PutEvent b_King_e_8 = put(Color.BLACK, Rank.King, Column.E, Row.R8);
    private PutEvent w_King_e_1 = put(Color.WHITE, Rank.King, Column.E, Row.R1);
    private PutEvent b_Queen_d_8 = put(Color.BLACK, Rank.Queen, Column.D, Row.R8);
    private PutEvent w_Queen_d_1 = put(Color.WHITE, Rank.Queen, Column.D, Row.R1);

    private PutEvent b_Bishop_c_8 = put(Color.BLACK, Rank.Bishop, Column.C, Row.R8);
    private PutEvent b_Bishop_f_8 = put(Color.BLACK, Rank.Bishop, Column.F, Row.R8);
    private PutEvent w_Bishop_c_1 = put(Color.WHITE, Rank.Bishop, Column.C, Row.R1);
    private PutEvent w_Bishop_f_1 = put(Color.WHITE, Rank.Bishop, Column.F, Row.R1);

    private PutEvent b_Knight_b_8 = put(Color.BLACK, Rank.Knight, Column.B, Row.R8);
    private PutEvent b_Knight_g_8 = put(Color.BLACK, Rank.Knight, Column.G, Row.R8);
    private PutEvent w_Knight_b_1 = put(Color.WHITE, Rank.Knight, Column.B, Row.R1);
    private PutEvent w_Knight_g_1 = put(Color.WHITE, Rank.Knight, Column.G, Row.R1);

    private PutEvent b_Rook_a_8 = put(Color.BLACK, Rank.Rook, Column.A, Row.R8);
    private PutEvent b_Rook_h_8 = put(Color.BLACK, Rank.Rook, Column.H, Row.R8);
    private PutEvent w_Rook_a_1 = put(Color.WHITE, Rank.Rook, Column.A, Row.R1);
    private PutEvent w_Rook_h_1 = put(Color.WHITE, Rank.Rook, Column.H, Row.R1);

    private PutEvent b_Pawn_a_7 = put(Color.BLACK, Rank.Pawn, Column.A, Row.R7);
    private PutEvent b_Pawn_b_7 = put(Color.BLACK, Rank.Pawn, Column.B, Row.R7);
    private PutEvent b_Pawn_c_7 = put(Color.BLACK, Rank.Pawn, Column.C, Row.R7);
    private PutEvent b_Pawn_d_7 = put(Color.BLACK, Rank.Pawn, Column.D, Row.R7);
    private PutEvent b_Pawn_e_7 = put(Color.BLACK, Rank.Pawn, Column.E, Row.R7);
    private PutEvent b_Pawn_f_7 = put(Color.BLACK, Rank.Pawn, Column.F, Row.R7);
    private PutEvent b_Pawn_g_7 = put(Color.BLACK, Rank.Pawn, Column.G, Row.R7);
    private PutEvent b_Pawn_h_7 = put(Color.BLACK, Rank.Pawn, Column.H, Row.R7);

    private PutEvent w_Pawn_a_2 = put(Color.WHITE, Rank.Pawn, Column.A, Row.R2);
    private PutEvent w_Pawn_b_2 = put(Color.WHITE, Rank.Pawn, Column.B, Row.R2);
    private PutEvent w_Pawn_c_2 = put(Color.WHITE, Rank.Pawn, Column.C, Row.R2);
    private PutEvent w_Pawn_d_2 = put(Color.WHITE, Rank.Pawn, Column.D, Row.R2);
    private PutEvent w_Pawn_e_2 = put(Color.WHITE, Rank.Pawn, Column.E, Row.R2);
    private PutEvent w_Pawn_f_2 = put(Color.WHITE, Rank.Pawn, Column.F, Row.R2);
    private PutEvent w_Pawn_g_2 = put(Color.WHITE, Rank.Pawn, Column.G, Row.R2);
    private PutEvent w_Pawn_h_2 = put(Color.WHITE, Rank.Pawn, Column.H, Row.R2);
}
