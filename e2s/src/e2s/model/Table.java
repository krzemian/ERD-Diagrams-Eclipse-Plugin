package e2s.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * A table model.
 */
public class Table extends SpaceAwareModel {

	private static IPropertyDescriptor[] descriptors;
	
	public static final String T_NAME_PROP = "T.NAME";
	
	private static final Image TABLE_ICON = createImage("icons/table_16.gif");

	private static final long serialVersionUID = 1;
	private static int tableCounter=1;
	private String name;
	private List<Column> columns = new ArrayList<Column>();
	
	public Table() {
		super();
		name="Table"+Integer.toString(tableCounter++);
	}

	public Image getIcon() {
		return TABLE_ICON;
	}

	public String toString() {
		return "[t]"+name;
	}

	/** SETTERS & GETTERS **/
	public void setName(String name) { this.name=name; firePropertyChange(SELF, null, name); }
	public String getName() { return name;	}
	
	public List<Column> getColumns() { return columns; }

	public void addColumn(Column column)
	{
		if (columns.contains(column))
		{
			throw new IllegalArgumentException("Kolumna ju¿ istnieje");
		}
		columns.add(column);
		firePropertyChange(CHILD, null, column);
	}

	public boolean removeColumn(Column column)
	{
		boolean ok = columns.remove(column);
		firePropertyChange(CHILD, column, null);
		return ok;
	}
	
	/** FOR PROPERTIES **/
	static {

		descriptors = new IPropertyDescriptor[] {
				new TextPropertyDescriptor(T_NAME_PROP, "1. Nazwa"),
		};
	}
	
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return descriptors;
	}
	
	public void setPropertyValue(Object property, Object value) {
		if(property.equals(T_NAME_PROP)) setName((String) value);		
	}
	
	public Object getPropertyValue(Object property) {
		if(property.equals(T_NAME_PROP)) return name;	
		return super.getPropertyValue(property); // TODO: why?	
	}
}
