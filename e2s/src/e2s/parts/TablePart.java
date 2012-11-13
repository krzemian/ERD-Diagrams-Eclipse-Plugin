
package e2s.parts;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import e2s.model.Column;
import e2s.model.PropertyAwareModel;
import e2s.model.Table;
import e2s.policies.ERDComponentEditPolicy;
import e2s.policies.TableLayoutEditPolicy;
import e2s.views.EditableLabel;
import e2s.views.TableFigure;

/**
 * EditPart used for Shape instances (more specific for EllipticalShape and
 * RectangularShape instances).
 * <p>
 * This edit part must implement the PropertyChangeListener interface, so it can
 * be notified of property changes in the corresponding model element.
 * </p>
 * 
 * Based on GEF Shape Plugin Example.
 */
public class TablePart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {


	public void activate() {
		if (!isActive()) {
			super.activate();
			((PropertyAwareModel) getModel()).addPropertyChangeListener(this);
		}
	}


	protected void createEditPolicies() {

				installEditPolicy(EditPolicy.COMPONENT_ROLE,
				new ERDComponentEditPolicy());
		installEditPolicy(EditPolicy.CONTAINER_ROLE, new TableLayoutEditPolicy());
		
	}



	protected IFigure createFigure()
	{
		Table table = (Table) getModel();
		EditableLabel label = new EditableLabel(table.getName());
		TableFigure tableFigure = new TableFigure(label);

	
		return tableFigure;
	}


	protected void refreshVisuals()
	{
		Table tableModel = (Table) getModel();
		Point location = tableModel.getLocation();
		Rectangle constraint = new Rectangle(location.x, location.y, -1, -1);
		((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), constraint);
		((TableFigure) getFigure()).getNameLabel().setText(tableModel.getName()); 
		
	
	}


	public IFigure getContentPane(){
		TableFigure figure = (TableFigure) getFigure();
		return figure.getColumnsFigure();
	
	}



	public void deactivate() {
		if (isActive()) {
			super.deactivate();
			((PropertyAwareModel) getModel()).removePropertyChangeListener(this);
		}
	}
	

	public Table getTable()
	{
		return (Table) getModel();
	}


	protected List<Column> getModelChildren()
	{
		return getTable().getColumns();
	}
	

	public void propertyChange(PropertyChangeEvent evt) {
		String prop = evt.getPropertyName();
		if (Table.SELF.equals(prop)) {
			refreshVisuals();
		} else if(Table.CHILD.equals(prop)) {
			refreshChildren();
			refreshVisuals();
		}
	}
}