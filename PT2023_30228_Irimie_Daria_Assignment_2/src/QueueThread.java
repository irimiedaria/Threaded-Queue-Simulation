import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueThread extends Thread {

    private BlockingDeque<Client> clients;
    private AtomicInteger waitingTime;

    private int ID;

    public QueueThread(int ID) {
        this.clients = new LinkedBlockingDeque<>();
        this.waitingTime = new AtomicInteger(0);
        this.ID = ID;
    }

    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }
    public int getSizeOfQueue() {
        return clients.size();
    }

    public BlockingDeque<Client> getClients() {
        return clients;
    }
    public void addClient(Client c) {
        clients.add(c);
        waitingTime.addAndGet(c.getTimeService());  // adauga la waitingTime, TimeService-ul obicetului
    }

    //metoda se asigura ca primul client din coada este servit in ordinea sosirii, se procedeaza la fel cu toti
    public void run() {
        try
        {
            while(true) {
                if(this.clients.size()==0){       //  se verifica daca coada de clienti este goala
                   continue;
                }
                Thread.sleep(1000);
                Client c = this.clients.element();  // se preia primul client din coada

                c.decrementTimeService();   // se decrementeaza TimeService-ul clientului
                this.waitingTime.decrementAndGet();
                if(c.getTimeService() == 0)  // daca timpul de servire al clientului a ajuns la 0
                {
                    this.clients.take();    // clientul este eliminat din coada
                }
            }
        }
        catch( InterruptedException e ){
            System.out.println( e.toString());
        }
    }

    @Override
    public String toString() {
        String str = new String();
        for(Client client : clients)
            str = str + " " + client.toString();
        return str;
    }
}
