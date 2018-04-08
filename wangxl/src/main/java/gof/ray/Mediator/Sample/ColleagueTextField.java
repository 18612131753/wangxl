package gof.ray.Mediator.Sample;

import java.awt.TextField;
import java.awt.Color;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;

public class ColleagueTextField extends TextField implements TextListener, Colleague {

    private static final long serialVersionUID = 6666768581955274681L;

    private Mediator mediator;

    public ColleagueTextField(String text, int columns) { // 构造函数
        super(text, columns);
    }

    public void setMediator(Mediator mediator) { // 保存Mediator
        this.mediator = mediator;
    }

    public void setColleagueEnabled(boolean enabled) { // Mediator下达启用/禁用的指示
        this.setEnabled(enabled);
        this.setBackground(enabled ? Color.white : Color.lightGray);
    }

    @Override
    public void textValueChanged(TextEvent e) { // 当文字发生变化时通知Mediator
        mediator.colleagueChanged();
    }
}
