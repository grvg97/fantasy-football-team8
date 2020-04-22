package GameLogic;

// Formation: 4-3-3
// limits the number of players with the according positions in a Team
public enum Formation {
    GKCOUNT(1),
    DEFCOUNT(4),
    MIDCOUNT(3),
    FWDCOUNT(3);

    private int numVal;

    Formation(int numVal) {
        this.numVal = numVal;
    }

    public int getValue() {
        return this.numVal;
    }

}
