///usr/bin/env jbang "$0" "$@" ; exit $?
// //DEPS <dependency1> <dependency2>

import static java.lang.System.*;
import java.util.HashMap;
import java.util.Map;
import static java.lang.System.out;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class FirstNonRepeatedCharacter {

    public static void main(String... args) {
        System.out.println(firstNonRepeatedCharacterV2("aaddefllf"));
    }


    public static String firstNonRepeatedCharacter(String str) {
        if (str == null || str.isBlank()){
            return "";
        }

        Map<Character, Integer> characterCount = new HashMap<>();
        str = str.replaceAll("\\s", "");
        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            characterCount.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }

        for (Map.Entry<Character, Integer> entry : characterCount.entrySet()) {
            if (entry.getValue() == 1){
                return String.valueOf(entry.getKey());
            }
        }

        return "";
    }

    public static String firstNonRepeatedCharacterV2(String str) {
        if (str == null || str.isBlank()){
            return "";
        }

        Map<Integer, Long> characterCount = str.codePoints()
            .mapToObj(cp -> cp)
            .collect(Collectors.groupingBy(Function.identity(),
                        LinkedHashMap::new, Collectors.counting()));
        
        int cp = characterCount.entrySet().stream()
            .filter(e -> e.getValue() == 1L)
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(Integer.valueOf(Character.MIN_VALUE));

        return String.valueOf(Character.toChars(cp));
    }
}

