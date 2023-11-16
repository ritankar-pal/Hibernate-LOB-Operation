package in.ineuron.Test;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.JobSeeker;
import in.ineuron.util.HibernateUtil;


public class SelectApp {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		int id = 1;
		boolean flag = false;
		JobSeeker customer = null;
		JobSeeker jobSeeker = null;
		
		try {
			session = HibernateUtil.getSession();
			jobSeeker = session.get(JobSeeker.class, 1);
			
			if(jobSeeker != null) {
				System.out.println("JOB ID is:: " + jobSeeker.getJsId());
				System.out.println("Name is:: " + jobSeeker.getJsName());
				System.out.println("Address is:: " + jobSeeker.getJsAddr());
				
				try(FileOutputStream fos = new FileOutputStream("./store/ritankar.jpg"); FileWriter fw = new FileWriter("./store/resume.txt")){
					fos.write(jobSeeker.getPhoto());
					fw.write(jobSeeker.getResume());
				}
				
			}
			
		}
		catch (HibernateException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {			
			
			if(flag) {
				transaction.commit();
				System.out.println("Object Updated to the Database...");
				System.out.println("After Updation:: " + customer);
			}
			else {
				transaction.rollback();
				System.out.println("Object Failed to Update...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
