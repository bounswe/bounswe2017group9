# bounswe2017group9

## Concerter

This project is about creating a social media platform for finding new concerts and recommending concerts to other people.

[Check out our wiki page for more information!](https://github.com/bounswe/bounswe2017group9/wiki)

### Members:

* [Hilal Dönmez](https://github.com/bounswe/bounswe2017group9/wiki/Hilal-D%C3%B6nmez)
* [Begün Ünal](https://github.com/bounswe/bounswe2017group9/wiki/Beg%C3%BCn-%C3%9Cnal)
* [Fatih Güven](https://github.com/bounswe/bounswe2017group9/wiki/Fatih-G%C3%BCven) (Communicator)
* [Doğa Can Dorum](https://github.com/bounswe/bounswe2017group9/wiki/Do%C4%9Fa-Can-Dorum)
* [Necip Fazıl Ergün](https://github.com/bounswe/bounswe2017group9/wiki/Necip-Faz%C4%B1l-Erg%C3%BCn)
* [Emre Kahreman](https://github.com/bounswe/bounswe2017group9/wiki/Emre-Kahreman)
* [İhsan Öztürk](https://github.com/bounswe/bounswe2017group9/wiki/%C4%B0hsan-%C3%96zt%C3%BCrk)
* [Mehmet Akif Yücel](https://github.com/bounswe/bounswe2017group9/wiki/Mehmet-Akif-Y%C3%BCcel)  
* [Kasım Bozdağ](https://github.com/bounswe/bounswe2017group9/wiki/Kas%C4%B1m-Bozda%C4%9F)
* [Ahmet Buğrahan Taşdan](https://github.com/bounswe/bounswe2017group8/wiki/Ahmet-Bu%C4%9Frahan-Ta%C5%9Fdan)
* [Harun Zengin](https://github.com/bounswe/bounswe2017group8/wiki/Harun-Zengin)
* [Efehan Atıcı](https://github.com/bounswe/bounswe2017group9/wiki/Efehan-At%C4%B1c%C4%B1) (Former Member)

### Deployment:

* Open the Database folder an see sample.sql.
* Create an Amazon RDS instance or import the sql file to your local MySQL client.
* Copy the host name of MySQL client, root username, and password.
* Open the Database.java file under RESTService project.
* Copy the MySQL client's host name, root username and password.
* Build the RESTService with Maven and generate the .war file.
* Upload the .war file to Amazon ElasticBeanStalk Tomcat instance, copy the URL.
* Open the Application.java file under ConcerterBackend
* Update the API_ENDPOINT variable with Amazon URL or local URL/port.
* Build the ConcerterBackend with Maven and generate the .war file.
* Upload the .war file to Amazn ElasticBeanStalk Tomcat instance, or deploy it to your local Tomcat.
* The Concerter Backend Application is deployed now.
