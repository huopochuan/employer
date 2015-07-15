package com.wyc.employ;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManger {
   private DBHelper helper;
   private SQLiteDatabase db;
   public DBManger(Context context){
	   helper=new DBHelper(context);
	   db=helper.getWritableDatabase();
   }
   
   /** 
    * add persons 
    * @param persons 
    */  
   public void add(employ person) {  
	   /*private int id;
	private String name;
	private int age;
	private int sex;
	private int por;
	private double money;
	private double ywze;//当月业务总额;
	private int time; //当月工作时间*/
       db.beginTransaction();  //开始事务  
       db.execSQL("INSERT INTO employ VALUES(null, ?, ?, ?,?,?,?,?)",
    		   new Object[]{person.getName(),
    		   person.getAge(), 
    		   person.getSex(),
    		   person.getPor(),
    		   person.getMoney(),
    		   person.getYwze(),
    		   person.getTime(),
    		   });  
       db.setTransactionSuccessful();  //设置事务成功完成  
       db.endTransaction();    //结束事务  
      
   }  
     
   /** 
    * update person's age 
    * @param person 
    */  
   public void update(employ person) {  
	 
	   String where ="_id = ?";
	   String[] whereValue = {String.valueOf(person.getId())};
	    
	   ContentValues cv = new ContentValues();
	   /*private int id;
		private String name;
		private int age;
		private int sex;
		private int por;
		private double money;
		private double ywze;//当月业务总额;
		private int time; //当月工作时间*/
	   cv.put("name", person.getName());
	   cv.put("age", person.getAge());
	   cv.put("sex", person.getSex());
	   cv.put("por", person.getPor());
	   cv.put("money", person.getMoney());
	   cv.put("ywze", person.getYwze());
	   cv.put("time", person.getTime());
	   db.update("employ", cv, where, whereValue);  
   }  
     
  
   public void delete(int id) {  
       db.delete("employ", "_id = ?", new String[]{String.valueOf(id)});  
   }  
     
  
   public List<employ> query(String sql) {  
       ArrayList<employ> persons = new ArrayList<employ>();
       
       Cursor c=null;
       if(sql.equals("")){
    	    c = db.rawQuery(
        		   "SELECT* FROM employ",
        		   new String[]{});   
    	   
       }
       else{
        c = db.rawQuery(
    		   "SELECT* FROM employ WHERE _id = ? or name like ?" ,
    		   new String[]{ sql,"%"+sql+"%"}); 
       }
       while (c.moveToNext()) {  
    	   /*private int id;
   		private String name;
   		private int age;
   		private int sex;
   		private int por;
   		private double money;
   		private double ywze;//当月业务总额;
   		private int time; //当月工作时间*/
    	   employ person = new employ();  
           person.setId(c.getInt(c.getColumnIndex("_id")));  
           person.setName(c.getString(c.getColumnIndex("name")));  
           person.setAge(c.getInt(c.getColumnIndex("age")));  
           person.setSex(c.getInt(c.getColumnIndex("sex"))); 
           person.setPor(c.getInt(c.getColumnIndex("por")));  
           person.setMoney(c.getDouble(c.getColumnIndex("money")));  
           person.setYwze(c.getDouble(c.getColumnIndex("ywze")));  
           person.setTime(c.getInt(c.getColumnIndex("time")));  
           persons.add(person);  
       }  
       c.close();  
       return persons;  
   }  
     
  
     
   /** 
    * close database 
    */  
   public void closeDB() {  
       db.close();  
   }  
}

