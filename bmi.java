package week9;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class bmi extends JFrame {
    private JTextField txtWeight, txtHeight;
    private JLabel lblBmiResult, lblEvaluation;
    private JButton btnCalculate, btnClear;

    public bmi() {
        // ตั้งค่าหน้าต่างโปรแกรม
        setTitle("4. โปรแกรมคำนวณ BMI");
        setSize(380, 320); // ขยายขนาดหน้าต่างขึ้นนิดหน่อยให้พอดีกับฟอนต์ใหม่
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(6, 2, 10, 10)); 

        // *** เพิ่มการตั้งค่าฟอนต์ที่รองรับภาษาไทย (Tahoma) ***
        Font thaiFont = new Font("Tahoma", Font.PLAIN, 14);
        Font boldThaiFont = new Font("Tahoma", Font.BOLD, 14);

        // สร้าง Component ต่างๆ พร้อมตั้งค่าฟอนต์
        JLabel lblWeight = new JLabel(" น้ำหนัก (กก.):");
        lblWeight.setFont(thaiFont);
        txtWeight = new JTextField();
        txtWeight.setFont(thaiFont);
        
        JLabel lblHeight = new JLabel(" ส่วนสูง (ซม.):");
        lblHeight.setFont(thaiFont);
        txtHeight = new JTextField();
        txtHeight.setFont(thaiFont);

        btnCalculate = new JButton("คำนวณ BMI");
        btnCalculate.setFont(boldThaiFont); // ให้ปุ่มคำนวณเป็นตัวหนา
        btnCalculate.setBackground(new Color(30, 144, 255));
        btnCalculate.setForeground(Color.WHITE);

        btnClear = new JButton("ล้างข้อมูล");
        btnClear.setFont(thaiFont);

        JLabel lblBmiTitle = new JLabel(" ค่า BMI:");
        lblBmiTitle.setFont(thaiFont);
        lblBmiResult = new JLabel("-", SwingConstants.CENTER);
        lblBmiResult.setFont(boldThaiFont);
        lblBmiResult.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblEvalTitle = new JLabel(" ผลประเมิน:");
        lblEvalTitle.setFont(thaiFont);
        lblEvaluation = new JLabel("-", SwingConstants.CENTER);
        lblEvaluation.setFont(boldThaiFont);
        lblEvaluation.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // นำ Component ใส่เข้าไปในหน้าต่างตามลำดับ
        add(lblWeight);    add(txtWeight);
        add(lblHeight);    add(txtHeight);
        add(btnCalculate); add(btnClear);
        add(lblBmiTitle);  add(lblBmiResult);
        add(lblEvalTitle); add(lblEvaluation);

        // --- ทำงานเมื่อกดปุ่ม คำนวณ ---
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double weight = Double.parseDouble(txtWeight.getText());
                    double heightCm = Double.parseDouble(txtHeight.getText());
                    
                    double heightM = heightCm / 100.0;
                    double bmiValue = weight / (heightM * heightM);
                    
                    // แสดงผลลัพธ์ BMI ทศนิยม 2 ตำแหน่ง
                    lblBmiResult.setText(String.format("%.2f", bmiValue));
                    
                    // แปลผลและเปลี่ยนสีข้อความตามความเหมาะสม
                    if (bmiValue < 18.5) {
                        lblEvaluation.setText("น้ำหนักน้อย / ผอม");
                        lblEvaluation.setForeground(Color.BLUE);
                    } else if (bmiValue >= 18.5 && bmiValue < 23.0) {
                        lblEvaluation.setText("น้ำหนักปกติ");
                        lblEvaluation.setForeground(new Color(0, 128, 0)); // สีเขียว
                    } else if (bmiValue >= 23.0 && bmiValue < 25.0) {
                        lblEvaluation.setText("น้ำหนักเกิน");
                        lblEvaluation.setForeground(Color.ORANGE);
                    } else {
                        lblEvaluation.setText("อ้วน");
                        lblEvaluation.setForeground(Color.RED);
                    }
                    
                } catch (NumberFormatException ex) {
                    // กำหนดฟอนต์ให้หน้าต่างแจ้งเตือน Error ด้วย
                    UIManager.put("OptionPane.messageFont", thaiFont);
                    UIManager.put("OptionPane.buttonFont", thaiFont);
                    JOptionPane.showMessageDialog(null, "กรุณากรอกตัวเลขให้ถูกต้องครับ", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // --- ทำงานเมื่อกดปุ่ม ล้างข้อมูล ---
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtWeight.setText("");
                txtHeight.setText("");
                lblBmiResult.setText("-");
                lblEvaluation.setText("-");
                lblEvaluation.setForeground(Color.BLACK);
            }
        });
    }

    public static void main(String[] args) {
        // รันโปรแกรม GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new bmi().setVisible(true);
            }
        });
    }
}
