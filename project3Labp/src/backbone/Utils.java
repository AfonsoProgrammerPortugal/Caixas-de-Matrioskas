package backbone;

import java.util.EmptyStackException;

public class Utils {
    
    /**
     *  Returns a new Stack that is the reverse of the specified Stack. 
     *  The original Stack is not modified.
     *  
     *  This method first creates a copy of the input Stack to avoid modifying the original object, 
     *  then iterates through the elements of the copy, 
     *  removing each element from the top of the Stack and pushing it onto a temporary Stack. 
     *  The result is a new Stack with the same elements as the input Stack, but in reverse order.
     *   @param stack the Stack to be reversed
     *   @param <E> the type of elements in the Stack
     *   @return a new Stack that is the reverse of the specified Stack
     *   @throws EmptyStackException if the Stack is empty
    */
    public static <E> Stack<E> invertStack(Stack<E> stack) throws EmptyStackException{
        stack = stack.copy();
        Stack <E> temp = new ArrayStack<E>();
        while (!stack.isEmpty())
            temp.push(peekPop(stack));
        return temp;
    }

    /**
        Retrieves and removes the top element of the specified Stack. 
        
        This method is equivalent to calling {@code stack.peek()} followed by {@code stack.pop()}, 
        but has the advantage of being atomic, so that another thread cannot modify the Stack 
        between the two operations.
        
        @param stack the Stack to retrieve and remove the top element from
        @param <E> the type of elements in the Stack
        @return the top element of the Stack that was removed
        @throws EmptyStackException if the Stack is empty
    */
    public static <E> E peekPop(Stack<E> stack) throws EmptyStackException{
        E elem = stack.peek();
        stack.pop();
        return elem;
    }

}
