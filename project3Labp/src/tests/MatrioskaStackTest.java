package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static project.Matrioska.*;

import org.junit.Test;

import backbone.Utils;
import project.Matrioska;
import project.MatrioskaStack;


public class MatrioskaStackTest {

   @Test
    public void test_constructor() {
         MatrioskaStack matrioskaStack = new MatrioskaStack();
         matrioskaStack.push(EL);
         assertEquals(2, matrioskaStack.getWeight());
    }
    @Test
    public void test_push() {
        MatrioskaStack matrioskaStack = new MatrioskaStack();
        matrioskaStack.push(EL);
        matrioskaStack.push(ANA);
        assertEquals(5, matrioskaStack.getWeight());
        if(Matrioska.smallerThan(matrioskaStack.peek(),D))
            matrioskaStack.push(D);
        assertEquals(5, matrioskaStack.getWeight());
    }

    @Test
    public void test_pop() {
        MatrioskaStack matrioskaStack = new MatrioskaStack();
        matrioskaStack.push(EL);
        matrioskaStack.push(ANA);
        matrioskaStack.pop();
        assertEquals(2, matrioskaStack.getWeight());
    }

    @Test
    public void test_copy() {
        MatrioskaStack matrioskaStack = fastStack(EL, ANA);
        MatrioskaStack matrioskaStack2 = matrioskaStack.copy();
        assertEquals(matrioskaStack.getWeight(), matrioskaStack2.getWeight());
        matrioskaStack2.push(RITA);
        assertNotEquals(matrioskaStack.peek(), matrioskaStack2.peek());
    }

    @Test
    public void test_peek() {
        MatrioskaStack matrioskaStack = new MatrioskaStack();
        matrioskaStack.push(EL);
        matrioskaStack.push(ANA);
        assertEquals(ANA, matrioskaStack.peek());
    }

    @Test
    public void test_merge() {
        MatrioskaStack matrioskaStack = fastStack(EL,ANA);
        MatrioskaStack matrioskaStack2 = fastStack(RITA);
        boolean merged = matrioskaStack.merge(matrioskaStack2);
        assertEquals(true, merged);
        assertEquals(9, matrioskaStack.getWeight());

        MatrioskaStack matrioskaStack3 = fastStack(RITA);
        boolean failMerged = matrioskaStack.merge(matrioskaStack3);
        assertEquals(false, failMerged);
        assertEquals(9, matrioskaStack.getWeight());

        MatrioskaStack matrioskaStack4 = fastStack(D,LIVIA,HELENA,MARIANA);
        boolean finalMerge = matrioskaStack.merge(matrioskaStack4);
        assertEquals(true, finalMerge);
        assertEquals(28, matrioskaStack.getWeight());
        
        assertEquals(MARIANA,Utils.peekPop(matrioskaStack));
        assertEquals(HELENA,Utils.peekPop(matrioskaStack));
        assertEquals(LIVIA,Utils.peekPop(matrioskaStack));
        assertEquals(RITA,Utils.peekPop(matrioskaStack));
        assertEquals(ANA,Utils.peekPop(matrioskaStack));
        assertEquals(EL,Utils.peekPop(matrioskaStack));
        assertEquals(D,Utils.peekPop(matrioskaStack));          
        
    }

    public static MatrioskaStack fastStack(Matrioska... matrioskas) {
        MatrioskaStack mStack = new MatrioskaStack();
        for (Matrioska matrioska : matrioskas) {
            mStack.push(matrioska);
        }
        return mStack;
    }

}

