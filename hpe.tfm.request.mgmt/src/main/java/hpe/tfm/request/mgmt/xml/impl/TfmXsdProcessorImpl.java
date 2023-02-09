package hpe.tfm.request.mgmt.xml.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXParseException;

import com.sun.tools.xjc.BadCommandLineException;
import com.sun.tools.xjc.Driver;
import com.sun.tools.xjc.XJCListener;

import hpe.tfm.request.mgmt.xml.TfmXsdProcessor;

public enum TfmXsdProcessorImpl implements TfmXsdProcessor {
	
	instance;
	
	private static final Logger log = LoggerFactory.getLogger(TfmXsdProcessorImpl.class);
	
	private TfmXsdProcessorImpl() {}
	
	private static String targetDir;

	private static String xsdFilePath;

	private static String bindingFilePath;

	public TfmXsdProcessor setTargetDir(String argTargetDir) {
		targetDir = argTargetDir;
		return instance;
	}

	public TfmXsdProcessor setXsdFilePath(String argXsdFile) {
		xsdFilePath = argXsdFile;
		return instance;
	}

	public TfmXsdProcessor setBinding(String argBindingFilePath) {
		bindingFilePath = argBindingFilePath;
		return instance;
	}

	public void generate() throws IOException, BadCommandLineException {
		Path path = Paths.get(targetDir);
		if (!Files.exists(path)) {
			Files.createDirectories(path);
		}
		executeXjc();
	}

	private String[] generateArgs() {
		List<String> argList = new ArrayList<String>() {
			{
				add("-d");
				if (null != targetDir && !"".equals(targetDir))
					add(targetDir);
				if (null != xsdFilePath && !"".equals(xsdFilePath))
					add(xsdFilePath);
				if (null != bindingFilePath && !"".equals(bindingFilePath)) {
					add("-b");
					add(bindingFilePath);
				}
			}
		};
		String[] args = argList.toArray(new String[argList.size()]);
		return args;
	}

	private void executeXjc() throws BadCommandLineException {
		Driver.run(generateArgs(), new XJCListener() {

			@Override
			public void error(SAXParseException exception) {
				log.error("Error: ", exception);
			}

			@Override
			public void fatalError(SAXParseException exception) {
				log.error("fatalError: ", exception);
			}

			@Override
			public void warning(SAXParseException exception) {
				log.error("warning: ", exception);
			}

			@Override
			public void info(SAXParseException exception) {
				log.error("info: ", exception);
			}

		});
	}
}
