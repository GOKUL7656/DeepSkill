public class Logger {
    private static Logger instance;
    private Logger(){
        System.out.println("Instance created using Logger");
    }
    public static Logger get(){
        if(instance == null) instance = new Logger();
        return instance;
    }
}
