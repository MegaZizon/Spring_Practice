public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        action((a,b)->{
            int v=a+b;
        });

    }

    public static void action(Calculable c) {
        int x= 10;
        int y= 4;
        c.calculate(x, y);
        c.calculate2(x, y);
    }
}

