import java.util.Scanner;

public class Main {
    private static ProcessManager pm = new ProcessManager();
    private static MemoryManager mm = new MemoryManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MiniOS!");

        while (true) {
            System.out.print("MiniOS> ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "create":
                        if (parts.length < 2) {
                            System.out.println("Usage: create [name]");
                            break;
                        }
                        pm.createProcess(parts[1]);
                        break;

                    case "ps":
                        pm.listProcesses();
                        break;

                    case "schedule":
                        pm.schedule();
                        break;

                    case "alloc":
                        if (parts.length < 3) {
                            System.out.println("Usage: alloc [pid] [size]");
                            break;
                        }
                        int pid = Integer.parseInt(parts[1]);
                        int size = Integer.parseInt(parts[2]);
                        mm.allocate(pid, size);
                        break;

                    case "free":
                        if (parts.length < 2) {
                            System.out.println("Usage: free [pid]");
                            break;
                        }
                        int pidToFree = Integer.parseInt(parts[1]);
                        mm.free(pidToFree);
                        break;

                    case "mem":
                        mm.printMemory();
                        break;

                    case "exit":
                    case "q":
                        System.out.println("Exiting MiniOS...");
                        scanner.close();
                        System.exit(0);

                    case "help":
                    case "h":
                        System.out.println("MiniOS Help\n");
                        System.out.println("Commands:");
                        System.out.println("create [process name]\tCreates a new process");
                        System.out.println("ps\t\t\tLists all processes and the current state");
                        System.out.println("schedule\t\tSchedules all of the processes to be executed");
                        System.out.println("alloc [pid] [size]\tAllocates [size] bytes to the provided PID");
                        System.out.println("free [pid]\t\tFrees the allocated memory to the provided PID");
                        System.out.println("mem\t\t\tPrints out the memory");
                        System.out.println("help\t\t\tPrints this message");
                        System.out.println("exit\t\t\tExits the program");
                        System.out.println("");

                        break;


                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (Exception e) {
                System.out.println("Invalid command or parameters.");
            }
        }
    }
}
