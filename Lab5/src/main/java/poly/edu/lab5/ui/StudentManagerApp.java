package poly.edu.lab5.ui;

import poly.edu.lab5.api.RestClient;
import poly.edu.lab5.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StudentManagerApp extends JFrame {
    private DefaultTableModel tableModel;
    private JTable studentTable;

    private Map<String, Student> currentStudentMap = new HashMap<>();

    JTextField txtId = new JTextField(10);
    JTextField txtFullName = new JTextField(10);
    JTextField txtMark = new JTextField(10);
    JRadioButton radioMale = new JRadioButton("Male");
    JRadioButton radioFemale = new JRadioButton("Female");

    public StudentManagerApp() {
        super("Quản lý Sinh viên - REST API");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());


        String[] columnNames = {"Id", "Full Name", "Gender", "Mark"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(tableModel);

        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JPanel formPanel = createFormAndButtonPanel();
        add(formPanel, BorderLayout.NORTH);

        addTableListeners();

        loadStudentsToTable();

        setVisible(true);
    }

    private JPanel createFormAndButtonPanel() {
        JButton btnCreate = new JButton("Create");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnReset = new JButton("Reset");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(radioMale);
        genderGroup.add(radioFemale);
        radioMale.setSelected(true);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(4, 2, 10, 5));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        fieldsPanel.add(new JLabel("ID:"));
        fieldsPanel.add(new JLabel("FullName:"));

        fieldsPanel.add(txtId);
        fieldsPanel.add(txtFullName);

        fieldsPanel.add(new JLabel("Gender:"));
        fieldsPanel.add(new JLabel("Mark:"));

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        genderPanel.add(radioMale);
        genderPanel.add(radioFemale);
        fieldsPanel.add(genderPanel);

        fieldsPanel.add(txtMark);

        btnCreate.addActionListener(e -> handleCreate());
        btnUpdate.addActionListener(e -> handleUpdate());
        btnDelete.addActionListener(e -> handleDelete());
        btnReset.addActionListener(e -> resetForm());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnReset);

        JPanel mainControlPanel = new JPanel(new BorderLayout());

        mainControlPanel.add(fieldsPanel, BorderLayout.NORTH);
        mainControlPanel.add(buttonPanel, BorderLayout.CENTER);

        return mainControlPanel;
    }

    private void addTableListeners() {
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    loadStudentToForm();
                }
            }
        });
    }

    private void loadStudentsToTable() {
        tableModel.setRowCount(0);
        currentStudentMap.clear();

        try {
            Map<String, Student> studentsFromApi = RestClient.getAll();
            if (studentsFromApi != null && !studentsFromApi.isEmpty()) {
                currentStudentMap.putAll(studentsFromApi);
                for (Map.Entry<String, Student> entry : studentsFromApi.entrySet()) {
                    Student student = entry.getValue();
                    Object[] rowData = {
                            student.getId(),
                            student.getName(),
                            student.isGender() ? "Male" : "Female",
                            student.getMark()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String currentKey = null;

    private void loadStudentToForm() {
        int selectedRow = studentTable.getSelectedRow();
        String studentId = (String) studentTable.getValueAt(selectedRow, 0);
        currentKey = currentStudentMap.entrySet().stream()
                .filter(entry -> entry.getValue().getId().equals(studentId))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        if (currentKey == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy key API tương ứng cho ID này.", "Lỗi Dữ liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Student student = RestClient.getByKey(currentKey);

            txtId.setText(student.getId());
            txtFullName.setText(student.getName());
            txtMark.setText(String.valueOf(student.getMark()));

            if (student.isGender()) {
                radioMale.setSelected(true);
            } else {
                radioFemale.setSelected(true);
            }

            txtId.setEnabled(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCreate() {
        Student student = new Student();
        student.setId(txtId.getText());
        student.setName(txtFullName.getText());
        student.setGender(radioMale.isSelected());
        student.setMark(Double.parseDouble(txtMark.getText()));
        RestClient.post(student);
        loadStudentsToTable();
    }

    private void handleUpdate() {
        Student student = new Student();
        student.setId(txtId.getText());
        student.setName(txtFullName.getText());
        student.setGender(radioMale.isSelected());
        student.setMark(Double.parseDouble(txtMark.getText()));
        RestClient.put(student, currentKey);
        loadStudentsToTable();
    }

    private void handleDelete() {
        RestClient.delete(currentKey);
        loadStudentsToTable();
        resetForm();
    }

    private void resetForm() {
        txtId.setText("");
        txtId.setEnabled(true);
        txtFullName.setText("");
        txtMark.setText("");
        radioMale.setSelected(true);
    }

    // Hàm main để chạy ứng dụng
    public static void main(String[] args) {
        // Đảm bảo Swing chạy trên EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(StudentManagerApp::new);
    }
}
