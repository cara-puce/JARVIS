package plugins.simpleBase.simpleModifier;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import plugins.simpleBase.Event;
import plugins.simpleBase.IEvent;

/**
 * Defines an {@link ActionListener} that manages the modification of event in simpleModifier plugin.
 */
public class ModifyListener implements ActionListener {

	ModifierEventFrame frame;
	IEvent event;
	
	public ModifyListener(ModifierEventFrame frame, IEvent event) {
		super();
		this.frame = frame;
		this.event = event;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<String> fieldsContent = (ArrayList<String>) frame.getFieldsContent();
		Field[] fields = Event.class.getDeclaredFields();
		Class<?> cl;
		Method method;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String tmp = null;
		try {
			for (int i = 0 ; i < fieldsContent.size() ; ++i) {
				String content = fieldsContent.get(i);
				String field = fields[i].getName();
				cl = fields[i].getType();
				method = event.getClass().getMethod("set"+upFirstChar(field), cl);
				if(cl.equals(Date.class)){
					if (field.equals("startDate")){ //Test to avoid simultaneous startDate and endDate modification which was leading to an Exception.
						tmp = content;
					} else {
						method.invoke(event, formatter.parse(content));
					}
				}else{
					method.invoke(event, content);
				}
			}
			if (tmp != null){
				method = event.getClass().getMethod("setStartDate", Date.class);
				method.invoke(event, formatter.parse(tmp));
			}
			frame.dispose();
		} catch (InvocationTargetException e1){
				try {
					throw e1.getCause();
				} catch (IllegalArgumentException e11){
					System.out.println("Wrong Date value");
				} catch (Throwable e2) {
					e2.printStackTrace();
				}
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | ParseException e1) {
				e1.printStackTrace();
		}
	}
	
	private String upFirstChar(String toUp){
		return toUp.substring(0, 1).toUpperCase() + toUp.substring(1);
	}

}
