package basic;

import java.util.Arrays;
import java.util.Date;

import domain.Baoma;
import domain.Vehicle;

/**
 * @author liming
 * @since 2015/8/11
 */
public class Arrayexample
{
    public static void main(String[] args)
    {
        Date date = new Date(1453894604876l);
        System.out.println(date);
        Integer[] ints = new Integer[10];
        Arrays.fill(ints, 10);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(Arrays.copyOf(ints, 11)));
        Integer[] copyInts = new Integer[11];
        System.arraycopy(ints,0,copyInts,0,Math.min(11,ints.length));
        System.out.println(Arrays.toString(copyInts));

        Baoma baomaA = new Baoma("red");
        Baoma baomaB = new Baoma("blue");
        Baoma baomaC = new Baoma("pink");

        Vehicle[] vehicles = {baomaA, baomaB, baomaC};

        Arrays.sort(vehicles, (Vehicle v1, Vehicle v2) -> ( v1.color().compareTo(v2.color())));
    }
}
