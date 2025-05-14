import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MiniOSGUI extends JFrame {
    private ProcessManager pm = new ProcessManager();
    private MemoryManager mm = new MemoryManager();

    private DefaultListModel<String> processListModel = new DefaultListModel<>();
    private JTextArea memoryArea = new JTextArea(10, 40);

    public MiniOSGUI() {
        setTitle("MiniOS Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Process Panel
        JPanel processPanel = new JPanel();
        processPanel.setLayout(new FlowLayout());
        JTextField processNameField = new JTextField(10);
        processNameField.addActionListener(e -> {
            String name = processNameField.getText().trim();
            if (!name.isEmpty()) {
                pm.createProcess(name);
                refreshProcessList();
                processNameField.setText("");
            }
        });
        JButton createButton = new JButton("Create Process");
        createButton.addActionListener(e -> {
            String name = processNameField.getText().trim();
            if (!name.isEmpty()) {
                pm.createProcess(name);
                refreshProcessList();
            }
        });
        JButton scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(e -> {
            pm.schedule();
            refreshProcessList();
        });
        processPanel.add(new JLabel("Process Name:"));
        processPanel.add(processNameField);
        processPanel.add(createButton);
        processPanel.add(scheduleButton);

        // Process List
        JList<String> processList = new JList<>(processListModel);
        JScrollPane processScroll = new JScrollPane(processList);

        // Memory Panel
        JPanel memoryPanel = new JPanel();
        memoryPanel.setLayout(new FlowLayout());
        JTextField pidField = new JTextField(3);
        JTextField sizeField = new JTextField(3);
        JButton allocButton = new JButton("Allocate Memory");
        ActionListener allocAction = e -> {
            try {
                int pid = Integer.parseInt(pidField.getText());
                int size = Integer.parseInt(sizeField.getText());
                if (mm.allocate(pid, size)) {
                    JOptionPane.showMessageDialog(this, "Memory allocated.");
                } else {
                    JOptionPane.showMessageDialog(this, "Allocation failed.");
                }
                refreshMemory();
                pidField.setText("");
                sizeField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid PID or size.");
                pidField.setText("");
                sizeField.setText("");
            }
        };

        pidField.addActionListener(allocAction);
        sizeField.addActionListener(allocAction);
        allocButton.addActionListener(allocAction);

        memoryPanel.add(new JLabel("PID:"));
        memoryPanel.add(pidField);
        memoryPanel.add(new JLabel("Size:"));
        memoryPanel.add(sizeField);
        memoryPanel.add(allocButton);

        // Memory Area
        memoryArea.setEditable(false);
        JScrollPane memoryScroll = new JScrollPane(memoryArea);

        // Layout
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(processPanel, BorderLayout.NORTH);
        leftPanel.add(processScroll, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(memoryPanel, BorderLayout.NORTH);
        rightPanel.add(memoryScroll, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        refreshProcessList();
        refreshMemory();
    }

    private void refreshProcessList() {
        processListModel.clear();
        for (PCB p : pm.getProcesses()) {
            processListModel.addElement("PID: " + p.getPid() + " | " + p.getName() + " | " + p.getState());
        }
    }

    private void refreshMemory() {
        StringBuilder sb = new StringBuilder();
        int[] mem = mm.getMemory();
        for (int i = 0; i < mem.length; i++) {
            sb.append(String.format("%3d", mem[i]));
            if ((i + 1) % 10 == 0) sb.append("\n");
        }
        memoryArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MiniOSGUI::new);
    }
}
