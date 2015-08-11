package collection;

import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author liming
 * @since 2015/8/11
 */
public class StackExample
{
    public static void main(String[] args)
    {
        Deque<Integer> stack = new LinkedBlockingDeque<>();
        stack.push(2);
        stack.push(3);
        stack.push(1);
        stack.push(100);

        System.out.println("(peek)last in int:"+stack.peek());
        System.out.println("pop!");
        Integer p;
        while((p = stack.pop()) != null)
        {
            System.out.println(p);
        }

    }
}
