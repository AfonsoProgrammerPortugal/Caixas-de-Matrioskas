package project;
public enum Matrioska {
    D(1, "D"),
    EL(2, "El"),
    ANA(3, "Ana"),
    RITA(4, "Rita"),
    LIVIA(5, "Livia"),
    HELENA(6, "Helena"),
    MARIANA(7, "Mariana");

    private final int size;
    private final String name;

    private Matrioska(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public double getSize() {
        return this.size;
    }
    
    public String getName() {
        return name;
    }
    
    public static boolean smallerThan(Matrioska first, Matrioska second) {
        return first.getSize() < second.getSize();
    }
}
