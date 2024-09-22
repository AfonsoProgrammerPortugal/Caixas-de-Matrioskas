package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static tests.BoxTest.fastBox;
import static project.Matrioska.*;

import project.Box;

import org.junit.Test;

import project.BoxStack;
import project.MatrioskaStack;

public class BoxStackTest {
    
    @Test
    public void test_constructor() {
        BoxStack boxStack = new BoxStack();
        assertEquals(0, boxStack.getHeight());
        assertEquals(0, boxStack.getWeight());
    }

    @Test
    public void test_peek() {
        BoxStack boxStack = new BoxStack();
        Box box = new Box();
        boxStack.push(box);
        assertNotEquals(box, boxStack.peek());
    }
    @Test
    public void test_getWeight() {
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(LIVIA));
        assertEquals(5, boxStack.getWeight());
        
        boxStack.push(fastBox(RITA));
        assertEquals(9, boxStack.getWeight());

        boxStack.push(fastBox(D, EL));
        assertEquals(12, boxStack.getWeight());
    }

    @Test
    public void test_getHeight() {
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(D));
        assertEquals(1, boxStack.getHeight());
        boxStack.push(new Box());
        assertEquals(2, boxStack.getHeight());
    }

    @Test
    public void test_push() {
        BoxStack boxStack = new BoxStack();
        Box box = new Box();

        //successful add a MatrioskaStack in the box 
        MatrioskaStack mStack = new MatrioskaStack();
        mStack.push(RITA);
        box.add(mStack);

        boxStack.push(box);
        assertEquals(1, boxStack.getHeight());
        assertEquals(4, boxStack.getWeight());

        box.getMatrioska(1).push(LIVIA); // makes no difference because its a copy
        assertEquals(1, boxStack.getHeight());
        assertEquals(4, boxStack.getWeight());

        MatrioskaStack mStack2 = new MatrioskaStack();
        mStack2.push(ANA);
        box.add(mStack2);    //was added to the box but not to the boxStack. 
                             //The box added into the boxStack is a copy of this box. 

        mStack.push(LIVIA);  //again, it does not make any difference because its a copy.
        assertEquals(1, boxStack.getHeight());
        assertEquals(4, boxStack.getWeight());


        boxStack.push(fastBox(D));  //this works because the new box is 
                                    //lighter than the one that is there
        assertEquals(2, boxStack.getHeight());
        assertEquals(5, boxStack.getWeight());
    }
    @Test
    public void test_pop() {
        BoxStack boxStack = new BoxStack();
        Box box = fastBox(EL);
        boxStack.push(box);
       
        if(Box.heavierThan(boxStack.peek(), box))
            boxStack.push(box);

        assertEquals(1, boxStack.getHeight());
        assertEquals(2, boxStack.getWeight());

        boxStack.pop();
        assertEquals(0, boxStack.getHeight());
        assertEquals(0, boxStack.getWeight());
    }

    @Test
    public void test_copy() {
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(EL));
        
        BoxStack boxStack2 = boxStack.copy();
        assertNotEquals(boxStack, boxStack2);
        assertNotEquals(boxStack.peek(), boxStack2.peek());
        assertNotEquals(
                boxStack.peek().getMatrioska(1),
                boxStack2.peek().getMatrioska(1)
                );

        assertEquals(2, boxStack.peek().getWeight());
        assertEquals(boxStack.peek().getWeight(), boxStack2.peek().getWeight());
    }



    @Test
    public void test_pile(){
        BoxStack boxStack = new BoxStack();
        Box box = fastBox(HELENA);
        boxStack.push(box);
        Box box2 = fastBox(LIVIA);
        boxStack.push(box2);

        assertEquals(2, boxStack.getHeight());
        assertEquals(11, boxStack.getWeight());
        
        BoxStack boxStack2 = new BoxStack();
        Box box3 = fastBox(ANA);
        Box box4 = fastBox(D);

        boxStack2.push(box3);
        boxStack2.push(box4);
        assertEquals(2, boxStack2.getHeight());
        assertEquals(4, boxStack2.getWeight());

        boxStack.pile(boxStack2);

        assertEquals(4, boxStack.getHeight());
        assertEquals(15, boxStack.getWeight());
    }
    
   /* @Test
    public void test_pack(){
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(LIVIA));
        assertEquals(5, boxStack.getWeight());

        boxStack.push(fastBox(RITA));
        assertEquals(2, boxStack.getHeight());
        assertEquals(9, boxStack.getWeight());

        boxStack.pack();
        assertEquals(1, boxStack.getHeight());
        assertEquals(9, boxStack.getWeight());
    }


    @Test
    public void test_pack_2(){
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(LIVIA,HELENA));
        assertEquals(11, boxStack.getWeight());

        boxStack.push(fastBox(RITA,HELENA));
        assertEquals(2, boxStack.getHeight());
        assertEquals(21, boxStack.getWeight());

        boxStack.pack();
        assertEquals(1, boxStack.getHeight());
        assertEquals(21, boxStack.getWeight());
    }

    @Test
    public void test_pack_3(){
        BoxStack boxStack = new BoxStack();
        boxStack.push(fastBox(LIVIA, HELENA));
        assertEquals(11, boxStack.getWeight());

        boxStack.push(fastBox(RITA, HELENA));
        assertEquals(2, boxStack.getHeight());
        assertEquals(21, boxStack.getWeight());

        boxStack.push(fastBox(D, HELENA));
        assertEquals(3, boxStack.getHeight());
        assertEquals(28, boxStack.getWeight());

        boxStack.pack();
        assertEquals(1, boxStack.getHeight());
        assertEquals(28, boxStack.getWeight());
    }*/
}
