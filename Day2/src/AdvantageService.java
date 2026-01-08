import java.util.*;

public class AdvantageService {
    private static final Map<Type, Set<Type>> ADVANTAGE_MAP = new HashMap<>();

    static {
        ADVANTAGE_MAP.put(Type.Fire, Set.of(Type.Grass, Type.Ghost));
        ADVANTAGE_MAP.put(Type.Water, Set.of(Type.Fire));
        ADVANTAGE_MAP.put(Type.Grass, Set.of(Type.Electric, Type.Fighting));
        ADVANTAGE_MAP.put(Type.Electric, Set.of(Type.Water));
        ADVANTAGE_MAP.put(Type.Psychic, Set.of(Type.Ghost));
        ADVANTAGE_MAP.put(Type.Ghost, Set.of(Type.Fighting, Type.Fire, Type.Electric));
        ADVANTAGE_MAP.put(Type.Fighting, Set.of(Type.Electric));
    }

    public boolean hasTypeAdvantage(Type attacker, Type defender) {
        return ADVANTAGE_MAP.getOrDefault(attacker, Set.of()).contains(defender);
    }
}
