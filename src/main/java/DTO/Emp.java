package DTO;

import java.util.Date;

/*
CREATE TABLE EMP 
(EMPNO number not null,
ENAME VARCHAR2(10),
JOB VARCHAR2(9),
MGR number ,
HIREDATE date,
SAL number ,
COMM number ,
DEPTNO number );
*/


public class Emp {
	
	private int avgSal;
	private String job;
	public int getAvgSal() {
		return avgSal;
	}
	public void setAvgSal(int avgSal) {
		this.avgSal = avgSal;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
}
