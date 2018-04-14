package com.ufgov.zc.client.ftp.apache.utils;

import java.text.DecimalFormat;

/**
 * ftp传输进度值
 * @author Administrator
 *
 */
public class ProgressArg {
	long max, min, value,startTime,endTime;
	long G=1024*1024*1024,M=1024*1024,KB=1024; 
	
	DecimalFormat df ;

	public ProgressArg(long total, long min, long cur) {
		max = total;
		min = min;
		value = cur;  
		df = new DecimalFormat("0.00");
	}

	/**
	 * 获取当前传输速度 或者最终的平均速度
	 * @return
	 */
	public String getSpeed(){
		
		if(startTime>0l && endTime>0l && max>0l && value>0l){
			if(max==min+value){ 
				return _getSpeed(endTime-startTime,value,"平均");
			}else{
				return _getSpeed(endTime-startTime,value,"");
			}
		} 
		return "";
	}
	private String _getSpeed(long times, long transfer,String title) {
		if(times<=0l || transfer==0l){
			return "";
		}
		long sec=times/1000;
		double speed=0;
		if(sec==0){ 
			sec=1;
		} 
		speed=transfer/sec;
		
//		System.out.println(""+transfer+"\t"+sec+"\t"+speed);
		if(speed>G){
			speed=speed/G;
			return title+df.format(speed)+"G/秒";
		}else if(speed>M){
			speed=speed/M;
			return title+df.format(speed)+"M/秒";
		}else if(speed>KB){
			speed=speed/KB;
			return title+df.format(speed)+"K/秒";
		}else{
			return ""+df.format(speed)+"字节/秒";
		} 
	}

	/**
	 * 获得当前的进度.
	 * 返回0---100之间的整数;
	 * @return
	 */
	public int getPer(){
		if(max==0){
			return 0;
		}
		if(max==(value+min)){
			return 100;
		}
		
		double per=(value+min)*100/max;
		 
		return Double.valueOf(per).intValue();		 
	}
	/**
	 * 获取进度的最大值
	 * @return
	 */
	public  int  getPerMax() {
		if(max==0){
			return 0;
		}
		return 100;
	}
	/**
	 * 获取进度的最小值
	 * @return
	 */
	public  int  getPerMin() {
		if(max==0 ){
			return 0;
		}
		double kk=min*100/max;
		return Double.valueOf(kk).intValue();		 
	}
	
	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
		this.startTime=System.currentTimeMillis();
	}

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
		this.endTime=System.currentTimeMillis();
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
    String rtn="";
    
    if(getPer()==100){
      rtn="完成";
    }else if(getPer()>0){
      rtn+=getPer()+"%";
    }
    return rtn;
  }
}
