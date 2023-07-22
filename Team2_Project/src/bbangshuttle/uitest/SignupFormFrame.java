package bbangshuttle.uitest;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import bbangshuttle.member.Member;
import bbangshuttle.member.MemberService;

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

    private MemberService memberService;

    public SignupFormFrame(MemberService memberService) {
        this.memberService = memberService;

        setTitle("Sign Up Form Frame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 아이디 입력 필드
        JLabel usernameLabel = new JLabel("아이디:");
        usernameField = new JTextField(10);

        // 비밀번호 입력 필드
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField(10);

        // 비밀번호 확인 입력 필드
        JLabel confirmPasswordLabel = new JLabel("비밀번호 확인:");
        confirmPasswordField = new JPasswordField(10);

        // 이름 입력 필드
        JLabel nameLabel = new JLabel("이름:");
        nameField = new JTextField(10);

        // 이메일 입력 필드
        JLabel emailLabel = new JLabel("이메일:");
        emailField = new JTextField(10);

        // 주소 입력 필드
        JLabel addressLabel = new JLabel("주소:");
        addressField = new JTextField(10);

        // 연락처 입력 필드
        JLabel contactLabel = new JLabel("연락처:");
        contactField = new JTextField(10);

        // 생년월일 입력 필드
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

        // 회원가입 버튼
        signUpButton = new JButton("회원가입");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 사용자가 회원가입 버튼을 클릭하면 입력된 정보를 가져옵니다.
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                char[] confirmPassword = confirmPasswordField.getPassword();
                String name = nameField.getText();
                String email = emailField.getText();
                String address = addressField.getText();
                String contact = contactField.getText();
                String year = yearCombo.getSelectedItem().toString();
                String month = monthCombo.getSelectedItem().toString();
                String day = dayCombo.getSelectedItem().toString();

                // 비밀번호와 비밀번호 확인이 일치하는지 확인합니다.
                if (!passwordMatch(password, confirmPassword)) {
                    JOptionPane.showMessageDialog(SignupFormFrame.this, "비밀번호와 비밀번호 확인이 일치하지 않습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 생년월일을 yyyy/mm/dd 형식으로 만듭니다.
                String birthday = year + "/" + month + "/" + day;

                // 회원가입 정보를 생성합니다.
                Member member = new Member(username, new String(password), name, email, address, contact, birthday,null,0);

                // MemberService를 통해 회원가입 기능을 수행합니다.
                try {
                    memberService.addMember(member);
                    JOptionPane.showMessageDialog(SignupFormFrame.this, "회원가입이 완료되었습니다.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // 현재 프레임을 닫습니다.
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(SignupFormFrame.this, "회원가입 과정에서 오류가 발생했습니다.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 레이아웃 설정
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(birthdayLabel);
        panel.add(yearCombo);
        panel.add(monthCombo);
        panel.add(dayCombo);
        panel.add(new JLabel());
        panel.add(signUpButton);

        add(panel, BorderLayout.CENTER);
    }

    private boolean passwordMatch(char[] password1, char[] password2) {
        return new String(password1).equals(new String(password2));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MemberService memberService = new MemberService();
                    SignupFormFrame signupFrame = new SignupFormFrame(memberService);
                    signupFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
