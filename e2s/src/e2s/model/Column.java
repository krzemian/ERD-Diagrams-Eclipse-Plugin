package e2s.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

/**
 * A column model.
 * Based on Elias Volanakis's GEF Shape Plugin Example.
 */
public class Column extends SpaceAwareModel {

 private static IPropertyDescriptor[] descriptors;
 
 public static final String C_NAME_PROP = "C.NAME";
 public static final String C_TYPE_PROP = "C.TYPE";
 public static final String C_LENGTH_PROP = "C.LENGTH";
 public static final String C_PK_PROP = "C.PK";
 
 private static final Image COLUMN_ICON = createImage("icons/column_16.gif");

 private static final long serialVersionUID = 1;

 private String name;
 private String type;
 private int length=0;
 private boolean isPk=false;

 public Image getIcon() {
  return COLUMN_ICON;
 }

 public String toString() {
  return name+":"+type;
 }

 // TODO: listenery
 public void setName(String name) { this.name=name; firePropertyChange(SELF, null, name); }
 public String getName() { return name; }

 public void setType(String type) { this.type=type;  firePropertyChange(SELF, null, type); }
 public String getType() { return type; }

 public void setLength(int l) { this.length=l;  firePropertyChange(SELF, null, l); }
 public int getLength() { return length; }
 
 public void setPkFlag(boolean isPk) { this.isPk=isPk;  firePropertyChange(SELF, null, isPk); }
 public boolean isPk() { return isPk; }
 
 static {
  descriptors = new IPropertyDescriptor[] 
  {
   new TextPropertyDescriptor(C_NAME_PROP, "1. Nazwa"),
   new TextPropertyDescriptor(C_TYPE_PROP, "2. Typ"), 
   new TextPropertyDescriptor(C_LENGTH_PROP, "3. D³ugoœæ"), 
   new TextPropertyDescriptor(C_PK_PROP, "4. PK"), 
  };
 } // static
 

 public IPropertyDescriptor[] getPropertyDescriptors() {
  return descriptors;
 }

 public Object getPropertyValue(Object propertyId) {
  if (C_NAME_PROP.equals(propertyId)) {
    return name;
 } else if (C_TYPE_PROP.equals(propertyId)) {
  return type;
 } else if (C_LENGTH_PROP.equals(propertyId)) {
  return Integer.toString(length);
 } else if (C_PK_PROP.equals(propertyId)) {
     return Boolean.toString(isPk);
 }
  return super.getPropertyValue(propertyId);
 }

 public void setPropertyValue(Object propertyId, Object value) {
  if (C_NAME_PROP.equals(propertyId)) {
   setName((String) value);
  } else if (C_TYPE_PROP.equals(propertyId)) {
   setType((String) value);
  }  else if (C_LENGTH_PROP.equals(propertyId)) {
   setLength((Integer.parseInt((String) value)));
  }  else if (C_PK_PROP.equals(propertyId)) {
   setPkFlag((Boolean.parseBoolean((String) value)));
  } else {
   super.setPropertyValue(propertyId, value);
  }
 }
 
}