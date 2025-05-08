package hello.proxy.code;



public class ProxyPatternClient {

    private Subject subject;

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    public void execute() {
        String result1 = subject.operation("data1");
        System.out.println("result1 = " + result1);

        String result2 = subject.operation();
        System.out.println("result2 = " + result2);
    }
}
