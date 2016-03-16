package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liming
 * @version 2.2.7
 * @date 15-2-26 下午4:27
 */
public class AtomicTest {
	private static AtomicInteger nextHashCode =
			new AtomicInteger();

	/**
	 * The difference between successively generated hash codes - turns
	 * implicit sequential thread-local IDs into near-optimally spread
	 * multiplicative hash values for power-of-two-sized tables.
	 */
	private static final int HASH_INCREMENT = 0x61c88647;
	private static final Long HASH_INCREMENT_S = 127l;
	private static final Long HASH_INCREMENT_SS = 127l;

	/**
	 * Returns the next hash code.
	 */
	private static int nextHashCode() {
		return nextHashCode.getAndAdd(HASH_INCREMENT);
	}
  /*  private static int nextHashCodeS() {
        return nextHashCode.getAndAdd(HASH_INCREMENT_S);
    }*/

	public static void main(String[] args) {
        System.out.println(System.identityHashCode(HASH_INCREMENT));
        System.out.println(System.identityHashCode(HASH_INCREMENT_S));
        System.out.println(System.identityHashCode(HASH_INCREMENT_SS));
        /*System.out.println(nextHashCodeS());
		System.out.println(nextHashCodeS());
		System.out.println(nextHashCodeS());
		System.out.println(nextHashCodeS());
		System.out.println(nextHashCodeS());
		System.out.println(nextHashCodeS());*/
	}
}
