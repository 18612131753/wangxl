package gof.ray.Mediator.Sample;

import java.awt.Frame;
import java.awt.Label;
import java.awt.Color;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * javase 仲裁者的实现
 */
public class LoginFrame extends Frame implements ActionListener, Mediator {

    private static final long serialVersionUID = 8853670020692744898L;
    private ColleagueCheckbox checkGuest; // 单选框
    private ColleagueCheckbox checkLogin; // 单选框
    private ColleagueTextField textUser; // 文本框
    private ColleagueTextField textPass; // 文本框
    private ColleagueButton buttonOk; // 按钮
    private ColleagueButton buttonCancel; // 按钮

    // 构造函数。
    // 生成并配置各个Colleague后，显示对话框。
    public LoginFrame(String title) {
        super(title);
        this.setBackground(Color.lightGray); // 浅灰色
        // 使用布局管理器生成4×2窗格，4行2列
        this.setLayout(new GridLayout(4, 2));
        // 初始化各个Colleague（自定义方法）
        this.createColleagues();
        // 配置，顺序装配，一行一行来
        this.add(checkGuest);
        this.add(checkLogin);
        this.add(new Label("Username:"));
        this.add(textUser);
        this.add(new Label("Password:"));
        this.add(textPass);
        this.add(buttonOk);
        this.add(buttonCancel);
        // 设置初始的启用起用/禁用状态
        this.colleagueChanged();
        this.pack(); // 打包、包装
        this.setVisible(true); // 设置可见,底层是this.show()方法
    }

    // 生成各个Colleague。
    public void createColleagues() {
        // 生成
        CheckboxGroup g = new CheckboxGroup(); // checkbox的组，同一组单选只有一个可以选中
        checkGuest = new ColleagueCheckbox("Guest", g, true); // 默认选中
        checkLogin = new ColleagueCheckbox("Login", g, false);
        textUser = new ColleagueTextField("", 10);
        textPass = new ColleagueTextField("", 10);
        textPass.setEchoChar('*'); // 这是密码
        buttonOk = new ColleagueButton("OK");
        buttonCancel = new ColleagueButton("Cancel");
        // 设置仲裁者Mediator
        checkGuest.setMediator(this);
        checkLogin.setMediator(this);
        textUser.setMediator(this);
        textPass.setMediator(this);
        buttonOk.setMediator(this);
        buttonCancel.setMediator(this);
        // 设置Listener,当这些组建监听到变化时，都会调用 mediator.colleagueChanged();
        checkGuest.addItemListener(checkGuest);
        checkLogin.addItemListener(checkLogin);
        textUser.addTextListener(textUser);
        textPass.addTextListener(textPass);
        buttonOk.addActionListener(this);
        buttonCancel.addActionListener(this);
    }

    // 实现mediator结构，接收来自于Colleague的通知然后判断各Colleage的启用/禁用状态。
    @Override
    public void colleagueChanged() {
        // 当选中guset，两个文本框不能使用，确认按钮可以使用
        // 当选中login，username框可以填写，然后走userpassChanged
        // 初始化
        if (checkGuest.getState()) { // guest被选中
            textUser.setColleagueEnabled(false);
            textPass.setColleagueEnabled(false);
            buttonOk.setColleagueEnabled(true);
        } else { // Login mode
            textUser.setColleagueEnabled(true);
            // 当textUser或是textPass文本输入框中的文字发生变化时，判断各Colleage的启用/禁用状态
            if (textUser.getText().length() > 0) {
                textPass.setColleagueEnabled(true);
                if (textPass.getText().length() > 0) {
                    buttonOk.setColleagueEnabled(true);
                } else {
                    buttonOk.setColleagueEnabled(false);
                }
            } else {
                textPass.setColleagueEnabled(false);
                buttonOk.setColleagueEnabled(false);
            }
        }
    }

    // 实现结构ActionListener，监听组建的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        // 点击按钮式，打印
        System.out.println(e.toString());
        System.exit(0);// 结束
    }
}
