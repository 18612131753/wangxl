package gof.ray.Mediator.Sample;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ColleagueCheckbox extends Checkbox implements ItemListener, Colleague {

    private static final long serialVersionUID = 1L;

    private Mediator mediator;

    public ColleagueCheckbox(String caption, CheckboxGroup group, boolean state) { // 构造函数
        super(caption, group, state);
    }

    @Override
    public void setMediator(Mediator mediator) { // 保存Mediator
        this.mediator = mediator;
    }

    @Override
    public void setColleagueEnabled(boolean enabled) { // Mediator下达启用/禁用指示
        this.setEnabled(enabled);
    }

    // 当状态发生变化时通知Mediator
    // ItemListener
    @Override
    public void itemStateChanged(ItemEvent e) {
        mediator.colleagueChanged();
    }
}
