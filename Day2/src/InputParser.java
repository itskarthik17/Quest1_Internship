import java.util.*;

public class InputParser {
    public static List<Mopoken> parse(String input) {

        String[] mopokens = input.split(";");
        //System.out.println(mopokens.length);
        if (mopokens.length != 5) {
            System.err.println("Each Breeder excatly need to have 5");
        } 

        Set<Type> seenTypes = new HashSet<>();
        List<Mopoken> result = new ArrayList<>();
        
    
        for(String mopoken : mopokens){
            String[] parts = mopoken.split("#");
            Type type = Type.valueOf(parts[0]);
            int level = Integer.parseInt(parts[1]);

            if(!seenTypes.add(type)) {
                System.err.println("Duplicate Mopoken Type: "+ type);
            }

            result.add(new Mopoken(type, level));
        }
        //System.out.println(result);
        return result;
    }
}
