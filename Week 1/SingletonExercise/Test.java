public class Test {
    public static void main(String args[]){
        Logger log1 = Logger.get();
        Logger log2 = Logger.get();
        Logger log3 = Logger.get();
        if((log1 == log2) &&(log2== log3)) System.out.println("Both log1 , log2 and log3 are the same instance of Logger class");
        else System.out.println("Nope! They ain't the same instance");
    }
}
