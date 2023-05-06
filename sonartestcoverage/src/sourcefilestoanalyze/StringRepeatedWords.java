package sourcefilestoanalyze;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class StringRepeatedWords {

	public static void main(String[] args) {
		String sentence = "Bad is good good is Bad so much good";

		String[] words = sentence.split(" ");
		
		/****************************************************************/
		List<String> list = Arrays.asList("aaa", "bb", "cccc", "d");
		Predicate<String> p = new Predicate<String>() {

			@Override
			public boolean test(String t) {
				// TODO Auto-generated method stub
				return t.length() > 2 ;
			}
			
		};
		list.stream().filter(p).forEach(s -> System.out.print(s + " "));
		
		/********************************************************************/
		
		Set<String> set = new HashSet<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String word : words) {
			if (!set.add(word))
				map.put(word, map.get(word) + 1);
			else
				map.put(word, 1);
		}

		for (Map.Entry<String, Integer> m : map.entrySet()) {
			if (m.getValue() > 1)
				System.out.println(m.getKey() + ":" + m.getValue());
		}

	}

}
