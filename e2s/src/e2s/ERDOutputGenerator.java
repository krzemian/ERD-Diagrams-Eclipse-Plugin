package e2s;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import e2s.model.Column;
import e2s.model.Table;

/*
 * SQL and POJO generators
 */
public class ERDOutputGenerator {
	
	List<Table> tables;
	List<Column> columns;
	String output;
	
	public ERDOutputGenerator(){
		tables = new ArrayList<Table>();
		columns = new ArrayList<Column>();
		output = "";
	}
	
	public ERDOutputGenerator(List<Table> tableList){
		tables = new ArrayList<Table>(tableList);
		columns = new ArrayList<Column>();
		output = "";
	}
	
	public void setTables(List<Table> tableList){
		tables = new ArrayList<Table>(tableList);
	}
	
	public void generateSQL() throws IOException{
		if (tables.size() == 0 || tables == null){
			return;
		}
		output = "";
		
		for(int i = 0; i < tables.size(); i++){
			output += "CREATE TABLE " + tables.get(i).getName() +" (\n";
			
			if (tables.get(i).getColumns().size() == 0 || tables.get(i).getColumns() == null){
				return;
			}
			
			columns = new ArrayList<Column>(tables.get(i).getColumns());
			for(int j = 0; j < columns.size(); j++){
				if (columns.get(j).isPk()){
					output += columns.get(j).getName() + " " + columns.get(j).getType();
					if (columns.get(j).getLength() != 0){
						output += "(" + columns.get(j).getLength() + ")";
					} 
					output += " PRIMARY KEY,\n";
				} else{
					output += columns.get(j).getName() + " " + columns.get(j).getType();
					if (columns.get(j).getLength() != 0){
						output += "(" + columns.get(j).getLength() + ")";
					} 
					output += ",\n";
				}
			}
			output = (String) output.subSequence(0, output.length()-2);
			output += "\n);\n";
		}
		
		Frame filedialog = new Frame();
		filedialog.setBounds(20,20,400,500);
		FileDialog saveFileDialog = new FileDialog(filedialog, "Save SQL file", FileDialog.SAVE);
		saveFileDialog.setVisible(true);
		
		
		File outputFile = new File(saveFileDialog.getDirectory()+saveFileDialog.getFile());
		if (!outputFile.exists()){
			outputFile.createNewFile();
		}
		
		
		FileWriter fw = new FileWriter(outputFile);
		fw.write(output);
		fw.close();
		
	}

	public void generatePOJO() throws IOException{
		if (tables.size() == 0 || tables == null){
			return;
		}
		
		Frame filedialog = new Frame();
		filedialog.setBounds(20,20,400,500);
		FileDialog saveFileDialog = new FileDialog(filedialog, "Select directory to save Java files", FileDialog.SAVE);

		
		saveFileDialog.setVisible(true);
		String type = "";
		String directory = saveFileDialog.getDirectory();
		
		for(int i = 0; i < tables.size(); i++){
			output = "";
			output += "public class " + tables.get(i).getName().toUpperCase().charAt(0) + tables.get(i).getName().substring(1) + "{\n";
			
			if (tables.get(i).getColumns().size() == 0 || tables.get(i).getColumns() == null){
				return;
			}
			
			columns = new ArrayList<Column>(tables.get(i).getColumns());
			for(int j = 0; j < columns.size(); j++){			
				type = determineType(columns.get(j).getType());
				output += "private " + type + " " + columns.get(j).getName() + ";\n";
				output += "public " + type + " " + "get" + columns.get(j).getName().toUpperCase().charAt(0) + columns.get(j).getName().substring(1) + "(){\n return " + columns.get(j).getName() + ";\n}\n";
				output += "public void " + "set" + columns.get(j).getName().toUpperCase().charAt(0) + columns.get(j).getName().substring(1) + "( " + type + " a){\n this." + columns.get(j).getName() + " = a; \n}\n";
			}
			
			output += "}\n";
			
			File outputFile = new File(directory + tables.get(i).getName().toUpperCase().charAt(0) + tables.get(i).getName().substring(1)+".java");
			if (!outputFile.exists()){
				outputFile.createNewFile();
			}
			
			
			FileWriter fw = new FileWriter(outputFile);
			fw.write(output);
			fw.close();
			
		}
	}

	private String determineType(String type) {
		type = type.toUpperCase();
		if (type.contains("INT")){
			return "int";
		} else if (type.contains("FLOAT")){
			return "float";
		} else if(type.contains("DOUBLE") || type.contains("REAL")){
			return "double";
		} else {
			return "String";
		}
	}
}
