
package e2s.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;

import e2s.model.ERDDiagramModel;
import e2s.model.PropertyAwareModel;
import e2s.model.SpaceAwareModel;
import e2s.model.Table;
import e2s.model.commands.ERDCreateCommand;
import e2s.model.commands.ERDSetConstraintCommand;

/**
 * EditPart for the a ShapesDiagram instance.
 * <p>
 * This edit part server as the main diagram container, the white area where
 * everything else is in. Also responsible for the container's layout (the way
 * the container rearanges is contents) and the container's capabilities (edit
 * policies).
 * </p>
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 * Based on GEF Shape Plugin Example.
 */
class ERDDiagramEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {


	public void activate() {
		if (!isActive()) {
			super.activate();
			((PropertyAwareModel) getModel()).addPropertyChangeListener(this);
		}
	}


	protected void createEditPolicies() {

		installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new RootComponentEditPolicy());

		installEditPolicy(EditPolicy.LAYOUT_ROLE,
				new ShapesXYLayoutEditPolicy());
	}

	
	protected IFigure createFigure() {
		Figure f = new FreeformLayer();
		f.setBorder(new MarginBorder(3));
		f.setLayoutManager(new FreeformLayout());

		
		ConnectionLayer connLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
		connLayer.setConnectionRouter(new ShortestPathConnectionRouter(f));

		return f;
	}


	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((PropertyAwareModel) getModel()).removePropertyChangeListener(this);
		}
	}

	private ERDDiagramModel getCastedModel() {
		return (ERDDiagramModel) getModel();
	}


	protected List<Table> getModelChildren() {
		return getCastedModel().getChildren(); // return a list of shapes
	}

	
	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		
		if (ERDDiagramModel.CHILD_ADDED_PROP.equals(prop)
				|| ERDDiagramModel.CHILD_REMOVED_PROP.equals(prop)) {
			refreshChildren();
		}
	}

	
	private static class ShapesXYLayoutEditPolicy extends XYLayoutEditPolicy {

		
		protected Command createChangeConstraintCommand(
				ChangeBoundsRequest request, EditPart child, Object constraint) {
			if ((child instanceof ColumnPart || child instanceof TablePart)
					&& constraint instanceof Rectangle) {
				
				return new ERDSetConstraintCommand((SpaceAwareModel) child.getModel(),
						request, (Rectangle) constraint);
			}
			return super.createChangeConstraintCommand(request, child,
					constraint);
		}

		
		protected Command createChangeConstraintCommand(EditPart child,
				Object constraint) {
		
			return null;
		}


		protected Command getCreateCommand(CreateRequest request) {
			Object childClass = request.getNewObjectType();
			if (childClass == Table.class) {
				
				return new ERDCreateCommand((Table) request.getNewObject(),
						(ERDDiagramModel) getHost().getModel(),
						(Rectangle) getConstraintFor(request));
			}
			return null;
		}

	}

}