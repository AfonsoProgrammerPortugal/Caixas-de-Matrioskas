package tests;
import static org.junit.Assert.assertEquals;
import static tests.MatrioskaStackTest.fastStack;
import static project.Matrioska.*;


import org.junit.Test;

import project.Box;
import project.Matrioska;
import project.MatrioskaStack;


public class BoxTest {
    

    @Test
    public void test_constructor() {
        Box box = new Box();
        assertEquals(true, box.isEmpty());
        assertEquals(false,box.isFull());
        assertEquals(0, box.getWeight());
    }

    @Test
    public void test_add() {
        Box box = new Box();
        MatrioskaStack mStack = fastStack(D);
        box.add(mStack);
        assertEquals(1, box.getWeight());

        MatrioskaStack mStack2 = fastStack(EL);
        box.add(mStack2);
        assertEquals(3, box.getWeight());
    }

    @Test
    public void test_copy(){
        Box box = new Box();
        MatrioskaStack mStack = fastStack(D);
        box.add(mStack);
        Box box2 = box.copy();
        assertEquals(1, box2.getWeight());
        box2.removeLast();
        assertEquals(1, box.getWeight());
        assertEquals(0, box2.getWeight());
    }

    @Test
    public void test_remove(){
        Box box = new Box();
        MatrioskaStack mStack = fastStack(D);
        box.add(mStack);   //just D here
        assertEquals(1, box.getWeight());
        box.removeLast();  
        assertEquals(0, box.getWeight());
        box.add(mStack);   //still just D
        mStack.push(ANA);
        box.add(mStack);   //D and ANA
        box.add(mStack);   //D and ANA again
        box.removeLast();  //removing a instance of D and ANA
        assertEquals(5, box.getWeight());

    }


    @Test
    public void test_capacity(){
        Box box = new Box();
        MatrioskaStack mStack = fastStack(D);
        box.add(mStack);
        assertEquals(1, box.getWeight());
        box.add(mStack);
        assertEquals(2, box.getWeight());
        for (int index = 0; index < 5; index++)
            if(box.getNumMatrioskas() < Box.CAPACITY)
                box.add(mStack);
        assertEquals(5, box.getWeight());
    }

    @Test
    public void test_getMatrioska() {
        Box box = new Box();
        MatrioskaStack mStack = fastStack(D);
        box.add(mStack);
        assertEquals(D, box.getMatrioska(1).peek());
        mStack.push(EL);
        mStack.push(LIVIA);
        box.add(mStack);
        assertEquals(D, box.getMatrioska(1).peek());
        assertEquals(LIVIA, box.getMatrioska(2).peek());
    }


    @Test
    public void test_compact() {
        Box box = fastBox(ANA,LIVIA);
        assertEquals(8, box.getWeight());

        box.add(fastStack(EL));
        box.compact();
        assertEquals(10, box.getWeight());

        box.add(fastStack(MARIANA));
        box.compact();
        assertEquals(17, box.getWeight());
    }

    @Test
    public void test_compact_2(){
        Box box = fastBox(ANA,LIVIA,MARIANA);
        assertEquals(15, box.getWeight());

        box.add(fastStack(EL,LIVIA));
        box.compact();
        assertEquals(22, box.getWeight());

        box.add(fastStack(MARIANA));
        box.compact();
        assertEquals(29, box.getWeight());

        box.add(fastStack(RITA));
        box.add(fastStack(D));
        box.compact();
        assertEquals(34, box.getWeight());
        assertEquals(false, box.isFull());

        MatrioskaStack ms0 = box.getMatrioska(1);
        assertEquals(MARIANA,ms0.peek());
        ms0.pop();
        assertEquals(LIVIA,ms0.peek());
        ms0.pop();
        assertEquals(RITA,ms0.peek());

        MatrioskaStack ms1 = box.getMatrioska(2);
        assertEquals(MARIANA,ms1.peek());
        ms1.pop();
        assertEquals(LIVIA,ms1.peek());
        ms1.pop();
        assertEquals(EL,ms1.peek());
    }

    @Test
    public void test_compact_3(){
        Box box = fastBox(LIVIA,MARIANA);
        assertEquals(12, box.getWeight());

        box.add(fastStack(ANA));
        assertEquals(15, box.getWeight());

        box.add(fastStack(LIVIA,HELENA));
        assertEquals(26, box.getWeight());

        box.add(fastStack(RITA));
        assertEquals(30, box.getWeight());

        box.add(fastStack(ANA));
        assertEquals(33, box.getWeight());
        assertEquals(true, box.isFull());
        assertEquals(box.getNumMatrioskas(), 5);

        box.compact();
        assertEquals(false, box.isFull());
        assertEquals(box.getNumMatrioskas(), 2);

        MatrioskaStack ms0 = box.getMatrioska(1);
        assertEquals(MARIANA,ms0.peek());
        ms0.pop();
        assertEquals(LIVIA,ms0.peek());
        ms0.pop();
        assertEquals(RITA,ms0.peek());
        ms0.pop();
        assertEquals(ANA,ms0.peek());

        MatrioskaStack ms1 = box.getMatrioska(2);
        assertEquals(HELENA,ms1.peek());
        ms1.pop();
        assertEquals(LIVIA,ms1.peek());
        ms1.pop();
        assertEquals(ANA,ms1.peek());

    }
    
    public static Box fastBox(Matrioska... matrioskas) {
        Box box = new Box();
        box.add(fastStack(matrioskas));
        return box;
    }

}
