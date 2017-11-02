package boun.group9.webservice.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class FileChecker {
	private static String bucketName = "concerter/concerts";
	public static void imageUpload(MultipartFile multipartFile) throws IOException {
		AWSCredentials awsCreds = new BasicAWSCredentials("AKIAJQWJ2BW25TAQX6UA", "X41/jZhLHMHVMvR8Iw6IYBEnAJhtPkIFtokKKPF7");
		String keyName = multipartFile.getOriginalFilename();
		         AmazonS3 s3client = new AmazonS3Client(awsCreds);
		         try {
		             //System.out.println("Uploading a new object to S3 from a file\n");
		             File file = new File(multipartFile.getOriginalFilename());
		             file.createNewFile();
		             FileOutputStream fos = new FileOutputStream(file);
		             fos.write(multipartFile.getBytes());
		             fos.close();
		             multipartFile.transferTo(file);
		             s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
		             
		         } catch (AmazonServiceException ase) {
		             System.out.println("Caught an AmazonServiceException, which " +
		                     "means your request made it " +
		                     "to Amazon S3, but was rejected with an error response" +
		                     " for some reason.");
		             System.out.println("Error Message:    " + ase.getMessage());
		             System.out.println("HTTP Status Code: " + ase.getStatusCode());
		             System.out.println("AWS Error Code:   " + ase.getErrorCode());
		             System.out.println("Error Type:       " + ase.getErrorType());
		             System.out.println("Request ID:       " + ase.getRequestId());
		         } catch (AmazonClientException ace) {
		             System.out.println("Caught an AmazonClientException, which " +
		                     "means the client encountered " +
		                     "an internal error while trying to " +
		                     "communicate with S3, " +
		                     "such as not being able to access the network.");
		             System.out.println("Error Message: " + ace.getMessage());
		         }
		     }
		 
		 }
