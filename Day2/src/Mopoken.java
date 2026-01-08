public class Mopoken {
    Type type;
    int level;

    public Mopoken(Type type, int level) {
        this.type = type;
        this.level = level;
    }

    public Type getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return type + "#" + level;
    }
}
