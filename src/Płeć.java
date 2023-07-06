
public class Płeć {
    private final PłećEnum płećEnum;

    public Płeć(PłećEnum płećEnum) {
        this.płećEnum = płećEnum;
    }

    public PłećEnum getPłećEnum() {
        return płećEnum;
    }

    @Override
    public String toString() {
        return płećEnum.getValue();
    }
}
