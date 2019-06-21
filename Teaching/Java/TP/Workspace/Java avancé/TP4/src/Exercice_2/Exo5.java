package Exercice_2;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.math.BigInteger;

public class Exo5 {

	public static void map_to_intstream() {
		int[][] arr = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
		IntStream stream = Arrays.stream(arr).flatMapToInt(Arrays::stream);
		System.out.println(Arrays.toString(stream.toArray()));
	}

	public static IntStream msb (BigInteger i) {
		int length = i.bitLength();
		int[] tab = new int[length];
		for(int j = 0;j<=length-1;j++)
			tab[length-1-j] =  i.testBit(j)?1:0;
		return 	Arrays.stream(tab);
	}

  public static void main(String[] args) {
	  Stream<BigInteger> pairs = Stream.iterate(BigInteger.ZERO, BigInteger.valueOf(2)::add);
	  Stream<BigInteger> pairs1000 = pairs.limit(4);
	  IntStream bits = pairs1000.flatMapToInt(s -> IntStream.concat(Exo5.msb(s).map(i-> i==0?'0':'1'),IntStream.of(' ')));
		//IntStream bits = pairs1000.flatMapToInt(s -> Exo5.msb(s).map(i-> i==0?'0':'1'));
	  bits.forEach(x -> {System.out.print((char)x);});
		System.out.println("");
  }
}
