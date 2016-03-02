package strategy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoardGradeTest {

    BoardGrade boardGrade = null;

    @Test
    public void the_final_grade_is_the_sum_of_all_the_grades() {
        boardGrade = new BoardGrade(0, 0, 0, 0);
        assertThat(boardGrade.finalGrade(), equalTo(0));

        boardGrade = new BoardGrade(1, 0, 0, 0);
        assertThat(boardGrade.finalGrade(), equalTo(1));

        boardGrade = new BoardGrade(0, 1, 0, 0);
        assertThat(boardGrade.finalGrade(), equalTo(1));

        boardGrade = new BoardGrade(0, 0, 1, 0);
        assertThat(boardGrade.finalGrade(), equalTo(1));

        boardGrade = new BoardGrade(0, 0, 0, 1);
        assertThat(boardGrade.finalGrade(), equalTo(1));

        boardGrade = new BoardGrade(1, 1, 1, 1);
        assertThat(boardGrade.finalGrade(), equalTo(4));
    }

    @Test
    public void it_returns_the_grade_with_the_higher_final_score() {
        BoardGrade lowerBoardGrade = new BoardGrade(0, 0, 0, 0);
        BoardGrade higherBoardGrade = new BoardGrade(2, 2, 2, 2);

        boardGrade = new BoardGrade(1, 1, 1, 1);

        assertThat(boardGrade.higherGrade(lowerBoardGrade), equalTo(boardGrade));
        assertThat(boardGrade.higherGrade(higherBoardGrade), equalTo(higherBoardGrade));
    }

    @Test
    public void it_returns_the_grade_with_the_lower_final_score() {
        BoardGrade lowerBoardGrade = new BoardGrade(0, 0, 0, 0);
        BoardGrade higherBoardGrade = new BoardGrade(2, 2, 2, 2);

        boardGrade = new BoardGrade(1, 1, 1, 1);

        assertThat(boardGrade.lowerGrade(lowerBoardGrade), equalTo(lowerBoardGrade));
        assertThat(boardGrade.lowerGrade(higherBoardGrade), equalTo(boardGrade));
    }

}
