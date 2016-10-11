package com.dbproject.davisbase;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DavisBaseLite {
	
	static String prompt = "davisql> ";
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
	splashScreen();
	
	initialize();
	
	Scanner sc=new Scanner(System.in).useDelimiter(";");
	String commands;
	
	do{
	System.out.print(prompt);
	commands=sc.next().trim();
	execute(commands);
	
	
}while(!commands.equals("exit"));
	System.out.println("Exit");
    
} 

	
	

public static void splashScreen() {
	System.out.println(line("*",80));
    System.out.println("Welcome to DavisBaseLite"); 
	version();
	System.out.println(line("*",80));
}

public static String line(String s,int num) {
	String a = "";
	for(int i=0;i<num;i++) {
		a += s;
	}
	return a;
}

public static void version() {
	System.out.println("DavisBaseLite v1.0\n");
}


	
	public static void initialize()
	{
	
		if(checkISPresent()){
			MakeInformationSchema.initialize();
			System.out.println("The database has been initialized");
		}
		else
		{
			System.out.println("The database already exists");
		}
	}
	
	public static boolean checkISPresent(){
		String[] initialFiles = new String[3];
		initialFiles[0] = "information_schema/information_schema.schemata.tbl";
		initialFiles[1] = "information_schema/information_schema.table.tbl";
		initialFiles[2] = "information_schema/information_schema.columns.tbl";
		for (String file : initialFiles){
			File f = new File(file);
			if(!f.exists())
			{
			    return true;
			}
			else
			{
				f.mkdirs();
				return false;
			}
		}
		
	return false;
}
	
	public static void execute(String str)
	{
		if (Pattern.matches("(?i)CREATE\\s+SCHEMA\\s+[a-zA-Z0-9]+", str))
		{
			String[] command = str.split("\\s+|;");
			Query.createSchema(command[2]);
			
		}
		
		else if (Pattern.matches("(?i)SHOW\\s+SCHEMAS", str))
		{
			Query.showSchemas();
		}
		
		else if (Pattern.matches("(?i)USE\\s+[a-zA-Z0-9]+", str))
		{
			String[] command = str.split("\\s+|;");
			Query.useSchema(command[1]);
			
		}
		
		else if (Pattern.matches("(?i)SHOW\\s+TABLES", str))
		{
			Query.showTables();
		}
		
		else if (Pattern.matches("(?i)CREATE\\s+TABLE\\s+[a-zA-Z0-9]+\\s*\\(\\s*.+\\s*\\)", str))
    	{
    		String table_name = str.split("\\s")[2];
    		String temp = str.replaceFirst("(?i)CREATE\\s+TABLE\\s+[a-zA-Z0-9]+\\s*\\(\\s*", "");
    		if (Pattern.matches("(?i).+\\)\\s*\\)", temp))
    			temp = temp.replaceAll("\\)\\s*\\)", ")");
    		String[] insertTuples = temp.split(",");
    		Query.createTable(table_name, insertTuples);
			
    	}
		
		else if (Pattern.matches("(?i)INSERT\\s+INTO\\s+[a-zA-Z0-9]+\\s+VALUES\\s*\\(\\s*.+\\s*\\)", str))
		{
			String table_name = str.split("\\s")[2];
    		String temp = str.replaceFirst("(?i)INSERT\\s+INTO\\s+[a-zA-Z0-9]+\\s+VALUES\\s*\\(\\s*", "");
    		temp = temp.replaceAll("\\s*\\)", "");
    		temp = temp.replaceAll("\\s", "");
    		String[] insertTuples = temp.split(",");
			Query.insert(table_name, insertTuples);
			
		}

		else if (Pattern.matches("(?i)SELECT\\s+.+\\s+FROM\\s+[a-zA-Z0-9]+\\s+WHERE\\s+.+", str))
    	{
    		String temp1 = str.replaceFirst("(?i).+FROM\\s+", "");
    		String table_name = temp1.split("\\s")[0];
    		temp1 = temp1.replaceFirst("(?i).+WHERE\\s+", "");
    		String condition = temp1.split(";")[0];
    		
    		String temp2 = str.replaceFirst("(?i)\\s+FROM.+", "");
    		temp2 = temp2.replaceFirst("(?i)SELECT\\s+", "");
    		temp2 = temp2.replaceFirst("\\s", "");
    		String[] columns = temp2.split(",");
    		Query.select(columns,table_name, condition);
			
    	}
		
		else if (Pattern.matches("(?i)DROP\\s+TABLE\\s+.+", str))
		{
			String table_name = str.replaceFirst("(?i)DROP\\s+TABLE\\s+", "");
			Query.drop(table_name);
			
		}
		
		else if (Pattern.matches("(?i)exit", str))
			{
				
				
			}
		else
			System.out.println("Error in SQL syntax.");
	}
}
