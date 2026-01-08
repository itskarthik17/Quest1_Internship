public class BattleEvaluator {
    public AdvantageService advantageService;

    public BattleEvaluator(AdvantageService advantageService) {
        this.advantageService = advantageService;
    }

    public int evaluate(Mopoken mine, Mopoken opponent){
        if (advantageService.hasTypeAdvantage(mine.getType(), opponent.getType())) {
            if (opponent.getLevel() >= mine.getLevel() * 2) {
                return -1;
            }
            return 1;
        }

        if (advantageService.hasTypeAdvantage(opponent.getType(), mine.getType())) {
            if (mine.getLevel() >= opponent.getLevel() * 2) {
                return 1;
            } 
            return -1;
        }

        if (mine.getLevel() > opponent.getLevel()) return 1;
        if (mine.getLevel() < opponent.getLevel()) return -1;

        return 0;
    }
    
    
}
