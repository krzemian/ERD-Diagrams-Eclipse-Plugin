
package e2s.views;

import org.eclipse.draw2d.Label;
import org.eclipse.swt.graphics.Color;

/**
 * Figure used to represent a table in the schema
 * Based on Database Schema Diagram Editor.
 */
public class ColumnFigure extends Label
{

	public static Color tableColor = new Color(null, 255, 255, 206);
	private ColumnsFigure columnsFigure = new ColumnsFigure();
	private EditableLabel nameLabel;

	public ColumnFigure(String name)
	{
		nameLabel = new EditableLabel(name);


	}

	public void setSelected(boolean isSelected)
	{
	}
	

	public EditableLabel getNameLabel()
	{
		return nameLabel;
	}
}