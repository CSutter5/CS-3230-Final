import java.util.ArrayList;
import java.util.List;

public class ProcessManager {
    private List<PCB> processes = new ArrayList<>();

    // Create a new process and add it to the list
    public void createProcess(String name) {
        PCB newProcess = new PCB(name);
        processes.add(newProcess);
        System.out.println("Created: PID " + newProcess.getPid() + " [" + newProcess.getName() + "] - READY");
    }

    // Simulate Round Robin scheduling
    public void schedule() {
        boolean found = false;
        for (PCB process : processes) {
            if (process.getState() == PCB.State.READY) {
                found = true;
                process.setState(PCB.State.RUNNING);
                System.out.println("Running: PID " + process.getPid() + " [" + process.getName() + "] - RUNNING");
                try {
                    Thread.sleep(1000); // 1 second for demonstration; change to 5000 for 5 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                process.setState(PCB.State.READY);
            }
        }
        if (!found) {
            System.out.println("No READY processes to schedule.");
        }
    }

    // Print all processes and their states
    public void listProcesses() {
        if (processes.isEmpty()) {
            System.out.println("No processes found.");
            return;
        }
        System.out.println("PID\tName\t\tState");
        for (PCB p : processes) {
            System.out.printf("%d\t%-10s\t%s\n", p.getPid(), p.getName(), p.getState());
        }
    }

    // Get a process by PID
    public PCB getProcessByPid(int pid) {
        for (PCB p : processes) {
            if (p.getPid() == pid) return p;
        }
        return null;
    }

    // Expose process list for GUI or other uses
    public List<PCB> getProcesses() {
        return processes;
    }
}
