package com.dbproject.davisbase;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;



public class MakeInformationSchema {
	
	
	@SuppressWarnings("resource")
	public static void initialize() {
		try {
			
			RandomAccessFile schemataTableFile = new RandomAccessFile("information_schema.schemata.tbl", "rw");
			RandomAccessFile tablesTableFile = new RandomAccessFile("information_schema.table.tbl", "rw");
			RandomAccessFile columnsFile = new RandomAccessFile("information_schema.columns.tbl", "rw");

			/*
			 *  Create the SCHEMATA table file.
			 *  Initially it has only one entry:
			 *      information_schema
			 */
			// ROW 1: information_schema.schemata.tbl
			schemataTableFile.writeByte("information_schema".length());
			schemataTableFile.writeBytes("information_schema");

			/*
			 *  Create the TABLES table file.
			 *  Initially it has three rows (each row may have a different length):
			 */
			// ROW 1: information_schema.tables.tbl
			tablesTableFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			tablesTableFile.writeBytes("information_schema");
			tablesTableFile.writeByte("SCHEMATA".length()); // TABLE_NAME
			tablesTableFile.writeBytes("SCHEMATA");
			tablesTableFile.writeLong(1); // TABLE_ROWS

			// ROW 2: information_schema.tables.tbl
			tablesTableFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			tablesTableFile.writeBytes("information_schema");
			tablesTableFile.writeByte("TABLES".length()); // TABLE_NAME
			tablesTableFile.writeBytes("TABLES");
			tablesTableFile.writeLong(3); // TABLE_ROWS

			// ROW 3: information_schema.tables.tbl
			tablesTableFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			tablesTableFile.writeBytes("information_schema");
			tablesTableFile.writeByte("COLUMNS".length()); // TABLE_NAME
			tablesTableFile.writeBytes("COLUMNS");
			tablesTableFile.writeLong(7); // TABLE_ROWS

			/*
			 *  Create the COLUMNS table file.
			 *  Initially it has 11 rows:
			 */
			// ROW 1: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("SCHEMATA".length()); // TABLE_NAME
			columnsFile.writeBytes("SCHEMATA");
			columnsFile.writeByte("SCHEMA_NAME".length()); // COLUMN_NAME
			columnsFile.writeBytes("SCHEMA_NAME");
			columnsFile.writeInt(1); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 2: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("TABLES".length()); // TABLE_NAME
			columnsFile.writeBytes("TABLES");
			columnsFile.writeByte("TABLE_SCHEMA".length()); // COLUMN_NAME
			columnsFile.writeBytes("TABLE_SCHEMA");
			columnsFile.writeInt(1); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 3: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("TABLES".length()); // TABLE_NAME
			columnsFile.writeBytes("TABLES");
			columnsFile.writeByte("TABLE_NAME".length()); // COLUMN_NAME
			columnsFile.writeBytes("TABLE_NAME");
			columnsFile.writeInt(2); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 4: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("TABLES".length()); // TABLE_NAME
			columnsFile.writeBytes("TABLES");
			columnsFile.writeByte("TABLE_ROWS".length()); // COLUMN_NAME
			columnsFile.writeBytes("TABLE_ROWS");
			columnsFile.writeInt(3); // ORDINAL_POSITION
			columnsFile.writeByte("long int".length()); // COLUMN_TYPE
			columnsFile.writeBytes("long int");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 5: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("TABLE_SCHEMA".length()); // COLUMN_NAME
			columnsFile.writeBytes("TABLE_SCHEMA");
			columnsFile.writeInt(1); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 6: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("TABLE_NAME".length()); // COLUMN_NAME
			columnsFile.writeBytes("TABLE_NAME");
			columnsFile.writeInt(2); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 7: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("COLUMN_NAME".length()); // COLUMN_NAME
			columnsFile.writeBytes("COLUMN_NAME");
			columnsFile.writeInt(3); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 8: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("ORDINAL_POSITION".length()); // COLUMN_NAME
			columnsFile.writeBytes("ORDINAL_POSITION");
			columnsFile.writeInt(4); // ORDINAL_POSITION
			columnsFile.writeByte("int".length()); // COLUMN_TYPE
			columnsFile.writeBytes("int");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 9: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("COLUMN_TYPE".length()); // COLUMN_NAME
			columnsFile.writeBytes("COLUMN_TYPE");
			columnsFile.writeInt(5); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(64)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(64)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 10: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("IS_NULLABLE".length()); // COLUMN_NAME
			columnsFile.writeBytes("IS_NULLABLE");
			columnsFile.writeInt(6); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(3)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(3)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

			// ROW 11: information_schema.columns.tbl
			columnsFile.writeByte("information_schema".length()); // TABLE_SCHEMA
			columnsFile.writeBytes("information_schema");
			columnsFile.writeByte("COLUMNS".length()); // TABLE_NAME
			columnsFile.writeBytes("COLUMNS");
			columnsFile.writeByte("COLUMN_KEY".length()); // COLUMN_NAME
			columnsFile.writeBytes("COLUMN_KEY");
			columnsFile.writeInt(7); // ORDINAL_POSITION
			columnsFile.writeByte("varchar(3)".length()); // COLUMN_TYPE
			columnsFile.writeBytes("varchar(3)");
			columnsFile.writeByte("NO".length()); // IS_NULLABLE
			columnsFile.writeBytes("NO");
			columnsFile.writeByte("".length()); // COLUMN_KEY
			columnsFile.writeBytes("");

		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static boolean existSCHEMATA(String compareItem)
	{
		try {
			RandomAccessFile IndexFile = new RandomAccessFile("information_schema.schemata.tbl", "rw");
			int indexLocation = 0;
			
			
			while(true) {
				if (Query.endFile(IndexFile.length(), indexLocation))
					break;
				
				IndexFile.seek(indexLocation);
				Byte varLen = IndexFile.readByte();
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < varLen; i++)
				{
					sb.append((char)IndexFile.readByte());
				}
				if (compareItem.equals(sb.toString()))
				{
					IndexFile.close();
					return true;
				}
				indexLocation += 1;
				indexLocation = indexLocation + varLen.intValue();
			}
			IndexFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isSchemaExist(String schemaName)
	{
		File f = new File(schemaName);
		if (f.exists())
			return true;
		else
		{
			f.mkdirs();
			return false;
		}	
	}
	
	public static void updateSystemTABLES(String schema_name, String table_name)
	{
		try {
			String file_name = "information_schema.table.tbl";
			RandomAccessFile tablesTableFile = new RandomAccessFile(file_name, "rw");
			
			if (existInTABLES(schema_name, table_name))
			{
				System.out.println(table_name + " already exists.");
			}
			else
			{
				tablesTableFile.seek(tablesTableFile.length());
				tablesTableFile.writeByte(schema_name.length()); 
				tablesTableFile.writeBytes(schema_name);
				tablesTableFile.writeByte(table_name.length());
				tablesTableFile.writeBytes(table_name);
				tablesTableFile.writeLong(0);
			}
			tablesTableFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void updateSystemCOLUMNS(String schema_name, String table_name, 
			List<String> column_name, List<String> column_type, List<String> is_nullable, List<String> column_key)
	{
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			columnsFile.seek(columnsFile.length());
			for (int j = 0; j < column_name.size(); j++)
			{
				columnsFile.writeByte(schema_name.length());
				columnsFile.writeBytes(schema_name);
				columnsFile.writeByte(table_name.length());
				columnsFile.writeBytes(table_name);
				columnsFile.writeByte(column_name.get(j).length());
				columnsFile.writeBytes(column_name.get(j));
				columnsFile.writeInt(j + 1);
				columnsFile.writeByte(column_type.get(j).length());
				columnsFile.writeBytes(column_type.get(j));
				columnsFile.writeByte(is_nullable.get(j).length());
				columnsFile.writeBytes(is_nullable.get(j));
				columnsFile.writeByte(column_key.get(j).length());
				columnsFile.writeBytes(column_key.get(j));
			}
			columnsFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void incrementSystemTABLE_ROW(String schema_name, String table_name) {
		try {
			String file_name = "information_schema.table.tbl";
			RandomAccessFile tablesTableFile = new RandomAccessFile(file_name, "rw");
			
			
			if (!existInTABLES(schema_name, table_name))
			{
				System.out.println(table_name + " does not exists.");
			}
			else
			{
				while(true) {
					if (Query.endFile(tablesTableFile.length(), tablesTableFile.getFilePointer()))
						break;

					Byte varLen1 = tablesTableFile.readByte();
					StringBuilder sb1 = new StringBuilder();
					for(int i = 0; i < varLen1; i++)
					{
						sb1.append((char)tablesTableFile.readByte());
					}
						
					Byte varLen2 = tablesTableFile.readByte();
					StringBuilder sb2 = new StringBuilder();
					for(int i = 0; i < varLen2; i++)
					{
						sb2.append((char)tablesTableFile.readByte());
					}
					
					if (sb1.toString().equals(schema_name) && sb2.toString().equals(table_name))
					{
						Long pointer = tablesTableFile.getFilePointer();
						Long row = tablesTableFile.readLong();
						tablesTableFile.seek(pointer);
						tablesTableFile.writeLong(row + 1);
						break;
					}
					tablesTableFile.skipBytes(8);
						
				}
			}
			tablesTableFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertIntoTable(String schema_name, String table_name,
			List<String> temp) {
		try {
			List<String> tab_schema = getSystemColumnTABLE_SCHEMA();
			List<String> tab_name = getSystemColumnTABLE_NAME();
			List<String> col_name = getSystemColumnCOLUMN_NAME();
			List<String> col_type = getSystemColumnCOLUMN_TYPE();
			
			List<String> insert_column_name = new ArrayList<String>();
			List<String> insert_column_type = new ArrayList<String>();
			for (int i = 0; i < tab_name.size(); i++)
			{
				if (tab_schema.get(i).equals(schema_name) && tab_name.get(i).equals(table_name))
				{
					insert_column_name.add(col_name.get(i));
					insert_column_type.add(col_type.get(i));
				}
			}
			
			for (int i = 0; i < insert_column_type.size(); i++)
			{
				if (Pattern.matches("(?i)null", temp.get(i)))
				{
					return;
				}
			}
		
			String TableFileName = schema_name + "." + table_name + ".tbl";
			for (int i = 0; i < insert_column_type.size(); i++)
			{
				String indexFileName = schema_name + "." + table_name + "." + insert_column_name.get(i) + ".ndx";
				RandomAccessFile TableFile = new RandomAccessFile(TableFileName, "rw");
				RandomAccessFile IndexFile = new RandomAccessFile(indexFileName, "rw");
				IndexFile.seek(IndexFile.length());
				
				switch (getDataSize(insert_column_type.get(i)))
				{
					case 1://BYTE
						IndexFile.writeByte(Byte.parseByte(temp.get(i)));
						break;
					case 2://SHORT INT or SHORT
						IndexFile.writeShort(Short.parseShort(temp.get(i)));
						break;
					case 4://INT or FLOAT
						IndexFile.writeInt(Integer.parseInt(temp.get(i)));
						break;
					case 8://LONG INT or LONG or DOUBLE	or DATETIME or DATE
						IndexFile.writeLong(Long.parseLong(temp.get(i)));
						break;
					case 0://VARCHAR(n)	
						IndexFile.writeByte(temp.get(i).length());
						IndexFile.writeBytes(temp.get(i));
						break;
					case -1://invalid type
						System.out.println("Invalid Type");
						break;
					default://CHAR(n)
						IndexFile.writeBytes(temp.get(i));
						break;
				}
				TableFile.seek(TableFile.length());
				IndexFile.writeLong(TableFile.getFilePointer());
				TableFile.close();
				IndexFile.close();
			}
			for (int i = 0; i < insert_column_name.size(); i++)
			{
				RandomAccessFile TableFile = new RandomAccessFile(TableFileName, "rw");
				TableFile.seek(TableFile.length());
				switch (getDataSize(insert_column_type.get(i)))
				{
					case 1://BYTE
						TableFile.writeByte(Byte.parseByte(temp.get(i)));
						break;
					case 2://SHORT INT or SHORT
						TableFile.writeShort(Short.parseShort(temp.get(i)));
						break;
					case 4://INT or FLOAT
						TableFile.writeInt(Integer.parseInt(temp.get(i)));
						break;
					case 8://LONG INT or LONG or DOUBLE	or DATETIME or DATE
						TableFile.writeLong(Long.parseLong(temp.get(i)));
						break;
					case 0://VARCHAR(n)	
						TableFile.writeByte(temp.get(i).length());
						TableFile.writeBytes(temp.get(i));
						break;
					case -1://invalid type
						System.out.println("Error: Invalid Type");
						break;
					default://CHAR(n)
						TableFile.writeBytes(temp.get(i));
						break;
				}
				TableFile.close();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void SelectFromTable(String schema_name, String table_name, String condition)
	{
		try {
			String [] condi = condition.trim().split("\\s+");
			if (condi.length != 3)
			{
				System.out.println("Error: Invalid condition format.");
				return;
			}
			String column_name = condi[0];
			String operator = condi[1];
			String value = condi[2];
			List<String> tab_schema = getSystemColumnTABLE_SCHEMA();
			List<String> tab_name = getSystemColumnTABLE_NAME();
			List<String> col_name = getSystemColumnCOLUMN_NAME();
			List<String> col_type = getSystemColumnCOLUMN_TYPE();
			String column_type = "";
			for (int i = 0; i < tab_name.size(); i++)
			{
				if (tab_schema.get(i).equals(schema_name) && tab_name.get(i).equals(table_name)
						&& col_name.get(i).equals(column_name))
				{
					column_type = col_type.get(i);		
				}
			} 
			
			ArrayList<Long> address = getTupleAddress(schema_name, table_name, column_name, column_type, operator, value);
			
			printSelectTuples(schema_name, table_name, address);
		}
		catch(Exception e) {
			System.out.println(e);
		}
				
	}
	
	@SuppressWarnings("resource")
	public static ArrayList<Long> getTupleAddress(String schema_name, String table_name, String column_name
			, String column_type, String operater, String compareItem)
	{
		String indexName = schema_name + "." + table_name + "." + column_name + ".ndx";
		try {
			ArrayList<Long> al = new ArrayList<Long>();
			RandomAccessFile IndexFile = new RandomAccessFile(indexName, "rw");
			int column_size = getDataSize(column_type);
			
			switch (column_size)
			{
				case 1://BYTE
				{
					List<Byte> list = ReadByteFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i) == Byte.parseByte(compareItem))
							{
								IndexFile.skipBytes(1);
								al.add(IndexFile.readLong());
							}
						}
						else if (operater.equals("<"))
						{
							if (list.get(i) < Byte.parseByte(compareItem))
							{
								IndexFile.skipBytes(1);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							if (list.get(i) > Byte.parseByte(compareItem))
							{
								IndexFile.skipBytes(1);
								al.add(IndexFile.readLong());
							}
						}
					}
					break;
				}
				case 2://SHORT INT or SHORT
				{
					List<Short> list = ReadShortFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i) == Short.parseShort(compareItem))
							{
								IndexFile.skipBytes(2);
								al.add(IndexFile.readLong());
							}
						}
						else if (operater.equals("<"))
						{
							if (list.get(i) < Short.parseShort(compareItem))
							{
								IndexFile.skipBytes(2);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							if (list.get(i) > Short.parseShort(compareItem))
							{
								IndexFile.skipBytes(2);
								al.add(IndexFile.readLong());
							}
						}
					}
					break;
				}
				case 4://INT or FLOAT
				{
					List<Integer> list = ReadIntFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i) == Integer.parseInt(compareItem))
							{
								IndexFile.skipBytes(4);
								al.add(IndexFile.readLong());
							}
						}
						else if (operater.equals("<"))
						{
							if (list.get(i) < Integer.parseInt(compareItem))
							{
								IndexFile.skipBytes(4);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							if (list.get(i) > Integer.parseInt(compareItem))
							{
								IndexFile.skipBytes(4);
								al.add(IndexFile.readLong());
							}
						}
					}
					break;
				}
				case 8://LONG INT or LONG or DOUBLE	or DATETIME or DATE
				{
					List<Long> list = ReadLongFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i) == Long.parseLong(compareItem))
							{
								IndexFile.skipBytes(8);
								al.add(IndexFile.readLong());
							}
						}
						else if (operater.equals("<"))
						{
							if (list.get(i) < Long.parseLong(compareItem))
							{
								IndexFile.skipBytes(8);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							if (list.get(i) > Long.parseLong(compareItem))
							{
								IndexFile.skipBytes(8);
								al.add(IndexFile.readLong());
							}
						}
					}
					break;
				}
				case 0://VARCHAR(n)	
				{
					List<String> list = ReadStringFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i).equals(compareItem))
							{
								IndexFile.skipBytes(8);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							System.out.println("Error: Invalid operator.");
							return al;
						}
					}
					break;
				}
				case -1://invalid type
				{
					System.out.println("Error: Invalid type.");
					return al;
				}
				default://CHAR(n)
				{
					List<String> list = ReadStringFile(schema_name, table_name, column_name);
					for (int i = 0; i < list.size(); i++)
					{
						if (operater.equals("="))
						{
							if (list.get(i).equals(compareItem))
							{
								IndexFile.skipBytes(column_size);
								al.add(IndexFile.readLong());
							}
						}
						else
						{
							System.out.println("Error: Invalid operator.");
							return al;
						}
					}
					break;
				}
			}
			IndexFile.close();
			return al;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static void printSelectTuples(String schema_name, String table_name, ArrayList<Long> addr)
	{
		String file_name = schema_name + "." + table_name + ".tbl";
		try {
			List<String> tab_schema = getSystemColumnTABLE_SCHEMA();
			List<String> tab_name = getSystemColumnTABLE_NAME();
			List<String> col_type = getSystemColumnCOLUMN_TYPE();
			List<String> column_type = new ArrayList<String>();
			for (int i = 0; i < tab_name.size(); i++)
			{
				if (tab_schema.get(i).equals(schema_name) && tab_name.get(i).equals(table_name))
				{
					column_type.add(col_type.get(i));		
				}
			} 
			RandomAccessFile tableFile = new RandomAccessFile(file_name, "rw");
			for (int j = 0; j < addr.size(); j++)
			{
				tableFile.seek(addr.get(j));
				for (int k = 0; k < column_type.size(); k++)
				{
					switch (getDataSize(column_type.get(k))) 
					{
						case 1://BYTE
						{
							System.out.print(tableFile.readByte() + "  \t");
							break;
						}
						case 2://SHORT INT or SHORT
						{
							System.out.print(tableFile.readShort() + "  \t");
							break;
						}
						case 4://INT or FLOAT
						{
							System.out.print(tableFile.readInt() + "  \t");
							break;
						}
						case 8:
						{
							System.out.print(tableFile.readLong() + "  \t");
							break;
						}
						case 0://VARCHAR(n)	
						{
							Byte varLen = tableFile.readByte();
							StringBuilder sb = new StringBuilder();
							for(int i = 0; i < varLen; i++)
							{
								sb.append((char)tableFile.readByte());
							}
							System.out.print(sb.toString() + "  \t");
							break;
						}
						case -1://invalid type
						{
							System.out.println("Error: Invalid type.");
							return;
						}
						default://CHAR(n)
						{
							int varLen = getDataSize(column_type.get(j));
							StringBuilder sb = new StringBuilder();
							for(int i = 0; i < varLen; i++)
							{
								sb.append((char)tableFile.readByte());
							}
							System.out.print(sb.toString() + "  \t");
							break;
						}
					}
				}
				System.out.println("");
			}
			tableFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public static boolean existInTABLES(String schema, String table_name) {
		try {
			RandomAccessFile IndexFile = new RandomAccessFile("information_schema.table.tbl", "rw");
			int indexLocation = 0;
			
			while(true) {
				if (Query.endFile(IndexFile.length(), indexLocation))
					break;
				
				IndexFile.seek(indexLocation);
				Byte varLen1 = IndexFile.readByte();
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < varLen1; i++)
				{
					sb.append((char)IndexFile.readByte());
				}
				String column1 = sb.toString();
				
				Byte varLen2 = IndexFile.readByte();
				sb = new StringBuilder();
				for(int i = 0; i < varLen2; i++)
				{
					sb.append((char)IndexFile.readByte());
				}
				String column2 = sb.toString();
				
				if (schema.equals(column1) && table_name.equals(column2))
				{
					IndexFile.close();
					return true;
				}
				
				indexLocation += 10;
				indexLocation = indexLocation + varLen1.intValue() + varLen2.intValue();
			}
			IndexFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean InsertTypeCheck(String schema, String table_name, List<String> typeCheck) {
		try {
			List<String> tab_schema = getSystemColumnTABLE_SCHEMA();
			List<String> tab_name = getSystemColumnTABLE_NAME();
			List<String> col_name = getSystemColumnCOLUMN_NAME();
			List<String> col_type = getSystemColumnCOLUMN_TYPE();
			List<String> is_nullalbe = getSystemColumnIS_NULLABLE();
			List<String> col_key = getCOLUMN_KEY();
			
			int count = 0;
			for (int i = 0; i < tab_name.size(); i++)
				if (tab_schema.get(i).equals(schema) && tab_name.get(i).equals(table_name))
					count++;
			if (count != typeCheck.size())
			{
				System.out.println("Error: Column count doesn't match Values count.");
				return false;
			}
			
			count = -1;
			for (int i = 0; i < tab_name.size(); i++)
			{
				if (tab_schema.get(i).equals(schema) && tab_name.get(i).equals(table_name))
				{
					count++;
					if (Pattern.matches("(?i)YES", is_nullalbe.get(i)) && Pattern.matches("(?i)NULL", typeCheck.get(count)))
						continue;
				
					switch (getDataSize(col_type.get(i)))
					{
						case 1://BYTE
						{
							if (Long.parseLong(typeCheck.get(count)) < -128 ||  Long.parseLong(typeCheck.get(count)) > 127)
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<Byte> list =  ReadByteFile(schema, table_name, col_name.get(i));
								for (int j = 0; j < list.size(); j++)
								{
									if (Long.parseLong(typeCheck.get(count)) == list.get(j))
									{
										System.out.println("Error: Violate Primary Key.");
										return false;
									}
								}	
							}
							break;
						}
						case 2://SHORT INT or SHORT
						{
							if (!Pattern.matches("(?i)[0-9]+", typeCheck.get(count)))
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Long.parseLong(typeCheck.get(count)) < -32768 ||  Long.parseLong(typeCheck.get(count)) > 32767)
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<Short> list =  ReadShortFile(schema, table_name, col_name.get(i));
								for (int j = 0; j < list.size(); j++)
								{
									if (Long.parseLong(typeCheck.get(count)) == list.get(j))
									{
										System.out.println("Error: Violate Primary Key.");
										return false;
									}
								}	
							}
							break;
						}
						case 4://INT or FLOAT
						{
							if (!Pattern.matches("(?i)[0-9]+", typeCheck.get(count)))
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Long.parseLong(typeCheck.get(count)) < -2147483648 ||  Long.parseLong(typeCheck.get(count)) > 2147483647)
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<Integer> list =  ReadIntFile(schema, table_name, col_name.get(i));
								for (int j = 0; j < list.size(); j++)
								{
									if (Long.parseLong(typeCheck.get(count)) == list.get(j))
									{
										System.out.println("Error: Violate Primary Key.");
										return false;
									}
								}	
							}
							break;
						}
						case 8://LONG INT or LONG or DOUBLE	or DATETIME or DATE
						{
							if (Pattern.matches("\'[0-9]+-[0-9]+-[0-9]+\'", typeCheck.get(count)))
						{
							break;
						}
						   if (!Pattern.matches("(?i)[0-9]+", typeCheck.get(count)))
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							if (Long.parseLong(typeCheck.get(count)) < -2147483648 ||  Long.parseLong(typeCheck.get(count)) > 2147483647)
							{
								System.out.println("Error: Unknown column " + typeCheck.get(count));
								return false;
							}
							
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<Long> list =  ReadLongFile(schema, table_name, col_name.get(i));
								for (int j = 0; j < list.size(); j++)
								{
									if (Long.parseLong(typeCheck.get(count)) == list.get(j))
									{
										System.out.println("Error: Violate Primary Key.");
										return false;
									}
								}	
							}
							break;
						}
						case 0:
						{
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<String> list =  ReadStringFile(schema, table_name, col_name.get(i));
								for (int j = 0; j < list.size(); j++)
								{
									if (typeCheck.get(count).equals(list.get(j)))
									{
										System.out.println("Error: Violate Primary Key.");
										return false;
									}
								}	
							}
							break;
						}
						case -1:
						{
							System.out.println("Error: Invalid type.");
							return false;
						}
						default:
						{
							
							if (Pattern.matches("(?i)PRI", col_key.get(i)))
							{
								List<String> list =  ReadCharsFile(schema, table_name, col_name.get(i), getDataSize(col_type.get(i)));
								for (int j = 0; j < list.size(); j++)
								{
									if (typeCheck.get(count).equals(list.get(j)))
									{
										System.out.println("Error: Primary Key Violation.");
										return false;
									}
								}	
							}
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings("resource")
	public static boolean dropTable(String schema, String table_name) {
		try {
			int indexLocation = 0;
			String systemTableName = "information_schema.table.tbl";
			
			RandomAccessFile tableFile = new RandomAccessFile(systemTableName, "rw");

			while(true) {
				if (Query.endFile(tableFile.length(), indexLocation))
					break;
				
				tableFile.seek(indexLocation);
				
				Byte varLen1 = tableFile.readByte();
				StringBuilder sb1 = new StringBuilder();
				for(int i = 0; i < varLen1; i++)
				{
					sb1.append((char)tableFile.readByte());
				}
				
				Long deletePointer = tableFile.getFilePointer();
				
				Byte varLen2 = tableFile.readByte();
				StringBuilder sb2 = new StringBuilder();
				for(int i = 0; i < varLen2; i++)
				{
					sb2.append((char)tableFile.readByte());
				}
				if (schema.equals(sb1.toString()) && table_name.equals(sb2.toString()))
				{
					tableFile.seek(deletePointer);
					tableFile.skipBytes(1);
					for (int i = 0; i < varLen2; i++)
					{
						tableFile.writeBytes("");
					}
					return true;
				}
				indexLocation += 10;
				indexLocation = indexLocation + varLen1.intValue() + varLen2.intValue();
			}
			tableFile.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
	
	public static List<String> getSystemColumnTABLE_SCHEMA()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				
				Byte varLen1 = columnsFile.readByte();
				StringBuilder sb1 = new StringBuilder();
				for(int i = 0; i < varLen1; i++)
				{
					sb1.append((char)columnsFile.readByte());
				}
				lst.add(sb1.toString());
				
				Byte varLen2 = columnsFile.readByte();
				columnsFile.skipBytes(varLen2);
				
				Byte varLen3 = columnsFile.readByte();
				columnsFile.skipBytes(varLen3 + 4);
					
				Byte varLen4 = columnsFile.readByte();
				columnsFile.skipBytes(varLen4);
					
				Byte varLen5 = columnsFile.readByte();
				columnsFile.skipBytes(varLen5);
				
				Byte varLen6 = columnsFile.readByte();
				columnsFile.skipBytes(varLen6);
			}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<String> getSystemColumnTABLE_NAME()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
		
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				
				Byte varLen1 = columnsFile.readByte();
				columnsFile.skipBytes(varLen1);

				Byte varLen2 = columnsFile.readByte();
				StringBuilder sb2 = new StringBuilder();
				for(int i = 0; i < varLen2; i++)
				{
					sb2.append((char)columnsFile.readByte());
				}
				lst.add(sb2.toString());

				Byte varLen3 = columnsFile.readByte();
				columnsFile.skipBytes(varLen3 + 4);
					
				Byte varLen4 = columnsFile.readByte();
				columnsFile.skipBytes(varLen4);
					
				Byte varLen5 = columnsFile.readByte();
				columnsFile.skipBytes(varLen5);
				
				Byte varLen6 = columnsFile.readByte();
				columnsFile.skipBytes(varLen6);
			}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<String> getSystemColumnCOLUMN_NAME()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				Byte varLen1 = columnsFile.readByte();
				columnsFile.skipBytes(varLen1);

				Byte varLen2 = columnsFile.readByte();
				columnsFile.skipBytes(varLen2);
				
				Byte varLen3 = columnsFile.readByte();
				StringBuilder sb2 = new StringBuilder();
				for(int i = 0; i < varLen3; i++)
				{
					sb2.append((char)columnsFile.readByte());
				}
				lst.add(sb2.toString());

				columnsFile.skipBytes(4);
					
				Byte varLen4 = columnsFile.readByte();
				columnsFile.skipBytes(varLen4);
					
				Byte varLen5 = columnsFile.readByte();
				columnsFile.skipBytes(varLen5);
				
				Byte varLen6 = columnsFile.readByte();
				columnsFile.skipBytes(varLen6);
				}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
		
	public static List<String> getSystemColumnCOLUMN_TYPE()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				
				Byte varLen1 = columnsFile.readByte();
				columnsFile.skipBytes(varLen1);

				Byte varLen2 = columnsFile.readByte();
				columnsFile.skipBytes(varLen2);
				
				Byte varLen3 = columnsFile.readByte();
				columnsFile.skipBytes(varLen3 + 4);
				
				Byte varLen4 = columnsFile.readByte();
				StringBuilder sb4 = new StringBuilder();
				for (int i = 0; i < varLen4; i++)
				{
					sb4.append((char)columnsFile.readByte());
				}
				lst.add(sb4.toString());
					
				Byte varLen5 = columnsFile.readByte();
				columnsFile.skipBytes(varLen5);
				
				Byte varLen6 = columnsFile.readByte();
				columnsFile.skipBytes(varLen6);
			}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<String> getSystemColumnIS_NULLABLE()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				Byte varLen1 = columnsFile.readByte();
				columnsFile.skipBytes(varLen1);

				Byte varLen2 = columnsFile.readByte();
				columnsFile.skipBytes(varLen2);				
				Byte varLen3 = columnsFile.readByte();
				columnsFile.skipBytes(varLen3 + 4);	
				Byte varLen4 = columnsFile.readByte();
				columnsFile.skipBytes(varLen4);
				
				Byte varLen5 = columnsFile.readByte();
				StringBuilder sb5 = new StringBuilder();
				for (int i = 0; i < varLen5; i++)
				{
					sb5.append((char)columnsFile.readByte());
				}
				lst.add(sb5.toString());
				
				Byte varLen6 = columnsFile.readByte();
				columnsFile.skipBytes(varLen6);
			}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<String> getCOLUMN_KEY()
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = "information_schema.columns.tbl";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				//read schema_name and table_name
				Byte varLen1 = columnsFile.readByte();
				columnsFile.skipBytes(varLen1);

				Byte varLen2 = columnsFile.readByte();
				columnsFile.skipBytes(varLen2);
				
				Byte varLen3 = columnsFile.readByte();
				columnsFile.skipBytes(varLen3 + 4);
				
				Byte varLen4 = columnsFile.readByte();
				columnsFile.skipBytes(varLen4);
				
				Byte varLen5 = columnsFile.readByte();
				columnsFile.skipBytes(varLen5);
				
				Byte varLen6 = columnsFile.readByte();
				StringBuilder sb6 = new StringBuilder();
				for (int i = 0; i < varLen6; i++)
				{
					sb6.append((char)columnsFile.readByte());
				}
				lst.add(sb6.toString());
			}
			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	private static int getDataSize(String data_type)
	{
		if (Pattern.matches("(?i)BYTE", data_type))
			return	1;
		else if (Pattern.matches("(?i)SHORT\\s+INT", data_type))
			return	2;
		else if (Pattern.matches("(?i)SHORT", data_type))
			return	2;
		else if (Pattern.matches("(?i)INT", data_type))
			return	4;
		else if (Pattern.matches("(?i)FLOAT", data_type))
			return	4;
		else if (Pattern.matches("(?i)LONG\\s+INT", data_type))
			return	8;
		else if (Pattern.matches("(?i)LONG", data_type))
			return	8;
		else if (Pattern.matches("(?i)DOUBLE", data_type))
			return	8;
		else if (Pattern.matches("(?i)DATETIME", data_type))
			return	8;
		else if (Pattern.matches("(?i)DATE", data_type))
			return	8;
		else if (Pattern.matches("(?i)CHAR\\([0-9]\\)", data_type))
		{
			String temp = data_type.replaceFirst("(?i)CHAR\\(\\s*", "");
			temp = temp.replaceFirst("\\s*\\)", "");
			return Integer.parseInt(temp);
		}
		else if (Pattern.matches("(?i)VARCHAR\\([0-9]+\\)", data_type))
			return 0;
		else
		{
			System.out.println("Invalid Data Type Found!" + data_type);
			return -1;
		}
	}
	
	public static List<Byte> ReadByteFile(String schema_name, String table_name, String column_name)
	{
		List<Byte> lst = new ArrayList<Byte>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				lst.add(columnsFile.readByte());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<Short> ReadShortFile(String schema_name, String table_name, String column_name)
	{
		List<Short> lst = new ArrayList<Short>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			//read index file
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				lst.add(columnsFile.readShort());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public static List<Integer> ReadIntFile(String schema_name, String table_name, String column_name)
	{
		List<Integer> lst = new ArrayList<Integer>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			//read index file
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				lst.add(columnsFile.readInt());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public static List<Long> ReadLongFile(String schema_name, String table_name, String column_name)
	{
		List<Long> lst = new ArrayList<Long>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			//read index file
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				lst.add(columnsFile.readLong());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public static List<String> ReadCharsFile(String schema_name, String table_name, String column_name, int length)
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;
				
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < length; i++)
				{
					sb.append((char)columnsFile.readByte());
				}
				lst.add(sb.toString());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static List<String> ReadStringFile(String schema_name, String table_name, String column_name)
	{
		List<String> lst = new ArrayList<String>();
		try {
			String file_name = schema_name + "." + table_name + "." + column_name + ".ndx";
			RandomAccessFile columnsFile = new RandomAccessFile(file_name, "rw");
			while(true) {
				if (Query.endFile(columnsFile.length(), columnsFile.getFilePointer()))
					break;

				Byte varLen = columnsFile.readByte();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < varLen; i++)
				{
					sb.append((char)columnsFile.readByte());
				}
				lst.add(sb.toString());
				columnsFile.skipBytes(8);
			}

			columnsFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	
}