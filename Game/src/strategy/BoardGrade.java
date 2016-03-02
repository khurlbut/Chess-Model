package strategy;

public class BoardGrade {

    private static final boolean TAILS = false;
    private static final boolean HEADS = true;
    private int material;
    private int territory;
    private int position;
    private int collaboration;

    public BoardGrade(int material, int territory, int position, int collaborator) {
        this.material = material;
        this.territory = territory;
        this.position = position;
        this.collaboration = collaborator;
    }

    public Integer finalGrade() {
        return material + territory + position + collaboration;
    }

    public Integer materialGrade() {
        return material;
    }

    public Integer territoryGrade() {
        return territory;
    }

    public Integer positionGrade() {
        return position;
    }

    public Integer collaborationGrade() {
        return collaboration;
    }

    public BoardGrade lowerGrade(BoardGrade otherGrade) {
        if (otherGrade.finalGrade() < this.finalGrade()) {
            return otherGrade;
        }
        if (otherGrade.finalGrade() == this.finalGrade()) {
            return coinToss() == HEADS ? this : otherGrade;
        }
        return this;
    }

    public BoardGrade higherGrade(BoardGrade otherGrade) {
        if (otherGrade.finalGrade() > this.finalGrade()) {
            return otherGrade;
        }
        if (otherGrade.finalGrade() == this.finalGrade()) {
            return coinToss() == TAILS ? this : otherGrade;
        }
        return this;
    }

    private boolean coinToss() {
        double coin = Math.random();
        if (coin > 0.5) {
            return HEADS;
        } else {
            return TAILS;
        }
    }

}
