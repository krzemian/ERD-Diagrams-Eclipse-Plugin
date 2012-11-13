
package e2s.model.commands;

import org.eclipse.gef.commands.Command;

import e2s.model.Column;
import e2s.model.Table;

/**
 * A command to remove a shape from its parent. The command can be undone or
 * redone.
 * 
 *Based on GEF Shape Plugin Example.
 */
public class ColumnDeleteCommand extends Command {
	
	private final Column oldColumn;
	private Column oldColumn2;

	
	private final Table parent;

	private boolean wasRemoved;

	
	public ColumnDeleteCommand(Table parent, Column oldColumn) {
		if (parent == null || oldColumn == null) {
			throw new IllegalArgumentException();
		}
		setLabel("column deletion");
		this.parent = parent;
		this.oldColumn = oldColumn;
	}

	
	public boolean canUndo() {
		return wasRemoved;
	}

	
	public void execute() {
		
		oldColumn2=oldColumn;
		redo();
	}

	
	public void redo() {
		
		wasRemoved = parent.removeColumn(oldColumn);
	}

	
	public void undo() {
		parent.addColumn(oldColumn2);
	}
}