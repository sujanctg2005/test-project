package com.test.utility.jmx;


public class SystemConfig implements SystemConfigMBean {
 
    private int threadCount;
    private String schemaName;
     
    public SystemConfig(int numThreads, String schema){
        this.threadCount=numThreads;
        this.schemaName=schema;
    }
     
    public void setThreadCount(int noOfThreads) {
        this.threadCount=noOfThreads;
    }
 
 
   
    public int getThreadCount() {
        return this.threadCount;
    }
 
 
    
    public void setSchemaName(String schemaName) {
        this.schemaName=schemaName;
    }
 
 
   
    public String getSchemaName() {
        return this.schemaName;
    }
     
    public String doConfig(){
        return "No of Threads="+this.threadCount+" and DB Schema Name="+this.schemaName;
    }
 
}