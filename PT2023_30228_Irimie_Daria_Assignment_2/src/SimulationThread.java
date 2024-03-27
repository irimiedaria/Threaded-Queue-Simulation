import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SimulationThread extends Thread {

    private Settings s;
    private ArrayList<QueueThread> queues;
    private ArrayList<Client> clients;

    private int t;

    private JTextArea textArea;

    public SimulationThread(Settings s, ArrayList<Client> clients, JTextArea textArea) {
        this.s = s;
        this.clients = clients;
        this.queues = new ArrayList<QueueThread>();
        this.textArea = textArea;
    }

    //verifica daca clientul este deja intr-una dintre cozi
    private boolean isInQueue(Client client) {
        if(client.getTimeService() == 0){  // daca timpul de servire al clientului e 0 => a fost servit deja
            return true;
        }
        for (QueueThread queue : queues) {
            for (Client c : queue.getClients()) {  // se parcurg toti clienti din coada respectiva
                if (c.equals(client)) {  // daca clientul din coada este egal cu cel dat ca si parametru => se afla in coada
                    return true;
                }
            }
        }
        return false;  // daca clientul nu a fost gasit in nicio coada
    }

    public boolean isEmpty() {
        return queues.size() == 0;
    }

    public String getStatus() {
        String stringPrint = new String();
        stringPrint += "Waiting : ";
        boolean hasWaitingClients = false;
        for(Client client : clients) {
            if (!isInQueue(client)) {   // daca un client nu este intr-o coada
                stringPrint += client.toString() + " ";  // se adauga informatiile despre ei (Waiting)
                hasWaitingClients = true;
            }
        }
        if (!hasWaitingClients) {    // daca nu exista clienti in afara cozilor se afiseaza "none"
            stringPrint += "none";
        }
        stringPrint += "\n";

        int cnt = 1;  // pentru a contoriza nr cozilor
        for(QueueThread queue : queues) {
            stringPrint += "Queue " + cnt + ": ";
            if(isEmpty()) {
                stringPrint += "closed\n";
            } else {
                boolean hasClients = false;
                for(Client client : queue.getClients()) {
                    stringPrint += client.toString() + " ";   // se adauga informatiile despre clientii care se afla in coada
                    hasClients = true;
                }
                if (!hasClients) {
                    stringPrint += "empty";    // daca coada este goala
                }
                stringPrint += "\n";
            }
            cnt++;
        }

        return  stringPrint;
    }

    public void run() {
        try
        {
            File outputFile = new File("output.txt");
            if (outputFile.createNewFile()) {   // se incearca crearea unui fiser
                System.out.println("Fisierul a fost creat cu succes!");
            } else {
                System.out.println("Fisierul exista deja.");
            }

            PrintWriter writer = new PrintWriter(outputFile);


            // se creeaza toate cozile
            for(int i = 0; i < s.getNrOfQueues(); i++) {
                QueueThread q = new QueueThread(i);
                queues.add(q);
                q.start();  // se porneste fiecare coada intr-un fir de executie diferit
            }

            String finalString = new String();

            while(t <= this.s.getSimulationTime()) {
                for(Client client : clients) {  // se parcurge fiecare client
                    if(client.getTimeArrival() == t) {   // se adauga in coada daca timpul de sosire este egat cu timpul curent de simulare
                       // se cauta coada cu timpul minim
                        int  minim = queues.get(0).getWaitingTime().intValue(); // se pune minimul ca fiind initial waitingTime-ul primului element din coada
                        for(QueueThread queue : queues) {
                            int currentTime = queue.getWaitingTime().intValue();
                            if(currentTime < minim) {
                                minim = currentTime;
                            }
                        }
                        for(QueueThread queue : queues) {
                            if(queue.getWaitingTime().intValue() == minim) {
                                queue.addClient(client);    // se adauga clientul in coada cu minim waiting time
                                break;
                            }
                        }
                    }

                }
                System.out.println("Time : " + t);
                t++;

                String currentStr = getStatus();  // se afiseaza starea curenta

                finalString += "Time : " + (t - 1) + "\n"  + currentStr;
                writer.println(currentStr);
                System.out.println(currentStr);
                textArea.setText(finalString);
                Thread.sleep(1000);
            }
            writer.close();
            interrupt();
        }
        catch(InterruptedException | IOException e ){
            System.out.println( e.toString());
        }
    }
}
