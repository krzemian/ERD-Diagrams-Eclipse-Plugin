
package e2s.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import e2s.model.Column;
import e2s.model.ERDDiagramModel;
import e2s.model.SpaceAwareModel;
import e2s.model.Table;
import e2s.model.commands.ColumnDeleteCommand;
import e2s.model.commands.TableDeleteCommand;
import e2s.parts.ColumnPart;

/**
 * This edit policy enables the removal of a Shapes instance from its container.
 * 
 * Based on GEF Shape Plugin Example.
 */
public class ERDComponentEditPolicy extends ComponentEditPolicy {


	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		Object parent = getHost().getParent().getModel();
		Object child = getHost().getModel();
		if (parent instanceof ERDDiagramModel && child instanceof Table) {
			return new TableDeleteCommand((ERDDiagramModel) parent, (Table) child);
		}
		if (parent instanceof Table && child instanceof Column) {
			return new ColumnDeleteCommand((Table) parent, (Column) child);
		}
		return super.createDeleteCommand(deleteRequest);
	}
}