public class transistions{
    String state;
    String symbol0;
    String symbol1;
    boolean isAccept;

    public transistions(String state, String symbol0, String symbol1, boolean isAccept) {
         this.state = state;
         this.symbol0 = symbol0;
         this.symbol1 = symbol1;
         this.isAccept = isAccept;
    }

    public String getState(){
        return this.state;
    }

    public String getSymbol0(){
        return this.symbol0;
    }

    public String getSymbol1() {
        return this.symbol1;
    }

    public void printString(transistions[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].state + ": " + arr[i].isAccept + "\n\t" + "0: " + arr[i].symbol0 + "\n\t" + "1: " + arr[i].symbol1);
        }
    }

    public static void main(String[] args) {
        transistions test = new transistions("S", "A", "B", false);
        System.out.println(test.state);
        System.out.println(test.symbol0);
        System.out.println(test.symbol1);
        transistions[] arr = new transistions[3];
        transistions holder = new transistions("S", "A", "B", false);
        arr[0] = holder;
        holder = new transistions("A", "A", "A", true);
        arr[1] = holder;
        holder = new transistions("B", "B", "B", false);
        arr[2] = holder;

        test.printString(arr);
    }

}
