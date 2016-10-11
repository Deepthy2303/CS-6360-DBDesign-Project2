package com.dbproject.davisbase;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Query {

	public static String schema;
	public static String[] insertTuples;
	public static String table_name;
	public static String condition;
	public static String[] columns;
	public static void createSchema(String schema_name)
	{
		
		schema = schema_name;
			RandomAccessFile schemataTableFile;
			try {
				if (MakeInformationSchema.existSCHEMATA(schema_name))
				{
					System.out.println("Schema \"" + schema_name + "\" exists.");
					return;
				}
				schemataTableFile = new RandomAccessFile("information_schema.schemata.tbl", "rw");
				schemataTableFile.seek(schemataTableFile.length());
				schemataTableFile.writeByte(schema_name.length());
				schemataTableFile.writeBytes(schema_name);
				schemataTableFile.close();
				
				if (!MakeInformationSchema.isSchemaExist(schema_name))
					System.out.println(schema_name + " created.");
			}catch(Exception e) {
				System.out.println(e);
			}
		}



	public static void showSchemas()
		{
			try {
				int indexFileLocation = 0;
				String tableName = "information_schema.schemata.tbl";
				RandomAccessFile schemataTableFile = new RandomAccessFile(tableName, "rw");

				while(true) {
					if (endFile(schemataTableFile.length(), indexFileLocation))
						break;
					
					schemataTableFile.seek(indexFileLocation);
					Byte varcharLength = schemataTableFile.readByte();
					StringBuilder sb = new StringBuilder();
					for(int i = 0; i < varcharLength; i++)
					{
						sb.append((char)schemataTableFile.readByte());
					}
					System.out.println(sb.toString());
					indexFileLocation += 1;
					indexFileLocation = indexFileLocation + varcharLength.intValue();
				}
				schemataTableFile.close();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	

	
	public static void useSchema(String schema_name)
	{
		schema=schema_name;
		
				if (MakeInformationSchema.existSCHEMATA(schema))
				{
					
					System.out.println("Current schema: " + schema_name);
				}
				else
				{
					System.out.println(schema_name + " is not a valid schema name.");
			}
			
			
		}
	

	
	public static void showTables()
		{
			try {
				int indexFileLocation = 0;
				String tableName = "information_schema.table.tbl";
				RandomAccessFile tableFile = new RandomAccessFile(tableName, "rw");

				while(true) {
					if (endFile(tableFile.length(), indexFileLocation))
						break;
					
					tableFile.seek(indexFileLocation);
					Byte varcharLength1 = tableFile.readByte();
					StringBuilder sb1 = new StringBuilder();
					for(int i = 0; i < varcharLength1; i++)
					{
						sb1.append((char)tableFile.readByte());
					}
					
					Byte varcharLength2 = tableFile.readByte();
					StringBuilder sb2 = new StringBuilder();
					for(int i = 0; i < varcharLength2; i++)
					{
						sb2.append((char)tableFile.readByte());
					}
					Long tableRow = tableFile.readLong();
					
					indexFileLocation += 10;
					indexFileLocation = indexFileLocation + varcharLength1.intValue() + varcharLength2.intValue();
					
					
					if (Pattern.matches("[Z]+", sb2.toString()))
						continue;
					System.out.print(sb1.toString() + "  \t");
					System.out.print(sb2.toString() + "  \t");
					System.out.println(tableRow);
				}
				tableFile.close();
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	

		
		public static void createTable(String table, String[] insertRows) 
		{
			
			table_name = table;
			insertTuples = insertRows;
			if (schema.equals(""))
				System.out.println("Error: Please choose schema first.");
			else
			{
				
				List<String> column_name = new ArrayList<String>();
				List<String> column_type = new ArrayList<String>();
				List<String> is_nullable = new ArrayList<String>();
				List<String> column_key = new ArrayList<String>();
				for (int i = 0; i < insertTuples.length; i++)
				{
					String[] temp = insertTuples[i].trim().split("\\s");
					column_name.add(temp[0]);
					column_type.add(temp[1]);
					if (temp.length <= 2)
					{
						is_nullable.add("YES");
						column_key.add("");
					}
					else if (Pattern.matches("(?i)primary", temp[2]))
					{
						is_nullable.add("NO");
						column_key.add("PRI");
					}
					else if (Pattern.matches("(?i)not", temp[2]))
					{
						is_nullable.add("NO");
						column_key.add("");
					}
					else
					{
						is_nullable.add("YES");
						column_key.add("");
					}
				}
				
				for (int i = 0; i < column_name.size() - 1; i++)
				{
					if (column_name.get(i).equals(column_name.get(i + 1)))
					{
						System.out.println("Error: Duplicate column name.");
						return;
					}
				}
				MakeInformationSchema.updateSystemTABLES(schema, table_name);
				MakeInformationSchema.updateSystemCOLUMNS(schema, table_name, column_name, column_type, is_nullable, column_key); 
			}
		}
	

	
		public static void insert(String table, String[] insertRows) 
		{
			table_name = table;
			insertTuples = insertRows;
		if (schema.equals(""))
				System.out.println("Error: Please choose schema first.");
			
			else if (!MakeInformationSchema.existInTABLES(schema, table_name))
			{
				System.out.println("Error: Table \"" + table_name + "\" not exists.");
			}
			
			else
			{
				
				List<String> temp = new ArrayList<String>();
				for (int i = 0; i < insertTuples.length; i++)
					temp.add(insertTuples[i].trim());
				
				
				if (MakeInformationSchema.InsertTypeCheck(schema, table_name, temp))
				{
					
				
					MakeInformationSchema.incrementSystemTABLE_ROW(schema, table_name);
					
						
					MakeInformationSchema.insertIntoTable(schema, table_name, temp);
				}
			}
		}
	

	public static void select(String[] col,String table, String cond)
		{
			columns=col;
			table_name = table;
			condition = cond;
			try {
				MakeInformationSchema.SelectFromTable(schema, table_name, condition);
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
	

	public static void drop(String table)
		{
			table_name = table;
		
	String deleteTableName = schema + "." + table_name + ".tbl";
			if (MakeInformationSchema.dropTable(schema, table_name))
			{
			    File f = new File(deleteTableName);
			    f.delete();
			    System.out.println("Drop successful.");	
			}
			else
				System.out.println("Error: Table \'" + table_name + "\' does not exist.");
		}



	public static boolean endFile(long fileSize, long currentPointer)
	{
		if (fileSize <= currentPointer)
		{
			return true;
		}
		else
			return false;
	}
	}