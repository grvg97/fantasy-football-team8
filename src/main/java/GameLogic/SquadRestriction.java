package GameLogic;

public enum SquadRestriction {
    GKCOUNT(1), DEFCOUNT(4),
    MIDCOUNT(3), FORCOUNT(3);

    private int numVal;

    SquadRestriction(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return this.numVal;
    }

}
