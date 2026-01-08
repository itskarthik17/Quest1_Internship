import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        String s1 = "Fire#10;Water#20;Fighting#6;Psychic#10;Electric#12";
        String s2 = "Water#10;Fighting#10;Psychic#10;Fire#12;Grass#2";

        List<Mopoken> mine = InputParser.parse(s1);
        //System.out.println(mine);
        List<Mopoken> opponent = InputParser.parse(s2);

        AdvantageService advantageService = new AdvantageService();
        BattleEvaluator battleEvaluator = new BattleEvaluator(advantageService);
        BattlePlanner planner = new BattlePlanner(battleEvaluator);

        List<Mopoken> result = planner.findOrder(mine, opponent);

        System.out.println(result);

        StringBuilder sb = new StringBuilder();

        for(Mopoken m: result) {
            sb.append(m).append(';');
        }

        System.out.println(sb.toString());
    }
}
