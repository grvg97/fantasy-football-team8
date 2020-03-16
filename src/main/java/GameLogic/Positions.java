package GameLogic;

public enum Positions {
    GK(1), DEF(2), MID(3), FOR(4);
    private int positionVal;

    Positions(int positionVal) {
        this.positionVal = positionVal;
    }

    public int getPositionVal() {
        return this.positionVal;
    }
}
