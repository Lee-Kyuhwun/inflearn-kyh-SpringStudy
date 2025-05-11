package hello.proxy.concretetproxy.code;



public class ConcreteClient {


    private ConCreteLogic conCreteLogic;

    public ConcreteClient(ConCreteLogic conCreteLogic) {
        this.conCreteLogic = conCreteLogic;
    }

    public void execute() {
        String result1 = conCreteLogic.operation();
        System.out.println("result1 = " + result1);

        String result2 = conCreteLogic.operation();
        System.out.println("result2 = " + result2);
    }
}
