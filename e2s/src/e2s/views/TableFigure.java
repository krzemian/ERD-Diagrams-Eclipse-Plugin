package e2s.views;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.graphics.Color;

/**
 * Figure used to represent a table in the schema
 * Based on Database Schema Diagram Editor.
 */
public class TableFigure extends Figure
{

	public static Color tableColor = new Color(null, 240, 250, 211);
	private ColumnsFigure columnsFigure = new ColumnsFigure();
	private EditableLabel nameLabel;

	public TableFigure(EditableLabel name)
	{
		this(name, null);
	}

	public TableFigure(EditableLabel name, List colums)
	{
		
		nameLabel = name;
		ToolbarLayout layout = new ToolbarLayout();
		layout.setVertical(true);
		layout.setStretchMinorAxis(true);
		setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(tableColor);
		setForegroundColor(ColorConstants.black);
		setOpaque(true);
		layout.setSpacing(3);

		name.setForegroundColor(ColorConstants.black);
		name.setBackgroundColor(ColorConstants.yellow);
		add(name);
		add(columnsFigure);

	}

	public void setSelected(boolean isSelected)
	{
		LineBorder lineBorder = (LineBorder) getBorder();
		if (isSelected)
		{
			lineBorder.setWidth(2);
		}
		else
		{
			lineBorder.setWidth(1);
		}
	}

	

	public EditableLabel getNameLabel()
	{
		return nameLabel;
	}

	public ColumnsFigure getColumnsFigure()
	{
		return columnsFigure;
	}
}