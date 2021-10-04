///usr/bin/env jbang "$0" "$@" ; exit $?
// //DEPS <dependency1> <dependency2>


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import static java.lang.System.out;

public class CountDuplicates {

    public static void main(String... args) {
	  System.out.println(countDuplicateCharactersV2("hello world"));
    }

    public static Map<Character, Integer> countDuplicateCharactersV1(String str) {
        if(str == null || str.isBlank()){
	        return Collections.emptyMap();
	    }

	    Map<Character, Integer> result = new HashMap<>();
	    str = str.replaceAll("\\s", "");
	    for (int i = 0; i < str.length(); i++){
	        char ch = str.charAt(i);
	        result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
	    }
	  
        return result;
    }

    public static Map<String, Long> countDuplicateCharactersV2(String str) {
        if (str == null || str.isBlank()){
            return Collections.emptyMap();
        }

        Map<String, Long> result = str.codePoints()
            .mapToObj(c -> String.valueOf(Character.toChars(c)))
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        return result;
    }
}







