package plugins.simpleBase.modifierPrinter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import platform.IPlugin;
import platform.IPluginDescriptor;
import platform.Platform;
import platform.UnkillableException;
import plugins.simpleBase.IAgenda;
import plugins.simpleBase.IEvent;
import plugins.simpleBase.IModifier;

/**
 * Defines an {@link ActionListener} that manage the opening of modifier in modifierPrinter plugin.
 */
public class ModifierListener implements ActionListener{

	IModifier modifier;
	List<IPluginDescriptor> list;
	int index;
	IAgenda agenda;
	IEvent event;

	public ModifierListener(int index, List<IPluginDescriptor> list, IAgenda agenda, IEvent event) {
		super();
		this.index = index;
		this.list = list;
		this.agenda = agenda;
		this.event = event;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.modifier = Platform.loadPlugin(list.get(index), IModifier.class);
		if (modifier != null){
			this.modifier.modify(agenda, event);
		}
		try {
			Platform.killPlugin((IPlugin) this.modifier);
		} catch (UnkillableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
