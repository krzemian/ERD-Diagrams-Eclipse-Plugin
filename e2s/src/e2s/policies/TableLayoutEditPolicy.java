package e2s.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import e2s.model.Column;
import e2s.model.Table;
import e2s.model.commands.ColumnCreateCommand;
import e2s.parts.TablePart;

/**
 * This edit policy is about Table Layout.
 * 
 * Based on GEF Shape Plugin Example.
 */
public class TableLayoutEditPolicy extends LayoutEditPolicy {

	

	public Command getCreateCommand(CreateRequest request)
	{

		Object newObject = request.getNewObject(); // CHILD
		if (!(newObject instanceof Column))
		{
			return null;
		}
		
		TablePart tablePart = (TablePart) getHost();
		Table table = (Table) tablePart.getModel(); // PARENT
		Column column = (Column) newObject; // CHILD
		ColumnCreateCommand command = new ColumnCreateCommand();
		command.setTable(table);
		command.setColumn(column);

		return command;
	}
	

	protected EditPolicy createChildEditPolicy(EditPart child) {

		return new NonResizableEditPolicy();
	}

	@Override
	protected Command getMoveChildrenCommand(Request request) {

		return null;
	}
}
