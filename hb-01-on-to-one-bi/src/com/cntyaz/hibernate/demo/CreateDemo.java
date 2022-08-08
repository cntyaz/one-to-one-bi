package com.cntyaz.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.cntyaz.hibernate.demo.entity.Instructor;
import com.cntyaz.hibernate.demo.entity.InstructorDetail;

public class CreateDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
									 .configure("hibernate.cfg.xml")
									 .addAnnotatedClass(Instructor.class)
									 .addAnnotatedClass(InstructorDetail.class)
									 .buildSessionFactory();
		//create a session
		/*
		Session session = factory.getCurrentSession();
		try {
			
			// create a student objects
			Instructor tempInstructor = 
					new Instructor("Cuneyt", "Yaz", "cntyaz@gmail.com");
			
			InstructorDetail tempInstructorDetail = 
					new InstructorDetail(
							"http://www.youtube.com",
							"write some code");
							*/
		Session session = factory.getCurrentSession();
		try {
		Instructor tempInstructor = 
				new Instructor("Tuna", "Yaz", "tntyaz@gmail.com");
		
		InstructorDetail tempInstructorDetail = 
				new InstructorDetail(
						"http://www.youtube.com",
						"love you dad");
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start transaction
			
			session.beginTransaction();
	
			// save the instructor
			// this will also save the detail object
			// because of CascadeType.ALL
			System.out.println("Saving instructor" + tempInstructor);
			session.save(tempInstructor);
			
			// commit transaction
			session.getTransaction().commit();
		
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
	}

}
