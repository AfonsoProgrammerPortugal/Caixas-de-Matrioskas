package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RunProject3 {
    

    public static void main(String[] args) throws FileNotFoundException {
        miniTest();
        bigTest();
    }

    private static MatrioskaStack[] createMatrioskaStacks(Scanner s, int limit) {
        MatrioskaStack[] stacks = new MatrioskaStack[limit];
        String[] inputs = s.nextLine().split(" ");
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new MatrioskaStack();
            Matrioska m = Matrioska.values()[Integer.parseInt(inputs[i])];
            if(stacks[i].isEmpty() || Matrioska.smallerThan(stacks[i].peek(),m));
                stacks[i].push(m);
        }
        return stacks;
    }

    private static BoxStack[] createBoxStacks(Scanner s, Box[] boxes) {
        BoxStack[] boxStacks = new BoxStack[boxes.length];
        for (int i = 0; i < boxStacks.length; i++) {
            boxStacks[i] = new BoxStack();
            pushIfValid(boxStacks[i], boxes[s.nextInt()]);
            pushIfValid(boxStacks[i], boxes[s.nextInt()]);
        }
        return boxStacks;
    }

    private static void pushIfValid(BoxStack boxStack, Box box) {
        if(boxStack.isEmpty() || Box.heavierThan(boxStack.peek(),box))
            boxStack.push(box);
    }

    private static Box[] createBoxes(Scanner s, MatrioskaStack[] stacks) {
        Box[] boxes = new Box[stacks.length];
        for (int i = 0; i < boxes.length; i++) {
            boxes[i] = new Box();
            if(boxes[i].getNumMatrioskas() < Box.CAPACITY)
                boxes[i].add(stacks[s.nextInt()]);
        }
        for (int i = 0; i < boxes.length*5; i++) {   
            addIfValid(s, stacks, boxes);
        }
        return boxes;
    }

    private static void addIfValid(Scanner s, MatrioskaStack[] stacks, Box[] boxes) {
        Box box = boxes[s.nextInt()]; 
        if(box.getNumMatrioskas() < Box.CAPACITY)
            box.add(stacks[s.nextInt()]);
    }

    private static void bigTest() throws FileNotFoundException {
        Scanner s = new Scanner(new File("IO/input.txt"));
        
        MatrioskaStack[] stacks = readingMatrioskaStack(s);
        Box[] boxes = readingBoxes(s, stacks);
        readingCompactedBoxes(s, boxes);

        BoxStack[] boxStacks = readingBoxStacks(s, boxes);

        for (int i = 0; i < boxes.length; i++) {
            BoxStack first = boxStacks[s.nextInt()];
            BoxStack second = boxStacks[s.nextInt()];
            if(BoxStack.canPile(first, second))
                first.pile(second);
        }
        boxes = createBoxes(s, stacks);

        for (int i = 0; i < boxStacks.length; i++) {
            pushIfValid(boxStacks[i], boxes[s.nextInt()]);
            pushIfValid(boxStacks[i], boxes[s.nextInt()]);
        }

        readingBoxStackPiles(boxStacks);
       // readingBoxStackCompact(boxStacks);

    }

    private static void readingBoxStackCompact(BoxStack[] boxStacks) throws FileNotFoundException {
        PrintWriter pw;
        pw = new PrintWriter("IO/output/boxstacks_compacted_output.txt");
        pw.println("Total box_stacks: " + boxStacks.length);
        for (int i = 0; i < boxStacks.length; i++) 
            boxStacks[i].pack();
        
        for (BoxStack boxStack : boxStacks) {
            pw.println(boxStack);
        }
        pw.close();
    }

    private static void readingBoxStackPiles(BoxStack[] boxStacks) throws FileNotFoundException {
        PrintWriter pw;
        pw = new PrintWriter("IO/output/boxstacks_piled_output.txt");
        pw.println("Total box_stacks: " + boxStacks.length);
        for (BoxStack boxStack : boxStacks) {
            pw.println(boxStack);
        }
        pw.close();
    }

    private static BoxStack[] readingBoxStacks(Scanner s, Box[] boxes) throws FileNotFoundException {
        PrintWriter pw;
        pw = new PrintWriter("IO/output/boxstacks_output.txt");
        BoxStack[] boxStacks = createBoxStacks(s, boxes);
        pw.println("Total box_stacks: " + boxStacks.length);

        for (BoxStack boxStack : boxStacks) {
            pw.println(boxStack);
        }
        pw.close();
        return boxStacks;
    }

    private static void readingCompactedBoxes(Scanner s, Box[] boxes) throws FileNotFoundException {
        PrintWriter pw;
        pw = new PrintWriter("IO/output/box_compacted_output.txt");
        Set<Integer> set  = new HashSet<Integer>();
        for (int i = 0; i < boxes.length; i++) {
            int boxNumber = s.nextInt();
            set.add(boxNumber);
            boxes[boxNumber].compact();
        }

        pw.println("Total boxes: " + boxes.length);
        pw.println("compacted boxes:");
        pw.println(set);

        for (Box box : boxes) {
            pw.write(box.toString());   
        }

        pw.close();
    }

    private static Box[] readingBoxes(Scanner s, MatrioskaStack[] stacks) throws FileNotFoundException {
        PrintWriter pw;
        pw = new PrintWriter("IO/output/box_output.txt");
        
        Box[] boxes = createBoxes(s, stacks);
        pw.println("Total boxes: " + boxes.length);
        for (Box box : boxes) {
            pw.write(box.toString());   
        }
        pw.close();
        return boxes;
    }

    private static MatrioskaStack[] readingMatrioskaStack(Scanner s) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("IO/output/matrioskas_output.txt");
        MatrioskaStack[] stacks = createMatrioskaStacks(s, 45);
        for (int i = 0; i < stacks.length-1; i++) {
            stacks[s.nextInt()].merge(stacks[s.nextInt()]);
        }
        pw.println("Total matrioskas: " + stacks.length);
        for (MatrioskaStack matrioskaStack : stacks) {
            pw.println(matrioskaStack.toString());
        }
        pw.close();
        return stacks;
    }

    private static void miniTest() {
        Box[] boxes = new Box[3];
        for (int i = 0; i < boxes.length; i++) 
            boxes[i] = new Box();

        MatrioskaStack fStack = firstStack();
        MatrioskaStack sStack = secondStack();
        MatrioskaStack tStack = thirdStack();

        BoxStack boxStack = smallMerging(fStack, sStack, tStack);
        smallBoxCompact(fStack, tStack, boxStack);
        //reset second stack
        sStack = secondStack();
        
        addStacksToBoxes(boxes, fStack, sStack, tStack);
        smallBoxStackPile(boxes, boxStack);
        
        resetBoxes(boxes, fStack, sStack, tStack);
        boxStack = new BoxStack();
        
       // smallBoxStackCompact(boxes, boxStack);
    }

    private static void resetBoxes(Box[] boxes, MatrioskaStack fStack, MatrioskaStack sStack, MatrioskaStack tStack) {
        for (int i = 0; i < boxes.length; i++) 
            boxes[i] = new Box();
        addStacksToBoxes(boxes, fStack, sStack, tStack);
    }

    private static void addStacksToBoxes(Box[] boxes, MatrioskaStack fStack, MatrioskaStack sStack,
            MatrioskaStack tStack) {
        boxes[0].add(fStack);
        boxes[1].add(sStack);
        boxes[2].add(tStack);
    }

    private static void smallBoxStackPile(Box[] boxes, BoxStack boxStack) {
        System.out.println("=> boxStack pile");
        BoxStack boxStack2 = new BoxStack();

        boxStack.push(boxes[1]);
        boxStack2.push(boxes[2]);
        boxStack2.push(boxes[0]);

        boxStack2.pile(boxStack);

        System.out.println("  .are they pilled? (expected: true) " + (boxStack2.getHeight() == 3));
        boxStack2.pack();

        print(boxStack2);
    }

    private static void smallBoxStackCompact(Box[] boxes, BoxStack boxStack) {
        System.out.println("=> boxStack pack");     

        boxStack.push(boxes[0]);

        boxStack.push(boxes[1]);

        if(Box.heavierThan(boxStack.peek(), boxes[2]))
            boxStack.push(boxes[2]);

        boxStack.pack();

        boxStack.push(boxes[2]);

        boxStack.pack();

        print(boxStack);
    }

    private static void smallBoxCompact(MatrioskaStack fStack, MatrioskaStack tStack, BoxStack boxStack) {
        MatrioskaStack sStack;
        System.out.println("=> box compact");
        Box allBox = new Box();
        //restart the secondStack
        sStack = secondStack();

        allBox.add(fStack);
        allBox.add(sStack);
        allBox.add(tStack);
        allBox.compact();

        boxStack.push(allBox);
        print(boxStack);
    }

    private static BoxStack smallMerging(MatrioskaStack fStack, MatrioskaStack sStack, MatrioskaStack tStack) {
        BoxStack boxStack = new BoxStack();
        System.out.println("=> merging");
        sStack.merge(fStack);
        sStack.merge(tStack);

        Box box = new Box();
        box.add(sStack);
        boxStack.push(box);

        print(boxStack);
        return boxStack;
    }

    private static void print(BoxStack boxStack) {
        System.out.println("  .print box with all elements");
        System.out.println(boxStack);
        boxStack.pop();
        System.out.println("  .empty stack");
        System.out.println(boxStack);
    }

    private static MatrioskaStack thirdStack() {
        MatrioskaStack matrioskaStack3 = new MatrioskaStack();
        matrioskaStack3.push(Matrioska.EL);
        matrioskaStack3.push(Matrioska.RITA);
        matrioskaStack3.push(Matrioska.HELENA);
        return matrioskaStack3;
    }

    private static MatrioskaStack secondStack() {
        MatrioskaStack matrioskaStack2 = new MatrioskaStack();
        matrioskaStack2.push(Matrioska.LIVIA);
        return matrioskaStack2;
    }

    private static MatrioskaStack firstStack() {
        MatrioskaStack matrioskaStack = new MatrioskaStack();
        matrioskaStack.push(Matrioska.D);
        matrioskaStack.push(Matrioska.ANA);
        matrioskaStack.push(Matrioska.MARIANA);
        return matrioskaStack;
    }

}
