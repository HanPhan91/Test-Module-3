package Enum;

public enum StatusEnum {
    active(1), deactive(0);
    private int value;

    StatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static StatusEnum parseStatus(int value) {
        StatusEnum[] values = values();
        for (StatusEnum st : values) {
            if (st.value == value)
                return st;
        }
        throw new IllegalArgumentException("value position invalid");
    }
}
