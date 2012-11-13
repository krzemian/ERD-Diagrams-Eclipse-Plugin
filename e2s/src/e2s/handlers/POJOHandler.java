package e2s.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import e2s.ERDEditor;
import e2s.ERDOutputGenerator;
/*
 * A handler for POJO class generator
 */
public class POJOHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
			ERDOutputGenerator generator = new ERDOutputGenerator();
			generator.setTables(ERDEditor.singleton.getModel().getChildren());
			try {
				generator.generatePOJO();
			} catch (IOException e) {
			}

	    return null;
	  }
}
