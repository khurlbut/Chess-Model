package strategy;

import enums.Coin;

public class BoardGrade {

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
            return Coin.toss() == Coin.HEADS ? this : otherGrade;
        }
        return this;
    }

    public BoardGrade higherGrade(BoardGrade otherGrade) {
        if (otherGrade.finalGrade() > this.finalGrade()) {
            return otherGrade;
        }
        if (otherGrade.finalGrade() == this.finalGrade()) {
            return Coin.toss() == Coin.TAILS ? this : otherGrade;
        }
        return this;
    }

}