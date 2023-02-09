package hpe.tfm.request.mgmt.xml;

import java.io.IOException;

import com.sun.tools.xjc.BadCommandLineException;

public interface TfmXsdProcessor {
	
	public TfmXsdProcessor setTargetDir(String argTargetDir);
	
	public TfmXsdProcessor setXsdFilePath(String argXsdFile);
	
	public TfmXsdProcessor setBinding(String argBindingFilePath);
	
	public void generate() throws IOException, BadCommandLineException;
	
}
