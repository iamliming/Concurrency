package chapter;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author liming
 * @version 2.2.7
 * @date 15-2-28 下午3:57
 */
//@ThreadSafe
public class OneValueCache {
	private final BigInteger lastNumber;
	private final BigInteger[] lastFactors;

	public OneValueCache(BigInteger i, BigInteger[] factors){
		lastNumber = i;
		if(factors != null) {
			lastFactors = Arrays.copyOf(factors, factors.length);
		}
		else{
			lastFactors = null;
		}
	}

	public BigInteger[] getLastFactors(BigInteger i){
		if(lastNumber == null || !lastNumber.equals(i)){
			return null;
		}
		else{
			return Arrays.copyOf(lastFactors, lastFactors.length);
		}
	}
}
