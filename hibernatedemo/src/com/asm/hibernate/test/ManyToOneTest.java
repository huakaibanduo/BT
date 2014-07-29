package com.asm.hibernate.test;
import com.asm.hibernate.domain.Department;
import com.asm.hibernate.domain.Employee;
import com.asm.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.*;

public class ManyToOneTest {
	public static void main(String[] args) {
		//add();
	//	query(2);
		Employee emp = query2(2);
		System.out.println(emp.getDepart().getName());
	}
	
	static Employee query2(int empId) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Employee emp = (Employee) s.get(Employee.class, empId);
			Hibernate.initialize(emp.getDepart()); 
			//上一句作用后面会作说明，这里略作了解
			return emp;
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	static Employee query(int empId) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Employee emp = (Employee) s.get(Employee.class, empId);
			System.out.println("Department Name:" + emp.getDepart().getName());
			return emp;
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	static void add() {
		Session s = null;
		Transaction tx = null;
		try {
			Department depart = new Department();
			depart.setName("departName1");
			Employee emp = new Employee();
			emp.setName("empName1");
			emp.setDepart(depart);

			s = HibernateUtil.getSession();
			tx = s.beginTransaction();

			s.save(depart);
			s.save(emp);
			
		
			
			
		// 交换以上两句的位置，看Hibernate执行的sql语句。会再增加一条更新操作。
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}
}
