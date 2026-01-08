import java.util.*;

public class BattlePlanner {

    public BattleEvaluator evaluator;

    public BattlePlanner(BattleEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Mopoken> findOrder(List<Mopoken> mine, List<Mopoken> opponent) {

        List<Mopoken> best = null;
        int bestScore = 0;

        List<List<Mopoken>> permutes = permutation(mine);

        for (List<Mopoken> perm : permutes) {
            int score = 0;

            for (int i = 0; i < 5; i++) {
                if (evaluator.evaluate(perm.get(i), opponent.get(i)) > 0) {
                    score++;
                }
            }

            if (score > bestScore) {
                bestScore = score;
                best = perm;
            }
        }

        if (bestScore < 3){
            System.err.println("There are no chance of winning");
        }

        return best;
    }

    private List<List<Mopoken>> permutation(List<Mopoken> list) {
        List<List<Mopoken>> result = new ArrayList<>();
        permute(list, 0, result);
        return result;
    }

    private void permute(List<Mopoken> arr, int index, List<List<Mopoken>> result) {
        if (index == arr.size()) {
            result.add(new ArrayList<>(arr));
            return;
        }
        for (int i = index; i < arr.size(); i++) {
            Collections.swap(arr, i, index);
            permute(arr, index + 1, result);
            Collections.swap(arr, i, index);
        }
    }
}
