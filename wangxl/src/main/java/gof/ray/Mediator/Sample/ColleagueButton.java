package gof.ray.Mediator.Sample;

import java.awt.Button;

public class ColleagueButton extends Button implements Colleague {

    private static final long serialVersionUID = -3649988792013233920L;
    
    private Mediator mediator;
    
    
    public ColleagueButton(String caption) {
        super(caption);
    }
    public void setMediator(Mediator mediator) {            // 保存Mediator
        this.mediator = mediator;
    }
    public Mediator getMediator() {
        return mediator;
    }
   
    public void setColleagueEnabled(boolean enabled) {      // Mediator下达启用/禁用的指示 
        this.setEnabled(enabled); //Button的方法
    }
}
