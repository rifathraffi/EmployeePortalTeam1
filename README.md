# Employee Portal Team1 Assignment
To run the project, create database employeeportal with the following tables:<br/>
-->document -- Long id, String name, Long empid, BLOB data<br/>
-->employees -- Long id, String email, String first_name, String last_name, String password, String Address, String Phone, Date DOB, Long projectid<br/>
-->leaves -- Long id, Long empid, String email, Date start_date, Date end_date, Integer num_of_days, String reason, String status<br/>
-->post -- Long id, String title, String description, Date date<br/>
-->project -- Long projectid, String project_name, String client_name, Date end_date<br/><br/>
-->users -- Long id, String email, String first_name, String last_name, String password<br/>

Create a csv file containing project details as follows and import them to the "project" table:
![image](https://user-images.githubusercontent.com/86296356/216672418-54e4d6a4-2163-4477-88b1-442164a591ea.png)
(Please give default project as projectid 5)

After creating these load a HR user to users table.<br/>
(In index.html file the code has been written to register new Admin HR user)<br/>

Then accessing HR portal we can add employees and the employees can log in through the Employee Portal from the main page.<br/>
The snapshots of various features area as follows:<br/>

![Screenshot (121)](https://user-images.githubusercontent.com/86296356/215067426-5c05034b-9c35-4ae0-8418-bcee3d459532.png)
The main page where the user chooses between HR portal/Employee portal.<br/><br/>

![Screenshot (122)](https://user-images.githubusercontent.com/86296356/215067601-ab10df42-c859-4398-baba-8c05b71018d9.png)
The HR login page. Logging in using email and password. Passwords are protected by encryption in DB.<br/><br/>

![Screenshot (123)](https://user-images.githubusercontent.com/86296356/215067872-83738670-6fa6-4a26-a915-c598db3a864c.png)<br/>
![Screenshot (124)](https://user-images.githubusercontent.com/86296356/215067942-f057037c-d4c2-4c31-bdc1-eea4527b8b9e.png)
The HR homepage has the HR features as shown and displays the complete list of employees at the bottom side of page.<br/><br/>

![Screenshot (125)](https://user-images.githubusercontent.com/86296356/215068153-1a7bc402-68b8-43c5-8074-959dfa03527c.png)
Registering for a new employee. After registration the password and email can be shared with the employee to enable them to login.<br/><br/>

![Screenshot (126)](https://user-images.githubusercontent.com/86296356/215068397-e8bc05cc-58dc-41a6-8b23-4e4969ae1a76.png)
After registering an employee, immediately the list is updated and they are unallocated until allocated to a project.<br/><br/>

![Screenshot (131)](https://user-images.githubusercontent.com/86296356/215068632-e7bb74c4-6ea3-4314-ac45-ae5fc522eff4.png)
The user can login using the credentials and the homepage displays the features as shown. After logging in they can opt to change the password from the options menu in top bar.<br/><br/>

![Screenshot (132)](https://user-images.githubusercontent.com/86296356/215068959-eacaf1c6-95c7-4d01-a937-9a3eb66b5f9c.png)
The Personal Details option displays the details of employees that cannot be changed in the top and editable options in the bottom half. The employee can edit their personal details and then login again to view changed details.<br/>
![Screenshot (134)](https://user-images.githubusercontent.com/86296356/215069220-ffa06444-b9c8-4f09-b47f-3423e4f9155e.png)
Updated details displayed.<br/><br/>

![Screenshot (135)](https://user-images.githubusercontent.com/86296356/215069404-8e3df1d6-29ce-43e2-bb99-2f7dbf583e41.png)
Employee can apply for leave using this feature. The request will be pending until the HR approves it. The list of all applied leaves can also be viewed.
![Screenshot (136)](https://user-images.githubusercontent.com/86296356/215069633-e05fbd96-61c7-4841-ab04-019cb47e118c.png)
After applying the list of applied leaves is displayed automatically.<br/><br/>

![Screenshot (140)](https://user-images.githubusercontent.com/86296356/215069909-5ef24d36-7c63-4628-b024-90a0818e9d8b.png)
The leave request can be seen in the HR portal by an HR admin after logging in to HR portal. Here they have the option to approve/reject the leave. The status is updated both in employee and HR portal after approving/rejecting.
![Screenshot (146)](https://user-images.githubusercontent.com/86296356/215070439-7f6fe612-da95-4948-981c-7db9c884bdd9.png)
The status is shown approved in employee's portal.<br/><br/>

![Screenshot (137)](https://user-images.githubusercontent.com/86296356/215070564-b5618f13-f7ab-494f-86d1-b1996d07c9db.png)
The HR can post notices to all employees that appear in the HR Notice Board.
![Screenshot (127)](https://user-images.githubusercontent.com/86296356/215070803-1f9f14f9-96db-41f0-b2e6-d7eaef3b3364.png)
<br/><br/>

![Screenshot (128)](https://user-images.githubusercontent.com/86296356/215070862-3625fb47-484f-4d03-a91f-710bbee8a54b.png)
The HR can allocate projects to employees through this feature.<br/><br/>

![Screenshot (130)](https://user-images.githubusercontent.com/86296356/215070996-ed92dccf-37de-4d2c-9040-197baa304945.png)
The HR can upload documents like payslips to individual employees through this feature.
![Screenshot (142)](https://user-images.githubusercontent.com/86296356/215071542-e88356d1-6bc0-4664-a392-c803a32f4bf5.png)
After uploading, the employee can view the list of documents and download them from the employee portal My Documents feature.
<br/><br/>

![Screenshot (139)](https://user-images.githubusercontent.com/86296356/215071378-898786f9-4bce-41cb-ba4b-37fe6ed0b745.png)
The employee can view Project details to which they are currently allocated through the Project option.<br/><br/>

The employee can sign out through the Logout button in homepage or using the Options->Logout option in the top menu bar. They will be redirected to main homepage.
<br/>
