package Exo1;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;

public class TD2M1 {
	
	//Exo 1.1
	public static Set<String> colors (List<Apple> apps){
		Set<String> result = new TreeSet<>();
		for (Apple a:apps){
			result.add(a.getColor());
		}
		return result;
	}
	
	//Exo 1.2
	public static Set<Farm> farms (List<Apple> apps){
		Set<Farm> result = new TreeSet<>();
		for (Apple a:apps){
			result.add(a.getOrigin());
		}
		return result;
	}
	
	//Exo 1.3
	public static Set<String> destinations (List<Apple> apps){
		Set<String> result = new TreeSet<>();
		for (Apple a:apps)
			for(Market mkt: a.getOrigin().getDestinations())
			  result.add(mkt.getCity());
		return result;
	}
	
	//Exo 1.4
	public static Integer totalprice (List<Apple> apps) {
		int result = 0;
		for (Apple a:apps) {
			result += a.getOrigin().getPrice();
		}
		return result;
	}
	
	//Exo 1.5
	public static Set<Market> markets (List<Apple> apps){
		Set<Market> result = new TreeSet<>();
		for (Apple a:apps)
			result.addAll(a.getOrigin().getDestinations());
		return result;
	}

	public static Integer stands (List<Apple> apps) {
		int result = 0;
		for (Market mkt:markets(apps))
				result +=mkt.getnStalls();
		return result;
	}
	
	//Exo 2
	public static <T> Set<T> mapApple (List<Apple> ls, Inter1<T> func) {
		
		Set<T> result = new TreeSet<>();
		for (Apple a:ls)
			result.add(func.f(a));
		return result;
	}
	
	// même chose que la méthode colors, mais avec une classe anonyme
	
	public static Set<String> colors1 (List<Apple> apps) {
		return mapApple(apps,new Inter1<String>(){
			public String f (Apple a) {
				return a.getColor();
			}
		});
	}

	public static Set<String> colors2 (List<Apple> apps) {
		Inter1<String> i = new Inter1<String>(){
			public String f (Apple a) {
				return a.getColor();
			}
		};
		return mapApple(apps,i);
	}
	
	// même chose que la méthode colors, mais avec un lambda
	
	public static Set<String> colors3 (List<Apple> apps) {
		return mapApple(apps, a-> a.getColor());
	}
	
	// même chose que la méthode colors, mais avec une méthode nommée
	
	public static Set<String> colors4 (List<Apple> apps) {
		return mapApple(apps, Apple::getColor);
	}
	
	//encore une methode nommée
	
	public static Set<Farm> farms4 (List<Apple> apps) {
		return mapApple(apps, Apple::getOrigin);
		
	}

	//autres lambdas: ici la méthode nommée n'est pas possible
	
	public static Set<Character> chars (List<Apple> apps) {
		return mapApple(apps, a -> a.getColor().charAt(2));
	}
	
	public static Set<Integer> prices (List<Apple> apps) {
		return mapApple(apps, a -> a.getOrigin().getPrice());
	}
 	
	// qu'est-ce que fait cette méthode?
	
	public static Set<Integer> h (List<Apple> apps) {
		return mapApple(apps, a -> 5);
	}
	
	//Exo 3
	
	public static <S,T> Collection<T> mapSet (Collection<S> ls, Inter1bis<S,T> func) {
		Collection<T> result = new TreeSet<>();
		for (S s:ls)
			result.add(func.f(s));
		return result;
	}
	
	public static Collection<Character> thirds (List<String> ls) {
		return mapSet(ls, s -> s.charAt(2));
	}

	public static void main(String[] args) {

		Market market = new Market("Boumerdes",3);
		List<Market> markets = new ArrayList<Market>();
		markets.add(market);

		Farm farm = new Farm(2,3,markets);
		List<Farm> farms = new ArrayList<Farm>();
		farms.add(farm);

		Apple apple = new Apple(2,"orange",farm);
		List<Apple> apples = new ArrayList<Apple>();
		apples.add(apple);

		System.out.println(mapApple(apples, Apple::getColor));
		System.out.println(mapApple(apples, Apple::getWeight));
//		System.out.println(mapApple(apples, Apple::getOrigin));

		System.out.println( mapSet (apples, Apple::getColor));
		System.out.println( mapSet (apples, Apple::getWeight));
//		System.out.println( mapSet (apples, Apple::getOrigin));

		System.out.println( mapSet (farms, Farm::getnTrees));
		System.out.println( mapSet (farms, Farm::getPrice));
//		System.out.println( mapSet (frames, Apple::getDestinations));

		System.out.println( mapSet (markets, Market::getCity));
		System.out.println( mapSet (markets, Market::getnStalls));
	}
	
}
