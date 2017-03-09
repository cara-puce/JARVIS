package plugins.monitoring;

import javax.swing.*;

import client.IPluginDescriptor;
import client.PluginDescriptor;
import platform.Platform;
import platform.plugins.IAutorun;
import platform.plugins.IMonitoring;
import platform.plugins.IPlugin;

public class Monitoring implements IPlugin, IMonitoring, IAutorun {

	@Override
	public void run() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		System.out.println(Platform.getPluginDescript());
		JFrame frame = new JFrame();
	    frame.setTitle("Monitoring");
	    frame.setSize(800, 600);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
	    JTextArea pd = new JTextArea();
	    String s = "";
	    for (IPluginDescriptor p : Platform.getPluginDescript()){
	    	s += p.toString() + "\n";
	    }
	    pd.setText(s);
	    frame.add(pd);
	    
	    frame.setVisible(true);
	    
	    
	    
	    
	    

	}
	
}