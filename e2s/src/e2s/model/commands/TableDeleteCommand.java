
package e2s.model.commands;

import org.eclipse.gef.commands.Command;

import e2s.model.ERDDiagramModel;
import e2s.model.SpaceAwareModel;
import e2s.model.Table;

/**
 * A command to remove a shape from its parent. The command can be undone or
 * redone.
 * 
 * Based on GEF Shape Plugin Example.
 */
public class TableDeleteCommand extends Command {
	
	private final Table child;

	
	private final ERDDiagramModel parent;

	private boolean wasRemoved;

	
	public TableDeleteCommand(ERDDiagramModel parent, Table child) {
		if (parent == null || child == null) {
			throw new IllegalArgumentException();
		}
		setLabel("shape deletion");
		this.parent = parent;
		this.child = child;
	}

	
	public boolean canUndo() {
		return wasRemoved;
	}

	
	public void execute() {
		
		redo();
	}


	public void redo() {
		
		wasRemoved = parent.removeChild(child);
	}

	
	public void undo() {
	}
}