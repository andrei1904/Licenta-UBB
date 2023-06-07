public class Supervisor extends Thread {

    private Data data;

    public Supervisor(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println(data.getAlocatedValue());
    }
}
