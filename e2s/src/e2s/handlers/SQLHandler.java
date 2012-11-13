package e2s.handlers;


import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;


import e2s.ERDEditor;
import e2s.ERDOutputGenerator;

/*
 * A handler for SQL code generator
 */
public class SQLHandler extends AbstractHandler {
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		ERDOutputGenerator generator = new ERDOutputGenerator();
		generator.setTables(ERDEditor.singleton.getModel().getChildren());
		try {
			generator.generateSQL();
		} catch (IOException e) {
		}

	    return null;
	  }
}
