package in.ineuron.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import in.ineuron.model.JobSeeker;
import in.ineuron.util.HibernateUtil;


public class InsertRecordWithincrementPK {

	public static void main(String[] args) {
		
		Session session = null; 
		Transaction transaction = null;
		boolean flag = false;
		Integer id = null;
		byte[] photo = null;
		char[] resume = null;
		File f = null;
		
		//Photo:
		try(FileInputStream fis = new FileInputStream("D:\\Canada Application\\Canada Application Process( Dreamz Global)\\My Photo\\Digital Photo.JPG")){
			photo = new byte[fis.available()];
			fis.read(photo);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//PDF::
		try {			
			f = new File("D:\\Java iNeuron\\10th March- Multiple DB, LOB, Locking\\Resume.txt");
			try(FileReader fr = new FileReader(f)){
				resume = new char[(int) f.length()];
				fr.read(resume);
			}
		}
		catch(IOException io) {
			io.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			session = HibernateUtil.getSession();
			
			if(session != null) {
				 transaction = session.beginTransaction();
			}
			
			if(transaction != null) {
				
				JobSeeker jobSeeker = new JobSeeker();
				jobSeeker.setJsName("Rahul");
				jobSeeker.setJsAddr("Delhi");
				jobSeeker.setPhoto(photo);
				jobSeeker.setResume(resume);
				
				id = (Integer)session.save(jobSeeker);
				flag = true;
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
				System.out.println("Object updated to the database with the PK ID:: " + id);
			}
			else {
				transaction.rollback();
				System.out.println("Object failed to update...");
			}
			
			HibernateUtil.closeSession(session);
			HibernateUtil.closeSessionFactory();
		}
		
	}

}
