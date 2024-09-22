package project;

import backbone.ArrayStack;
import backbone.Stack;
import backbone.Utils;
/**
 * 
 * @author Afonso Santos - fc59808
 *
 */
public class MatrioskaStack implements Stack<Matrioska>{
    
    private Stack<Matrioska> stack;
    private int weight;
    /**
     * Constructor of MatrioskaStack
     */
    public MatrioskaStack() {
    	weight = 0;
    	stack = new ArrayStack<>();
    }
    /**
     * Returns the weight of the Stack of matrioskas
     * 
     * @return weight
     */
    public int getWeight() {
    	return weight;
    }
    
    /**
     * Tries to merge the current stack with the receiving stack
     * 
     * @requires {@code other != null}
     * @param other(Second Stack)
     * @return true will merge the two Stacks,
     * false cannot merge the Stacks
     */
    public boolean merge(MatrioskaStack other) {
    	if (stack.isEmpty()) {
    		stack = other.copy();
    		weight = other.weight;
    		return true;
    	}
    	else {
    		boolean result = true;
    		
    		Stack<Matrioska> copyOther = other.copy();
    		copyOther = Utils.invertStack(copyOther);
    		Stack<Matrioska> stackCopy = stack.copy();
    		stackCopy = Utils.invertStack(stackCopy);
    		MatrioskaStack finalStack = new MatrioskaStack();
    		
    		while ((!copyOther.isEmpty() || !stackCopy.isEmpty()) && result) {
    			if (copyOther.isEmpty()) {
    				finalStack.push(Utils.peekPop(stackCopy));
    			}
    			else if (stackCopy.isEmpty()) {
    				finalStack.push(Utils.peekPop(copyOther));
    			}
    			else if (stackCopy.peek().getSize() == copyOther.peek().getSize()) {
    				result = false;
    			}
    			else {
    				if (stackCopy.peek().getSize() > copyOther.peek().getSize()) {
    					finalStack.push(Utils.peekPop(copyOther));
    				}
    				else {
    					finalStack.push(Utils.peekPop(stackCopy));
    				}
    			}
    		}
    		if (result) {
    			while (!stack.isEmpty()) {
    				pop();
    			}
    			stack = finalStack.copy();
    			weight = finalStack.getWeight();
    		}
    		return result;
    	}
    	
    }

	@Override
    public MatrioskaStack copy() {
		MatrioskaStack result = new MatrioskaStack();
		
		result.stack = stack.copy();
		result.weight = weight;
		
		return result;
    }

    @Override
    public void push(Matrioska matrioska) {
    	stack.push(matrioska);
    	weight += matrioska.getSize();
    }

    @Override
    public void pop() {
    	weight -= stack.peek().getSize();
    	stack.pop();
    }

    @Override
    public Matrioska peek() {
    	return stack.peek();
    }

    @Override
    public boolean isEmpty() {
    	return stack.isEmpty();
    }
    
    /**
     * 
     * Retorna uma representacao de string do estado atual de uma instancia MatrioskaStack.
     * A string inclui o peso de toda a MatrioskaStack e uma representação de string de cada
     * Matrioska dentro da pilha, listados na ordem inversa a que foram adicionados à pilha.
     * @return uma representação de string do MatrioskaStack
     * 
     */
    @Override
    public String toString(){
        MatrioskaStack toPrint = this.copy();
        StringBuilder sb = new StringBuilder("      ");
        int i = 0;
        for(;!toPrint.isEmpty(); i++){
            sb.append(Utils.peekPop(toPrint));
            sb.append("[");
        }
        sb.setLength(sb.length()-1);
        for (; i > 1; i--) 
            sb.append("]");
        sb.append(" w: ").append(getWeight());
        return sb.toString();
    }

}
