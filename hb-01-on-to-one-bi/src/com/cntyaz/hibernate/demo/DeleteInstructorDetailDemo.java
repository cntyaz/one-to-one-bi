package com.cntyaz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cntyaz.hibernate.demo.entity.Instructor;
import com.cntyaz.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
 
		// create session factory
		SessionFactory factory = new Configuration()
									 .configure("hibernate.cfg.xml")
									 .addAnnotatedClass(Instructor.class)
									 .addAnnotatedClass(InstructorDetail.class)
									 .buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
		
			// start transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int theId = 4;
			InstructorDetail tempInstructorDetail =
					session.get(InstructorDetail.class, theId);
			// print instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			// print the associated instructor
			System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());
			
			// now lets delete the instructor detail
			System.out.println("deleting tempInstructorDetail: " + tempInstructorDetail);
			
			// remove the associated object reference
			// break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			
			// commit transaction
			session.getTransaction().commit();
		
			System.out.println("Done!");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
