package com.asm.hibernate.test;
import com.asm.hibernate.domain.Department;
import com.asm.hibernate.domain.Employee;
import com.asm.hibernate.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.*;

public class OneToManyTest {
	public static void main(String[] args) {
		add();
		query(4);
	}
	static Department query(int departId) {
		Session s = null;
		try {
			s = HibernateUtil.getSession();
			Department depart = (Department) s.get(Department.class, departId);
			System.out.println("employee size:" + depart.getEmps().size());
			return depart;
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
			depart.setName("departName2");

			Employee emp1 = new Employee();
			emp1.setName("empName2");
			emp1.setDepart(depart);
			Employee emp2 = new Employee();
			emp2.setName("empName3");
			emp2.setDepart(depart);

			// Set<Employee> emps = new HashSet<Employee>();
			// emps.add(emp1);
			// emps.add(emp2);
			// depart.setEmps(emps);

			s = HibernateUtil.getSession();
			tx = s.beginTransaction();
			s.save(depart);
			s.save(emp1);
			s.save(emp2);
			tx.commit();
		} finally {
			if (s != null)
				s.close();
		}
	}

}
