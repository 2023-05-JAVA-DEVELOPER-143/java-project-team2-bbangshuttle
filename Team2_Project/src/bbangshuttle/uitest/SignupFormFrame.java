package bbangshuttle.uitest;

import javax.swing.*;
import java.awt.*;

public class SignupFormFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField addressField;
    private JTextField contactField;
    private JComboBox<String> yearCombo;
    private JComboBox<String> monthCombo;
    private JComboBox<String> dayCombo;
    private JButton signUpButton;

    public SignupFormFrame() {
        setTitle("Sign Up Form Frame");
        setSize(1024, 860);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 컴포넌트 생성
        JLabel usernameLabel = new JLabel("아이디:");
        usernameField = new JTextField(10);

        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField(10);

        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordField = new JPasswordField(10);

        JLabel nameLabel = new JLabel("이름:");
        nameField = new JTextField(10);

        JLabel emailLabel = new JLabel("이메일:");
        emailField = new JTextField(10);

        JLabel addressLabel = new JLabel("주소:");
        addressField = new JTextField(10);

        JLabel contactLabel = new JLabel("연락처:");
        contactField = new JTextField(10);

        JLabel birthdayLabel = new JLabel("생년월일:");
        String[] years = new String[100];
        String[] months = new String[12];
        String[] days = new String[31];
        for (int i = 0; i < 100; i++) {
            years[i] = Integer.toString(2023 - i);
        }
        for (int i = 0; i < 12; i++) {
            months[i] = Integer.toString(i + 1);
        }
        for (int i = 0; i < 31; i++) {
            days[i] = Integer.toString(i + 1);
        }
        yearCombo = new JComboBox<>(years);
        monthCombo = new JComboBox<>(months);
        dayCombo = new JComboBox<>(days);

        signUpButton = new JButton("회원가입");

        // 앱솔루트 레이아웃으로 설정
        getContentPane().setLayout(null);

        // 컴포넌트 위치 설정
        usernameLabel.setBounds(20, 20, 100, 30);
        usernameField.setBounds(120, 20, 200, 30);

        passwordLabel.setBounds(20, 60, 100, 30);
        passwordField.setBounds(120, 60, 200, 30);

        confirmPasswordLabel.setBounds(20, 100, 100, 30);
        confirmPasswordField.setBounds(120, 100, 200, 30);

        nameLabel.setBounds(20, 140, 100, 30);
        nameField.setBounds(120, 140, 200, 30);

        emailLabel.setBounds(20, 180, 100, 30);
        emailField.setBounds(120, 180, 200, 30);

        addressLabel.setBounds(20, 220, 100, 30);
        addressField.setBounds(120, 220, 200, 30);

        contactLabel.setBounds(20, 260, 100, 30);
        contactField.setBounds(120, 260, 200, 30);

        birthdayLabel.setBounds(20, 300, 100, 30);
        yearCombo.setBounds(120, 300, 97, 30);
        monthCombo.setBounds(244, 302, 60, 30);
        dayCombo.setBounds(316, 301, 60, 30);

        signUpButton.setBounds(120, 340, 200, 30);

        // 컴포넌트를 프레임에 추가
        getContentPane().add(usernameLabel);
        getContentPane().add(usernameField);
        getContentPane().add(passwordLabel);
        getContentPane().add(passwordField);
        getContentPane().add(confirmPasswordLabel);
        getContentPane().add(confirmPasswordField);
        getContentPane().add(nameLabel);
        getContentPane().add(nameField);
        getContentPane().add(emailLabel);
        getContentPane().add(emailField);
        getContentPane().add(addressLabel);
        getContentPane().add(addressField);
        getContentPane().add(contactLabel);
        getContentPane().add(contactField);
        getContentPane().add(birthdayLabel);
        getContentPane().add(yearCombo);
        getContentPane().add(monthCombo);
        getContentPane().add(dayCombo);
        getContentPane().add(signUpButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SignupFormFrame signupFrame = new SignupFormFrame();
                signupFrame.setVisible(true);
            }
        });
    }
}
