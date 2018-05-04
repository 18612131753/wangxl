package gof.ray.Mediator.A1;

import java.awt.Button;

public class ColleagueButton extends Button implements Colleague {

    private static final long serialVersionUID = 1L;
    @SuppressWarnings("unused")
    private Mediator mediator;

    public ColleagueButton(String caption) {
        super(caption);
    }

    public void setMediator(Mediator mediator) { // 保存Mediator
        this.mediator = mediator;
    }

    public void setColleagueEnabled(boolean enabled) { // Mediator下达启用/禁用的指示
        setEnabled(enabled);
    }
}
