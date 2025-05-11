package hello.proxy.concretetproxy.code;

public class TimeProxy extends ConCreteLogic {

    private ConCreteLogic concreteLogic;

    public TimeProxy(ConCreteLogic concreteLogic) {
        this.concreteLogic = concreteLogic;
    }

    public String operation() {
        System.out.println("TimeProxy execution start");
        long startTime = System.currentTimeMillis();
        String result = concreteLogic.operation();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        System.out.println("resultTime = " + resultTime);
        return result;
    }
}
