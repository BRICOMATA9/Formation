import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TP3 {
	//EXO 1 
	public static <T> List<T> mapToList(HashMap<String,List<T>> map){
		List<T> liste = new ArrayList<T>();
		map.forEach((c,l) -> liste.addAll(l));
		return liste;
	}
	public static <T> HashMap<String,List<T>> toArrayList(HashMap<String,List<T>> map){
		map.replaceAll((c,l) -> c.startsWith("*")? l=new ArrayList<T>(l) : l  );
		return map;
	}
	public static <T> HashMap<String,List<T>> append(HashMap<String,List<T>> map, String c, T t){
		map.compute(c, (k,l) -> {
			if(map.containsKey(c)){
				l.add(t);
				return l;
			}
			else 
				return new ArrayList<>(Collections.singleton(t)); 
		});
		return map;
	}
	//EXO2
	public static <U,T> U fold(Collection<T> c, U u, BiFunction<U,T,U> f) {
		for(T t:c) {
			u = f.apply(u,t);
		}
		return u;
	}
	public static Integer sumListInteger(List<Integer> l){
		return fold(l,0,(u,t)->t+u);
	}
	public static String concatListString(List<String> l){
		return fold(l,"",String::concat);
	}
	public static <U,T,V> V fold(Collection<T> c, U u, BiFunction<U,T,U> f, Function<U,V> g) {
		for(T t:c) {
			u = f.apply(u,t);
		}
		return g.apply(u);
	}
	public static <T> String likeToString(List<T> l){
		StringBuilder sb = new StringBuilder([);
		return fold(l,"", )
	}
	//MAIN
	public static void main(String[] args) {
//		HashMap<String,List<Character>> map = new HashMap<>();
//		List<Character> l1 = new ArrayList<>(Arrays.asList('a','b','c','d','e','f','g','h','i','j'));
//		List<Character> l2 = new ArrayList<>(Arrays.asList('k','l','m','n','o','p','q','r','s','t'));
//		map.put("*s", l1);
//		map.put("2", l2);
//		append(map,"2",'u');
//		System.out.println(map);
//		append(map,"Plo",'u');
//		System.out.println(map);
		List <String> l = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));
		System.out.println(concatListString(l));
		
	}

}
