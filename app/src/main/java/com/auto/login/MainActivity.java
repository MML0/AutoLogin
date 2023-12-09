package com.auto.login;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import android.os.Bundle;
import android.view.Gravity;
import android.app.*;
import android.os.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import android.widget.TextView; 
import android.widget.*; 
import java.io.*;
import java.util.Scanner;
import android.view.View; 
public class MainActivity extends Activity 
{
	TextView textView; 
	EditText user; 
	EditText pass;
	Button save; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy);
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		textView = (TextView) findViewById(R.id.mainTextView);
		user = (EditText) findViewById(R.id.user);
		pass = (EditText) findViewById(R.id.pass);
		String userval = "";
		String passval = "";
		save = (Button) findViewById(R.id.save); 
	  	save.setOnClickListener(new View.OnClickListener() { 
			public void onClick(View v) { 
			try{
				File file = new File(getFilesDir(), "credentials.txt");
				FileWriter writer = new FileWriter(file);
				if (user.getText().toString().isEmpty()||pass.getText().toString().isEmpty()){
					Toast.makeText(getBaseContext(), "empty!", Toast.LENGTH_SHORT);
					return;
				}
				writer.write(user.getText() + "\n" + pass.getText());
				writer.close();
				
				Toast.makeText(getBaseContext(), "saved", Toast.LENGTH_SHORT).show(); 
				String userval = user.getText().toString();
				String passval = pass.getText().toString();
				
				try {
					// URL and data
					String url = "http://10.10.10.10:8090/login.xml";
					String postData = "mode=191&username="+userval+"&password="+passval+"&a=" + Instant.now().getEpochSecond() + "&producttype=0";

					// Set up the connection
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();

					// Set the request method
					con.setRequestMethod("POST");

					// Set request headers
					con.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 16_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Mobile/15E148 Safari/604.1");
					con.setRequestProperty("Referer", "http://10.10.10.10:8090/httpclient.html");

					// Enable input/output
					con.setDoOutput(true);

					// Write the POST data
					try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
						byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
						wr.write(postDataBytes);
					}

					// Get the response
					int responseCode = con.getResponseCode();
					System.out.println("Response Code: " + responseCode);
					textView.setText("Response Code: " + responseCode); 
					// Read the response content
					try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
						String inputLine;
						StringBuilder response = new StringBuilder();
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						System.out.println("Response Content: " + response.toString());
						textView.setText(textView.getText()+ "\n"+response.toString());
						Toast.makeText(getBaseContext(), "done", Toast.LENGTH_SHORT).show(); 
					}
				} catch (Exception e) {
					e.printStackTrace();
					textView.setText(e.toString()); 
				}
				}catch(IOException e){
					Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show(); 
				}
				} });
		try {
            File file = new File(getFilesDir(), "credentials.txt");
            Scanner fileScanner = new Scanner(file);
            if (fileScanner.hasNextLine()) {
				Toast.makeText(getBaseContext(), "reading", Toast.LENGTH_SHORT);
				userval = fileScanner.nextLine();
				passval = fileScanner.nextLine();
				try {
					// URL and data
					String url = "http://10.10.10.10:8090/login.xml";
					String postData = "mode=191&username="+userval+"&password="+passval+"&a=" + Instant.now().getEpochSecond() + "&producttype=0";

					// Set up the connection
					URL obj = new URL(url);
					HttpURLConnection con = (HttpURLConnection) obj.openConnection();

					// Set the request method
					con.setRequestMethod("POST");

					// Set request headers
					con.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 16_6 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.6 Mobile/15E148 Safari/604.1");
					con.setRequestProperty("Referer", "http://10.10.10.10:8090/httpclient.html");

					// Enable input/output
					con.setDoOutput(true);

					// Write the POST data
					try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
						byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
						wr.write(postDataBytes);
					}

					// Get the response
					int responseCode = con.getResponseCode();
					System.out.println("Response Code: " + responseCode);
					textView.setText("Response Code: " + responseCode); 
					// Read the response content
					try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
						String inputLine;
						StringBuilder response = new StringBuilder();
						while ((inputLine = in.readLine()) != null) {
							response.append(inputLine);
						}
						System.out.println("Response Content: " + response.toString());
						textView.setText(textView.getText()+ "\n"+response.toString());
						Toast.makeText(getBaseContext(), "done", Toast.LENGTH_SHORT).show(); 
					}
				} catch (Exception e) {
					e.printStackTrace();
					textView.setText(e.toString()); 
				}
            }
        } catch (FileNotFoundException e) {
			Toast.makeText(getBaseContext(), "f m l ", Toast.LENGTH_SHORT).show(); 
            System.out.println("File not found.");
            e.printStackTrace();
        }
		
		
    }
	
}



