package onceagain.movements;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import onceagain.enums.Rank;

import org.junit.Test;

public class StrategySelectorTest {

    private StrategySelector strategySelectory = null;

    @Test
    public void test() {
        strategySelectory = new StrategySelector();
        for (Rank rank : Rank.values()) {
            MoveStrategy selectedStrategy = strategySelectory.selectMoveStrategy(rank);
            assertNotNull(selectedStrategy);
            assertThat(selectedStrategy.rank(), equalTo(rank));
        }
    }

}
