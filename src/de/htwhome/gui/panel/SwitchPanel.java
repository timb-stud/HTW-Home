package de.htwhome.gui.panel;

import de.htwhome.devices.ConfigList;
import de.htwhome.devices.Panel;
import de.htwhome.utils.Config;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Tim Bartsch
 */
public class SwitchPanel extends JPanel{
    JLabel label;
    JToggleButton btn;
    int id;
    Panel panel;

    public SwitchPanel(String location, String description, boolean status,  int id, Panel panel) {
	this.panel = panel;
	label = new JLabel(location + ", " + description);
	btn = new JToggleButton();
	btn.addActionListener(new SwitchActionListener(this));
	setBtnStatus(status);
	this.id = id;
	setLayout(new FlowLayout());
	add(label);
	add(btn);
    }

    public final void setBtnStatus(boolean status){
	if(status){
	    btn.setText("AN");
	}else{
	    btn.setText("AUS");
	}
	btn.setSelected(status);
    }

    public void btnClick(){
	boolean status = btn.isSelected();
	if(status){
	    btn.setText("AN");
	}else{
	    btn.setText("AUS");
	}
	ConfigList cfgList = panel.getConfigList();
	for(Config c: cfgList){
	    if(c.getId() == this.id){
		panel.changeStatus(c.getGid(), status);
	    }
	}
    }

    public void setText(String location, String description){
	label.setText(location + ", " + description);
    }

    public int getId() {
	return id;
    }

}
