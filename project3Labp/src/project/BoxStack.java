package project;

import backbone.ArrayStack;
import backbone.Stack;
import backbone.Utils;

/**
 * 
 * @author Afonso Santos - fc59808
 *
 */
public class BoxStack implements Stack<Box>{

    private Stack<Box> boxStack;
    private int weight;
    private int height;
    
    /**
     * Check if the first's lightest box is
     * heavier than second's heaviest box
     * 
     * @requires {@code first != null && second != null}
     * @param first the first box
     * @param second the second box
     * @return true if can pile the second box on the first box,
     * false if cannot compile
     */
    public static boolean canPile(BoxStack first, BoxStack second){
    	return lightestBox(first) > heaviestBox(second);
    }
    
    /**
     * Constructor of BoxStack
     */
	public BoxStack() {
    	weight = 0;
    	height = 0;
    	boxStack = new ArrayStack<>();
    }
	
	/**
	 * Returns the height of the box stack
	 * 
	 * @return the height of the box stack
	 */
    public int getHeight() {
    	return height;
    }

    /**
     * Returns the weight of the box stack
     * 
     * @return the weight of the box stack
     */
    public int getWeight() {
    	return weight;
    }

    /**
     * Push other onto the current stack
     * 
     * @param other the other box stack
     */
    public void pile(BoxStack other){
    	Stack<Box> copyOther = other.boxStack.copy();
    	copyOther = Utils.invertStack(copyOther);
		Stack<Box> stackCopy = boxStack.copy();
		stackCopy = Utils.invertStack(stackCopy);
		BoxStack finalStack = new BoxStack();
		
		while (!copyOther.isEmpty() || !stackCopy.isEmpty()) {
			if (copyOther.isEmpty()) {
				finalStack.push(Utils.peekPop(stackCopy).copy());
			}
			else if (stackCopy.isEmpty()) {
				finalStack.push(Utils.peekPop(copyOther).copy());
			}
			else {
				if (stackCopy.peek().getWeight() > copyOther.peek().getWeight()) {
					finalStack.push(Utils.peekPop(stackCopy).copy());
				}
				else {
					finalStack.push(Utils.peekPop(copyOther).copy());
				}
			}
		}
		while (!boxStack.isEmpty()) {
			pop();
		}
		boxStack = finalStack.copy();
		weight = finalStack.getWeight();
		height = finalStack.getHeight();
    }

    @Override
    public boolean isEmpty() {
    	return weight == 0;
    }

    @Override
    public BoxStack copy() {
    	BoxStack result = new BoxStack();
    	Stack<Box> copy = boxStack.copy();
    	copy = Utils.invertStack(copy);
    	
    	while (!copy.isEmpty()) {
    		result.boxStack.push(Utils.peekPop(copy).copy());
    	}
    	
    	result.height = height;
    	result.weight = weight;
    	
    	return result;
    }

    /**
     * 
     * 
     * @requires Box.heavierThan(this.peek(),box)
     */
    @Override
    public void push(Box box) {
    	boxStack.push(box);
    	weight += box.getWeight();
    	height++;
    }

    @Override
    public void pop() {
    	weight -= boxStack.peek().getWeight();
    	height--;
    	boxStack.pop();
    }
    
    @Override
    public Box peek() {
    	return boxStack.peek().copy();
    }
    
    /**
     * Retorna uma representação de string do estado atual de uma instancia BoxStack.

       A string inclui o peso de todo o BoxStack e uma representação de string de cada Box
       dentro da pilha, listados na ordem inversa a que foram adicionados à pilha.
       @return uma representação textual do BoxStack
     */
    @Override
    public String toString() {
        BoxStack toPrint = this.copy();
        StringBuilder sb = new StringBuilder("↙----Box Stack----↘");
        sb.append(" w: ").append(getWeight());
        sb.append(System.lineSeparator());
        while(!toPrint.isEmpty())
            sb.append(Utils.peekPop(toPrint).toString());
        sb.append("↖-------End-------↗");
        sb.append(System.lineSeparator());
        return sb.toString();
    }
    
    /*
     * 
     * Método desafio. Implementacao facultativa
     * 
     */
    public void pack(){}
    
    /**
     * Returns the heaviest box in the box stack
     * 
     * @param boxStack the box stack
     * @return the heaviest box in the box stack
     */
    private static int heaviestBox(BoxStack boxStack) {
    	BoxStack copy = boxStack.copy();
    	copy.boxStack = Utils.invertStack(copy);
    	
    	int max = Utils.peekPop(copy.boxStack).getWeight();
    	
    	return max;
    }
    
    /**
     * Returns the lightest box in the box stack
     * 
     * @param boxStack the box stack
     * @return the lightest box in the box stack
     */
    private static int lightestBox(BoxStack boxStack) {
    	return boxStack.peek().getWeight();
    }
    
    

}