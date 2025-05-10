public class MemoryManager {
    private int[] memory = new int[40]; // 40 units for easier display

    // First-Fit allocation
    public boolean allocate(int pid, int size) {
        int start = -1, count = 0;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == 0) {
                if (count == 0) start = i;
                count++;
                if (count == size) {
                    for (int j = start; j < start + size; j++) {
                        memory[j] = pid;
                    }
                    System.out.println("Allocated " + size + " units to PID " + pid + " at position " + start + ".");
                    return true;
                }
            } else {
                count = 0;
            }
        }
        System.out.println("Allocation failed: Not enough memory.");
        return false;
    }

    // Free all memory blocks belonging to a PID
    public void free(int pid) {
        boolean found = false;
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == pid) {
                memory[i] = 0;
                found = true;
            }
        }
        if (found) {
            System.out.println("Freed memory for PID " + pid + ".");
        } else {
            System.out.println("No memory found for PID " + pid + ".");
        }
    }

    // Print memory layout in a readable grid
    public void printMemory() {
        System.out.println("Memory Layout:");
        for (int i = 0; i < memory.length; i++) {
            if (i % 10 == 0 && i != 0) System.out.println();
            System.out.print(memory[i] == 0 ? ". " : memory[i] + " ");
        }
        System.out.println();
    }

    // Getter for GUI or other uses
    public int[] getMemory() {
        return memory;
    }
}
