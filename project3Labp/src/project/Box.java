package project;

/**
 * 
 * @author Afonso Santos - fc59808
 *
 */
public class Box {

    public static final int CAPACITY = 5;
    private int weight;
    private int numMatrioskas;
    private MatrioskaStack[] content;
    
    /**
     * Checks if the first box is
     * heavier than the second.
     * 
     * @param peek (first Box)
     * @param box (second Box)
     * @return true if first Box heavier than second,
     * false if second Box heavier than first
     */
    public static boolean heavierThan(Box peek, Box box) {
    	return peek.getWeight() > box.getWeight();
    }
    
    /**
     * Constructor of Box
     */
    public Box() {
    	weight = 0;
    	numMatrioskas = 0;
    	content = new MatrioskaStack[CAPACITY];
    }
    
    /**
     * return the current number of Matrioskas inside the box
     * 
     * @return number of Matrioskas
     */
    public int getNumMatrioskas(){
    	return numMatrioskas;
    }
    
    /**
     * adds matrioska to the box, in the first available position
     * 
     * @requires {@code getNumMatrioskas() < Box.CAPACITY}
     * @requires {@code matrioska != null}
     * @param matrioska
     */
    public void add(MatrioskaStack matrioska) {
    		content[numMatrioskas] = matrioska.copy();
    		weight += matrioska.getWeight();
    		numMatrioskas++;
    }
    
    /**
     * Returns a copy of the current box
     * 
     * @return the copy of the box
     */
    public Box copy() {
        Box copy = new Box();
        for (int i = 0; i < numMatrioskas; i++) {
        	copy.add(content[i]);
        }
        return copy;
    }
    
    /**
     * Returns a copy of MatrioskaStack in
     * pos position inside the box
     * 
     * @requires {@code 1 <= pos <= getNumMatrioskas()}
     * @param pos the position of matrioska
     * @return the matrioska in pos
     */
    public MatrioskaStack getMatrioska(int pos) {
       return content[pos-1].copy();
    }
    
    /**
     * Removes the last inserted matrioska from the box
     * 
     * @requires {@code getNumMatrioskas() > 0}
     */
    public void removeLast() {
    	weight -= content[numMatrioskas-1].getWeight();
        content[numMatrioskas-1] = null;
        numMatrioskas--;
    }
    
    /**
     * Verifies if the box is empty
     * 
     * @return true if the box is empty,
     * false if the box is not empty
     */
    public boolean isEmpty() {
        return numMatrioskas == 0;
    }
    
    /**
     * Verifies if the box is full
     * 
     * @return true if the box is full,
     * false if the box is not full
     */
    public boolean isFull() {
        return numMatrioskas == 5;
    }
    
    /**
     * Returns the weight of the box
     * @return the weight
     */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Compact the box to reduce the number of matrioska Stacks
	 * @requires {@code getNumMatrioskas() > 1}
	 */
    public void compact(){
        
    	 int n = 0;
         int m = 1;
         
         while (n < numMatrioskas) {
             if (m < numMatrioskas && content[n].merge(content[m])) {
                 for (int i = m; i < numMatrioskas - 1; i++) {
                     content[i] = content[i + 1];
                 }

                 content[numMatrioskas - 1] = null;
                 numMatrioskas--;
             } else {
                 m++;

                 if (m >= numMatrioskas) {
                     n++;
                     m = n + 1;
                 }
             }
         }
    }
    
  

    /**
     * Constroi uma representacao de string do estado atual da instancia de Box.
        A string começa com o peso da caixa e o numero de MatrioskaStack dentro da caixa, e entao
        inclui uma representacao de string de cada MatrioskasStack aninhada.
        @return uma representacao textual da instancia de Box
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("   ↳BOX: w: ").append(getWeight()) ;
        sb.append(" #itens: ").append(getNumMatrioskas()).append(System.lineSeparator());
        for (int i = 0; i < getNumMatrioskas(); i++) {
            sb.append(content[i].toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

}
